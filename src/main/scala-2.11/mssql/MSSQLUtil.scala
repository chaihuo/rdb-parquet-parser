package mssql

import java.sql.{DriverManager, ResultSet}
import java.util

/**
 * Created by root on 15-9-17.
 */
object MSSQLUtil {


  def getTableList(connString: String): util.ArrayList[String] = {

    if(connString == null || connString.isEmpty) {
      throw new IllegalArgumentException("MSSQL connection string is null or empty")
    }

    val conn = DriverManager.getConnection(connString)
    val statement = conn.createStatement()
    val SQL = "select * from sysobjects where xtype='u' order by name;"
    // Execute Query
    val rs = statement.executeQuery(SQL)

    var table: util.ArrayList[String] = new util.ArrayList[String]
    while (rs.next()) {
      table.add(rs.getString("name"))
    }

    return table
  }

  def getRowCount(connString: String, tableName: String): Int = {


    if(connString == null || connString.isEmpty) {
      throw new IllegalArgumentException("The connection string is null or empty")
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

  def getTable(connString: String, tableName: String, columnName: String, startNum: Int, limitNum: Int): ResultSet = {

    if(connString == null || connString.isEmpty) {
      throw new IllegalArgumentException("MSSQL connection string is null or empty")
    }
    if(tableName == null || tableName.isEmpty) {
      throw new IllegalArgumentException("SQL argument table name is null or empty")
    }
    if(columnName == null || columnName.isEmpty) {
      throw new IllegalArgumentException("SQL argument column name is null or empty")
    }
    if(startNum < 0 || limitNum <= 0) {
      throw new IllegalArgumentException("SQL argument start number or limit number is illegal")
    } else {
      if(limitNum > 100000) {throw new IllegalArgumentException("DATA_SET_LIMIT is too large, this may cause performance issue")}
    }


    val conn = DriverManager.getConnection(connString)
    val statement = conn.createStatement()
    val SQL = "select top " + limitNum + " * from " + tableName + " where " + columnName + " not in (" +
      "select top " + startNum + " " + columnName + " from " + tableName + " order by " + columnName + ") order by " + columnName + ";"
//    print(SQL)
    // Execute Query
    val rs = statement.executeQuery(SQL)
    return rs
  }

  def getContractID(connString: String, tableName: String): String = {


    if(connString == null || connString.isEmpty) {
      throw new IllegalArgumentException("MSSQL connection string is null or empty")
    }
    if(tableName == null || tableName.isEmpty) {
      throw new IllegalArgumentException("SQL argument table name is null or empty")
    }

    val conn = DriverManager.getConnection(connString)
    val statement = conn.createStatement()
    // Execute Query
    val SQL = "select CONTRACTID from " + tableName + " group by CONTRACTID;"
    val rs = statement.executeQuery(SQL)

    if(rs.next()) {
      return rs.getString("CONTRACTID")
    } else {
      return null
    }
  }

  def getSymbol(connString: String, tableName: String): String = {


    if(connString == null || connString.isEmpty) {
      throw new IllegalArgumentException("MSSQL connection string is null or empty")
    }
    if(tableName == null || tableName.isEmpty) {
      throw new IllegalArgumentException("SQL argument table name is null or empty")
    }

    val conn = DriverManager.getConnection(connString)
    val statement = conn.createStatement()
    // Execute Query
    val SQL = "select SYMBOL from " + tableName + " group by SYMBOL;"
    val rs = statement.executeQuery(SQL)

    if(rs.next()) {
      return rs.getString("SYMBOL")
    } else {
      return null
    }
  }

}
