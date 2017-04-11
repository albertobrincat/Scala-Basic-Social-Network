
import java.io._

object WriteFile {

  def write(json : String) {

    val writer = new FileWriter(new File("postDB.txt"),true)
    writer.write(json+ System.getProperty("line.separator"))
    writer.close()




  }
}