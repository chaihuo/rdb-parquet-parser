package parser

import java.io._
import java.sql.{ResultSet, SQLException}
import java.util

import model.{CommodityFuture, NationalDebt}
import mssql.MSSQLUtil
import mysql.MySQLUtil

/**
 * Created by root on 15-7-30.
 */
object MSSQL2MySQLUtils {
//  log class
  
  val MSSQLConnString : String = "jdbc:sqlserver://"
  
  val MySQLConnString: String = "jdbc:mysql://"
  val MySQLuser: String = ""
  val MySQLkey: String = ""
  val targetNationalDebtTableName: String = ""


  @throws(classOf[IOException])
  def convertNationalDebt() {

    // Get MSSQL tables
    val tableList: util.ArrayList[String] = MSSQLUtil.getTableList(MSSQLConnString)

    // Convert tables in table listh

    val tableNum: Int = tableList.size()
    var currentTableNum: Int = 0
    val limit: Int = 1000
    while (currentTableNum < tableNum) {
      val tableName: String = tableList.get(currentTableNum)
      // Copy into MySQL table
      val RowCount: Int = MSSQLUtil.getRowCount(MSSQLConnString, tableName)
      if(RowCount <= 0) {
        throw new IOException("Table " + tableName + " is empty.")
      }
      if(RowCount > limit) {
        var parsedCount = 0
        while (parsedCount <= RowCount) {

          val rs: ResultSet = MSSQLUtil.getTable(
            MSSQLConnString,
            tableName,
            "BUSINESSTIME",
            parsedCount,
            limit)
          try {
            var data: String = ""
            if(rs.next()) {
              data += NationalDebt.getData(rs)
            }
            while ((rs.next)) {
              // Collect data
              data += ","
              data += NationalDebt.getData(rs)
            }
            MySQLUtil.insertNationalDebt(MySQLConnString, MySQLuser, MySQLkey, targetNationalDebtTableName, data)
            parsedCount += limit
            print("page:" + parsedCount + "\r\n")
          }
          catch {
            case e: SQLException => {
              e.printStackTrace
            }
          } finally {
            //      LOG.info("Number of lines: " + lineNumber)
          }
        }
      } else {

        val rs: ResultSet = MSSQLUtil.getTable(
          MSSQLConnString,
          tableName,
          "BUSINESSTIME",
          0,
          limit)
        try {
          var data: String = null
          if(rs.next()) {
            data += NationalDebt.getData(rs)
          }
          while ((rs.next)) {
            // Collect data
            data += ","
            data += NationalDebt.getData(rs)
          }
          MySQLUtil.insertNationalDebt(MySQLConnString, MySQLuser, MySQLkey, tableName, data)
        }
        catch {
          case e: SQLException => {
            e.printStackTrace
          }
        } finally {
          //      LOG.info("Number of lines: " + lineNumber)
        }
      }
      currentTableNum += 1
    }
    print("Finish!\r\n")
  }


  @throws(classOf[IOException])
  def convertCommodityFuture() {

    // Get MSSQL tables
    val tableList: util.ArrayList[String] = MSSQLUtil.getTableList(MSSQLConnString)

    // Convert tables in table listh

    val tableNum: Int = tableList.size()
    var currentTableNum: Int = 0
    val limit: Int = 1000
    while (currentTableNum < tableNum) {
      val tableName: String = tableList.get(currentTableNum)
      // Copy into MySQL table
      val RowCount: Int = MSSQLUtil.getRowCount(MSSQLConnString, tableName)
      if(RowCount <= 0) {
        throw new IOException("Table " + tableName + " is empty.")
      }
      val targetContractID: String = MSSQLUtil.getContractID(MSSQLConnString, tableName)
      val targetTableName: String = CommodityFuture.whichTable(targetContractID)
      if(targetTableName != null) {
        if(RowCount > limit) {
          var parsedCount = 0
          while (parsedCount <= RowCount) {

            val rs: ResultSet = MSSQLUtil.getTable(
              MSSQLConnString,
              tableName,
              "TDATETIME",
              parsedCount,
              limit)
            try {
              var data: String = ""
              if(rs.next()) {
                data += CommodityFuture.getData(rs)
              }
              while ((rs.next)) {
                // Collect data
                data += ","
                data += CommodityFuture.getData(rs)
              }
              MySQLUtil.insertCommodityFuture(MySQLConnString, MySQLuser, MySQLkey, targetTableName, data)
              parsedCount += limit
              print("page:" + parsedCount + "\r\n")
            }
            catch {
              case e: SQLException => {
                e.printStackTrace
              }
            } finally {
              //      LOG.info("Number of lines: " + lineNumber)
            }
          }
        } else {

          val rs: ResultSet = MSSQLUtil.getTable(
            MSSQLConnString,
            tableName,
            "TDATETIME",
            0,
            limit)
          try {
            var data: String = null
            if(rs.next()) {
              data += CommodityFuture.getData(rs)
            }
            while ((rs.next)) {
              // Collect data
              data += ","
              data += CommodityFuture.getData(rs)
            }
            MySQLUtil.insertCommodityFuture(MySQLConnString, MySQLuser, MySQLkey, targetTableName, data)
          }
          catch {
            case e: SQLException => {
              e.printStackTrace
            }
          } finally {
            //      LOG.info("Number of lines: " + lineNumber)
          }
        }
      }
      currentTableNum += 1
    }
    print("Finish!\r\n")
  }

  def main(args: Array[String]) {
    try {
      MSSQL2MySQLUtils.convertCommodityFuture()
    }
    catch {
      case e: IOException => {
        e.printStackTrace
      }
    }
  }

//  @throws(classOf[IOException])
//  def convertMSSQL2MySQL(connString: String, tableName: String, columnName: String, startNum: Int, limitNum: Int) {
//  }
//  def main(args: Array[String]) {
//    try {
//      MysqlConvertUtils.convertSQLToParquet()
//    }
//    catch {
//      case e: IOException => {
//        e.printStackTrace
//      }
//    }
//  }
}
