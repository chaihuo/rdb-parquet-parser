package model

import java.sql.ResultSet
import java.util
import java.util.Arrays

/**
 * Created by root on 15-8-14.
 */
object ParquetTable {
  def tableList(rs: ResultSet): util.List[String] = {
    val list: util.List[String] = util.Arrays.asList(
      String.valueOf(rs.getInt("id")),
      rs.getString("content"),
      String.valueOf(rs.getFloat("price")),
      String.valueOf(rs.getTimestamp("time").getTime)
    )
    return list
  }
}
