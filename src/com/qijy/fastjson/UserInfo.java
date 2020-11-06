package com.qijy.fastjson;
import com.google.gson.annotations.SerializedName;


/**
 * 3.2.1. 1500：用户周期信息（接入网关送）<br/>
 * 对应的数据库表：
 * @author chenk
 * @time 2015-9-17 09;//45;//53
 *
 */
public class UserInfo extends AbstractMonData implements Cloneable {
	@Redis(showflag=true)
	@SerializedName(value = "USERID")
	private String userid;//"用户账号1",
	@Redis(showflag=true)
	@SerializedName(value = "JTYPE")
	private int jtype;//"所使用的协议类型(整型)",
	@Redis(showflag=true)
	@SerializedName(value = "LINKNUM")
	private int linknum;//"连接数（整型）",
	@Redis(showflag=true,targetName="LOGINIP")
	@SerializedName(value = "IP")
	private String ip;//"登陆使用的ip地址信息(字符串),如：192.169.1.130;//8082",
	@Redis(showflag=true,targetName="ONLINESTATUS")
	@SerializedName(value = "STATUS")
	private int status;//"用户的状态(整型 0;//在线 1;//离线 其他暂视为未知)" ,
	@Redis(showflag=true,targetName="LOGININTM")
	@SerializedName(value = "INTM")
	private String intm;//"登陆时间(字符串),如：2012-10-26 15;//30;//50",
	@Redis(showflag=true,targetName="LOGINOUTTM")
	@SerializedName(value = "OUTTM")
	private String outtm;//"离线时间(字符串),如：2012-10-26 15;//50;//50",
	@Redis(showflag=true,targetName="LASTVST")
	@SerializedName(value = "LASTVST")
	private String lastvst;//”最近访问时间，如：2012-10-26 15;//50;//50”,
	@Redis(showflag=true,targetName="OUTCNT")
	@SerializedName(value = "OUTCNT")
	private int outcnt;//”账号当天离线次数（整形）”,
	@Redis(showflag=true)
	@SerializedName(value = "FEEFLAG")
	private int feeflag;//"费率类型(整型),1;//预付 2;//后付",
	@Redis(showflag=true,targetName="USERFEE")
	@SerializedName(value = "FEE")
	private int fee;//"国内短信余额(整型)", 
	@Redis(showflag=true,targetName="USERFEEGLB")
	@SerializedName(value = "GLBFEE")
	private int glbfee;//"国际短信余额(整型)",
	@Redis(showflag=true,targetName="SUCCRATE")
	@SerializedName(value = "SUCCRATE")
	private int succrate;//"成功率(整型)" ,
	@Redis(showflag=true,targetName="MTTOTALSND")
	@SerializedName(value = "MT")
	private int mt;//"mt接交总数(整型)" ,
	@Redis(showflag=true,targetName="MTHAVESND")
	@SerializedName(value = "MTSD")
	private int mtsd;//"mt转发量(整型)",
	@Redis(showflag=true,targetName="MTREMAINED")
	@SerializedName(value = "MTNOSD")
	private int mtnosd;//"mt滞留量(整型)" ,
	@Redis(showflag=true,targetName="MTSNDSPD")
	@SerializedName(value = "MTSPD")
	private int mtspd;//"mt提交速度(整型)" ,
	@Redis(showflag=true,targetName="MTMINDLY")
	@SerializedName(value = "MTMINDLY")
	private int mtmindly;//"用户提交mt请求最近1分钟的最小延时值(整型,单位;//毫秒)" ,
	@Redis(showflag=true,targetName="MTMAXDLY")
	@SerializedName(value = "MTMAXDLY")
	private int mtmaxdly;//"用户提交mt请求最近1分钟的最大延时值(整型,单位;//毫秒)" ,
	@Redis(showflag=true,targetName="MTAVGDLY")
	@SerializedName(value = "MTAVGDLY")
	private int mtavgdly;//"用户提交mt请求最近1分钟的平均延时值(整型,单位;//毫秒)" ,
	@Redis(showflag=true,targetName="MOTOTAL")
	@SerializedName(value = "MO")
	private int mo;//"mo总量(整型)" ,
	@Redis(showflag=true,targetName="MOTOTALRECV")
	@SerializedName(value = "MORV")
	private int morv;//"mo已接收量(整型)" ,
	@Redis(showflag=true,targetName="MOREMAINED")
	@SerializedName(value = "MONORV")
	private int monorv;//"mo滞留量(整型)" ,
	@Redis(showflag=true,targetName="MORPTRECVSPD")
	@SerializedName(value = "MOSPD")
	private int mospd;//"mo接收速度(整型)" ,
	@Redis(showflag=true,targetName="RPTTOTAL")
	@SerializedName(value = "RPT")
	private int rpt;//"rpt总量(整型)" ,
	@Redis(showflag=true,targetName="RPTTOTALRECV")
	@SerializedName(value = "RPTRV")
	private int rptrv;//"rpt已接收量(整型)" ,
	@Redis(showflag=true,targetName="RPTREMAINED")
	@SerializedName(value = "RPTNORV")
	private int rptnorv;//"rpt滞留量(整型)" ,
	@Redis(showflag=true,targetName="RPTSNDSPD")
	@SerializedName(value = "RPTSPD")
	private int rptspd;//"rpt接收速度(整型)",
	@SerializedName(value = "MTLVLNOSD")
	private String mtlvlnosd;//"按级0-9级以字符串的格式,输出MT滞留量,如;//0/1/2/3/4/5/6/7/8/9"
	private int moRate;//账号上行转换率
	private int rptReturnRate;//状态报告返回率
	
