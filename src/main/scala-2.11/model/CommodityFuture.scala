package model

import java.sql.ResultSet
import java.text.SimpleDateFormat

/**
 * Created by root on 15-9-20.
 */
object CommodityFuture {

  def whichTable(contractID: String): String = {
    if (contractID.toLowerCase().contains("a") && contractID.toLowerCase().matches("\\ba\\d{4}")) return "a"
    if (contractID.toLowerCase().contains("ag")) return "ag"
    if (contractID.toLowerCase().contains("al")) return "al"
    if (contractID.toLowerCase().contains("au")) return "au"
    if (contractID.toLowerCase().contains("b") && contractID.toLowerCase().matches("\\bb\\d{4}")) return "b"
    if (contractID.toLowerCase().contains("bb")) return "bb"
    if (contractID.toLowerCase().contains("bu")) return "bu"
    if (contractID.toLowerCase().contains("c") && contractID.toLowerCase().matches("\\bc\\d{4}")) return "c"
    if (contractID.toLowerCase().contains("cf")) return "cf"
    if (contractID.toLowerCase().contains("cs")) return "cs"
    if (contractID.toLowerCase().contains("cu")) return "cu"
    if (contractID.toLowerCase().contains("fb")) return "fb"
    if (contractID.toLowerCase().contains("fg")) return "fg"
    if (contractID.toLowerCase().contains("fu")) return "fu"
    if (contractID.toLowerCase().contains("hc")) return "hc"
    if (contractID.toLowerCase().contains("i") && contractID.toLowerCase().matches("\\bi\\d{4}")) return "i"
    if (contractID.toLowerCase().contains("j") && contractID.toLowerCase().matches("\\bj\\d{4}")) return "j"
    if (contractID.toLowerCase().contains("jd")) return "jd"
    if (contractID.toLowerCase().contains("jm")) return "jm"
    if (contractID.toLowerCase().contains("jr")) return "jr"
    if (contractID.toLowerCase().contains("l") && contractID.toLowerCase().matches("\\bl\\d{4}")) return "l"
    if (contractID.toLowerCase().contains("lr")) return "lr"
    if (contractID.toLowerCase().contains("m") && contractID.toLowerCase().matches("\\bm\\d{4}")) return "m"
    if (contractID.toLowerCase().contains("ma")) return "ma"
    if (contractID.toLowerCase().contains("me")) return "me"
    if (contractID.toLowerCase().contains("ni")) return "ni"
    if (contractID.toLowerCase().contains("ot")) return "ot"
    if (contractID.toLowerCase().contains("p") && contractID.toLowerCase().matches("\\bp\\d{4}")) return "p"
    if (contractID.toLowerCase().contains("pb")) return "pb"
    if (contractID.toLowerCase().contains("pm")) return "pm"
    if (contractID.toLowerCase().contains("pp")) return "pp"
    if (contractID.toLowerCase().contains("rb")) return "rb"
    if (contractID.toLowerCase().contains("ri")) return "ri"
    if (contractID.toLowerCase().contains("rm")) return "rm"
    if (contractID.toLowerCase().contains("rs")) return "rs"
    if (contractID.toLowerCase().contains("ru")) return "ru"
    if (contractID.toLowerCase().contains("sf")) return "sf"
    if (contractID.toLowerCase().contains("sm")) return "sm"
    if (contractID.toLowerCase().contains("sn")) return "sn"
    if (contractID.toLowerCase().contains("sr")) return "sr"
    if (contractID.toLowerCase().contains("ta")) return "ta"
    if (contractID.toLowerCase().contains("tc")) return "tc"
    if (contractID.toLowerCase().contains("v") && contractID.toLowerCase().matches("\\bv\\d{4}")) return "v"
    if (contractID.toLowerCase().contains("wh")) return "wh"
    if (contractID.toLowerCase().contains("wr")) return "wr"
    if (contractID.toLowerCase().contains("y") && contractID.toLowerCase().matches("\\by\\d{4}")) return "y"
    if (contractID.toLowerCase().contains("zc")) return "zc"
    if (contractID.toLowerCase().contains("zn")) return "zn"
    return null
  }

  def getData(rs: ResultSet): String = {
    val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val data = "('" + rs.getString("CONTRACTID") + "','" +
      df.format(rs.getTimestamp("TDATETIME")) + "','" +
      rs.getString("CONTRACTNAME") + "','" +
      rs.getDouble("LASTPX") + "','" +
      rs.getDouble("HIGHPX") + "','" +
      rs.getDouble("LOWPX") + "','" +
      rs.getDouble("CQ") + "','" +
      rs.getDouble("TQ") + "','" +
      rs.getDouble("LASTQTY") + "','" +
      rs.getDouble("INITOPENINTS") + "','" +
      rs.getDouble("OPENINTS") + "','" +
      rs.getDouble("INTSCHG") + "','" +
      rs.getDouble("TURNOVER") + "','" +
      rs.getDouble("RISELIMIT") + "','" +
      rs.getDouble("FALLLIMIT") + "','" +
      rs.getDouble("PRESETTLE") + "','" +
      rs.getDouble("PRECLOSE") + "','" +
      rs.getDouble("OPENPX") + "','" +
      rs.getDouble("CLOSEPX") + "','" +
      rs.getDouble("SETTLEMENTPX") + "','" +
      rs.getDouble("LIFELOW") + "','" +
      rs.getDouble("LIFEHIGH") + "','" +
      rs.getDouble("AVGPX") + "','" +
      rs.getDouble("BIDIMPLYQTY") + "','" +
      rs.getDouble("ASKIMPLYQTY") + "','" +
      rs.getString("SIDE") + "','" +
      rs.getDouble("S1") + "','" +
      rs.getDouble("B1") + "','" +
      rs.getDouble("SV1") + "','" +
      rs.getDouble("BV1") + "','" +
      rs.getDouble("S5") + "','" +
      rs.getDouble("S4") + "','" +
      rs.getDouble("S3") + "','" +
      rs.getDouble("S2") + "','" +
      rs.getDouble("B2") + "','" +
      rs.getDouble("B3") + "','" +
      rs.getDouble("B4") + "','" +
      rs.getDouble("B5") + "','" +
      rs.getDouble("SV5") + "','" +
      rs.getDouble("SV4") + "','" +
      rs.getDouble("SV3") + "','" +
      rs.getDouble("SV2") + "','" +
      rs.getDouble("BV2") + "','" +
      rs.getDouble("BV3") + "','" +
      rs.getDouble("BV4") + "','" +
      rs.getDouble("BV5") + "','" +
      rs.getDouble("PREDELTA") + "','" +
      rs.getDouble("CURRDELTA") + "','" +
      rs.getDouble("CHG") + "','" +
      rs.getDouble("CHGPCT") + "','" +
      rs.getString("VARIETIES") + "','" +
      df.format(rs.getTimestamp("LOCALTIME")) + "','" +
      rs.getString("MFLXID") + "','" +
      rs.getString("MARKET") + "','" +
      rs.getLong("UNIX") + "')"

    return data
  }
  
  
  
}
