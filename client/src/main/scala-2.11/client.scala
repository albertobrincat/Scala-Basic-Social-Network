


import akka.actor._
import net.liftweb.json.parse

import scala.util.Random
import java.io._

object Client_spec {
  var nomeClient = "Salvo" //si ipotizza sia un identificatore univoco
}
object Client extends App{

  val system = ActorSystem("Client")
  //mi autentico nella rete
  val client = system.actorOf(Props[interfaccia], name = Client_spec.nomeClient)
  client ! Autenticazione
}


class interfaccia extends Actor {


  def receive = {
   case Ack =>
    println("\nAck dal server: pubblicazione avvenuta")
     this.menu();

   case Post(post,n_cond,id) =>
    var xx= true
     var k = 0
     var flag=0
     readOnly.cont.clear()


     println("Post ricevuto:[ "+post+" ] \ncondiviso "+n_cond+" volte.\nId del post: "+id)
   // var PostSerial = (JSON)Post;
    val json ="{\"data\":\""+post+"\",\"n_cond\":\""+n_cond+"\",\"id\":\""+id+"\"}"
    readOnly.read()

     implicit  val formats = net.liftweb.json.DefaultFormats

     if(readOnly.cont.size==0){
       println("PRIMO NUOVO POST")
       val writer = new FileWriter(new File("postDB.txt"), true)
       writer.write(json + System.getProperty("line.separator"))
       writer.close()
     }

     for (j <- 0 to readOnly.cont.size-1) {
       if(xx==true){
         // println("STAMPO->"+readOnly.cont(k))
       val postString_db = parse(readOnly.cont(k))
       val postToObject_buffer = postString_db.extract[postBuff]
      // println(Integer.parseInt(postToObject_buffer.id)+" vs "+ id.toString())
       if (Integer.parseInt(postToObject_buffer.id) == id) {
        //println("oggetto cpn lo stesso id " + postToObject_buffer)
        // println("int " + Integer.parseInt(postToObject_buffer.n_cond) + " est" + n_cond)
         if (Integer.parseInt(postToObject_buffer.n_cond) < n_cond) {
           println("IL TUO POST TI E' STATO RICONDIVISO!\n")
           println("IL TUO POST " + postToObject_buffer.data)
          // println("lista CON il vecchio " + readOnly.cont)
           readOnly.cont.remove(j)
           //println("rimuovo il vecchio el dall alista e ne aggiungo uno nuovo\n")
         // println("lista SENZA il vecchio" + readOnly.cont)
           readOnly.cont += json
         //  println("lista CON il nuovo" + readOnly.cont)
           val writer = new FileWriter(new File("postDB.txt"))
           for (x <- 0 to readOnly.cont.size - 1) {
             writer.write(readOnly.cont(x) + System.getProperty("line.separator"))
           }
           writer.close()
           xx=false
           //break
         } else {
           if (Integer.parseInt(postToObject_buffer.n_cond) >= n_cond) {
             xx=false
           }
         }
       }else{
         flag=1
       }
       }
       k=k+1
     }

     if(flag==1){
       println("NUOVO POST")
       val writer = new FileWriter(new File("postDB.txt"), true)
       writer.write(json + System.getProperty("line.separator"))
       writer.close()
       this.menu()
     }


     this.menu;
     case Autenticazione =>
     this.menu;
     println("")
}




  def pubblica  = {

    println("Mi autentico\n")
    println("Inserisci la notizia da pubblicare:")
    val input = scala.io.StdIn.readLine().toString()
    println("Invio il messaggio\n")
    val server = context.actorSelection("akka.tcp://Server@192.168.1.162:2552/user/admin")  //INDIRIZZO DEL SERVER
    val n_cond=0
    val caratteri ="123456789"
     val id = (1 to 9).map(
      x =>
      {
        val index = Random.nextInt(caratteri.length)
        caratteri(index)
      }
      ).mkString("")
     println("id random generato->"+Integer.parseInt(id))  //da eliminare
     server ! Messaggio(Client_spec.nomeClient,input.toString(),n_cond+1,Integer.parseInt(id))
  }



  def bacheca = {

    readPrint.read()
    println("\nInserisci il numero del post che vuoi condividere o inserisci '0' per tornare al menu")
    val input1 = scala.io.StdIn.readInt()
    if(input1 == 0){
      menu()
    }else{

      implicit  val formats = net.liftweb.json.DefaultFormats
      readPrint.read()
      val jValue = parse(readPrint.cont(input1-1).toString())
      val postObj = jValue.extract[postBuff] //singolo post letto dal file
      val server = context.actorSelection("akka.tcp://Server@192.168.1.162:2552/user/admin") //SERVER
      println(postObj)
      server ! Messaggio(Client_spec.nomeClient,postObj.data,Integer.parseInt(postObj.n_cond)+1,Integer.parseInt(postObj.id))

    }

  }






  def menu(): Unit = {
    println("\n\n------------------CHATROOM-------------------\n")
    println("\nScegli una nuova operazione")
    println("\n1: Leggi messaggi\n")
    println("\n2: Scrivi messaggio\n")
    println("3: Visualizza bacheca\n")
    val input = scala.io.StdIn.readInt()
    if(input == 1){

      this.receive
    }
    if(input == 2){
      this.pubblica
    }

    if(input == 3){
      this.bacheca
    }

  }


}