    /******************报文中新增字段***********************/
	@Redis(showflag=true,targetName="MTMEMNOSD")
	private int mtmemnosd; //内存中mt滞留量(整型)
	@Redis(showflag=true,targetName="MTFILENOSD")
	private int mtfilenosd;//文件中mt滞留量(整型)
	@Redis(showflag=true,targetName="MTVFYNOSD")
	private int mtvfynosd; //审核表中mt滞留量(整型)
	@Redis(showflag=true,targetName="MTLVL0NOSD")
	private int mtlvl0nosd; //lvl表中mt滞留量(整型)
	@Redis(showflag=true,targetName="MOMEMNORV")
	private int momemnorv;  //mo滞留量(整型)
	@Redis(showflag=true,targetName="MOFILENORV")
	private int mofilenorv; //mo滞留量(整型)
	@Redis(showflag=true,targetName="MODBNORV")
	private int modbnorv;   //mo滞留量(整型)
	@Redis(showflag=true,targetName="RPTMEMNORV")
	private int rptmemnorv; //rpt内存滞留量(整型)
	@Redis(showflag=true,targetName="RPTFILENORV")
	private int rptfilenorv; //rpt文件滞留量(整型)
	@Redis(showflag=true,targetName="RPTDBNORV")
	private int rptdbnorv;   //rpt数据库滞留量(整型)
	@Redis(showflag=true,targetName="DTRPT")
	private int dtrpt=-1;		//当天的rpt总量
	@Redis(showflag=true,targetName="RPTDTRV")
	private int rptdtrv=-1;	//rpt当天的状态报告已接收量
	@Redis(showflag=true,targetName="RPTDTRVSUCC")
	private int rptdtrvsucc=-1;	//rpt当天的状态报告已接收量
	@Redis(showflag=true,targetName="RPTDTRVFAIL")
	private int rptdtrvfail;	//rpt当天的状态报告已接收量
	@Redis(showflag=true,targetName="OPTRPT")
	private int optrpt;
	@Redis(showflag=true,targetName="OPTDTRPT")
	private int optdtrpt;
	@Redis(showflag=true,targetName="OPTRPTRV")
	private int optrptrv;
	@Redis(showflag=true,targetName="OPTRPTDTRV")
	private int optrptdtrv;
	@Redis(showflag=true,targetName="OPTRPTDTRVSUCC")
	private int optrptdtrvsucc;
	@Redis(showflag=true,targetName="OPTRPTDTRVFAIL")
	private int optrptdtrvfail;
	
	//private String lastGateOutTm;   //集群用户中多个报文最小的时间
	
	
 
	
	
	
	/**
	 * 
	 */
	public UserInfo() {
		super();
	}
	/**
	 * @param header
	 */
	public UserInfo(Header header) {
		super(header);
	}
	
	
	
	public UserInfo(Header header, String userid, int linknum, int status) {
		super(header);
		this.userid = userid;
		this.linknum = linknum;
		this.status = status;
	}
	
