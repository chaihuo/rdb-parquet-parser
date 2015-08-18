package json

import java.io._

/**
 * Created by root on 15-8-12.
 */
class JsonConfigUtil {
  def ReadJsonFile(file_path: String): String = {
    var reader: BufferedReader = null
    var last_str: String = ""
    try {
      val fileInputStream: FileInputStream = new FileInputStream(file_path)
      val inputStreamReader: InputStreamReader = new InputStreamReader(fileInputStream, "UTF-8")
      reader = new BufferedReader(inputStreamReader)
      var tempString: String = null
      while ((({
        tempString = reader.readLine;
        tempString
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


}
