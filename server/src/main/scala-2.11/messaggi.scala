/**
  * Created by Salvo on 02/03/2017.
*/
case class Messaggio(myaddress : String, data : String, n_cond : Int, id: Int)

case object Ack
case object Autenticazione
case class Post(data : String, n_cond : Int, id: Int)