# Töötajate haldamine
Arendada lihtne töötajate haldamise süsteem, nii backend (Java 21, Spring Boot, Maven) kui ka frontend (Angular).
Läbi frontendi peab olema võimalik kasutajal vaadata töötajate nimekirja ning lisada,muuta ja kustutada töötajaid.
***
## Frontend
+ npm peab olema installitud
+ `npm i`
+ `npm i -g @angular/cli`
+ `ng serve`
+ Ava `http://localhost:4200/`
## Backend
+ jdk 21
+ Maven 4.0.0
+ spring boot 3.3.4

REST API: Pakub otsupunkte töötajate haldamiseks (lisamine, muutmine, kustutamine, lehekülgedeks jaotatud nimekiri).
Transactional Outbox muster: Kui lisatakse uus töötaja, salvestatakse samas transaktsioonis kirje ka outbox_events tabelisse. See garanteerib, et sündmus ei lähe kaduma, kui väline süsteem (nt Kafka) peaks maas olema.
Temporal Workflow: Koodis on ette valmistatud liides töötaja "onboarding" protsessi jaoks. See on mõeldud pikaajaliste ja töökindlate töövoogude haldamiseks.
Data DTOs: Kasutatakse record tüüpi andmevahetusobjekte (Request/Response), mis on Java moderne standard.
Andmete laadimine: DataLoader genereerib käivitamisel 102 testkasutajat.
