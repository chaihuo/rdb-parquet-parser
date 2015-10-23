package parser.MSSQL2MySQL

import java.io.IOException
import java.util
import java.util.concurrent.ThreadPoolExecutor

import mssql.MSSQLUtil

/**
 * Created by root on 15-9-24.
 */
object TFL2MSSQLParser {
  def databaseParser(executor: ThreadPoolExecutor, MSSQLConnString: String, MySQLConnString: String, MySQLuser: String, MySQLkey: String, limit: Int): Unit = {
    try {
      // Get MSSQL tables
      val tableList: util.ArrayList[String] = MSSQLUtil.getTableList(MSSQLConnString)

      // Convert tables in table list

      val tableNum: Int = tableList.size()
      var currentTableNum: Int = 0
      while (currentTableNum < tableNum) {
        val MSSQLTableName: String = tableList.get(currentTableNum)
        print(MSSQLTableName)
        // Copy into MySQL table

        if(MSSQLTableName != null) {
          val tfL2Parser = new TFL2Parser(MSSQLConnString, MSSQLTableName, MySQLConnString, MySQLuser, MySQLkey, limit)
          executor.execute(tfL2Parser)
          print(
            " Pool size：" + executor.getPoolSize()
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
  }
}
