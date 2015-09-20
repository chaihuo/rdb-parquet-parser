package parser

import java.io._
import java.sql.{ResultSet, SQLException}
import java.util

import model.NationalDept
import mssql.MSSQLUtil
import mysql.MySQLUtil

/**
 * Created by root on 15-7-30.
 */
object MSSQL2MySQLUtils {
//  log class
  
  val MSSQLConnString : String = "jdbc:sqlserver://172.19.17.222:1433;databaseName=GTA_TFL2_TAQ_201501;user=sa;password=sa"
  
  val MySQLConnString: String = "jdbc:mysql://qkdata1.cltv2qruve9e.rds.cn-north-1.amazonaws.com.cn:3306/HF_Future?user=hf_app&password='hf_app%2015'"


  @throws(classOf[IOException])
  def convertMSSQL2MySQL() {

    // Get MSSQL tables
    val tableList: util.ArrayList[String] = MSSQLUtil.getTableList(MSSQLConnString)

    // Convert tables in table listh

    val tableNum: Int = tableList.size()
    var currentTableNum: Int = 0
    val limit: Int = 10000
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
              data += NationalDept.getData(rs)
            }
            while ((rs.next)) {
              // Collect data
              data += ","
              data += NationalDept.getData(rs)
            }
            MySQLUtil.insertNationalDebt(MySQLConnString, tableName, data)
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
            data += NationalDept.getData(rs)
          }
          while ((rs.next)) {
            // Collect data
            data += ","
            data += NationalDept.getData(rs)
          }
          MySQLUtil.insertNationalDebt(MySQLConnString, tableName, data)
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
  }


  def main(args: Array[String]) {
    try {
      MSSQL2MySQLUtils.convertMSSQL2MySQL()
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
