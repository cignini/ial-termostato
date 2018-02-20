# ESERCIZIO: TERMOSTATO

Modellazione di un termostato come griglia di fasce orarie (0-23) per giorni della settimana (1-7).

Esportazione della griglia a video e su file Excel tramite la libreria HSSF.

1. importazione del progetto da github: in Eclipse: 
  - copia percorso del progetto GIT > da clone or download
  - File > Import
  - Projects from GIT
  - next > next > fino alla fine poi scegliere > import from general project
  - quindi conferma
2. click destro sul progetto > configure > convert to maven project
3. in Eclipse:
  - window > preferences > general > workspace --> mettere al posto di cp1252 --> UTF-8
  
4. documentazione HSSF: https://poi.apache.org/spreadsheet/quick-guide.html
5. dipendenza maven HSSF: https://mvnrepository.com/artifact/org.apache.poi/poi/3.17
6. nel pom.xml
  - aggiungere dopo 
  ```...</version>``` 
  il testo
  ```<dependencies></dependencies>```
  - dentro copiarci 
 ```
  <!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>3.17</version>
</dependency>
```

