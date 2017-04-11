

import akka.actor._



object server extends App{

  val system = ActorSystem("Server")
  var nomeClient = "admin" //nome del server
  //mi autentico nella rete
  val server = system.actorOf(Props[interfaccia], name = "admin")
  server ! Autenticazione

}

class interfaccia extends Actor {

  def receive = {
    case Messaggio(address,data,n_cond,id) =>

      println("arrivato il secondo messaggio da"+ " "+ self.path.name.toString() +"\n")
      println("Il messaggio proviene da : "+address)
      println("\n"+"Messaggio ricevuto correttamente")
      println("\n"+"Faccio il forwarding al  destinatario remoto")
      //println("\n"+"La lista degli amici di "+address+" è "+FriendList.mappa(address)+"\n");
      for( i <- 0 to FriendList.mappa(address).size-1){
        println("amico"+i+":" +FriendList.mappa(address)(i))
        println("invio a "+i+":" +FriendList.mappa(address)(i))
        val utente_remoto = context.actorSelection(FriendList.mappa(address)(i)) //FriendList.mappa(address)(i) è l'indirizzo del destinatario
        utente_remoto ! Post(data,n_cond,id)
         }

      println("\n"+"Invio l'ack")
      sender ! Ack

    case Autenticazione =>
      println("Mi autentico") //MI ESPONGO NELLA RETE
  }


}