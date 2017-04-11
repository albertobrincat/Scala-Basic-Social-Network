import java.util

/**
  * Created by Salvo on 02/03/2017.
*/
case class Messaggio(myaddress : String, data : String, n_cond : Int, id: Int) //data è il valore in uscita
case class Post(data : String, n_cond : Int, id: Int) //in post data è il valore in ingresso delle pubblicazione degli amici
case object Ack
case object Autenticazione
