# M114 Codebeispiel Kompression mit Entropie

BBZW Sursee Modul 114, Codebeispiel Kompression mit Entropie: Shannon-Fano und Huffman
geschrieben von Johannes Zeller

Jar: https://drive.google.com/uc?id=1doJwDC0npcD4Ri_Pbkgb4-ExmbSojpj2&export=download

Exe: https://drive.google.com/uc?id=1mSM8R-8AWAQB_bvVElu8YGo41h-xMG4E&export=download

Befehle:
  "/encode {stringToEncode}"
  "/decode {stringToDecode}"
  "/exit"
  
Beispiel: Input = /encode Hello World -> Output = 0000001000111010000000100000101001100001_{ =00000001, r=001, d=00001, e=0001, W=000001, H=0000001, l=1, o=01}

Beispiel: Input = /decode 0000001000111010000000100000101001100001_{ =00000001, r=001, d=00001, e=0001, W=000001, H=0000001, l=1, o=01} -> Output = Hello World
    
Bemerkung: Die Applikation reagiert momentan beim Decodieren nicht auf Formatierungsfehler, zeigt dann auch nicht unbedingt ein Fehler an. Solange aber nur genau die Outputs von hier genommen werden, die immer korrekt sein sollten, sollte alles funktionieren.
