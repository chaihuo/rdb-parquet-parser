package json

import java.io.IOException

/**
 * Created by root on 15-8-6.
 */
class MySQLConnInfo {
  private var MYSQL_HOST: String = null
  private var MYSQL_PORT: String = null
  private var MYSQL_DB_NAME: String = null
  private var MYSQL_USER: String = null
  private var MYSQL_PWD: String = null
//  private var MYSQL_TABLE: String = null
  private var MYSQL_PRIMARY_COLUMN: String = null //abandoned
  private var DATA_SET_LIMIT: Integer = 1000
  private var CF_SCHEMA_FILE_PATH: String = null
  private var TF_SCHEMA_FILE_PATH: String = null
  private var OUTPUT_PATH: String = null


  def getMysqlHost: String = {
    return MYSQL_HOST
  }

  def setMysqlHost(mysqlHost: String) {
    MYSQL_HOST = mysqlHost
  }

  def getMysqlPort: String = {
    return MYSQL_PORT
  }

  def setMysqlPort(mysqlPort: String) {
    MYSQL_PORT = mysqlPort
  }

  def getMysqlDbName: String = {
    return MYSQL_DB_NAME
  }

  def setMysqlDbName(mysqlDbName: String) {
    MYSQL_DB_NAME = mysqlDbName
  }

  def getMysqlUser: String = {
    return MYSQL_USER
  }

  def setMysqlUser(mysqlUser: String) {
    MYSQL_USER = mysqlUser
  }

  def getMysqlPwd: String = {
    return MYSQL_PWD
  }

  def setMysqlPwd(mysqlPwd: String) {
    MYSQL_PWD = mysqlPwd
  }

//  def getMysqlTable: String = {
//    return MYSQL_TABLE
//  }
//
//  def setMysqlTable(mysqlTable: String) {
//    MYSQL_TABLE = mysqlTable
//  }

  def getMysqlPrimaryColumn: String = {
    return MYSQL_PRIMARY_COLUMN
  }

  def setMysqlPrimaryColumn(mysqlPrimaryColumn: String) {
    MYSQL_PRIMARY_COLUMN = mysqlPrimaryColumn
  }

  def getDataSetLimit: Integer = {
    return DATA_SET_LIMIT
  }

  def setDataSetLimit(dataSetLimit: Integer) {
    DATA_SET_LIMIT = dataSetLimit
  }


  def getCFSchemaFilePath: String = {
    return CF_SCHEMA_FILE_PATH
  }

  def setCFSchemaFilePath(schemaFilePath: String) {
    CF_SCHEMA_FILE_PATH = schemaFilePath
  }


  def getTFSchemaFilePath: String = {
    return TF_SCHEMA_FILE_PATH
  }

  def setTFSchemaFilePath(schemaFilePath: String) {
    TF_SCHEMA_FILE_PATH = schemaFilePath
  }

  def getOutputPath: String = {
    return OUTPUT_PATH
  }

  def setOutputPath(outputPath: String) {
    OUTPUT_PATH = outputPath
  }

  def getConnStringWithoutAccount: String =  {
//    return "jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DB_NAME + "?user=" + MYSQL_USER + "&password=" + MYSQL_PWD
    return "jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DB_NAME + "?useUnicode=true&characterEncoding=utf8"
  }

  def getConnStringWithAccount: String =  {
    return "jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DB_NAME + "?user=" + MYSQL_USER + "&password=" + MYSQL_PWD
//    return "jdbc:mysql://" + MYSQL_HOST + ":" + MYSQL_PORT + "/" + MYSQL_DB_NAME + "?useUnicode=true&characterEncoding=utf8"
  }

  def isValid(): Boolean = {
    if(MYSQL_HOST != null
      && MYSQL_PORT != null
      && MYSQL_DB_NAME != null
      && MYSQL_USER != null
      && MYSQL_PWD != null
//      && MYSQL_TABLE != null
      && MYSQL_PRIMARY_COLUMN  != null
      && DATA_SET_LIMIT != null
      && CF_SCHEMA_FILE_PATH != null
      && TF_SCHEMA_FILE_PATH != null
      && OUTPUT_PATH != null) {
      if(DATA_SET_LIMIT > 0) {
        return true
      }
      throw new IllegalArgumentException("DATA_SET_LIMIT is invalid")
    } else {
      throw new IllegalArgumentException("Configuration file has missing some objects")
    }
  }
}
