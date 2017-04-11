/**
  * Created by Salvo on 03/03/2017.
  */
import scala.io.Source
import net.liftweb.json._
import scala.collection.mutable.ListBuffer

case class postBuff(data : String, n_cond : String, id: String)

object readPrint{

  var cont = new ListBuffer[String]()

  def read() {

 cont.clear()
    var i=1;
    println("----------------------BACHECA---------------------\n")
      for (line <- Source.fromFile("postDB.txt").getLines()) {
        cont+=line.toString()
        implicit  val formats = net.liftweb.json.DefaultFormats
        val jValue = parse(line.toString())
        val postObj = jValue.extract[postBuff] //singolo post letto dal file
        println(""
            +i+") Id del post:"+postObj.id+"\nInfo:["+postObj.data+"]\ncondiviso "+postObj.n_cond+" volte." +
            "\n---------------------------------------")
        i=i+1
      }
  }
}


object readOnly{
  this.cont = null
  var cont = new ListBuffer[String]()

  def read() {
   // println("SONO NELLA READ E PRIMA DI CARICARE CONT VALE-> "+cont)
    for (line <- Source.fromFile("postDB.txt").getLines()) {
      cont+=line.toString()
    }
   // println("SONO NELLA READ DOPO AVER CARICCATO CONT VALE-> "+cont)
  }


}

