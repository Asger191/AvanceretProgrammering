// ─── Graf-data ────────────────────────────────────────────────────────────────

const SRC = 0;   // startpunkt (hus 0, venstre side)
const DST = 12;  // slutpunkt  (hus 12, højre side)

// Huse: relative koordinater (x og y mellem 0 og 1)
const houses = [
    { id: 0,  x: .04, y: .50 },
    { id: 1,  x: .18, y: .20 },
    { id: 2,  x: .18, y: .50 },
    { id: 3,  x: .18, y: .80 },
    { id: 4,  x: .36, y: .12 },
    { id: 5,  x: .36, y: .38 },
    { id: 6,  x: .36, y: .65 },
    { id: 7,  x: .36, y: .88 },
    { id: 8,  x: .60, y: .25 },
    { id: 9,  x: .60, y: .55 },
    { id: 10, x: .60, y: .80 },
    { id: 11, x: .78, y: .38 },
    { id: 12, x: .96, y: .50 },
];

// Rettede kanter: [fra, til, vægt]
const edges = [
    [0,  1,  4], [0,  2,  2], [0,  3,  7],
    [1,  4,  3], [1,  5,  6],
    [2,  5,  4], [2,  6,  5],
    [3,  6,  3], [3,  7,  6],
    [4,  8,  5], [4,  5,  2],
    [5,  8,  4], [5,  9,  7],
    [6,  9,  3], [6,  10, 5],
    [7,  10, 4],
    [8,  11, 3], [8,  9,  6],
    [9,  11, 5], [9,  10, 4],
    [10, 12, 8],
    [11, 12, 4],
];

// Byg naboliste
const adj = Array.from({ length: houses.length }, () => []);
edges.forEach(([u, v, w]) => adj[u].push({ v, w }));

// ─── Canvas-opsætning ─────────────────────────────────────────────────────────

const cv  = document.getElementById('cv');
const ctx = cv.getContext('2d');
const logEl = document.getElementById('log');

const SZ = 30; // husbredde i pixels

function W() { return cv.clientWidth || 720; }
function H() { return 460; }
function px(h) { return [h.x * W(), h.y * H()]; }
function houseBaseY(hy) { return hy - SZ * 0.75 - SZ * 0.55; }

// ─── Algoritmens tilstand ─────────────────────────────────────────────────────

let nodeState = [];   // 'unvisited' | 'queued' | 'current' | 'visited'
let distArr   = [];   // kendte afstande fra SRC
let prevArr   = [];   // forgænger-array til rekonstruktion af sti
let pathEdges = new Set();   // kanter på den fundne korteste vej
let activeEdge = null;       // den kant der undersøges lige nu
let gen = null;              // generator-objekt for step-by-step

// ─── Hjælpefunktioner til tegning ────────────────────────────────────────────

// Finder det punkt på husets kant der vender mod (fromX, fromY)
function houseEdgePoint(hIdx, fromX, fromY) {
    const h = houses[hIdx];
    const [hx, hy] = px(h);
    const bY   = houseBaseY(hy);
    const topY = bY - SZ * 0.55;
    const cy   = (topY + bY + SZ * 0.75) / 2;
    const ang  = Math.atan2(fromY - cy, fromX - hx);
    const hw   = SZ / 2 + 3;
    const hh   = (SZ * 0.75 + SZ * 0.55) / 2;
    const sc   = Math.min(Math.abs(hw / Math.cos(ang)), Math.abs(hh / Math.sin(ang)));
    return [hx + Math.cos(ang) * sc, cy + Math.sin(ang) * sc];
}

// Tegner en rettet linje med pil
function drawArrow(x1, y1, x2, y2, color, lw) {
    const ang = Math.atan2(y2 - y1, x2 - x1);
    ctx.beginPath();
    ctx.moveTo(x1, y1);
    ctx.lineTo(x2 - Math.cos(ang) * 2, y2 - Math.sin(ang) * 2);
    ctx.strokeStyle = color;
    ctx.lineWidth   = lw;
    ctx.stroke();
    ctx.beginPath();
    ctx.moveTo(x2, y2);
    ctx.lineTo(x2 - 9 * Math.cos(ang - 0.4), y2 - 9 * Math.sin(ang - 0.4));
    ctx.lineTo(x2 - 9 * Math.cos(ang + 0.4), y2 - 9 * Math.sin(ang + 0.4));
    ctx.closePath();
    ctx.fillStyle = color;
    ctx.fill();
}

