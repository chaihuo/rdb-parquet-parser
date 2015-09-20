package mysql

import java.sql.{DriverManager, ResultSet}

/**
 * Created by root on 15-7-30.
 */
object MySQLUtil {

  def getTable(connString: String, tableName: String, columnName: String): ResultSet = {

    if(connString == null || connString.isEmpty) {
      throw new IllegalArgumentException("MySQL connection string is null or empty")
    }
    if(tableName == null || tableName.isEmpty) {
      throw new IllegalArgumentException("SQL argument table name is null or empty")
    }
    if(columnName == null || columnName.isEmpty) {
      throw new IllegalArgumentException("SQL argument column name is null or empty")
    }

    val conn = DriverManager.getConnection(connString)
    val statement = conn.createStatement()
    val SQL = "select * from " + tableName + " order by " + columnName + ";"
    // Execute Query
    val rs = statement.executeQuery(SQL)
    return rs
  }

  def getTable(connString: String, tableName: String, columnName: String, startNum: Int, limitNum: Int): ResultSet = {

    if(connString == null || connString.isEmpty) {
      throw new IllegalArgumentException("MySQL connection string is null or empty")
    }
    if(tableName == null || tableName.isEmpty) {
      throw new IllegalArgumentException("SQL argument table name is null or empty")
    }
    if(columnName == null || columnName.isEmpty) {
      throw new IllegalArgumentException("SQL argument column name is null or empty")
    }
    if(startNum <= 0 || limitNum <= 0) {
      throw new IllegalArgumentException("SQL argument start number or limit number is illegal")
    } else {
      if(limitNum > 100000) {throw new IllegalArgumentException("DATA_SET_LIMIT is too large, this may cause performance issue")}
    }


    val conn = DriverManager.getConnection(connString)
    val statement = conn.createStatement()
    val SQL = "select * from " + tableName + " order by " + columnName + " limit " + startNum + "," + limitNum + ";"
    // Execute Query
    val rs = statement.executeQuery(SQL)
    return rs
  }

  def getRowCount(connString: String, tableName: String): Int = {


    if(connString == null || connString.isEmpty) {
      throw new IllegalArgumentException("MySQL connection string is null or empty")
    }
    if(tableName == null || tableName.isEmpty) {
      throw new IllegalArgumentException("SQL argument table name is null or empty")
    }

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

  // Insert into MySQL
  def insertNationalDebt(connString: String, user: String, key: String, tableName: String, data: String): Unit = {


    if(data == null || data.isEmpty) {
      throw new IllegalArgumentException("Data string is null or empty")
    }

    Class.forName("com.mysql.jdbc.Driver")


    val conn = DriverManager.getConnection(connString, user, key)
    val statement = conn.createStatement()
    // Execute Query
    val SQL = "insert into tf " +
//      "(SYMBOL,BUSINESSTIME," +
//      "OPENPRICE,LASTPRICE,HIGHPRICE,LOWPRICE,SETTLEPRICE,PRESETTLE," +
//      "CLOSEPRICE,PRECLOSE,CQ,VOLUME,CM,AMOUNT,PREPOSITION,POSITION," +
//      "POSITIONCHANGE,LIMITUP,LIMITDOWN,SIDE,OC,B01,B02,B03,B04,B05," +
//      "S01,S02,S03,S04,S05,BV01,BV02,BV03,BV04,BV05,SV01,SV02,SV03," +
//      "SV04,SV05,CURRDELTA,PREDELTA,SETTLEMENTGROUPID,SETTLEMENTID," +
//      "CHANGE,CHANGERATIO,CONTINUESIGN,TRADINGDATE,LOCALTIME,RECTIME,EXCHANGECODE,ID,UNIX) " +
      "values " + data

//    print(SQL)

    val rs = statement.executeUpdate(SQL)
    conn.close()

  }

}
