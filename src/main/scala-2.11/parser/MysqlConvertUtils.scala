package parser

import java.io._
import java.sql.{ResultSet, SQLException}
import java.text.SimpleDateFormat
import java.util.Arrays

import mysql.MySQLUtil
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.parquet.Preconditions
import org.apache.parquet.example.data.Group
import org.apache.parquet.hadoop.example.GroupReadSupport
import org.apache.parquet.hadoop.metadata.ParquetMetadata
import org.apache.parquet.hadoop.{ParquetFileReader, ParquetReader}
import org.apache.parquet.schema.{MessageType, MessageTypeParser}
import writesupport.MysqlParquetWriter

/**
 * Created by root on 15-7-30.
 */
object MysqlConvertUtils {
//  private val LOG: Log = Log.getLog(classOf[MysqlConvertUtils])
  val LIMIT = 1000

  @throws(classOf[IOException])
  private def readFile(path: String): String = {
    val reader: BufferedReader = new BufferedReader(new FileReader(path))
    val stringBuilder: StringBuilder = new StringBuilder
    try {
      var line: String = null
      val ls: String = System.getProperty("line.separator")
      while ((({
        line = reader.readLine; line
      })) != null) {
        stringBuilder.append(line)
        stringBuilder.append(ls)
      }
    } finally {
      CloseUtils.closeQuietly(reader)
    }
    return stringBuilder.toString
  }

  @throws(classOf[IOException])
  def getSchema(schemaFile: File): String = {
    return readFile(schemaFile.getAbsolutePath)
  }

  @throws(classOf[IOException])
  def convertSQLToParquet(schemaFile: File, outputParquetFile: File) {
    convertSQLToParquet(schemaFile, outputParquetFile, false)
  }

  @throws(classOf[IOException])
  def convertSQLToParquet(schemaFile: File, outputParquetFile: File, enableDictionary: Boolean) {
//    LOG.info("Converting " + schemaFile.getName + " to " + outputParquetFile.getName)
    val rawSchema: String = getSchema(schemaFile)
    if (outputParquetFile.exists) {
      throw new IOException("Output file " + outputParquetFile.getAbsolutePath + " already exists")
    }
    val path: Path = new Path(outputParquetFile.toURI)
    val schema: MessageType = MessageTypeParser.parseMessageType(rawSchema)
    val writer: MysqlParquetWriter = new MysqlParquetWriter(path, schema, enableDictionary)
    // Repeat get data set from MySQL, data set size defined in json file
    val RowCount = MySQLUtil.getRowCount();
    if(RowCount <= 0) {
      throw new IOException("Table " + "parquet_copy" + " is empty.")
    }
    if(RowCount > LIMIT) {
      var parsedCount = 0
      while (parsedCount <= RowCount) {
        // Get data set limited by LIMIT
        // Then write into parquet file
        val rs: ResultSet = MySQLUtil.getTable(parsedCount, LIMIT)
        val line: String = null
        //    var lineNumber: Int = 0
        try {
          while ((rs.next)) {
            val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            writer.write(Arrays.asList(String.valueOf(rs.getInt("id")), rs.getString("content"), String.valueOf(rs.getFloat("price")), String.valueOf(rs.getTimestamp("time").getTime)))
            //        lineNumber += 1
          }
          parsedCount += LIMIT
        }
        catch {
          case e: SQLException => {
            e.printStackTrace
          }
        } finally {
          //      LOG.info("Number of lines: " + lineNumber)
        }
      }
      writer.close
    } else {

      val rs: ResultSet = MySQLUtil.getTable()
      val line: String = null
      //    var lineNumber: Int = 0
      try {
        while ((rs.next)) {
          val df: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
          writer.write(Arrays.asList(String.valueOf(rs.getInt("id")), rs.getString("content"), String.valueOf(rs.getFloat("price")), String.valueOf(rs.getTimestamp("time").getTime)))
          //        lineNumber += 1
        }
        writer.close
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
