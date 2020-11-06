package com.qijy.fastjson;



/**
 * 仅包含通用的信息头的抽象类，方便后续所有子类的继承
 * @author Administrator
 *
 */
public class AbstractMonData implements Cloneable{
	
//	private static final Logger LOG = Logger.getLogger(AbstractMonData.class);
	
	//消息头
	private Header header;

	/**初始化Header部分
	 * @param header
	 */
	public AbstractMonData(Header header) {
		this.header = header;
	}

	/**
	 * 空的初始化方法，后续必须通过SET方法设置Header属性
	 */
	public AbstractMonData() {
	}

	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(Header header) {
		this.header = header;
	}

	@Override
	public String toString() {
		return "AbstractMonData [header=" + header + "]";
	}
	
	/**
	 * 实现深拷贝功能，所有继承此父类的子类都能调用并且实现深拷贝功能。
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	public AbstractMonData copy() throws CloneNotSupportedException{
		try {
			return (AbstractMonData) super.clone();
		} catch (CloneNotSupportedException e) {
//			LOG.error("com.mon3x.analysis.model.AbstractMonData没有继承java.lang.Cloneable接口，不能进行深拷贝",e);
			throw e; 
		}
	}
	
}
