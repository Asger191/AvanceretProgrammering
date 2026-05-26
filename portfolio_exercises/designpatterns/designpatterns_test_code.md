# Opgaver: Design Patterns

Alle kodeeksempler ligger i pakken `designpatterns`.
Lav din egen klasse med en `main`-metode for hver pakke.

---

## Adapter

Kig på klasserne `OldPrinter`, `NewPrinter` og `PrinterAdapter` i pakken `designpatterns.adapter`.

`OldPrinter` har en metode der hedder noget andet end det `NewPrinter`-interfacet forventer.
`PrinterAdapter` bro­bygger mellem de to.

Opgaven er nu

- Lav en `main`-metode der opretter en `PrinterAdapter` og kalder `print()` på den.
- Koden der kalder `print()` skal ikke kende til `OldPrinter` — kun til `NewPrinter`-interfacet.

---

## Builder

Kig på klassen `User` og den indre klasse `User.Builder` i pakken `designpatterns.builder`.

Opgaven er nu

- Lav en `main`-metode der opretter to brugere med `User.Builder`:
    - Én med alle felter sat.
    - Én med kun de obligatoriske felter (`name` og `email`).
- Udskriv begge brugere og sammenlign outputtet.
- Hvad er defaultværdien for `country`, hvis den ikke sættes?

---

## Command

Kig på klasserne `Command`, `LightOnCommand`, `LightOffCommand`, `Light` og `RemoteControl`
i pakken `designpatterns.command`.

Opgaven er nu

- Tilføj en metode `undo()` til `Command`-interfacet.
- Implementér `undo()` i `LightOnCommand` og `LightOffCommand` — `undo` af "tænd" er "sluk" og omvendt.
- Udvid `RemoteControl` med en liste der gemmer udførte kommandoer, og en metode `undoLast()` der fortryder den seneste.
- Test det i `main`: tænd lyset, sluk lyset, fortryd — hvad sker der?

---

## Composite

Kig på klasserne `GameObject`, `Enemy` og `EnemyGroup` i pakken `designpatterns.composite`.

Opgaven er nu

- Byg en fjendestruktur med mindst to niveauer: en gruppe der indeholder en anden gruppe.
- Kald `update()` på den øverste gruppe.
- Hvad sker der? Hvordan minder det om traversal af et træ?

---

## Decorator

Kig på klasserne `Notifier`, `EmailNotifier` og `SMSDecorator` i pakken `designpatterns.decorator`.

Opgaven er nu

- Lav en `main`-metode der opretter en `Notifier` der sender både email og SMS.
- Prøv at bytte om på rækkefølgen — pak `SMSDecorator` ind i `EmailNotifier` i stedet for omvendt.
- Hvad sker der? Betyder rækkefølgen noget?

---

## Factory Method

Kig på klassen `AnimalFactory` og interfacet `Animal` i pakken `designpatterns.factory`.

Opgaven er nu

- Lav en `main`-metode der opretter alle tre dyretyper via `AnimalFactory` og lader dem tale.
- Hvad skal der til for at tilføje et nyt dyr? Hvilke filer skal ændres?

---

## Observer

Kig på klasserne `Subject`, `Observer` og `User` i pakken `designpatterns.observer`.

Opgaven er nu

- Lav en `main`-metode der tilføjer to observatorer til `Subject` og kalder `notifyObservers`.
- Fjern én observatør og kald `notifyObservers` igen.
- Hvad sker der? Hvilken observatør modtager beskeden?

---

## Proxy

Kig på klasserne `Service`, `RealService` og `ServiceProxy` i pakken `designpatterns.proxy`.

Opgaven er nu

- Lav en `main`-metode der kalder `perform()` via `ServiceProxy`.
- Hvornår oprettes `RealService`? Med det samme, eller først når den bruges?
- Hvad kalder man det når et objekt først oprettes når det er nødvendigt?

---

## Singleton

Kig på klassen `Singleton` i pakken `designpatterns.singleton`.

Opgaven er nu

- Hent instansen to gange med `getInstance()` og sammenlign dem med `==`.
- Hvad printer `System.out.println(a == b)`?
- **Bonusspørgsmål:** Er dette eksempel *eager* eller *lazy* initialization? Hvad er forskellen?

---

## Strategy

Kig på klasserne `Sorter`, `SortStrategy` og `BubbleSort` i pakken `designpatterns.strategy`.
BubbleSort-algoritmen kender du fra `searchandsort`-pakken.

Opgaven er nu

- Lav en `main`-metode der sorterer en liste tal med `BubbleSort` via `Sorter`.
- Hvad skal der til for at tilføje en ny sorteringsalgoritme? Hvilke filer skal ændres?
- Sammenlign med Strategy-mønstret: hvad er `Sorter`'s rolle, og hvad er `BubbleSort`'s?

---

## Template Method

Kig på klasserne `SortTest`, `BubbleSortTest` og `QuickSortTest` i pakken `designpatterns.templatemethod`.

`SortTest` definerer en fast skabelon i `run()` — opret liste, forbered, sorter, rapportér.
Subklasserne implementerer kun `sort()`.

Opgaven er nu

- Kør `Main` og sammenlign tiderne for bubble og quick sort.
- `run()` er markeret `final`. Hvad betyder det? Prøv at fjerne `final` og override `run()` i en subklasse — hvad bliver muligt?
- Hvad er forskellen på Template Method og almindelig nedarving?