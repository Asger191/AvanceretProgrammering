# Øvelser: Concurrency

## 1. Lav et observer pattern uden tråde

Kig på klasserne `Sensor`, `Logger`, `Display` og `Alarm` i pakken `concurrency.observerexercise`. Tanken er, at bygge
et system med en sensor, der opfanger et eller andet og sender besked til en logger, et display (en skærm med advarsler)
og en alarm. For at det skal kunne ske, vil vi gerne lave et observer pattern.

Opgaven er nu:

- Lav et interface `SensorObserver` med metoden `eventDetected (int code)` som skal bruges til at sende besked til de
  forskellige systemer, der skal notificeres (fx logger, display, alarm).
- Lad klasserne `Logger`, `Display` og `Alarm` implementere interfacet.
- Giv klassen `Sensor` metoder til at tilføje og slette `SensorObserver`-objekter.
- Sørg for at `Sensor` i tilfælde af at `eventHappened` bliver kaldt vil notificere alle sine observers.
- Test i `Main` at det virker.

## 2. Lav et observer pattern med tråde

Prøv at lade `Logger` være langsom ved at indsætte et `Thread.sleep(2000)` i dens `eventDetected()`-metode. Kør `main` igen.
Nu bliver de andre observere blokeret. Det betyder at alarmen ikke kan gå i gang før loggeren er færdig med at skrive i en fil. Det er et
dårligt design!

Opgaven er nu:

- Refaktorer `Sensor` så den starter en ny tråd hver gang en ny observer notificeres.
- Test i `main` at din logger ikke længere blokerer for alarmen.


## 3. Gør BankAccount trådsikker

Kig på klasserne `BankAccount`, `BankRunnable` og `Main` i pakken `concurrency.bankaccount`. 
Hvad sker der i `main`? Og i `deposit()` og `withdraw()` i `BankAccunt`? 

Opgaven er nu:

- Prøv at køre `main` flere gange
  og se hvad saldoen bliver på vores konto. Forklar til din sidemand hvad problemet er. 
- Løs problemet med synkronisering. 
- Kør `main` igen for at se om din løsning virker. 
- Ekstra: Find ud af hvad `join()` gør i `main`. Kig på dokumentationen for tråde. 

## 4. Undgå deadlocks

Kig på klasserne `BankAccount`, `TransferRunnable` og `Main` i pakken
`bankaccountdeadlock`. Du skal forstå hvad koden gør. 

Opgaven er nu:

- Kør `Main`. Hvad sker der og hvorfor? 
- Hvilke strategier kan man bruge for at løse problemet? Beskriv i pseudokode hvad der skal gøres.  
- Vælg en strategi og implementer den. Test at din kode virker. 


## Brug Executor, ExecutorService og Future til din Sensor

Vi skal nu lave en opgave, hvor vi sorterer et stort datasæt. Til det kan vi bruge `searchandsort.SortExamples.quickSort`. Der er lidt
eksempelkode at gå i gang med. Kig på `Main` i pakken `executorexercise`. 

Det vi gerne vil opnå er at sætte en sortering i gang og lave noget
andet, mens vi venter på at sorteringen bliver færdig. Når vi er klar, henter vi den sortede liste via en `Future`.

Så vi skal altså bruge `Executor`, `ExecutorService`, `Callable` og `Future`.

Opgaven er nu: 

- Lav en klasse `SortTask` som implementerer interfacet `Callable`
  - Giv klassen en attribut `List<Students> students`
  - Giv klassen en konstruktør, der tager en liste som argument 
  - Huske at importere klassen `searchandsort.entities.Student`
- Implementer metoden `call()` 
  - metoden skal sortere listen (her kan du kalde en af metoderne i `searchandsort.SortExamples`).
  - metoden skal returnere den sorterede liste 
- Nu er du klar til at bruge din `Callable` 
  - I `main` starter du med at lave en `ExecutorService`.
  - Dernæst laver du en instans af din `SortTask` og giver den listen med studerende. 
  - Tilsidst giver du din `SortTask` til din `ExecutorService` og gemmer resultatet af kaldet som en `Future`.
- Nu laver `main` noget andet mens sorteringen foregår i en anden tråd. 
- Når forløkke er kørt færdig, er `main` klar til at se om `Future`-objektet har 
et resultat klar. 
  - Så du laver en variable `sorted` og henter resultatet ud fra din `Future` og tildeler det til sorted. 
  - Printe de første 10 studerende ud.
- Ekstra
  - Prøv at lave flere tråde i din pool og leg med flere sorteringer .
  - Leg med `Thread.sleep()` i `call()` for at simulere lang sorteringstid

