# M114 Codebeispiel Kompression mit Entropie

#### BBZW Sursee Modul 114, Codebeispiel Kompression und Dekopression mit Entropie: Huffman-Algorithmus
geschrieben von Johannes Zeller

Empfohlene Java-Version: 17.0.5

Download Jar: https://drive.google.com/uc?id=1doJwDC0npcD4Ri_Pbkgb4-ExmbSojpj2&export=download

Download Exe: https://drive.google.com/uc?id=1mSM8R-8AWAQB_bvVElu8YGo41h-xMG4E&export=download&confirm=t&uuid=4bdbdbf4-7ca0-43fc-b283-dd16bc6ab0f0&at=ALAFpqwypR2VZ50drNH2qxJc-g4s:1666453197895

## Befehle
- /encode {stringToEncode}
- /decode {stringToDecode}
- /exit

## Beispiel Encodierung
- Input = /encode Hello World
- Output = 0000001000111010000000100000101001100001_{ =00000001, r=001, d=00001, e=0001, W=000001, H=0000001, l=1, o=01}

## Beispiel Decodierung
- Input = /decode 0000001000111010000000100000101001100001_{ =00000001, r=001, d=00001, e=0001, W=000001, H=0000001, l=1, o=01}
- Output = Hello World

### Bemerkung
Die Applikation reagiert momentan beim Decodieren nicht auf Formatierungsfehler, zeigt dann auch nicht unbedingt ein Fehler an. Solange aber nur genau die Outputs von hier genommen werden, die immer korrekt sein sollten, sollte alles funktionieren.
