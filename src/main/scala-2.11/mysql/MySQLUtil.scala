package mysql

import java.sql.{DriverManager, ResultSet}

/**
 * Created by root on 15-7-30.
 */
object MySQLUtil {

//  def MySQLUtil(): Unit = {
//
//  }


  def getTable(): ResultSet = {

    val conn_string = "jdbc:mysql://172.19.17.211:3306/test?user=root&password=root"

    val conn = DriverManager.getConnection(conn_string)
    val statement = conn.createStatement()
    // Execute Query
    val rs = statement.executeQuery("select * from parquet;")

    val _ROW_COUNT_ = statement.executeQuery("select count(*) from parquet_copy as row_number;").getInt("row_number")
    if(_ROW_COUNT_ >= 1000) {

    }

    return rs
  }

  /** **
    * Get data set, row number begins at from startNum.
    * Totally get limitNum rows.
    * @param startNum
    * @param limitNum
    * @return
    */
  def getTable(startNum: Int, limitNum: Int): ResultSet = {

    val conn_string = "jdbc:mysql://172.19.17.211:3306/test?user=root&password=root"

    val conn = DriverManager.getConnection(conn_string)
    val statement = conn.createStatement()
    val SQL = "select * from parquet_copy order by id limit " + startNum + "," + limitNum
    // Execute Query
    val rs = statement.executeQuery(SQL)

    return rs
  }

  def getTable(tableName: String, columnName: String, startNum: Int, limitNum: Int): ResultSet = {

    val conn_string = "jdbc:mysql://172.19.17.211:3306/test?user=root&password=root"

    val conn = DriverManager.getConnection(conn_string)
    val statement = conn.createStatement()
    val SQL = "select * from " + tableName + " order by " + columnName + " limit " + startNum + "," + limitNum
    // Execute Query
    val rs = statement.executeQuery(SQL)
    return rs
  }

  def getRowCount(): Int = {

    val conn_string = "jdbc:mysql://172.19.17.211:3306/test?user=root&password=root"

    val conn = DriverManager.getConnection(conn_string)
    val statement = conn.createStatement()
    // Execute Query
    val rs = statement.executeQuery("select count(*) as row_number from parquet_copy;")

    if(rs.next()) {
      return rs.getInt("row_number")
    } else {
      return 0
    }
  }
//  def getDataSet(): ResultSet = {
//  }


}
