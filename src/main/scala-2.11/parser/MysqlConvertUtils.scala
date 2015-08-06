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
//  val CSV_DELIMITER: String = "|"
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
      Utils.closeQuietly(reader)
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

//  @throws(classOf[IOException])
//  def convertParquetToCSV(parquetFile: File, csvOutputFile: File) {
//    Preconditions.checkArgument(parquetFile.getName.endsWith(".parquet"), "parquet file should have .parquet extension")
//    Preconditions.checkArgument(csvOutputFile.getName.endsWith(".csv"), "csv file should have .csv extension")
//    Preconditions.checkArgument(!csvOutputFile.exists, "Output file " + csvOutputFile.getAbsolutePath + " already exists")
////    LOG.info("Converting " + parquetFile.getName + " to " + csvOutputFile.getName)
//    val parquetFilePath: Path = new Path(parquetFile.toURI)
//    val configuration: Configuration = new Configuration(true)
//    val readSupport: GroupReadSupport = new GroupReadSupport
//    val readFooter: ParquetMetadata = ParquetFileReader.readFooter(configuration, parquetFilePath)
//    val schema: MessageType = readFooter.getFileMetaData.getSchema
//    readSupport.init(configuration, null, schema)
//    val w: BufferedWriter = new BufferedWriter(new FileWriter(csvOutputFile))
//    val reader: ParquetReader[Group] = new ParquetReader[Group](parquetFilePath, readSupport)
//    try {
//      var g: Group = null
//      while ((({
//        g = reader.read; g
//      })) != null) {
//        writeGroup(w, g, schema)
//      }
//      reader.close
//    } finally {
//      Utils.closeQuietly(w)
//    }
//  }

//  @throws(classOf[IOException])
//  private def writeGroup(w: BufferedWriter, g: Group, schema: MessageType) {
//    {
//      var j: Int = 0
//      while (j < schema.getFieldCount) {
//        {
//          if (j > 0) {
//            w.write(CSV_DELIMITER)
//          }
//          val valueToString: String = g.getValueToString(j, 0)
//          w.write(valueToString)
//        }
//        ({
//          j += 1; j - 1
//        })
//      }
//    }
//    w.write('\n')
//  }

//  @deprecated
//  @throws(classOf[IOException])
////  def convertParquetToCSVEx(parquetFile: File, csvOutputFile: File) {
//    Preconditions.checkArgument(parquetFile.getName.endsWith(".parquet"), "parquet file should have .parquet extension")
//    Preconditions.checkArgument(csvOutputFile.getName.endsWith(".csv"), "csv file should have .csv extension")
//    Preconditions.checkArgument(!csvOutputFile.exists, "Output file " + csvOutputFile.getAbsolutePath + " already exists")
//    LOG.info("Converting " + parquetFile.getName + " to " + csvOutputFile.getName)
//    val parquetFilePath: Path = new Path(parquetFile.toURI)
//    val configuration: Configuration = new Configuration(true)
//    val readFooter: ParquetMetadata = ParquetFileReader.readFooter(configuration, parquetFilePath)
//    val schema: MessageType = readFooter.getFileMetaData.getSchema
//    val parquetFileReader: ParquetFileReader = new ParquetFileReader(configuration, parquetFilePath, readFooter.getBlocks, schema.getColumns)
//    val w: BufferedWriter = new BufferedWriter(new FileWriter(csvOutputFile))
//    var pages: PageReadStore = null
//    try {
//      while (null != (({
//        pages = parquetFileReader.readNextRowGroup; pages
//      }))) {
//        val rows: Long = pages.getRowCount
//        LOG.info("Number of rows: " + rows)
//        val columnIO: MessageColumnIO = new ColumnIOFactory().getColumnIO(schema)
//        val recordReader: RecordReader[Group] = columnIO.getRecordReader(pages, new GroupRecordConverter(schema))
//        {
//          var i: Int = 0
//          while (i < rows) {
//            {
//              val g: Group = recordReader.read
//              writeGroup(w, g, schema)
//            }
//            ({
//              i += 1; i - 1
//            })
//          }
//        }
//      }
//    } finally {
//      Utils.closeQuietly(parquetFileReader)
//      Utils.closeQuietly(w)
//    }
//  }
}
