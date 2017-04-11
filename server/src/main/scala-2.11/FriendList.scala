import java.util

import scala.collection.immutable.HashMap.HashMap1

/**
  * Created by Salvo on 02/03/2017.
  */
object FriendList {

  val mappa = Map(
                  "Salvo" -> List("akka.tcp://Client@192.168.1.34:2550/user/Alberto","akka.tcp://Client@192.168.1.162:2554/user/Paperino"), //ipotizzo che i miei amici siano sull'indirizzo del pc di alberto //192.168.1.34
                  "Alberto" -> List("akka.tcp://Client@192.168.1.162:2550/user/Salvo","akka.tcp://Client@192.168.1.35:2550/user/Topolino")  //ipotizzo che gli amici di alberto siano sul mio indirizzo //192.168.1.162

    //  in questo caso ho solo due utenti e due amici per ogni utente ( teoricamente la lista non ha limite superiore, fisicamente è limitata dalle prestazioni della rete e del server).

  )




}
