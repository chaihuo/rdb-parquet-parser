package model

import java.sql.ResultSet
import java.text.SimpleDateFormat
import java.util

/**
 * Created by root on 15-8-14.
 */
object CFDataAsList {
  def tableList(rs: ResultSet): util.List[String] = {
    val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val dfDate: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
    val list: util.List[String] = util.Arrays.asList(
      rs.getString("CONTRACTID"),
      df.format(rs.getTimestamp("TDATETIME")),
      rs.getString("CONTRACTNAME"),
      rs.getDouble("LASTPX").toString(),
      rs.getDouble("HIGHPX").toString(),
      rs.getDouble("LOWPX").toString(),
      rs.getDouble("CQ").toString(),
      rs.getDouble("TQ").toString(),
      rs.getDouble("LASTQTY").toString(),
      rs.getDouble("INITOPENINTS").toString(),
      rs.getDouble("OPENINTS").toString(),
      rs.getDouble("INTSCHG").toString(),
      rs.getDouble("TURNOVER").toString(),
      rs.getDouble("RISELIMIT").toString(),
      rs.getDouble("FALLLIMIT").toString(),
      rs.getDouble("PRESETTLE").toString(),
      rs.getDouble("PRECLOSE").toString(),
      rs.getDouble("OPENPX").toString(),
      rs.getDouble("CLOSEPX").toString(),
      rs.getDouble("SETTLEMENTPX").toString(),
      rs.getDouble("LIFELOW").toString(),
      rs.getDouble("LIFEHIGH").toString(),
      rs.getDouble("AVGPX").toString(),
      rs.getDouble("BIDIMPLYQTY").toString(),
      rs.getDouble("ASKIMPLYQTY").toString(),
      rs.getString("SIDE"),
      rs.getDouble("S1").toString(),
      rs.getDouble("B1").toString(),
      rs.getDouble("SV1").toString(),
      rs.getDouble("BV1").toString(),
      rs.getDouble("S5").toString(),
      rs.getDouble("S4").toString(),
      rs.getDouble("S3").toString(),
      rs.getDouble("S2").toString(),
      rs.getDouble("B2").toString(),
      rs.getDouble("B3").toString(),
      rs.getDouble("B4").toString(),
      rs.getDouble("B5").toString(),
      rs.getDouble("SV5").toString(),
      rs.getDouble("SV4").toString(),
      rs.getDouble("SV3").toString(),
      rs.getDouble("SV2").toString(),
      rs.getDouble("BV2").toString(),
      rs.getDouble("BV3").toString(),
      rs.getDouble("BV4").toString(),
      rs.getDouble("BV5").toString(),
      rs.getDouble("PREDELTA").toString(),
      rs.getDouble("CURRDELTA").toString(),
      rs.getDouble("CHG").toString(),
      rs.getDouble("CHGPCT").toString(),
      rs.getString("VARIETIES"),
      df.format(rs.getTimestamp("LOCALTIME")),
      dfDate.format(rs.getTimestamp("LOCALTIME")),
      rs.getString("MFLXID"),
      rs.getString("MARKET"),
      rs.getLong("UNIX").toString()
    )
    return list
  }
}
