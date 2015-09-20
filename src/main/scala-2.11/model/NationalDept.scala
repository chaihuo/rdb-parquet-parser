package model

import java.sql.{DriverManager, ResultSet}
import java.text.SimpleDateFormat

/**
 * Created by root on 15-9-19.
 */
object NationalDept {
  def getData(rs: ResultSet): String = {
    val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val data = "('" + rs.getString("SYMBOL") + "','" +
      df.format(rs.getTimestamp("BUSINESSTIME")) + "','" +
      rs.getBigDecimal("OPENPRICE") + "','" +
      rs.getBigDecimal("LASTPRICE") + "','" +
      rs.getBigDecimal("HIGHPRICE") + "','" +
      rs.getBigDecimal("LOWPRICE") + "','" +
      rs.getBigDecimal("SETTLEPRICE") + "','" +
      rs.getBigDecimal("PRESETTLE") + "','" +
      rs.getBigDecimal("CLOSEPRICE") + "','" +
      rs.getBigDecimal("PRECLOSE") + "','" +
      rs.getBigDecimal("CQ") + "','" +
      rs.getBigDecimal("VOLUME") + "','" +
      rs.getBigDecimal("CM") + "','" +
      rs.getBigDecimal("AMOUNT") + "','" +
      rs.getBigDecimal("PREPOSITION") + "','" +
      rs.getBigDecimal("POSITION") + "','" +
      rs.getBigDecimal("POSITIONCHANGE") + "','" +
      rs.getBigDecimal("LIMITUP") + "','" +
      rs.getBigDecimal("LIMITDOWN") + "','" +
      rs.getInt("SIDE") + "','" +
      rs.getInt("OC") + "','" +
      rs.getBigDecimal("B01") + "','" +
      rs.getBigDecimal("B02") + "','" +
      rs.getBigDecimal("B03") + "','" +
      rs.getBigDecimal("B04") + "','" +
      rs.getBigDecimal("B05") + "','" +
      rs.getBigDecimal("S01") + "','" +
      rs.getBigDecimal("S02") + "','" +
      rs.getBigDecimal("S03") + "','" +
      rs.getBigDecimal("S04") + "','" +
      rs.getBigDecimal("S05") + "','" +
      rs.getBigDecimal("BV01") + "','" +
      rs.getBigDecimal("BV02") + "','" +
      rs.getBigDecimal("BV03") + "','" +
      rs.getBigDecimal("BV04") + "','" +
      rs.getBigDecimal("BV05") + "','" +
      rs.getBigDecimal("SV01") + "','" +
      rs.getBigDecimal("SV02") + "','" +
      rs.getBigDecimal("SV03") + "','" +
      rs.getBigDecimal("SV04") + "','" +
      rs.getBigDecimal("SV05") + "','" +
      rs.getBigDecimal("CURRDELTA") + "','" +
      rs.getBigDecimal("PREDELTA") + "','" +
      rs.getInt("SETTLEMENTGROUPID") + "','" +
      rs.getInt("SETTLEMENTID") + "','" +
      rs.getBigDecimal("CHANGE") + "','" +
      rs.getBigDecimal("CHANGERATIO") + "','" +
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
