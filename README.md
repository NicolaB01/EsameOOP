

# <p align="center"> 🇵🇱  Ticketmaster per la Polonia e la Nuova Zelanda 🇳🇿 </p>

# Progetto d'esame di Programmazione a oggetti 2021/2022 📚📚

![image](https://user-images.githubusercontent.com/95362468/150032924-0be1de69-d1ac-48c0-bb04-b678f47fa586.png)


# Introduzione e descrizione dell'applicazione
Questo progetto sviluppa un'applicazione Java che consente
all'utente di confrontare dati e statistiche di tutti gli eventi che avranno luogo
in Nuova Zelanda e in Polonia.                                                                                    
L'applicazione mette a disposizione delle rotte che permettono all'utente di interagire con l'API fornita dal sito web [TicketMaster](https://developer.ticketmaster.com/products-and-docs/apis/getting-started/).                                                            

L'applicazione permette :
* Di filtrare gli eventi con degli opportuni parametri
* Ricevere statistiche in funzione del paese o della regione inserita
* Effetuare un confrontro tra i dati ottenuti della Polonia e NuovaZelanda




# Indice

* [Installazione](https://github.com/NicolaB01/EsameOOP#Installazione)
* [Rotte](https://github.com/NicolaB01/EsameOOP#rotte-%EF%B8%8F-)
  * [Rotte Principali](https://github.com/NicolaB01/EsameOOP#rotte-principali-1)
  * [Rotte Secondarie](https://github.com/NicolaB01/EsameOOP#rotte-secondarie-1)
- [Eccezioni](https://github.com/NicolaB01/EsameOOP#eccezioni)
- [JUnit](https://github.com/NicolaB01/EsameOOP#junit-)
- [Documentazione](https://github.com/NicolaB01/EsameOOP/tree/main/progetto/doc)
- [Autori](https://github.com/NicolaB01/EsameOOP#Autori)


# Installazione
L' applicazione può essere installata direttamente 
dal Prompt dei Comandi, scrivendo:

```bash
  git clone https://github.com/NicolaB01/EsameOOP
```
Dopo essere stata scaricata l'applicazione dovrà essere importata nell'IDE come "Existing Maven Projects".                  
Inoltre bisogna ottenere la propria *API-KEY* disponibile gratuitamente iscrivendosi al sito [TicketMaster](https://developer-acct.ticketmaster.com/user/register), la quale dovrà essere inserita nella classe [ReaderAPI](https://github.com/NicolaB01/EsameOOP/blob/main/progetto/src/main/java/univpm/esame/progetto/parsing/ReaderAPI.java) sostituendola con la parola tra virgolette.

```java
  private final String API_KEY= "7elxdku9GGG5k8j0Xm8KWdANDgecHMV0" 
```
Infine bisogna avviare l'applicazione eseguendo la classe [ProgettoApplication.java](https://github.com/NicolaB01/EsameOOP/blob/main/progetto/src/main/java/univpm/esame/progetto/ProgettoApplication.java) come SpringBootApplication.
    
***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

# Rotte ✈️ 📍

Per poter effettuare la chiamata tutte le rotte devono essere precedute da:

```bash
 http://localhost:8080
```
***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

### Le rotte messe a disposizione dal programma si dividono in due categorie:
- ####  [Rotte Principali](https://github.com/NicolaB01/EsameOOP#rotte-principali-1)
- ####  [Rotte Secondarie](https://github.com/NicolaB01/EsameOOP#rotte-principali-1)

***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

# Rotte Principali

| **Tipo**      | **Rotta**    | **Descrizione**                |
| :-------- | :------- | :------------------------- |
| `GET` |  [ *`/eventi `*](https://github.com/NicolaB01/EsameOOP#eventi) | Restituisce un confronto degli eventi avvenuti in Nuova Zelanda e in Polonia|
| `GET` | [*`/statistiche`*](https://github.com/NicolaB01/EsameOOP#statistiche) | Restituisce un confronto delle statistiche ottenute dagli eventi della Polonia e della Nuova Zelanda | 

***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

## */eventi*

Questa rotta permette all'utente finale di visualizzare un confronto tra tutti gli eventi programmati in Polonia e in Nuova zelanda.
Inoltre è possibile applicare dei filtri :

### Filtri 
| Identificatore | Descrizione                |
| :-------- |  :------------------------- |
| `generi` |Filtra in base ai generi inseriti |
| `regioni` |Filtra in base alle regioni inserite |

La rotta non richiede esplicitamente dei parametri, in aggiunta se vengono applicati dei filtri con parametri vuoti verrà stampato il confronto completo degli eventi programmati.                                                                         
Tuttavia possono essere aggiunti uno o più parametri per ogni filtro disponibile separandoli da un'apposita virgola, questo permetterà di ottenere un confronto filtrato tra i due paesi.

### Chiamata : 
![Schermata 2022-01-19 alle 00 34 12](https://user-images.githubusercontent.com/95362468/150036096-2b174564-f061-44bf-9efc-4e1d5d06f50b.png)

``` JSON
{
    "confronto": [
        {
            "paese": "Polonia",
            "eventiTot": 464,
            "eventiFiltrati": 126,
            "regioni": {
                "Mazowieckie": 82,
                "Lubelskie": 6,
                "Śląskie": 38
            },
            "generi": {
                "Pop": 4,
                "Rock": 87,
                "Classical": 35
            }
        },
        {
            "paese": "Nuova Zelanda",
            "eventiTot": 1006,
            "eventiFiltrati": 88,
            "regioni": {
                "Auckland": 88
            },
            "generi": {
                "Pop": 8,
                "Rock": 42,
                "Classical": 38
            }
        }
    ]
}
```

### Spiegazione JSON :
Il JSON di ritorno , *confronto*, è formato da una coppia di JSON, uno per la Polonia e uno per la Nuova Zelanda, strutturati come segue : 

-  **paese** : nome del paese
-  **eventiTot** : numero di eventi totale del paese
-  **eventiFiltrati** : numero di eventi ottenuti dopo aver applicato dei filtri (se presenti)
-  **regioni** : Lista contenente il nome delle regioni filtrate seguito dal numero di eventi per una specifica regione
    - *Nome della regione* : numero di eventi totali della regione
-  **generi** :  Lista contenente il nome dei generi filtrate seguito dal numero di eventi per uno specifico genere
      - *Nome del genere* : numero di eventi totali del genere

***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***    
        
## */statistiche* 
Questa rotta permette all'utente finale di visualizzare le statistiche di entrambi i paesi, Polonia e in Nuova Zelanda.
Non sono richiesti parametri per questa chiamata.
È stato scelto di ridurre il numero per rende il JSON a scopo illustrativo più leggibile.

### Chiamata : 
![image](https://user-images.githubusercontent.com/95362468/150036062-ae0aa54d-8981-4194-b442-fc8e1828458c.png)

``` JSON
[
    {
        "paese": "Polonia",
        "regioni": [
            "Śląskie",
            "Lubelskie",
            "Warmińsko",
            "Mazowieckie",
            "Zachodniopomorskie",
            "Wielkopolskie",
            "Małopolskie",
            "Dolnośląskie",
            "Łódzkie",
            "Lubuskie",
            "Podkarpackie",
            "Podlaskie",
            "Opolskie",
            "Pomorskie",
            "Kujawsko",
            "Świętokrzyskie"
        ],
        "eventi": 100,
        "segmenti": [
            {
                "segmento": "Miscellaneous",
                "eventi": 22,
                "percentuale": "22%",
                "generi": [
                    "Hobby/Special Interest Expos",
                    "Family",
                    "Fairs & Festivals"
                ]
            },
            {
                "segmento": "Music",
                "eventi": 75,
                "percentuale": "75%",
                "generi": [
                    "Classical",
                    "Rock",
                    "Blues",
                    "Dance/Electronic",
                    "Alternative",
                    "Reggae",
                    "Jazz",
                    "Hip-Hop/Rap",
                    "Folk",
                    "R&B"
                ]
            },
            {
                "segmento": "Sports",
                "eventi": 3,
                "percentuale": "3%",
                "generi": [
                    "Hockey",
                    "Athletic Races"
                ]
            }
        ],
        "anni": [
            {
                "anno": "2022",
                "mesi": [
                    {
                        "mese": "Gennaio",
                        "eventi": 34,
                        "max": 10,
                        "min": 1,
                        "media": "1,1"
                    },
                    {
                        "mese": "Febbraio",
                        "eventi": 66,
                        "max": 7,
                        "min": 1,
                        "media": "2,36"
                    }
                ]
            }
        ]
    },
    {
        "paese": "NuovaZelanda",
        "regioni": [
            "Northland",
            "Auckland",
            "BayofPlenty",
            "Waikato",
            "Gisborne",
            "HawkesBay",
            "Taranaki",
            "ManawatuWanganui",
            "Wellington",
            "Tasman",
            "Nelson",
            "Marlborough",
            "Canterbury",
            "WestCoast",
            "Otago",
            "Southland"
        ],
        "eventi": 100,
        "segmenti": [
            {
                "segmento": "Miscellaneous",
                "eventi": 12,
                "percentuale": "12%",
                "generi": [
                    "Community/Civic"
                ]
            },
            {
                "segmento": "Music",
                "eventi": 19,
                "percentuale": "19%",
                "generi": [
                    "Dance/Electronic",
                    "Undefined",
                    "Other",
                    "Reggae",
                    "Rock",
                    "R&B"
                ]
            },
            {
                "segmento": "Arts & Theatre",
                "eventi": 69,
                "percentuale": "69%",
                "generi": [
                    "Fine Art",
                    "Miscellaneous",
                    "Theatre",
                    "Comedy",
                    "Variety",
                    "Miscellaneous Theatre"
                ]
            }
        ],
        "anni": [
            {
                "anno": "2022",
                "mesi": [
                    {
                        "mese": "Gennaio",
                        "eventi": 41,
                        "max": 7,
                        "min": 1,
                        "media": "1,32"
                    },
                    {
                        "mese": "Febbraio",
                        "eventi": 59,
                        "max": 8,
                        "min": 1,
                        "media": "2,11"
                    }
                ]
            }
        ]
    }
]
```

### Spiegazione JSON : 
Il JSON di ritorno è formato da una coppia di JSON, uno per la Polonia e uno per la Nuova Zelanda, strutturati come segue : 

-  ***paese***: nome del paese
      - **regioni** : Lista dei nomi delle regioni del paese
         - Nome della regione
      - **eventi** : numero di eventi totale del paese 
      - **segmenti** : Lista dei segmenti 
         - *segmento* : nome del segmento
         - *eventi* : numero di eventi totale del segmento
         - *percentuale* : peso in percentuale del segmento rispetto agli eventi totali del paese
         - *generi* :  Lista dei generi contenuti nel segmento
            - nome del genere
                
    - **anni** : Lista di anni
        - *anno* : anno in cui avvengono gli eventi sotto riportati
            - *mesi* : Lista dei mesi contenuti in quel anno con i corrispettivi dati
                - mese: nome del mese
                - eventi: numero di eventi totali del mese
                - max : numero massimo di eventi in un giorno del mese 
                - min : numero minimo di eventi in un giorno del mese in cui avvengono eventi
                - media : media calcolata tra il numero totale di eventi mensili e il numero dei giorni del mese
    
***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

# Rotte Secondarie 

| **Tipo**      | **Rotta**    | **Descrizione**                |
| :-------- | :------- | :------------------------- |
| `GET` | [*`/eventi/{paese}`*](https://github.com/NicolaB01/EsameOOP#eventipaese) | Restituisce una lista di eventi di un determinato paese|
| `GET` | [*`/statistiche/{paese}`*](https://github.com/NicolaB01/EsameOOP/blob/main/README.md#statistichepaese) | Restituisce le statistiche ottenute dagli eventi di un determinato paese | 
| `GET` | [*`/statistiche/{paese}/{regione}`*](https://github.com/NicolaB01/EsameOOP/blob/main/README.md#statistichepaeseregione) | Restituisce le statistiche ottenute dagli eventi di una o più regioni di un determinato paese | 

***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

## */eventi/{paese}*

Questa rotta permette all'utente finale di visualizzare una lista di eventi di un determinato paese, Polonia o Nuova Zelanda.
Inoltre permette all'utente di poter applicare dei filtri: 

### Filtri 

| Identificatore | Descrizione                |
| :-------- |  :------------------------- |
| `regioni` |Filtra in base alla regione inserita |
| `segmenti` | Filtra in base al segmento inserito | 
| `anni` | Filtra in base al anno inserito |
| `mesi` | Filtra in base al mese inserito |
| `giorni` |  Filtra in base al giorno inserito|
| `generi` |  Filtra in base al genere inserito |
| `size` |  Filtra in base alla size inserita |

La rotta non richiede esplicitamente dei parametri, in aggiunta se vengono applicati i filtri con parametri vuoti verrà stampata la lista completa degli eventi per quel determinato paese.                                                                 
Tuttavia possono essere aggiunti uno o più parametri per ogni filtro disponibile separandoli da un'apposita virgola, questo permetterà di ottenere una lista di eventi filtrata per quel determinato paese.

### Chiamata : 
![image](https://user-images.githubusercontent.com/95362468/150037908-9056b5e2-b535-4ea1-9b1d-0d4b12c5ac32.png)

```JSON
[
    {
        "nome": "Michał Wiśniewski Akustycznie Część 2 - ZAWSZE NAPRZÓD - NIGDY WSTECZ",
        "luogo": {
            "nazione": "Poland",
            "regione": "Lubelskie",
            "longitudine": "51.24906",
            "latitudine": "22.55304"
        },
        "data": {
            "giorno": "17",
            "mese": "Marzo",
            "anno": "2022"
        },
        "classificazione": {
            "segmento": "Music",
            "genere": "Rock",
            "sottoGenere": "Pop"
        },
        "pagina": {
            "elementiTot": 464,
            "totalPage": 24,
            "numeroPagina": 8,
            "paginaSuccessiva": 9
        }
    },
    {
        "nome": "LemON 10-lecie zespołu, goście: Miuosh, Grubson",
        "luogo": {
            "nazione": "Poland",
            "regione": "Śląskie",
            "longitudine": "50.26606",
            "latitudine": "19.02543"
        },
        "data": {
            "giorno": "20",
            "mese": "Marzo",
            "anno": "2022"
        },
        "classificazione": {
            "segmento": "Music",
            "genere": "Rock",
            "sottoGenere": "Pop"
        },
        "pagina": {
            "elementiTot": 464,
            "totalPage": 24,
            "numeroPagina": 9,
            "paginaSuccessiva": 10
        }
    }
]
```
### Spiegazione JSON
Il JSON di ritorno è formato da una lista di eventi ognuno dei quali presenta la seguente struttura : 
- **nome** : nome dell'evento
- **luogo**: 
    - *nazione* : nazione in cui avviene l'evento
    - *regione* : regione in cui avviene l'evento
    - *longitudine* : longitudine in cui avviene l'evento
    - *latitudine*: latitudine in cui avviene l'evento
- **data** : 
    - *giorno* : giorno in cui avviene l'evento
    - *mese* : mese in cui avviene l'evento
    - *anno* : anno in cui avviene l'evento
- **classificazione** :  
    - *segmento* : segmento dell'evento
    - *genere* : genere dell'evento
    - *sottoGenere* : sottogenere dell'evento
- **pagina** :
  - *elementiTot* : numero di eventi totale del paese
  - *totalPage* : numero di pagine totali presenti nella rotta di TicketMaster in cui la size è settata a 20
  - *numeroPagina* : numero della pagina corrente in cui è contenuto l'evento nella rotta di TicketMaster
  - *paginaSuccessiva* : numero della pagina successiva a quella corrente
    
***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

## */statistiche/{paese}*
Questa rotta permette all'utente finale di visualizzare le statistiche complessive calcolate sugli eventi programmati in un determinato paese , Polonia o Nuova Zelanda.       
Non sono richiesti parametri per questa chiamata.
È stato scelto di ridurre il numero per rende il JSON a scopo illustrativo più leggibile.

### Chiamata : 
![Schermata 2022-01-19 alle 01 07 58](https://user-images.githubusercontent.com/95362468/150038892-405b9dbc-bea9-46be-8b05-42f2fa68287b.png)

```JSON
{
    "paese": "Polonia",
    "regioni": [
        "Śląskie",
        "Lubelskie",
        "Warmińsko",
        "Mazowieckie",
        "Zachodniopomorskie",
        "Wielkopolskie",
        "Małopolskie",
        "Dolnośląskie",
        "Łódzkie",
        "Lubuskie",
        "Podkarpackie",
        "Podlaskie",
        "Opolskie",
        "Pomorskie",
        "Kujawsko",
        "Świętokrzyskie"
    ],
    "eventi": 100,
    "segmenti": [
        {
            "segmento": "Miscellaneous",
            "eventi": 22,
            "percentuale": "22%",
            "generi": [
                "Hobby/Special Interest Expos",
                "Family",
                "Fairs & Festivals"
            ]
        },
        {
            "segmento": "Music",
            "eventi": 75,
            "percentuale": "75%",
            "generi": [
                "Classical",
                "Rock",
                "Blues",
                "Dance/Electronic",
                "Alternative",
                "Reggae",
                "Jazz",
                "Hip-Hop/Rap",
                "Folk",
                "R&B"
            ]
        },
        {
            "segmento": "Sports",
            "eventi": 3,
            "percentuale": "3%",
            "generi": [
                "Hockey",
                "Athletic Races"
            ]
        }
    ],
    "anni": [
        {
            "anno": "2022",
            "mesi": [
                {
                    "mese": "Gennaio",
                    "eventi": 34,
                    "max": 10,
                    "min": 1,
                    "media": "1,1"
                },
                {
                    "mese": "Febbraio",
                    "eventi": 66,
                    "max": 7,
                    "min": 1,
                    "media": "2,36"
                }
            ]
        }
    ]
}
```
### Spiegazione JSON
Il JSON di ritorno è strutturato come segue : 

-  ***paese***: nome del paese
      - **regioni** : Lista dei nomi delle regioni del paese
         - Nome della regione
      - **eventi** : numero di eventi totale del paese 
      - **segmenti** : Lista dei segmenti 
         - *segmento* : nome del segmento
         - *eventi* : numero di eventi totale del segmento
         - *percentuale* : peso in percentuale del segmento rispetto agli eventi totali del paese
         - *generi* :  Lista dei generi contenuti nel segmento
            - nome del genere
                
    - **anni** : Lista di anni
        - *anno* : anno in cui avvengono gli eventi sotto riportati
            - *mesi* : Lista dei mesi contenuti in quel anno con i corrispettivi dati
                - mese: nome del mese
                - eventi: numero di eventi totali del mese
                - max : numero massimo di eventi in un giorno del mese 
                - min : numero minimo di eventi in un giorno del mese in cui avvengono eventi
                - media : media calcolata tra il numero totale di eventi mensili e il numero dei giorni del mese

***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

## *statistiche/{paese}/{regione}*
Questa rotta permette all'utente finale di visualizzare le statistiche complessive calcolate sugli eventi programmati in una o più regioni di un determinato paese, Polonia o Nuova Zelanda.
Non sono richiesti parametri per questa chiamata.
![Schermata 2022-01-19 alle 23 50 19](https://user-images.githubusercontent.com/95362468/150231105-f8977c94-5315-4520-ad57-a36c66dc84ec.png)

#### Chiamata :
```JSON
{
    "paese": "Polonia",
    "regioni": [
        "Śląskie"
    ],
    "eventi": 17,
    "segmenti": [
        {
            "segmento": "Miscellaneous",
            "eventi": 3,
            "percentuale": "18%",
            "generi": [
                "Family",
                "Fairs & Festivals"
            ]
        },
        {
            "segmento": "Music",
            "eventi": 12,
            "percentuale": "71%",
            "generi": [
                "Classical",
                "Rock",
                "Hip-Hop/Rap"
            ]
        },
        {
            "segmento": "Sports",
            "eventi": 2,
            "percentuale": "12%",
            "generi": [
                "Hockey"
            ]
        }
    ],
    "anni": [
        {
            "anno": "2022",
            "mesi": [
                {
                    "mese": "Gennaio",
                    "eventi": 6,
                    "max": 2,
                    "min": 1,
                    "media": "0,19"
                },
                {
                    "mese": "Febbraio",
                    "eventi": 11,
                    "max": 2,
                    "min": 1,
                    "media": "0,39"
                }
            ]
        }
    ]
}
```
### Spiegazione JSON
Il JSON di ritorno è strutturato come segue : 

-  ***paese***: nome del paese
      - **regioni** : Lista dei nomi delle regioni passate alla rotta
         - Nome della regione
      - **eventi** : numero di eventi totale del paese 
      - **segmenti** : Lista dei segmenti 
         - *segmento* : nome del segmento
         - *eventi* : numero di eventi totale del segmento
         - *percentuale* : peso in percentuale del segmento rispetto agli eventi totali del paese
         - *generi* :  Lista dei generi contenuti nel segmento
            - nome del genere
                
    - **anni** : Lista di anni
        - *anno* : anno in cui avvengono gli eventi sotto riportati
            - *mesi* : Lista dei mesi contenuti in quel anno con i corrispettivi dati
                - mese: nome del mese
                - eventi: numero di eventi totali del mese
                - max : numero massimo di eventi in un giorno del mese 
                - min : numero minimo di eventi in un giorno del mese in cui avvengono eventi
                - media : media calcolata tra il numero totale di eventi mensili e il numero dei giorni del mese

***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

# Eccezioni
L'applicazione permette di gestire tre eccezioni personalizzate : 
- [**ExceptionPaeseInesistente**](https://github.com/NicolaB01/EsameOOP/blob/main/progetto/src/main/java/univpm/esame/progetto/eccezioni/ExceptionPaeseInesistente.java) : Questa eccezione viene lanciata quando l'utente immette come variabile di percorso un paese che non coincide con Polonia o Nuova Zelanda.                                                  
     Le rotte che possono lanciare questa eccezione sono: 
   -  */eventi/{paese}* 
   -  */statistiche/{paese}*
   -  */statistiche/{paese}/{regione}*.
![image](https://user-images.githubusercontent.com/93011457/150129046-4eec07af-2db1-40a9-b54a-bdd25c7cd365.png)

- [**ExceptionRegioneInesistente**](https://github.com/NicolaB01/EsameOOP/blob/main/progetto/src/main/java/univpm/esame/progetto/eccezioni/ExceptionRegioneInesistente.java) : Questa eccezione viene lanciata quando l'utente immette come variabile di percorso una regione che non coincide con una delle regioni della Polonia o Nuova Zelanda.                                     
    Le rotte che possono lanciare questa eccezione sono: 
   -  */eventi/{paese}* 
   -  */statistiche/{paese}/{regione}*.
![Schermata 2022-01-19 alle 13 26 43](https://user-images.githubusercontent.com/93011457/150129450-a6314695-1e10-4581-a3cc-63bd44a55da1.png)

- [**ExceptionListaVuota**](https://github.com/NicolaB01/EsameOOP/blob/main/progetto/src/main/java/univpm/esame/progetto/eccezioni/ExceptionListaVuota.java) : Questa eccezione viene lanciata quando non ci sono eventi che soddifsfano i parametri che si vogliono filtrare.             
    Le rotte che possono lanciare questa eccezione sono: 
   -  */eventi* 
   -  */eventi/{paese}*.
![Schermata 2022-01-19 alle 13 31 08](https://user-images.githubusercontent.com/93011457/150129890-56a05ae0-4cab-42d4-8284-82554885d027.png)

Le nostre eccezioni vengono gestite dalla classe [**GestoreEccezioni**](https://github.com/NicolaB01/EsameOOP/blob/main/progetto/src/main/java/univpm/esame/progetto/controller/GestoreEccezioni.java)


***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

# JUnit 🧮
I test unitari sono stati divisi in 4 package ognuno dei quali contiene una classe : 

- univpm.esame.progetto.testEccezioni
   - [TestEccezioni](https://github.com/NicolaB01/EsameOOP/tree/main/progetto/src/test/java/univpm/esame/progetto/testEccezioni)
      - Classe di test per verificare il corretto funzionamento delle eccezioni 
- univpm.esame.progetto.testStrumenti 
   - [TestStrumenti](https://github.com/NicolaB01/EsameOOP/tree/main/progetto/src/test/java/univpm/esame/progetto/testStrumenti)
      - Classe di test per verificare il corretto funzionamento di alcune classi di supporto 
- univpm.esame.progetto.testFiltro
   - [TestFiltri](https://github.com/NicolaB01/EsameOOP/tree/main/progetto/src/test/java/univpm/esame/progetto/testFiltri)
      - Classe di test per verificare il corretto funzionamento dei metodi che effetuano un filtro
- univpm.esame.progetto.testStatistiche
   - [TestStatistiche](https://github.com/NicolaB01/EsameOOP/tree/main/progetto/src/test/java/univpm/esame/progetto/testStatistiche)
      - Classe di test per verificare il corretto funzionamento dei metodi che effettuano le statistiche  
                                                                                                                  
Non sono stati riportati **error** tuttavia possono essere riportati **failure** sui test che verificano le statistiche del paese e della regione dato che controllano il numero totale degli eventi il quale è variabile nel tempo.

![Schermata 2022-01-19 alle 10 43 34](https://user-images.githubusercontent.com/95362468/150105271-3adf63d1-24b7-4354-9b88-78f4104bf5f8.png)

***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***


# Documentatazione
Tutto il programma è documentato nella [Javadoc](https://github.com/NicolaB01/EsameOOP/tree/main/progetto/doc)

***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***

# Autori
| Cognome e Nome | Email Istituzionali       |
| :-------- |  :------------------------- |
| `Biagioli Nicola` | S1099740@studenti.univpm.it |
| `Di Battista Simone` | S1098060@studenti.univpm.it |

***Per tornare all'[indice](https://github.com/NicolaB01/EsameOOP#indice).***
