package model

import java.sql.ResultSet
import java.text.SimpleDateFormat
import java.util

/**
 * Created by root on 15-8-14.
 */
object TFDataAsList {
  def tableList(rs: ResultSet): util.List[String] = {
    val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val dfDate: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val list: util.List[String] = util.Arrays.asList(
      rs.getString("SYMBOL"),
      df.format(rs.getTimestamp("BUSINESSTIME")),
      rs.getDouble("OPENPRICE").toString(),
      rs.getDouble("LASTPRICE").toString(),
      rs.getDouble("HIGHPRICE").toString(),
      rs.getDouble("LOWPRICE").toString(),
      rs.getDouble("SETTLEPRICE").toString(),
      rs.getDouble("PRESETTLE").toString(),
      rs.getDouble("CLOSEPRICE").toString(),
      rs.getDouble("PRECLOSE").toString(),
      rs.getDouble("CQ").toString(),
      rs.getDouble("VOLUME").toString(),
      rs.getDouble("CM").toString(),
      rs.getDouble("AMOUNT").toString(),
      rs.getDouble("PREPOSITION").toString(),
      rs.getDouble("POSITION").toString(),
      rs.getDouble("POSITIONCHANGE").toString(),
      rs.getDouble("LIMITUP").toString(),
      rs.getDouble("LIMITDOWN").toString(),
      rs.getInt("SIDE").toString(),
      rs.getInt("OC").toString(),
      rs.getDouble("B01").toString(),
      rs.getDouble("B02").toString(),
      rs.getDouble("B03").toString(),
      rs.getDouble("B04").toString(),
      rs.getDouble("B05").toString(),
      rs.getDouble("S01").toString(),
      rs.getDouble("S02").toString(),
      rs.getDouble("S03").toString(),
      rs.getDouble("S04").toString(),
      rs.getDouble("S05").toString(),
      rs.getDouble("BV01").toString(),
      rs.getDouble("BV02").toString(),
      rs.getDouble("BV03").toString(),
      rs.getDouble("BV04").toString(),
      rs.getDouble("BV05").toString(),
      rs.getDouble("SV01").toString(),
      rs.getDouble("SV02").toString(),
      rs.getDouble("SV03").toString(),
      rs.getDouble("SV04").toString(),
      rs.getDouble("SV05").toString(),
      rs.getDouble("CURRDELTA").toString(),
      rs.getDouble("PREDELTA").toString(),
      rs.getInt("SETTLEMENTGROUPID").toString(),
      rs.getInt("SETTLEMENTID").toString(),
      rs.getDouble("CHANGE").toString(),
      rs.getDouble("CHANGERATIO").toString(),
      rs.getInt("CONTINUESIGN").toString(),
      rs.getTimestamp("TRADINGDATE").toString(),
      df.format(rs.getTimestamp("LOCALTIME").getTime()),
      df.format(rs.getTimestamp("RECTIME").getTime()),
      rs.getString("EXCHANGECODE"),
      rs.getInt("ID").toString(),
      rs.getLong("UNIX").toString()
    )
    return list
  }
}
