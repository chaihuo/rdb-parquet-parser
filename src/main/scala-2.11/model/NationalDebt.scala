package model

import java.math.{BigDecimal, BigInteger}
import java.util.Date

/**
 * Created by root on 15-9-19.
 */
class NationalDebt {
  private var SYMBOL: String = null
  private var BUSINESSTIME: Date = null
  private var OPENPRICE: BigDecimal = null
  private var LASTPRICE: BigDecimal = null
  private var HIGHPRICE: BigDecimal = null
  private var LOWPRICE: BigDecimal = null
  private var SETTLEPRICE: BigDecimal = null
  private var PRESETTLE: BigDecimal = null
  private var CLOSEPRICE: BigDecimal = null
  private var PRECLOSE: BigDecimal = null
  private var CQ: BigDecimal = null
  private var VOLUME: BigDecimal = null
  private var CM: BigDecimal = null
  private var AMOUNT: BigDecimal = null
  private var PREPOSITION: BigDecimal = null
  private var POSITION: BigDecimal = null
  private var POSITIONCHANGE: BigDecimal = null
  private var LIMITUP: BigDecimal = null
  private var LIMITDOWN: BigDecimal = null
  private var SIDE: Int = 0
  private var OC: Int = 0
  private var B01: BigDecimal = null
  private var B02: BigDecimal = null
  private var B03: BigDecimal = null
  private var B04: BigDecimal = null
  private var B05: BigDecimal = null
  private var S01: BigDecimal = null
  private var S02: BigDecimal = null
  private var S03: BigDecimal = null
  private var S04: BigDecimal = null
  private var S05: BigDecimal = null
  private var BV01: BigDecimal = null
  private var BV02: BigDecimal = null
  private var BV03: BigDecimal = null
  private var BV04: BigDecimal = null
  private var BV05: BigDecimal = null
  private var SV01: BigDecimal = null
  private var SV02: BigDecimal = null
  private var SV03: BigDecimal = null
  private var SV04: BigDecimal = null
  private var SV05: BigDecimal = null
  private var CURRDELTA: BigDecimal = null
  private var PREDELTA: BigDecimal = null
  private var SETTLEMENTGROUPID: Int = 0
  private var SETTLEMENTID: Int = 0
  private var CHANGE: BigDecimal = null
  private var CHANGERATIO: BigDecimal = null
  private var CONTINUESIGN: Int = 0
  private var TRADINGDATE: Date = null
  private var LOCALTIME: Date = null
  private var RECTIME: Date = null
  private var EXCHANGECODE: String = null
  private var ID: Int = 0
  private var UNIX: Long = 0

  def getSYMBOL: String = {
    return SYMBOL
  }

  def setSYMBOL(SYMBOL: String) {
    this.SYMBOL = SYMBOL
  }

  def getBUSINESSTIME: Date = {
    return BUSINESSTIME
  }

  def setBUSINESSTIME(BUSINESSTIME: Date) {
    this.BUSINESSTIME = BUSINESSTIME
  }

  def getOPENPRICE: BigDecimal = {
    return OPENPRICE
  }

  def setOPENPRICE(OPENPRICE: BigDecimal) {
    this.OPENPRICE = OPENPRICE
  }

  def getLASTPRICE: BigDecimal = {
    return LASTPRICE
  }

  def setLASTPRICE(LASTPRICE: BigDecimal) {
    this.LASTPRICE = LASTPRICE
  }

  def getHIGHPRICE: BigDecimal = {
    return HIGHPRICE
  }

  def setHIGHPRICE(HIGHPRICE: BigDecimal) {
    this.HIGHPRICE = HIGHPRICE
  }

  def getLOWPRICE: BigDecimal = {
    return LOWPRICE
  }

  def setLOWPRICE(LOWPRICE: BigDecimal) {
    this.LOWPRICE = LOWPRICE
  }

  def getSETTLEPRICE: BigDecimal = {
    return SETTLEPRICE
  }

  def setSETTLEPRICE(SETTLEPRICE: BigDecimal) {
    this.SETTLEPRICE = SETTLEPRICE
  }

