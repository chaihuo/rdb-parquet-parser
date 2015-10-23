package json

import java.io.IOException

import com.google.gson.Gson

/**
 * Created by root on 15-8-12.
 */
object MSSQLConnInfoJsonUtil extends JsonConfigUtil{
  
  val DEFAULT_JSON_PATH = "./src/test/resources/MSSQLConnConfig.json"
  
  def getInfo(): MSSQLConnInfo = {
    val gson: Gson = new Gson()
    val jsonString: String = this.ReadJsonFile(DEFAULT_JSON_PATH)
    val info: MSSQLConnInfo = gson.fromJson(jsonString, classOf[MSSQLConnInfo])
    if (info == null) {
      throw new IOException("Configuration file maybe empty or contains error")
    }
    info.isValid()
    return info
  }
  
  def getInfo(file_path: String): MSSQLConnInfo = {
    val gson: Gson = new Gson()
    val jsonString: String = this.ReadJsonFile(file_path)
    val info: MSSQLConnInfo = gson.fromJson(jsonString, classOf[MSSQLConnInfo])
    if (info == null) {
      throw new IOException("Configuration file maybe empty or contains error")
    }
    info.isValid()
    return info
  }
}