	public UserInfo(Header header,String userId ,int mt, int mtsd, int mtnosd, int mtspd,
			int mo, int morv, int monorv, int mospd, int rpt, int rptrv,
			int rptnorv, int rptspd, int mtmemnosd, int mtfilenosd,
			int mtvfynosd, int mtlvl0nosd, int momemnorv, int mofilenorv,
			int modbnorv, int rptmemnorv, int rptfilenorv, int rptdbnorv,
			int dtrpt, int rptdtrv,int rptdtrvsucc,int jType,int linkNum,String ip) {
		super(header);
		this.userid = userId;
		this.mt = mt;
		this.mtsd = mtsd;
		this.mtnosd = mtnosd;
		this.mtspd = mtspd;
		this.mo = mo;
		this.morv = morv;
		this.monorv = monorv;
		this.mospd = mospd;
		this.rpt = rpt;
		this.rptrv = rptrv;
		this.rptnorv = rptnorv;
		this.rptspd = rptspd;
		this.mtmemnosd = mtmemnosd;
		this.mtfilenosd = mtfilenosd;
		this.mtvfynosd = mtvfynosd;
		this.mtlvl0nosd = mtlvl0nosd;
		this.momemnorv = momemnorv;
		this.mofilenorv = mofilenorv;
		this.modbnorv = modbnorv;
		this.rptmemnorv = rptmemnorv;
		this.rptfilenorv = rptfilenorv;
		this.rptdbnorv = rptdbnorv;
		this.dtrpt = dtrpt;
		this.rptdtrv = rptdtrv;
		this.rptdtrvsucc = rptdtrvsucc;
	}
	public UserInfo(Header header, String userid, int jtype, int linknum,
			String ip, int status, String intm, String outtm, String lastvst,
			int outcnt, int feeflag, int fee, int glbfee, int succrate, int mt,
			int mtsd, int mtnosd, int mtspd, int mtmindly, int mtmaxdly,
			int mtavgdly, int mo, int morv, int monorv, int mospd, int rpt,
			int rptrv, int rptnorv, int rptspd, String mtlvlnosd, int moRate,
			int rptReturnRate, int mtmemnosd, int mtfilenosd, int mtvfynosd,
			int mtlvl0nosd, int momemnorv, int mofilenorv, int modbnorv,
			int rptmemnorv, int rptfilenorv, int rptdbnorv, int dtrpt,
			int rptdtrv, int rptdtrvsucc, int rptdtrvfail,int optRpt,int optDtRpt,
			int optRptRv,int optRptDtRv,int optrptdtrvsucc,int optrptdtrvfail) {
		
		super(header);
		this.userid = userid;
		this.jtype = jtype;
		this.linknum = linknum;
		this.ip = ip;
		this.status = status;
		this.intm = intm;
		this.outtm = outtm;
		this.lastvst = lastvst;
		this.outcnt = outcnt;
		this.feeflag = feeflag;
		this.fee = fee;
		this.glbfee = glbfee;
		this.succrate = succrate;
		this.mt = mt;
		this.mtsd = mtsd;
		this.mtnosd = mtnosd;
		this.mtspd = mtspd;
		this.mtmindly = mtmindly;
		this.mtmaxdly = mtmaxdly;
		this.mtavgdly = mtavgdly;
		this.mo = mo;
		this.morv = morv;
		this.monorv = monorv;
		this.mospd = mospd;
		this.rpt = rpt;
		this.rptrv = rptrv;
		this.rptnorv = rptnorv;
		this.rptspd = rptspd;
		this.mtlvlnosd = mtlvlnosd;
		this.moRate = moRate;
		this.rptReturnRate = rptReturnRate;
		this.mtmemnosd = mtmemnosd;
		this.mtfilenosd = mtfilenosd;
		this.mtvfynosd = mtvfynosd;
		this.mtlvl0nosd = mtlvl0nosd;
		this.momemnorv = momemnorv;
		this.mofilenorv = mofilenorv;
		this.modbnorv = modbnorv;
		this.rptmemnorv = rptmemnorv;
		this.rptfilenorv = rptfilenorv;
		this.rptdbnorv = rptdbnorv;
		this.dtrpt = dtrpt;
		this.rptdtrv = rptdtrv;
		this.rptdtrvsucc = rptdtrvsucc;
		this.rptdtrvfail = rptdtrvfail;
		
		this.optrpt = optRpt;
		this.optdtrpt = optDtRpt;
		this.optrptrv = optRptRv;
		this.optrptdtrv = optRptDtRv;
		this.optrptdtrvsucc = optrptdtrvsucc;
		this.optrptdtrvfail = optrptdtrvfail;
		
	}
	
