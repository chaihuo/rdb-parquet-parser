package mysql

import java.sql.{DriverManager, ResultSet}

/**
 * Created by root on 15-7-30.
 */
object MySQLUtil {

  def getTable(connString: String, tableName: String, columnName: String): ResultSet = {

    val conn = DriverManager.getConnection(connString)
    val statement = conn.createStatement()
    val SQL = "select * from " + tableName + " order by " + columnName + ";"
    // Execute Query
    val rs = statement.executeQuery(SQL)
    return rs
  }

  def getTable(connString: String, tableName: String, columnName: String, startNum: Int, limitNum: Int): ResultSet = {

    val conn = DriverManager.getConnection(connString)
    val statement = conn.createStatement()
    val SQL = "select * from " + tableName + " order by " + columnName + " limit " + startNum + "," + limitNum + ";"
    // Execute Query
    val rs = statement.executeQuery(SQL)
    return rs
  }

  def getRowCount(connString: String, tableName: String): Int = {

    val conn = DriverManager.getConnection(connString)
    val statement = conn.createStatement()
    // Execute Query
    val SQL = "select count(*) as row_number from " + tableName + ";"
    val rs = statement.executeQuery(SQL)

    if(rs.next()) {
      return rs.getInt("row_number")
    } else {
      return 0
    }
  }


}