// Tegner et hus (krop + tag + dør)
function drawHouse(x, y, fill, roofFill, stroke) {
    const bh    = SZ * 0.75;
    const roofH = SZ * 0.55;
    const by    = houseBaseY(y);

    // Krop
    ctx.beginPath();
    ctx.rect(x - SZ / 2, by, SZ, bh);
    ctx.fillStyle   = fill;
    ctx.fill();
    ctx.strokeStyle = stroke;
    ctx.lineWidth   = 1.5;
    ctx.stroke();

    // Tag
    ctx.beginPath();
    ctx.moveTo(x - SZ / 2 - 3, by);
    ctx.lineTo(x, by - roofH);
    ctx.lineTo(x + SZ / 2 + 3, by);
    ctx.closePath();
    ctx.fillStyle   = roofFill;
    ctx.fill();
    ctx.strokeStyle = stroke;
    ctx.lineWidth   = 1.5;
    ctx.stroke();

    // Dør
    const dw = SZ * 0.22, dh = bh * 0.4;
    ctx.beginPath();
    ctx.rect(x - dw / 2, by + bh - dh, dw, dh);
    ctx.fillStyle   = 'rgba(0,0,0,0.18)';
    ctx.fill();
    ctx.strokeStyle = stroke;
    ctx.lineWidth   = 1;
    ctx.stroke();
}

// Returnerer farver baseret på algoritmens tilstand for det pågældende hus
function houseColors(state, id) {
    if (id === SRC) return { fill: '#1D9E75', roof: '#0F6E56', stroke: '#0F6E56' };
    if (id === DST) return { fill: '#E24B4A', roof: '#A32D2D', stroke: '#A32D2D' };
    if (state === 'current') return { fill: '#7F77DD', roof: '#534AB7', stroke: '#534AB7' };
    if (state === 'queued')  return { fill: '#EF9F27', roof: '#BA7517', stroke: '#BA7517' };
    if (state === 'visited') return { fill: '#1D9E75', roof: '#0F6E56', stroke: '#0F6E56' };
    if (state === 'path')    return { fill: '#E24B4A', roof: '#A32D2D', stroke: '#A32D2D' };
    return { fill: '#D3D1C7', roof: '#B4B2A9', stroke: '#888780' };
}

// Tegner START / SLUT-markør
function drawMarker(x, y, label, color) {
    ctx.beginPath();
    ctx.arc(x, y, 14, 0, 2 * Math.PI);
    ctx.fillStyle = color;
    ctx.fill();
    ctx.font      = '500 10px system-ui, sans-serif';
    ctx.fillStyle = '#fff';
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.fillText(label, x, y);
}

// ─── Hoved-tegne-funktion ─────────────────────────────────────────────────────

function draw() {
    cv.width  = W();
    cv.height = H();
    ctx.clearRect(0, 0, W(), H());

    // START- og SLUT-markører
    const [sx, sy] = px(houses[SRC]);
    const [dx, dy] = px(houses[DST]);
    drawMarker(sx - 28, sy, 'START', '#1D9E75');
    ctx.beginPath(); ctx.moveTo(sx - 14, sy); ctx.lineTo(sx - 4, sy);
    ctx.strokeStyle = '#1D9E75'; ctx.lineWidth = 2; ctx.stroke();
    drawMarker(dx + 28, dy, 'SLUT', '#E24B4A');
    ctx.beginPath(); ctx.moveTo(dx + 4, dy); ctx.lineTo(dx + 14, dy);
    ctx.strokeStyle = '#E24B4A'; ctx.lineWidth = 2; ctx.stroke();

    // Kanter med pile
    edges.forEach(([u, v, w]) => {
        const [x1, y1] = px(houses[u]);
        const [x2, y2] = px(houses[v]);
        const [ex1, ey1] = houseEdgePoint(u, x2, y2);
        const [ex2, ey2] = houseEdgePoint(v, x1, y1);
        const pkey  = `${u}-${v}`;
        const inPath = pathEdges.has(pkey);
        const isAct  = activeEdge === pkey;
        const color  = inPath ? '#E24B4A' : isAct ? '#EF9F27' : '#C4C2BA';
        drawArrow(ex1, ey1, ex2, ey2, color, inPath ? 3 : isAct ? 2 : 1.2);

        // Kantens vægt, forskudt vinkelret fra linjen
        const mx  = (ex1 + ex2) / 2;
        const my  = (ey1 + ey2) / 2;
        const ang = Math.atan2(ey2 - ey1, ex2 - ex1);
        ctx.font         = '11px system-ui, sans-serif';
        ctx.fillStyle    = '#888780';
        ctx.textAlign    = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText(w, mx - Math.sin(ang) * 11, my + Math.cos(ang) * 11);
    });

    // Huse
    houses.forEach((h, i) => {
        const [x, y] = px(h);
        let st = nodeState[i] || 'unvisited';

        if (pathEdges.size > 0) {
            const onPath = [...pathEdges].some(k => {
                const [a, b] = k.split('-');
                return +a === i || +b === i;
            });
            if (onPath) st = 'path';
        }

        const { fill, roof, stroke } = houseColors(st, i);
        drawHouse(x, y, fill, roof, stroke);

        const by   = houseBaseY(y);
        const topY = by - SZ * 0.55;

        // Afstand over taget (? indtil kendt)
        const d = distArr[i];
        const distLabel = (i === SRC) ? '0' : (d === undefined || d === Infinity ? '?' : String(d));
        ctx.font         = '500 12px system-ui, sans-serif';
        ctx.textAlign    = 'center';
        ctx.textBaseline = 'bottom';
        ctx.fillStyle    = '#ffffff';
        ctx.fillText(distLabel, x, topY - 3);

        // Husnummer inde i huset
        ctx.font         = '500 10px system-ui, sans-serif';
        ctx.textBaseline = 'middle';
        ctx.fillStyle    = '#ffffff';
        ctx.fillText('Hus ' + i, x, by + SZ * 0.75 * 0.5);
    });
}

