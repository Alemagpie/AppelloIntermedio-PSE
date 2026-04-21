Dispositivi usati per testare:
- Xiaomi 11T Pro (via USB - android 14.0)
- Samsung A54 (via USB - android 16.0)


Note per la correzione:
- Durante i test sullo Xiaomi 11T pro è stato riscontrato che se i pulsanti (inclusa la griglia di colori),
    sono schiacciati ripetutamente e molto velocemente subito dopo un cambio di schermata (sia da 1 a 2 
    che viceversa), l'applicazione presenta uno schermo vuoto risolvibile solo con la ri-creazione dell'attività
    (quindi rotazione o chiusura). Lo stesso non accade con i test sul Samsung A54.
    La ragione potrebbe essere attribuibile ad una versione più vecchia di Android sullo Xiaomi (?)

- Alla fine della funzione "MainUI" c'è una funzione commentata di nome "LanguageIcon". È usata SOLO 
    per i test della localizzazione e non fa parte della versione finale dell' applicazione (il cambio di
    lingua comporta la cancellazione dei dati delle partite).
