# Portfolio: Design Patterns

## 1. Strategy på A*

Kig på klassen `graphs.astar.MazeSolver` eller din egen implementation
af A* (portfolioopgave). Heuristikken er hardkodet i `MazeSolver` og formodentlig
også i din implementation og kan ikke skiftes
ud uden at ændre i klassen selv.

Opgaven er nu at refaktorére `MazeSolver` eller din egen implementation, så heuristikken kan udskiftes dynamisk ved hjælp
af Strategy pattern. Du skal lave mindst to forskellige heuristikker.

Du kender Manhattan-afstand fra undervisningen. Du kan fx undersøge Euklidisk afstand,
eller overveje hvad der sker hvis heuristikken altid returnerer 0.



---

## 2. Proxy på AccessController

Kig på klasserne `reflection.annotations.AccessController` og `reflection.annotations.SecureService`.

Opgaven er nu at refaktorére koden så `AccessController` bliver et formelt Proxy pattern.



---

## 3. Valgfrit pattern

Find et sted i kursets kode eller i din egen kode (fx fra en anden portfolioopgave eller noget kode du har lavet
i et andet fag) hvor et design pattern
ville forbedre koden. Refaktorer koden.

Skriv en kort kommentar i koden der forklarer:
- Hvilket pattern du har valgt
- Hvilket problem det løser
- Hvorfor netop dette pattern passer her