  def getPRESETTLE: BigDecimal = {
    return PRESETTLE
  }

  def setPRESETTLE(PRESETTLE: BigDecimal) {
    this.PRESETTLE = PRESETTLE
  }

  def getCLOSEPRICE: BigDecimal = {
    return CLOSEPRICE
  }

  def setCLOSEPRICE(CLOSEPRICE: BigDecimal) {
    this.CLOSEPRICE = CLOSEPRICE
  }

  def getPRECLOSE: BigDecimal = {
    return PRECLOSE
  }

  def setPRECLOSE(PRECLOSE: BigDecimal) {
    this.PRECLOSE = PRECLOSE
  }

  def getCQ: BigDecimal = {
    return CQ
  }

  def setCQ(CQ: BigDecimal) {
    this.CQ = CQ
  }

  def getVOLUME: BigDecimal = {
    return VOLUME
  }

  def setVOLUME(VOLUME: BigDecimal) {
    this.VOLUME = VOLUME
  }

  def getCM: BigDecimal = {
    return CM
  }

  def setCM(CM: BigDecimal) {
    this.CM = CM
  }

  def getAMOUNT: BigDecimal = {
    return AMOUNT
  }

  def setAMOUNT(AMOUNT: BigDecimal) {
    this.AMOUNT = AMOUNT
  }

  def getPREPOSITION: BigDecimal = {
    return PREPOSITION
  }

  def setPREPOSITION(PREPOSITION: BigDecimal) {
    this.PREPOSITION = PREPOSITION
  }

  def getPOSITION: BigDecimal = {
    return POSITION
  }

  def setPOSITION(POSITION: BigDecimal) {
    this.POSITION = POSITION
  }

  def getPOSITIONCHANGE: BigDecimal = {
    return POSITIONCHANGE
  }

  def setPOSITIONCHANGE(POSITIONCHANGE: BigDecimal) {
    this.POSITIONCHANGE = POSITIONCHANGE
  }

  def getLIMITUP: BigDecimal = {
    return LIMITUP
  }

  def setLIMITUP(LIMITUP: BigDecimal) {
    this.LIMITUP = LIMITUP
  }

  def getLIMITDOWN: BigDecimal = {
    return LIMITDOWN
  }

  def setLIMITDOWN(LIMITDOWN: BigDecimal) {
    this.LIMITDOWN = LIMITDOWN
  }

  def getSIDE: Int = {
    return SIDE
  }

  def setSIDE(SIDE: Int) {
    this.SIDE = SIDE
  }

  def getOC: Int = {
    return OC
  }

  def setOC(OC: Int) {
    this.OC = OC
  }

  def getB01: BigDecimal = {
    return B01
  }

  def setB01(b01: BigDecimal) {
    B01 = b01
  }

  def getB02: BigDecimal = {
    return B02
  }

  def setB02(b02: BigDecimal) {
    B02 = b02
  }

  def getB03: BigDecimal = {
    return B03
  }

  def setB03(b03: BigDecimal) {
    B03 = b03
  }

  def getB04: BigDecimal = {
    return B04
  }

  def setB04(b04: BigDecimal) {
    B04 = b04
  }

  def getB05: BigDecimal = {
    return B05
  }

  def setB05(b05: BigDecimal) {
    B05 = b05
  }

  def getS01: BigDecimal = {
    return S01
  }

  def setS01(s01: BigDecimal) {
    S01 = s01
  }

  def getS02: BigDecimal = {
    return S02
  }

  def setS02(s02: BigDecimal) {
    S02 = s02
  }

  def getS03: BigDecimal = {
    return S03
  }

  def setS03(s03: BigDecimal) {
    S03 = s03
  }

  def getS04: BigDecimal = {
    return S04
  }

  def setS04(s04: BigDecimal) {
    S04 = s04
  }

  def getS05: BigDecimal = {
    return S05
  }

  def setS05(s05: BigDecimal) {
    S05 = s05
  }

  def getBV01: BigDecimal = {
    return BV01
  }

