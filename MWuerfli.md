1.03:Gruppenfindung+Erstellung des Git REP
Konstruktion des Projektes Besprochen.
Erste Ideen gesammelt. Klassendiagramm + Client Server Schnittstelle fertig besprochen.
Client

2.03: Client Konzept => mit JavaFX
Die Daten kommen in Form eines Strings an
Dieser befindet sich in der Doku
Je nach dem welcher Fragetyp kommt
    Ja/nein =>  Optionsfelder
    min/max Option => Slider mit min und max
    float Zahl => Eingabefeld

08.03 Client mit Server verbinden (Main_CLient.java)
      JavaFX einbinden => Fehler
       => wichtig dafür, dass Client mit Controller für FX verbunden werden kann
       Client.fxml eingebunden
       Client.fxml mit Controller_Client.java verbunden => erstellen von Action Events

09.03 Fehlersuche, warum der kein Text vom Server empfangen wird
       => Fehlerursache: Serverseitiger Fehler, wurde am Ende der Stunde angepasst
       Client Verbindung Consolenanwendung funktioniert => erweitern auf javafx

12.03 Design der Benutzeroberfläche überlegen => umsetzten mit dem Scenebuilder

13.03 JavaFX einrichten => noch nicht vollständig funktionsfähig
    => GUI kann zumindest gestartet werden, dann gibt es jedoch einen Fehler


15.03 Fehler: "Graphics Device initialization failed for :  d3d, sw
Error initializing QuantumRenderer: no suitable pipeline found" beim Versuch JavaFX einzubinden

=> umsteigen auf 1.8 JDK (VM Options nicht mehr nötig)

Anpassen der Grafischen Oberfläche


16.03 GUI Konzept ändern => Fragen über Modalfenster => wenn ok geklickt werden
die eingegebenen Werte zurückgeschickt und dem Server zurückgeschickt. Das Modalfenster
muss je nach Frage angepasst werden. Nach dem Zurücksenden wird die nächste Frage gesendet.

Der Port wurde einmal über das gesammte Projekt definiert

22.03 Erste Version der GUI fertig gestellt(Fehler aufgrund einer leeren Zeile => Serverseitig geändert)
      Nach dem Beheben des Fehlers => GUI schöner gestalten

23.03 Anmeldefenster für GUI erstellt => vorbereitet für Passwörter-Checkfunktion.
      Diese prüft, ob das eingegebene Passwort korrekt ist. Wenn dies der Fall ist, wird der Client gestartet.

29.03 Erstellen einer Funktion, die mehrere Clientpasswörter zulässt (csv-File)
      Unterscheidung der Fragen => umstellen auf Switch

30.03 Schleife im CLient abändern
Abbruchbedingung im CLient etwas schöner gestalten
(Fenster wird am Schluss noch nicht sauber geschlossen)