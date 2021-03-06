//package parser
//
//import java.io._
//import java.sql.{ResultSet, SQLException}
//import java.text.SimpleDateFormat
//import java.util.Arrays
//
//import json.{ParquetInfoJsonUtil, ParserInfo}
//import model.ParquetTable
//import mysql.MySQLUtil
//import org.apache.hadoop.fs.Path
//import org.apache.parquet.schema.{MessageType, MessageTypeParser}
//import writesupport.MysqlParquetWriter
//
///**
// * Created by root on 15-7-30.
// */
//object MysqlConvertUtils {
////  log class
//  val pi: ParserInfo = ParquetInfoJsonUtil.getInfo()
//
//  @throws(classOf[IOException])
//  private def readSchemaFile(path: String): String = {
//    val file: File = new File(path)
//    if(file.isDirectory) {
//      throw new FileNotFoundException("Is a directory")
//    }
//    if(!file.exists()) {
//      throw new FileNotFoundException("Cannot find schema file")
//    }
//    val reader: BufferedReader = new BufferedReader(new FileReader(path))
//    val stringBuilder: StringBuilder = new StringBuilder
//    try {
//      var line: String = null
//      val ls: String = System.getProperty("line.separator")
//      while ((({
//        line = reader.readLine; line
//      })) != null) {
//        stringBuilder.append(line)
//        stringBuilder.append(ls)
//      }
//    } finally {
//      CloseUtils.closeQuietly(reader)
//    }
//    return stringBuilder.toString
//  }
//
//  @throws(classOf[IOException])
//  def getSchema(schemaFile: File): String = {
//    return readSchemaFile(schemaFile.getAbsolutePath)
//  }
//
//  @throws(classOf[IOException])
//  def convertSQLToParquet() {
//    pi.isValid()
//    val schemaFile: File = new File(pi.getSchemaFilePath)
//    val outputParquetFile: File = new File(pi.getOutputPath)
//    convertSQLToParquet(schemaFile, outputParquetFile, false)
//  }
//
//  @throws(classOf[IOException])
//  def convertSQLToParquet(schemaFile: File, outputParquetFile: File, enableDictionary: Boolean) {
////    LOG.info("Converting " + schemaFile.getName + " to " + outputParquetFile.getName)
//
//    val rawSchema: String = getSchema(schemaFile)
//    if (outputParquetFile.exists) {
//      throw new IOException("Output file " + outputParquetFile.getAbsolutePath + " already exists")
//    }
//    val path: Path = new Path(outputParquetFile.toURI)
//    val schema: MessageType = MessageTypeParser.parseMessageType(rawSchema)
//    val writer: MysqlParquetWriter = new MysqlParquetWriter(path, schema, enableDictionary)
//    // Repeat get data set from MySQL, data set size defined in json file
//    val RowCount = MySQLUtil.getRowCount(pi.getConnString, pi.getMysqlTable);
//    if(RowCount <= 0) {
//      throw new IOException("Table " + pi.getMysqlTable + " is empty.")
//    }
//    if(RowCount > pi.getDataSetLimit) {
//      var parsedCount = 0
//      while (parsedCount <= RowCount) {
//        // Get data set limited by LIMIT
//        // Then write into parquet file
//        val rs: ResultSet = MySQLUtil.getTable(
//          pi.getConnString,
//          pi.getMysqlTable,
//          pi.getMysqlPrimaryColumn,
//          parsedCount,
//          pi.getDataSetLimit)
//        //    var lineNumber: Int = 0
//        try {
//          while ((rs.next)) {
////            val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//            writer.write(ParquetTable.tableList(rs))
//            //        lineNumber += 1
//            println(rs.getInt("id"))
//          }
//          parsedCount += pi.getDataSetLimit
//        }
//        catch {
//          case e: SQLException => {
//            e.printStackTrace
//          }
//        } finally {
//          //      LOG.info("Number of lines: " + lineNumber)
//        }
//      }
//      writer.close
//    } else {
//
//      val rs: ResultSet = MySQLUtil.getTable(
//        pi.getConnString,
//        pi.getMysqlTable,
//        pi.getMysqlPrimaryColumn)
//      val line: String = null
//      //    var lineNumber: Int = 0
//      try {
//        while ((rs.next)) {
//          val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//          writer.write(Arrays.asList(String.valueOf(rs.getInt("id")), rs.getString("content"), String.valueOf(rs.getFloat("price")), String.valueOf(rs.getTimestamp("time").getTime)))
//          //        lineNumber += 1
//        }
//        writer.close
//      }
//      catch {
//        case e: SQLException => {
//          e.printStackTrace
//        }
//      } finally {
//        //      LOG.info("Number of lines: " + lineNumber)
//      }
//    }
//
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
//}
