# 📋 Template Method Pattern – Kort Forklaring

## 🔹 Idéen
Template Method bruges, når du har en **fast skabelon for et forløb**, men vil lade subklasser bestemme ét eller flere af trinene.

> Skelettet er låst fast – men det variable trin er åbent.

---

## 🔹 I praksis

Du laver en **abstrakt superklasse** med en `templateMethod()` der kalder de enkelte trin.
Ét eller flere trin er abstrakte – og overlades til subklassen.

```java
public abstract class SortTest {

    // Skabelonen – må ikke overrides
    public final void run(int size) {
        List<Student> list = createList(size);
        prepare(list);
        long start = System.currentTimeMillis();
        sort(list);
        long stop = System.currentTimeMillis();
        report(stop - start);
    }

    protected List<Student> createList(int size) {
        List<Student> list = new ArrayList<>();
        Factory.fillWithStudents(list, size);
        return list;
    }

    protected void prepare(List<Student> list) {
        Collections.shuffle(list);
    }

    // Det variable trin – subklassen bestemmer
    protected abstract void sort(List<Student> list);

    protected void report(long ms) {
        System.out.println(getClass().getSimpleName() + ": " + ms + " ms");
    }
}

public class BubbleSortTest extends SortTest {
    protected void sort(List<Student> list) {
        SortExamples.bubbleSort(list);
    }
}
```

---

## 🔹 Pointen
- **Skelettet er defineret ét sted** – ingen kodeduplikering
- **Det variable trin er isoleret** – subklassen kan ikke bryde strukturen
- `final` på `templateMethod()` sikrer at ingen kan omgå skabelonen

---

## 🔹 Forskel på Template Method og Strategy

| | Template Method | Strategy |
|---|---|---|
| Mekanisme | **Arv** | **Komposition** |
| Det variable trin | Overrides i subklasse | Injiceres som objekt |
| Struktur | Fast i superklasse | Defineres udefra |
| Fleksibilitet | Vælges ved compile-time | Kan skiftes ved runtime |

> Tommelfingerregel: Skal du *arve og udfylde*? → Template Method. Skal du *injicere og skifte*? → Strategy.

---

## 🔹 Virkelighedsnært

Template Method bruges ofte i:
- 🧪 Test-frameworks (JUnit's `setUp` / `tearDown`)
- 🌐 Webframeworks (livscyklus-metoder i servlets)
- 🎮 Spil (game loop med fast struktur, variabelt indhold)
- 📊 Rapportgeneratorer med fast layout, variabelt indhold