	public UserInfo(Header header, String userid, int jtype, int linknum,
			String ip, int status, String intm, String outtm, String lastvst,
			int outcnt, int feeflag, int fee, int glbfee, int succrate, int mt,
			int mtsd, int mtnosd, int mtspd, int mtmindly, int mtmaxdly,
			int mtavgdly, int mo, int morv, int monorv, int mospd, int rpt,
			int rptrv, int rptnorv, int rptspd, String mtlvlnosd, int moRate,
			int rptReturnRate, int mtmemnosd, int mtfilenosd, int mtvfynosd,
			int mtlvl0nosd, int momemnorv, int mofilenorv, int modbnorv,
			int rptmemnorv, int rptfilenorv, int rptdbnorv, int dtrpt,
			int rptdtrv, int rptdtrvsucc, int rptdtrvfail) {
		
		super(header);
		this.userid = userid;
		this.jtype = jtype;
		this.linknum = linknum;
		this.ip = ip;
		this.status = status;
		this.intm = intm;
		this.outtm = outtm;
		this.lastvst = lastvst;
		this.outcnt = outcnt;
		this.feeflag = feeflag;
		this.fee = fee;
		this.glbfee = glbfee;
		this.succrate = succrate;
		this.mt = mt;
		this.mtsd = mtsd;
		this.mtnosd = mtnosd;
		this.mtspd = mtspd;
		this.mtmindly = mtmindly;
		this.mtmaxdly = mtmaxdly;
		this.mtavgdly = mtavgdly;
		this.mo = mo;
		this.morv = morv;
		this.monorv = monorv;
		this.mospd = mospd;
		this.rpt = rpt;
		this.rptrv = rptrv;
		this.rptnorv = rptnorv;
		this.rptspd = rptspd;
		this.mtlvlnosd = mtlvlnosd;
		this.moRate = moRate;
		this.rptReturnRate = rptReturnRate;
		this.mtmemnosd = mtmemnosd;
		this.mtfilenosd = mtfilenosd;
		this.mtvfynosd = mtvfynosd;
		this.mtlvl0nosd = mtlvl0nosd;
		this.momemnorv = momemnorv;
		this.mofilenorv = mofilenorv;
		this.modbnorv = modbnorv;
		this.rptmemnorv = rptmemnorv;
		this.rptfilenorv = rptfilenorv;
		this.rptdbnorv = rptdbnorv;
		this.dtrpt = dtrpt;
		this.rptdtrv = rptdtrv;
		this.rptdtrvsucc = rptdtrvsucc;
		this.rptdtrvfail = rptdtrvfail;
	}
	
