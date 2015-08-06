package json

import java.io.{BufferedReader, FileInputStream, IOException, InputStreamReader}

import com.google.gson.Gson

/**
 * Created by root on 15-8-4.
 */
object JsonUtil {
  val cnf_path = "./src/test/resources/configuration.json"
  def ReadJsonFile(): String = {
    var reader: BufferedReader = null
    var last_str: String = ""
    try {
      val fileInputStream: FileInputStream = new FileInputStream(cnf_path)
      val inputStreamReader: InputStreamReader = new InputStreamReader(fileInputStream, "UTF-8")
      reader = new BufferedReader(inputStreamReader)
      var tempString: String = null
      while ((({
        tempString = reader.readLine; tempString
      })) != null) {
        last_str += tempString
      }
      reader.close
    }
    catch {
      case e: IOException => {
        e.printStackTrace
      }
    } finally {
      if (reader != null) {
        try {
          reader.close
        }
        catch {
          case e: IOException => {
            e.printStackTrace
          }
        }
      }
    }
    return last_str
  }
  
  def getParserInfo(): ParserInfo = {
    val gson: Gson = new Gson()
    val jsonString: String = this.ReadJsonFile()
    val pi: ParserInfo = gson.fromJson(jsonString, classOf[ParserInfo])
    return pi
  }
  
  
  
}
