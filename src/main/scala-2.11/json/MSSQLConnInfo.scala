package json

/**
 * Created by root on 15-8-6.
 */
class MSSQLConnInfo {
  private var MSSQL_HOST: String = null
  private var MSSQL_PORT: String = null
  private var MSSQL_DB_NAME: String = null
  private var MSSQL_USER: String = null
  private var MSSQL_PWD: String = null


  def getMSsqlHost: String = {
    return MSSQL_HOST
  }

  def setMSsqlHost(mssqlHost: String) {
    MSSQL_HOST = mssqlHost
  }

  def getMSsqlPort: String = {
    return MSSQL_PORT
  }

  def setMSsqlPort(mssqlPort: String) {
    MSSQL_PORT = mssqlPort
  }

  def getMSsqlDbName: String = {
    return MSSQL_DB_NAME
  }

  def setMSsqlDbName(mssqlDbName: String) {
    MSSQL_DB_NAME = mssqlDbName
  }

  def getMSsqlUser: String = {
    return MSSQL_USER
  }

  def setMSsqlUser(mssqlUser: String) {
    MSSQL_USER = mssqlUser
  }

  def getMSsqlPwd: String = {
    return MSSQL_PWD
  }

  def setMSsqlPwd(mssqlPwd: String) {
    MSSQL_PWD = mssqlPwd
  }

  def getConnString: String =  {
    //    return "jdbc:mssql://" + MSSQL_HOST + ":" + MSSQL_PORT + "/" + MSSQL_DB_NAME + "?user=" + MSSQL_USER + "&password=" + MSSQL_PWD
    return "jdbc:sqlserver://" + MSSQL_HOST + ":" + MSSQL_PORT + ";databaseName=" + MSSQL_DB_NAME + ";user=" + MSSQL_USER + ";password=" + MSSQL_PWD
  }

  def isValid(): Boolean = {
    if(MSSQL_HOST != null
      && MSSQL_PORT != null
      && MSSQL_DB_NAME != null
      && MSSQL_USER != null
      && MSSQL_PWD != null) {
      return true
    } else {
      throw new IllegalArgumentException("Configuration file has missing some objects")
    }
  }
}
