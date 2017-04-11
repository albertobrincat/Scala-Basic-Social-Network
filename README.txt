-------------SocialNetwork in Linguaggio Scala---------------
Realizzato da Quattropani Salvatore e Brincat Alberto Attilio


ISTRUZIONI D’USO:

1. Scaricare l’ambiente di programmazione (consigliato l'utilizzo di "IntelliJIDEA COMMUNITY EDITION")
2. Aprire su n macchine diverse il progetto “Client”, su una singola macchina il progetto “Server”
3. Customizzare il client secondo le seguenti specifiche:

   - Modificare il file (situato in client/src/main/resource/application.conf) personalizzando hostname, porta ed eventualmente il provider(può essere remoto 
     o locale, ma di default viene utilizzato quello della libreria akka.remote, essenziale per lo scambio di messaggio tra gli attori sfruttando il protocollo TCP)

   - Modificare il file (situato in client/src/main/scala-2.11/client.scala/Client_spec), dove sarà necessario specificare il proprio identificativo descritto dalla
     variabile "nomeClient". 
   
   - Modificare le funzioni "pubblica" e "bacheca" all'interno del file (situato in client/src/main/scala-2.11/client.scala/Client) editando secondo la seguente sintassi 
     l'indirizzo del server: "akka.tcp://NOME_ACTOR_SYSTEM@IP:PORTA/user/NOMEsystem.actorOf"

4. Customizzare il server secondo le seguenti specifiche:
   
    - Modificare il file (situato in server/src/main/resource/application.conf) personalizzando hostname, porta ed eventualmente il provider(può essere remoto 
     o locale, ma di default viene utilizzato quello della libreria akka.remote, essenziale per lo scambio di messaggio tra gli attori sfruttando il protocollo TCP)
   
    - Modificare il file (situato in server/src/main/scala-2.11/FriendList.scala) entro il quale è presente una lista di liste rappresentante l'insieme degli amici 
      di ogni singolo utente
     



***PER VISUALIZZARE UN NUOVO MESSAGGIO IN ARRIVO DALLA RETE BISOGNA ACCEDERE DAL MENU ALLA FUNZIONALITA' "LEGGI MESSAGGI". 
   IN SEGUITO I POST VERRANNO CONSERVATI IN BACHECA, CIOE' SARANNO SALVATI SUL FILE "postDB.txt" ALL'INTERNO DELLA ROOT DIRECTORY DEL CLIENT.***



NB: NON SONO GESTITE LE ECCEZIONI RIGUARDO ALL’IMMISSIONE DA TASTIERA DI CARATTERI NON PREVISTI.