	public int getRptdtrvsucc() {
		return rptdtrvsucc;
	}
	public void setRptdtrvsucc(int rptdtrvsucc) {
		this.rptdtrvsucc = rptdtrvsucc;
	}
	public int getRptdtrvfail() {
		return rptdtrvfail;
	}
	public void setRptdtrvfail(int rptdtrvfail) {
		this.rptdtrvfail = rptdtrvfail;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the jtype
	 */
	public int getJtype() {
		return jtype;
	}
	/**
	 * @param jtype the jtype to set
	 */
	public void setJtype(int jtype) {
		this.jtype = jtype;
	}
	/**
	 * @return the linknum
	 */
	public int getLinknum() {
		return linknum;
	}
	/**
	 * @param linknum the linknum to set
	 */
	public void setLinknum(int linknum) {
		this.linknum = linknum;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the intm
	 */
	public String getIntm() {
		return intm;
	}
	/**
	 * @param intm the intm to set
	 */
	public void setIntm(String intm) {
		this.intm = intm;
	}
	/**
	 * @return the outtm
	 */
	public String getOuttm() {
		return outtm;
	}
	/**
	 * @param outtm the outtm to set
	 */
	public void setOuttm(String outtm) {
		this.outtm = outtm;
	}
	/**
	 * @return the lastvst
	 */
	public String getLastvst() {
		return lastvst;
	}
	/**
	 * @param lastvst the lastvst to set
	 */
	public void setLastvst(String lastvst) {
		this.lastvst = lastvst;
	}
	/**
	 * @return the outcnt
	 */
	public int getOutcnt() {
		return outcnt;
	}
	/**
	 * @param outcnt the outcnt to set
	 */
	public void setOutcnt(int outcnt) {
		this.outcnt = outcnt;
	}
	/**
	 * @return the feeflag
	 */
	public int getFeeflag() {
		return feeflag;
	}
	/**
	 * @param feeflag the feeflag to set
	 */
	public void setFeeflag(int feeflag) {
		this.feeflag = feeflag;
	}
	/**
	 * @return the fee
	 */
	public int getFee() {
		return fee;
	}
	/**
	 * @param fee the fee to set
	 */
	public void setFee(int fee) {
		this.fee = fee;
	}
	/**
	 * @return the glbfee
	 */
	public int getGlbfee() {
		return glbfee;
	}
	/**
	 * @param glbfee the glbfee to set
	 */
	public void setGlbfee(int glbfee) {
		this.glbfee = glbfee;
	}

	public int getSuccrate() {
		return succrate;
	}
	public void setSuccrate(int succrate) {
		this.succrate = succrate;
	}
	/**
	 * @return the mt
	 */
	public int getMt() {
		return mt;
	}
	/**
	 * @param mt the mt to set
	 */
	public void setMt(int mt) {
		this.mt = mt;
	}
	/**
	 * @return the mtsd
	 */
	public int getMtsd() {
		return mtsd;
	}
	/**
	 * @param mtsd the mtsd to set
	 */
	public void setMtsd(int mtsd) {
		this.mtsd = mtsd;
	}
	/**
	 * @return the mtnosd
	 */
	public int getMtnosd() {
		return mtnosd;
	}
	/**
	 * @param mtnosd the mtnosd to set
	 */
	public void setMtnosd(int mtnosd) {
		this.mtnosd = mtnosd;
	}
	/**
	 * @return the mtspd
	 */
	public int getMtspd() {
		return mtspd;
	}
	/**
	 * @param mtspd the mtspd to set
	 */
	public void setMtspd(int mtspd) {
		this.mtspd = mtspd;
	}
	/**
	 * @return the mtmindly
	 */
	public int getMtmindly() {
		return mtmindly;
	}
	/**
	 * @param mtmindly the mtmindly to set
	 */
	public void setMtmindly(int mtmindly) {
		this.mtmindly = mtmindly;
	}
	/**
	 * @return the mtmaxdly
	 */
	public int getMtmaxdly() {
		return mtmaxdly;
	}
	/**
	 * @param mtmaxdly the mtmaxdly to set
	 */
	public void setMtmaxdly(int mtmaxdly) {
		this.mtmaxdly = mtmaxdly;
	}
	/**
	 * @return the mtavgdly
	 */
	public int getMtavgdly() {
		return mtavgdly;
	}
	/**
	 * @param mtavgdly the mtavgdly to set
	 */
	public void setMtavgdly(int mtavgdly) {
		this.mtavgdly = mtavgdly;
	}
	/**
	 * @return the mo
	 */
	public int getMo() {
		return mo;
	}
	/**
	 * @param mo the mo to set
	 */
	public void setMo(int mo) {
		this.mo = mo;
	}
	/**
	 * @return the morv
	 */
	public int getMorv() {
		return morv;
	}
	/**
	 * @param morv the morv to set
	 */
	public void setMorv(int morv) {
		this.morv = morv;
	}
	/**
	 * @return the monorv
	 */
	public int getMonorv() {
		return monorv;
	}
	/**
	 * @param monorv the monorv to set
	 */
	public void setMonorv(int monorv) {
		this.monorv = monorv;
	}
	/**
	 * @return the mospd
	 */
	public int getMospd() {
		return mospd;
	}
	/**
	 * @param mospd the mospd to set
	 */
	public void setMospd(int mospd) {
		this.mospd = mospd;
	}
	/**
	 * @return the rpt
	 */
	public int getRpt() {
		return rpt;
	}
	/**
	 * @param rpt the rpt to set
	 */
	public void setRpt(int rpt) {
		this.rpt = rpt;
	}
	/**
	 * @return the rptrv
	 */
	public int getRptrv() {
		return rptrv;
	}
	/**
	 * @param rptrv the rptrv to set
	 */
	public void setRptrv(int rptrv) {
		this.rptrv = rptrv;
	}
	/**
	 * @return the rptnorv
	 */
	public int getRptnorv() {
		return rptnorv;
	}
	/**
	 * @param rptnorv the rptnorv to set
	 */
	public void setRptnorv(int rptnorv) {
		this.rptnorv = rptnorv;
	}
	/**
	 * @return the rptspd
	 */
	public int getRptspd() {
		return rptspd;
	}
	/**
	 * @param rptspd the rptspd to set
	 */
	public void setRptspd(int rptspd) {
		this.rptspd = rptspd;
	}
	/**
	 * @return the mtlvlnosd
	 */
	public String getMtlvlnosd() {
		return mtlvlnosd;
	}
	/**
	 * @param mtlvlnosd the mtlvlnosd to set
	 */
	public void setMtlvlnosd(String mtlvlnosd) {
		this.mtlvlnosd = mtlvlnosd;
	}
	/**    
	 * moRate    
	 *    
	 * @return  the moRate    
	 * @since   CodingExample Ver(编码范例查看) 1.0    
	 */
	
	public int getMoRate() {
		return moRate;
	}
	/**    
	 * @param moRate the moRate to set    
	 */
	public void setMoRate(int moRate) {
		this.moRate = moRate;
	}
	/**    
	 * rptReturnRate    
	 *    
	 * @return  the rptReturnRate    
	 * @since   CodingExample Ver(编码范例查看) 1.0    
	 */
	
	public int getRptReturnRate() {
		return rptReturnRate;
	}
	/**    
	 * @param rptReturnRate the rptReturnRate to set    
	 */
	public void setRptReturnRate(int rptReturnRate) {
		this.rptReturnRate = rptReturnRate;
	}
	public int getMtmemnosd() {
		return mtmemnosd;
	}
	public void setMtmemnosd(int mtmemnosd) {
		this.mtmemnosd = mtmemnosd;
	}
	public int getMtfilenosd() {
		return mtfilenosd;
	}
	public void setMtfilenosd(int mtfilenosd) {
		this.mtfilenosd = mtfilenosd;
	}
	public int getMtvfynosd() {
		return mtvfynosd;
	}
	public void setMtvfynosd(int mtvfynosd) {
		this.mtvfynosd = mtvfynosd;
	}
	public int getMtlvl0nosd() {
		return mtlvl0nosd;
	}
	public void setMtlvl0nosd(int mtlvl0nosd) {
		this.mtlvl0nosd = mtlvl0nosd;
	}
	public int getMomemnorv() {
		return momemnorv;
	}
	public void setMomemnorv(int momemnorv) {
		this.momemnorv = momemnorv;
	}
	public int getMofilenorv() {
		return mofilenorv;
	}
	public void setMofilenorv(int mofilenorv) {
		this.mofilenorv = mofilenorv;
	}
	public int getModbnorv() {
		return modbnorv;
	}
	public void setModbnorv(int modbnorv) {
		this.modbnorv = modbnorv;
	}
	public int getRptmemnorv() {
		return rptmemnorv;
	}
	public void setRptmemnorv(int rptmemnorv) {
		this.rptmemnorv = rptmemnorv;
	}
	public int getRptfilenorv() {
		return rptfilenorv;
	}
	public void setRptfilenorv(int rptfilenorv) {
		this.rptfilenorv = rptfilenorv;
	}
	public int getRptdbnorv() {
		return rptdbnorv;
	}
	public void setRptdbnorv(int rptdbnorv) {
		this.rptdbnorv = rptdbnorv;
	}
	public int getDtrpt() {
		return dtrpt;
	}
	public void setDtrpt(int dtrpt) {
		this.dtrpt = dtrpt;
	}
	public int getRptdtrv() {
		return rptdtrv;
	}
	public void setRptdtrv(int rptdtrv) {
		this.rptdtrv = rptdtrv;
	}
	/*public String getLastGateOutTm() {
		return lastGateOutTm;
	}
	public void setLastGateOutTm(String lastGateOutTm) {
		this.lastGateOutTm = lastGateOutTm;
	}*/
	public int getOptrpt() {
		return optrpt;
	}
	public void setOptrpt(int optrpt) {
		this.optrpt = optrpt;
	}
	public int getOptdtrpt() {
		return optdtrpt;
	}
	public void setOptdtrpt(int optdtrpt) {
		this.optdtrpt = optdtrpt;
	}
	public int getOptrptrv() {
		return optrptrv;
	}
	public void setOptrptrv(int optrptrv) {
		this.optrptrv = optrptrv;
	}
	public int getOptrptdtrv() {
		return optrptdtrv;
	}
	public void setOptrptdtrv(int optrptdtrv) {
		this.optrptdtrv = optrptdtrv;
	}
	public int getOptrptdtrvsucc() {
		return optrptdtrvsucc;
	}
	public void setOptrptdtrvsucc(int optrptdtrvsucc) {
		this.optrptdtrvsucc = optrptdtrvsucc;
	}
	public int getOptrptdtrvfail() {
		return optrptdtrvfail;
	}
	public void setOptrptdtrvfail(int optrptdtrvfail) {
		this.optrptdtrvfail = optrptdtrvfail;
	}
	/* (non-Javadoc)    
	 * @see java.lang.Object#toString()    
	 */
	@Override
	public String toString() {
		return  this.getHeader().toString()  + "   UserInfo [userid=" + userid + ", jtype=" + jtype + ", linknum="
				+ linknum + ", ip=" + ip + ", status=" + status + ", intm="
				+ intm + ", outtm=" + outtm + ", lastvst=" + lastvst
				+ ", outcnt=" + outcnt + ", feeflag=" + feeflag + ", fee="
				+ fee + ", glbfee=" + glbfee + ", succrate=" + succrate
				+ ", mt=" + mt + ", mtsd=" + mtsd + ", mtnosd=" + mtnosd
				+ ", mtspd=" + mtspd + ", mtmindly=" + mtmindly + ", mtmaxdly="
				+ mtmaxdly + ", mtavgdly=" + mtavgdly + ", mo=" + mo
				+ ", morv=" + morv + ", monorv=" + monorv + ", mospd=" + mospd
				+ ", rpt=" + rpt + ", rptrv=" + rptrv + ", rptnorv=" + rptnorv
				+ ", rptspd=" + rptspd + ", mtlvlnosd=" + mtlvlnosd
				+ ", moRate=" + moRate + ", rptReturnRate=" + rptReturnRate
				+ ", mtmemnosd=" + mtmemnosd + ", mtfilenosd=" + mtfilenosd
				+ ", mtvfynosd=" + mtvfynosd + ", mtlvl0nosd=" + mtlvl0nosd
				+ ", momemnorv=" + momemnorv + ", mofilenorv=" + mofilenorv
				+ ", modbnorv=" + modbnorv + ", rptmemnorv=" + rptmemnorv
				+ ", rptfilenorv=" + rptfilenorv + ", rptdbnorv=" + rptdbnorv
				+ ", dtrpt=" + dtrpt + ", rptdtrv=" + rptdtrv
				+ ", rptdtrvsucc=" + rptdtrvsucc + ", rptdtrvfail="
				+ rptdtrvfail + ", optrpt=" + optrpt + ", optdtrpt=" + optdtrpt
				+ ", optrptrv=" + optrptrv + ", optrptdtrv=" + optrptdtrv
				+ ", optrptdtrvsucc=" + optrptdtrvsucc + ", optrptdtrvfail="
				+ optrptdtrvfail + "]";
	}
	
	
	public String toStatusString() {
		return  this.getHeader().toString()  + "   UserInfo [linkNum"+this.getLinknum() +"  status： "+this.getStatus()+"  OUTTM:"+this.getOuttm()+"]";
	}
	
	/**
	 * 深度克隆当前对象
	 */
	@Override
	public UserInfo clone() throws CloneNotSupportedException {
		UserInfo userInfo = (UserInfo) super.clone();
		Header header = (Header) userInfo.getHeader().clone();
		userInfo.setHeader(header);
		return userInfo;
	}
	
	
	private int mtavgdlyTotal = 0;
	private int mtavgdlyCnt = 0;
	public void addMtavgdly(int mtavgdly){
		mtavgdlyTotal += mtavgdly;
		mtavgdlyCnt++;
	}
	public int getMtavgdlyResult(){
		mtavgdlyTotal += this.mtavgdly;
		mtavgdlyCnt++;
		return mtavgdlyCnt>0?mtavgdlyTotal/mtavgdlyCnt:0;
	}
	
}
