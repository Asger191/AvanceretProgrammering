# Portfolioopgave: Dijkstra

Lav en implementering af Dijkstras algoritme, der finder den korteste vej mellem byer i et vejnet.

- Design selv dit vejnet med mindst 8 byer og vægtede rettede veje
- Find den korteste vej fra én by til en anden
- Lav en visualisering der giver mening. Hvad er nyttigt at se når man skal forstå algoritmen?

Du kan finde inspiration til koden i `graphs.Portfolio.PortfolioDijkstra.dijkstra`.

---

## Visualisering

Tænk over hvad der er meningsfuldt at vise for at forklare nogen hvordan Dijkstra virker. Nogle muligheder:

- Print i konsollen hvad der sker i hvert trin, fx *"Afstanden fra Skovby til Lilleby er nu 13 km"* eller *"Lilleby er nu visited"*
- Lav evt. tabeller over hvad der er i kø og/eller i visited
- Lav et interaktivt step-by-step mode hvor brugeren trykker `y` for at gå videre og kan følge med i hvad der er afsøgt indtil videre
- Lav en grafisk visualisering i JavaScript eller Processing hvor noder skifter farve efterhånden som de bliver visited eller afsøgt

> Hvis du bruger JavaScript eller Processing må du gerne få hjælp af AI til selve koden, men du skal selv beslutte hvad du vil visualisere og hvorfor. Det er designbeslutningen der er vigtig, ikke om du skriver koden selv.

---

## Implementering

### `WeighedNote`

`WeighedNote` er en simpel klasse der repræsenterer én **node i en vægtet graf**. Tænk på den som et "sted" i et kort, der kender sine nabosteder og ved hvor langt der er til dem.

#### Felterne

```java
private String name;
private Map<WeighedNote, Integer> neighbors;
```

- **`name`** – nodens navn, f.eks. `"S"`, `"A"`, `"M"`. Bruges kun til at printe stien til sidst.
- **`neighbors`** – et map der binder nabonoder til kantvægte. Nøglen er naboen, værdien er afstanden.

For eksempel vil node `S` efter opsætningen have dette map:

```
{ A → 1, B → 3, C → 2 }
```

#### Konstruktoren

```java
public WeighedNote(String name) {
    this.name = name;
    this.neighbors = new HashMap<>();
}
```

Opretter en ny node med et navn og et tomt nabomap. Ingen naboer kendes endnu.

#### Metoderne

- **`getName()`** – returnerer nodens navn. Bruges når stien printes til sidst.
- **`getNeighbors()`** – returnerer hele nabokortet. `Dijkstra` bruger dette til at iterere over en nodes naboer under søgningen.
- **`addNeighbor(WeighedNote neighbor, int weight)`** – tilføjer en nabo med en given vægt. Kaldes i `main()` for at bygge grafen op:

```java
S.addNeighbor(A, 1); // Fra S til A koster 1
S.addNeighbor(B, 3); // Fra S til B koster 3
```

#### Sammenhængen med Dijkstra

`WeighedNote` er udelukkende en **datastruktur** – den ved intet om Dijkstra-algoritmen. Den holder bare på kanter og vægte. Det er `Dijkstra`-klassen der bruger `getNeighbors()` til at udforske grafen og `getName()` til at printe den korteste vej til sidst.

---

### `Dijkstra`

#### Grafen der bygges op i `main()`

Før algoritmen kører, bygges en graf med 14 noder (S → M) og en række vægtede kanter. Det er denne graf algoritmen skal finde den korteste vej igennem, fra **S** til **M**.

#### De fire datastrukturer i `findShortestPath()`

```java
Map<WeighedNote, WeighedNote> prev      // Hvem kom vi fra?
Map<WeighedNote, Integer> dist          // Billigste kendte afstand
Set<WeighedNote> visited                // Noder vi er færdige med
PriorityQueue<NodeWithDist> queue       // Hvem undersøger vi næst?
```

- **`prev`** bruges til at rekonstruere stien bagfra til sidst
- **`dist`** bruges til at vide om vi har fundet en bedre vej til en node
- **`visited`** sikrer vi ikke genbehandler noder vi allerede har fundet den optimale vej til
- **`queue`** er hjertet – den sorterer automatisk så den billigste node altid popper ud først

#### Algoritmen trin for trin

##### Initialisering

```java
queue.add(new NodeWithDist(source, 0));
dist.put(source, 0);
```

Startnoden S puttes i køen med afstand 0. Den er 0 væk fra sig selv.

##### Hovedløkken

```java
while (!queue.isEmpty()) {
    NodeWithDist current = queue.poll(); // Tag den billigste node
```

`poll()` henter altid den node med **lavest afstand** ud af køen, fordi `NodeWithDist` implementerer `Comparable`.

##### De to brudsbetingelser

```java
if (current.node.equals(destination)) break;   // Fundet målet
if (visited.contains(current.node)) continue;  // Allerede behandlet
```

Den anden betingelse er vigtig fordi samme node kan ligge i køen flere gange med forskellige afstande. Kun den første (billigste) gang er relevant.

##### Udforskning af naboer

```java
for (Map.Entry<WeighedNote, Integer> entry : current.node.getNeighbors().entrySet()) {
    WeighedNote next = entry.getKey();
    int weight = entry.getValue();

    int newDist = current.dist + weight;

    if (newDist < dist.getOrDefault(next, Integer.MAX_VALUE)) {
        dist.put(next, newDist);
        prev.put(next, current.node);
        queue.add(new NodeWithDist(next, newDist));
    }
}
```

For hver nabo beregnes den potentielle nye afstand. Hvis den er **bedre end hvad vi kendte før**, opdateres `dist` og `prev`, og naboen puttes i køen. `Integer.MAX_VALUE` bruges som "uendeligt" for noder vi ikke har set endnu.

##### Rekonstruktion af stien

```java
List<String> path = new ArrayList<>();
WeighedNote step = destination;
while (step != null) {
    path.add(0, step.getName()); // Indsæt forrest
    step = prev.get(step);
}
```

Vi starter ved M og følger `prev`-kæden baglæns til S. `add(0, ...)` vender listen undervejs så den ender med at gå fra S → M.

#### `NodeWithDist` – hjælpeklassen

```java
private static class NodeWithDist implements Comparable<NodeWithDist> {
    WeighedNote node;
    int dist;

    public int compareTo(NodeWithDist other) {
        return Integer.compare(this.dist, other.dist);
    }
}
```

Pakker en node og dens afstand sammen. `compareTo` gør at `PriorityQueue` automatisk sorterer efter afstand, uden at slå op i `dist`-mappet. Den kunne ikke ligge på selve `WeighedNote`, fordi den samme node kan have forskellig afstand afhængig af hvilken startnode man bruger.

#### Resultat

For denne konkrete graf finder algoritmen:

```
Korteste vej: [S, C, E, H, J, M]
Samlet dist: 11
```
