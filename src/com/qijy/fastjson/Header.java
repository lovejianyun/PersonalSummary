package com.qijy.fastjson;



/**
 * 监控报文头信息
 * @author chenk
 * @time 2015-9-8 10:50:51
 */
public class Header implements Cloneable {
	//主机ID
	@Redis(showflag=true,targetName="HOSTID")
	private int hostId;
	//程序ID
	@Redis(showflag=true,targetName="PROCEID")
	private int proceId;
	//应用程序类型
	@Redis(showflag=true,targetName="APPTYPE")
	private int appType;
	//产品类型
	@Redis(showflag=true,targetName="PROTYPE")
	private int proType;
	//事件ID
	@Redis(showflag=true,targetName="EVENTID")
	private int eventId;
	//事件类型
	@Redis(showflag=true,targetName="EVENTTYPE")
	private int eventType;
	//事件时间
	@Redis(showflag=true,targetName="EVTTIME")
	private String evtTime;
	//事件体
	//客户端上传报文时间
	@Redis(showflag=true,targetName="UPLOADTM")
	private String uploadtm;
	//监控实体产生报文时间
	@Redis(showflag=true,targetName="CREATETM")
	private String createtm;	
	//服务端生产报文时间
	@Redis(showflag=true,targetName="EVT_SERCTTM")
	private String evt_sercttm;
	//事件解析程序读取报文的时间
	@Redis(showflag=true,targetName="EVT_JXSTTM")
	private String evt_jxsttm;
	//版本号
	@Redis(showflag=true,targetName="VERSION")
	private String version;
	
	public Header(){}
	
	/**
	 * @param hostId 主机ID
	 * @param appId  程序ID
	 * @param appType 主机类型
	 * @param eventId 事件ID
	 * @param eventType 事件类型
	 * @param evtTime 事件时间，时间格式为：yyyy-MM-dd HH:mm:ss.SSS
	 * @param evtContent 具体的报文体
	 */
	public Header(int hostId, int proceId, int appType, int eventId,
			int eventType, String evtTime) {
		super();
		this.hostId = hostId;
		this.proceId = proceId;
		this.appType = appType;
		this.eventId = eventId;
		this.eventType = eventType;
		this.evtTime = evtTime;
	}
	
	public Header(int hostId, int proceId, int appType, int proType, int eventId,
			int eventType, String evtTime,String uploadtm, String createtm,String evt_sercttm,String evt_jxsttm) {
		super();
		this.hostId = hostId;
		this.proceId = proceId;
		this.appType = appType;
		this.proType = proType;
		this.eventId = eventId;
		this.eventType = eventType;
		this.evtTime = evtTime;
		this.uploadtm = uploadtm;
		this.createtm = createtm;
		this.evt_sercttm = evt_sercttm;
		this.evt_jxsttm = evt_jxsttm;
	}

	
	
	/**
	 * @return the hostId
	 */
	public int getHostId() {
		return hostId;
	}


	/**
	 * @param hostId the hostId to set
	 */
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	/**
	 * @return the appId
	 */

	
	/**
	 * @return the appType
	 */
	public int getAppType() {
		return appType;
	}
	public int getProceId() {
		return proceId;
	}

	public int getProType() {
		return proType;
	}

	public void setProType(int proType) {
		this.proType = proType;
	}

	public void setProceId(int proceId) {
		this.proceId = proceId;
	}

	/**
	 * @param appType the appType to set
	 */
	public void setAppType(int appType) {
		this.appType = appType;
	}
	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the eventType
	 */
	public int getEventType() {
		return eventType;
	}
	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return the evtTime
	 */
	public String getEvtTime() {
		return evtTime;
	}
	/**
	 * @param 事件时间，时间格式为：yyyy-MM-dd HH:mm:ss.SSS
	 */
	public void setEvtTime(String evtTime) {
		this.evtTime = evtTime;
	}


	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getUploadtm() {
		return uploadtm;
	}

	public void setUploadtm(String uploadtm) {
		this.uploadtm = uploadtm;
	}

	public String getCreatetm() {
		return createtm;
	}

	public void setCreatetm(String createtm) {
		this.createtm = createtm;
	}

	public String getEvt_sercttm() {
		return evt_sercttm;
	}

	public void setEvt_sercttm(String evt_sercttm) {
		this.evt_sercttm = evt_sercttm;
	}

	public String getEvt_jxsttm() {
		return evt_jxsttm;
	}

	public void setEvt_jxsttm(String evt_jxsttm) {
		this.evt_jxsttm = evt_jxsttm;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	

	@Override
	public String toString() {
		return "Header [hostId=" + hostId + ", proceId=" + proceId
				+ ", appType=" + appType + ", proType=" + proType
				+ ", eventId=" + eventId + ", eventType=" + eventType
				+ ", evtTime=" + evtTime + ", uploadtm=" + uploadtm
				+ ", createtm=" + createtm + ", evt_sercttm=" + evt_sercttm
				+ ", evt_jxsttm=" + evt_jxsttm + ", version=" + version + "]";
	}

	public Header copy(){
		Header header = new Header(hostId, proceId, appType, proType, eventId, eventType, evtTime,uploadtm, createtm, evt_sercttm, evt_jxsttm);
		return header;
	}

}

