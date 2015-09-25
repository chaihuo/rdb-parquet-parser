package model

import java.sql.{DriverManager, ResultSet}
import java.text.SimpleDateFormat

/**
 * Created by root on 15-9-19.
 */
object NationalDebt {


  def whichTable(symbol: String): String = {
    if (symbol.toLowerCase().contains("t") && symbol.toLowerCase().matches("\\bt\\d{4}")) return "t"
    if (symbol.toLowerCase().contains("tf")) return "tf"
    return null
  }
  
  def getData(rs: ResultSet): String = {
    val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val data = "('" + rs.getString("SYMBOL") + "','" +
      df.format(rs.getTimestamp("BUSINESSTIME")) + "','" +
      rs.getDouble("OPENPRICE") + "','" +
      rs.getDouble("LASTPRICE") + "','" +
      rs.getDouble("HIGHPRICE") + "','" +
      rs.getDouble("LOWPRICE") + "','" +
      rs.getDouble("SETTLEPRICE") + "','" +
      rs.getDouble("PRESETTLE") + "','" +
      rs.getDouble("CLOSEPRICE") + "','" +
      rs.getDouble("PRECLOSE") + "','" +
      rs.getDouble("CQ") + "','" +
      rs.getDouble("VOLUME") + "','" +
      rs.getDouble("CM") + "','" +
      rs.getDouble("AMOUNT") + "','" +
      rs.getDouble("PREPOSITION") + "','" +
      rs.getDouble("POSITION") + "','" +
      rs.getDouble("POSITIONCHANGE") + "','" +
      rs.getDouble("LIMITUP") + "','" +
      rs.getDouble("LIMITDOWN") + "','" +
      rs.getInt("SIDE") + "','" +
      rs.getInt("OC") + "','" +
      rs.getDouble("B01") + "','" +
      rs.getDouble("B02") + "','" +
      rs.getDouble("B03") + "','" +
      rs.getDouble("B04") + "','" +
      rs.getDouble("B05") + "','" +
      rs.getDouble("S01") + "','" +
      rs.getDouble("S02") + "','" +
      rs.getDouble("S03") + "','" +
      rs.getDouble("S04") + "','" +
      rs.getDouble("S05") + "','" +
      rs.getDouble("BV01") + "','" +
      rs.getDouble("BV02") + "','" +
      rs.getDouble("BV03") + "','" +
      rs.getDouble("BV04") + "','" +
      rs.getDouble("BV05") + "','" +
      rs.getDouble("SV01") + "','" +
      rs.getDouble("SV02") + "','" +
      rs.getDouble("SV03") + "','" +
      rs.getDouble("SV04") + "','" +
      rs.getDouble("SV05") + "','" +
      rs.getDouble("CURRDELTA") + "','" +
      rs.getDouble("PREDELTA") + "','" +
      rs.getInt("SETTLEMENTGROUPID") + "','" +
      rs.getInt("SETTLEMENTID") + "','" +
      rs.getDouble("CHANGE") + "','" +
      rs.getDouble("CHANGERATIO") + "','" +
      rs.getInt("CONTINUESIGN") + "','" +
      rs.getTimestamp("TRADINGDATE") + "','" +
      df.format(rs.getTimestamp("LOCALTIME").getTime()) + "','" +
      df.format(rs.getTimestamp("RECTIME").getTime()) + "','" +
      rs.getString("EXCHANGECODE") + "','" +
      rs.getInt("ID") + "','" +
      rs.getLong("UNIX") + "')"






    return data
  }
}
