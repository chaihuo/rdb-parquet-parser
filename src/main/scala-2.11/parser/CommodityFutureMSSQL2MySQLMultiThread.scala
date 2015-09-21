package parser

import java.io._
import java.sql.{ResultSet, SQLException}
import java.util
import java.util.concurrent.{TimeUnit, ThreadPoolExecutor, ArrayBlockingQueue}

import model.{CommodityFuture, NationalDebt}
import mssql.MSSQLUtil
import mysql.MySQLUtil

/**
 * Created by root on 15-7-30.
 */
object CommodityFutureMSSQL2MySQLMultiThread{
//  log class
  
  val MSSQLConnString : String = "jdbc:sqlserver://"
  
  val MySQLConnString: String = "jdbc:mysql://"
  val MySQLuser: String = ""
  val MySQLkey: String = ""

  def main(args: Array[String]) {

    val blockingQueue: ArrayBlockingQueue[Runnable] = new ArrayBlockingQueue[Runnable](50)
    val executor: ThreadPoolExecutor = new ThreadPoolExecutor(5, 10, 2, TimeUnit.DAYS, blockingQueue)// All threads wait for 2 days max
    try {
      // Get MSSQL tables
      val tableList: util.ArrayList[String] = MSSQLUtil.getTableList(MSSQLConnString)

      // Convert tables in table listh

      val tableNum: Int = tableList.size()
      var currentTableNum: Int = 0
      val limit: Int = 1000
      while (currentTableNum < tableNum) {
        val tableName: String = tableList.get(currentTableNum)
        // Copy into MySQL table

        if(tableName != null) {
          val commodityFutureParser = new CommodityFutureParser(tableName, 10000)
          executor.execute(commodityFutureParser)
          print(
            "Pool size：" + executor.getPoolSize()
              + "，queue size：" + executor.getQueue().size()
              + "，Completed task count：" + executor.getCompletedTaskCount()
              + ".\r\n"
          )
        }
        currentTableNum += 1
      }
    }
    catch {
      case e: IOException => {
        e.printStackTrace
      }
    }
    executor.shutdown()
    print("Finish!\r\n")
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
