package parser

import java.io.IOException
import java.sql.{ResultSet, SQLException}

import model.{NationalDebt, CommodityFuture}
import mssql.MSSQLUtil
import mysql.MySQLUtil

/**
 * Created by root on 15-9-21.
 */
class TFL2Parser(MSSQLConnString: String, MSSQLTableName: String, MySQLConnString: String, MySQLuser: String, MySQLkey: String, limit: Int) extends Runnable {

  override def run(): Unit = {
    val RowCount: Int = MSSQLUtil.getRowCount(MSSQLConnString, MSSQLTableName)
    if(RowCount <= 0) {
      print(MSSQLConnString + "\r\n")
      print(MSSQLTableName)
      throw new IOException("Table " + MSSQLTableName + " is empty.")
    }
    val targetSymbolID: String = MSSQLUtil.getSymbol(MSSQLConnString, MSSQLTableName)
    val targetTableName: String = NationalDebt.whichTable(targetSymbolID)
    if(targetTableName != null) {
      if(RowCount > limit) {
        var parsedCount = 0
        while (parsedCount <= RowCount) {

          val rs: ResultSet = MSSQLUtil.getTable(
            MSSQLConnString,
            MSSQLTableName,
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
            MySQLUtil.insertNationalDebt(MySQLConnString, MySQLuser, MySQLkey, targetTableName, data)
            parsedCount += limit
            print("From table:" + MSSQLTableName + ",page:" + parsedCount + "\r\n")
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
          MSSQLTableName,
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
          print("From table:" + MSSQLTableName + ".\r\n")
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
  }


}