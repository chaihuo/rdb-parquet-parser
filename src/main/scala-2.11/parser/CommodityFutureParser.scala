package parser

import java.io.IOException
import java.sql.{SQLException, ResultSet}

import model.CommodityFuture
import mssql.MSSQLUtil
import mysql.MySQLUtil

/**
 * Created by root on 15-9-21.
 */
class CommodityFutureParser(tableName: String, limit: Int) extends Runnable {
  val MSSQLConnString : String = "jdbc:sqlserver://"

  val MySQLConnString: String = "jdbc:mysql://"
  val MySQLuser: String = ""
  val MySQLkey: String = ""
  val tn: String = tableName
  val l: Int = 1000

  // Constructor
  def this(tableName : String, limit : String) = {
    this(tn, l);
  }

  override def run(): Unit = {
    val RowCount: Int = MSSQLUtil.getRowCount(MSSQLConnString, tn)
    if(RowCount <= 0) {
      throw new IOException("Table " + tn + " is empty.")
    }
    val targetContractID: String = MSSQLUtil.getContractID(MSSQLConnString, tn)
    val targetTableName: String = CommodityFuture.whichTable(targetContractID)
    if(targetTableName != null) {
      if(RowCount > l) {
        var parsedCount = 0
        while (parsedCount <= RowCount) {

          val rs: ResultSet = MSSQLUtil.getTable(
            MSSQLConnString,
            tn,
            "TDATETIME",
            parsedCount,
            l)
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
            parsedCount += l
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
          tn,
          "TDATETIME",
          0,
          l)
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
  }

  
}