// ─── Log-hjælper ──────────────────────────────────────────────────────────────

function log(msg) {
    const el = document.createElement('div');
    el.className   = 'le';
    el.textContent = msg;
    logEl.prepend(el);
}

// ─── Dijkstras algoritme (generator for step-by-step) ────────────────────────

function* dijkstra(s, t) {
    distArr   = Array(houses.length).fill(Infinity);
    prevArr   = Array(houses.length).fill(-1);
    nodeState = Array(houses.length).fill('unvisited');
    pathEdges.clear();
    activeEdge = null;

    distArr[s]   = 0;
    nodeState[s] = 'queued';
    log('Start fra hus ' + s + ' — afstand 0');
    draw(); yield;

    const visited = new Set();
    const pq = [[0, s]]; // prioritetskø: [afstand, node]

    while (pq.length) {
        pq.sort((a, b) => a[0] - b[0]);
        const [d, u] = pq.shift();
        if (visited.has(u)) continue;

        visited.add(u);
        nodeState[u] = 'current';
        log('Behandler hus ' + u + ' (afstand: ' + d + ')');
        draw(); yield;

        if (u === t) break;

        for (const { v, w } of adj[u]) {
            if (visited.has(v)) continue;
            activeEdge = u + '-' + v;
            const nd = d + w;

            if (nd < distArr[v]) {
                distArr[v]   = nd;
                prevArr[v]   = u;
                nodeState[v] = 'queued';
                pq.push([nd, v]);
                log('  Hus ' + v + ' opdateret → ' + nd + ' (via hus ' + u + ')');
            }

            draw(); yield;
        }

        activeEdge   = null;
        nodeState[u] = 'visited';
        draw(); yield;
    }

    // Rekonstruer korteste vej
    let cur = t;
    while (prevArr[cur] !== -1) {
        const p = prevArr[cur];
        pathEdges.add(p + '-' + cur);
        pathEdges.add(cur + '-' + p);
        cur = p;
    }

    log('Korteste vej: hus ' + s + ' → hus ' + t + ' = ' + distArr[t]);
    draw();
}

// ─── Knap-logik ───────────────────────────────────────────────────────────────

function reset() {
    gen        = null;
    pathEdges.clear();
    activeEdge = null;
    distArr    = Array(houses.length).fill(Infinity);
    nodeState  = Array(houses.length).fill('unvisited');
    logEl.innerHTML = '';
    document.getElementById('btn-step').disabled = true;
    document.getElementById('btn-go').disabled   = false;
    draw();
}

document.getElementById('btn-go').onclick = () => {
    reset();
    gen = dijkstra(SRC, DST);
    document.getElementById('btn-step').disabled = false;
    document.getElementById('btn-go').disabled   = true;
    gen.next();
};

document.getElementById('btn-step').onclick = () => {
    if (!gen) return;
    const r = gen.next();
    if (r.done) document.getElementById('btn-step').disabled = true;
};

document.getElementById('btn-reset').onclick = reset;

// ─── Init ─────────────────────────────────────────────────────────────────────

reset();