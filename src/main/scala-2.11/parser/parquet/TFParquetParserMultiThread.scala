package parser.parquet

import java.util
import java.util.concurrent.{LinkedBlockingQueue, ThreadPoolExecutor, TimeUnit}

import json.{ParquetInfoJsonUtil, ParserInfo}
import mysql.MySQLUtil
import parser.MSSQL2MySQL.TFL2MSSQLParser

/**
  * Created by root on 15-7-30.
  */
object TFParquetParserMultiThread{
   //  log class


   def main(args: Array[String]) {

     val linkedQueue: LinkedBlockingQueue[Runnable] = new LinkedBlockingQueue[Runnable]
     val executor: ThreadPoolExecutor = new ThreadPoolExecutor(60, 60, 7, TimeUnit.DAYS, linkedQueue)// All threads wait for 2 days max

     val pi: ParserInfo = ParquetInfoJsonUtil.getInfo()
     val MySQLTableList: util.ArrayList[String] = MySQLUtil.getTableList(pi.getConnString, pi.getMysqlUser, pi.getMysqlPwd)

     for(i <- 0 to MySQLTableList.size()-1) {
//       val parquetParser: MySQL2ParquetParser
       if(MySQLTableList.get(i).equalsIgnoreCase("t") || MySQLTableList.get(i).equalsIgnoreCase("tf")) {
         val parquetParser: MySQLTFData2ParquetParser = new MySQLTFData2ParquetParser(
           pi.getOutputPath, pi.getTFSchemaFilePath, pi.getConnString, pi.getMysqlUser, pi.getMysqlPwd, MySQLTableList.get(i), "BUSINESSTIME,CONTRACTID", pi.getDataSetLimit)
         executor.execute(parquetParser)
         print(
           " Pool size：" + executor.getPoolSize()
             + "，queue size：" + executor.getQueue().size()
             + "，Completed task count：" + executor.getCompletedTaskCount()
             + ".\r\n"
         )
       } else {
         val parquetParser: MySQLCFData2ParquetParser = new MySQLCFData2ParquetParser(
           pi.getOutputPath, pi.getCFSchemaFilePath, pi.getConnString, pi.getMysqlUser, pi.getMysqlPwd, MySQLTableList.get(i), "TDATETIME,CONTRACTID", pi.getDataSetLimit)
         executor.execute(parquetParser)
         print(
           " Pool size：" + executor.getPoolSize()
             + "，queue size：" + executor.getQueue().size()
             + "，Completed task count：" + executor.getCompletedTaskCount()
             + ".\r\n"
         )
       }
     }

     executor.shutdown()
     executor.awaitTermination(7, TimeUnit.DAYS)
     print("Finish!\r\n")
   }

 }