  def setBV01(BV01: BigDecimal) {
    this.BV01 = BV01
  }

  def getBV02: BigDecimal = {
    return BV02
  }

  def setBV02(BV02: BigDecimal) {
    this.BV02 = BV02
  }

  def getBV03: BigDecimal = {
    return BV03
  }

  def setBV03(BV03: BigDecimal) {
    this.BV03 = BV03
  }

  def getBV04: BigDecimal = {
    return BV04
  }

  def setBV04(BV04: BigDecimal) {
    this.BV04 = BV04
  }

  def getBV05: BigDecimal = {
    return BV05
  }

  def setBV05(BV05: BigDecimal) {
    this.BV05 = BV05
  }

  def getSV01: BigDecimal = {
    return SV01
  }

  def setSV01(SV01: BigDecimal) {
    this.SV01 = SV01
  }

  def getSV02: BigDecimal = {
    return SV02
  }

  def setSV02(SV02: BigDecimal) {
    this.SV02 = SV02
  }

  def getSV03: BigDecimal = {
    return SV03
  }

  def setSV03(SV03: BigDecimal) {
    this.SV03 = SV03
  }

  def getSV04: BigDecimal = {
    return SV04
  }

  def setSV04(SV04: BigDecimal) {
    this.SV04 = SV04
  }

  def getSV05: BigDecimal = {
    return SV05
  }

  def setSV05(SV05: BigDecimal) {
    this.SV05 = SV05
  }

  def getCURRDELTA: BigDecimal = {
    return CURRDELTA
  }

  def setCURRDELTA(CURRDELTA: BigDecimal) {
    this.CURRDELTA = CURRDELTA
  }

  def getPREDELTA: BigDecimal = {
    return PREDELTA
  }

  def setPREDELTA(PREDELTA: BigDecimal) {
    this.PREDELTA = PREDELTA
  }

  def getSETTLEMENTGROUPID: Int = {
    return SETTLEMENTGROUPID
  }

  def setSETTLEMENTGROUPID(SETTLEMENTGROUPID: Int) {
    this.SETTLEMENTGROUPID = SETTLEMENTGROUPID
  }

  def getSETTLEMENTID: Int = {
    return SETTLEMENTID
  }

  def setSETTLEMENTID(SETTLEMENTID: Int) {
    this.SETTLEMENTID = SETTLEMENTID
  }

  def getCHANGE: BigDecimal = {
    return CHANGE
  }

  def setCHANGE(CHANGE: BigDecimal) {
    this.CHANGE = CHANGE
  }

  def getCHANGERATIO: BigDecimal = {
    return CHANGERATIO
  }

  def setCHANGERATIO(CHANGERATIO: BigDecimal) {
    this.CHANGERATIO = CHANGERATIO
  }

  def getCONTINUESIGN: Int = {
    return CONTINUESIGN
  }

  def setCONTINUESIGN(CONTINUESIGN: Int) {
    this.CONTINUESIGN = CONTINUESIGN
  }

  def getTRADINGDATE: Date = {
    return TRADINGDATE
  }

  def setTRADINGDATE(TRADINGDATE: Date) {
    this.TRADINGDATE = TRADINGDATE
  }

  def getLOCALTIME: Date = {
    return LOCALTIME
  }

  def setLOCALTIME(LOCALTIME: Date) {
    this.LOCALTIME = LOCALTIME
  }

  def getRECTIME: Date = {
    return RECTIME
  }

  def setRECTIME(RECTIME: Date) {
    this.RECTIME = RECTIME
  }

  def getEXCHANGECODE: String = {
    return EXCHANGECODE
  }

  def setEXCHANGECODE(EXCHANGECODE: String) {
    this.EXCHANGECODE = EXCHANGECODE
  }

  def getID: Int = {
    return ID
  }

  def setID(ID: Int) {
    this.ID = ID
  }

  def getUNIX: Long = {
    return UNIX
  }

  def setUNIX(UNIX: Long) {
    this.UNIX = UNIX
  }
  }
