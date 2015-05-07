package com.liuzy.export.pdf;

import java.io.ByteArrayOutputStream;

/**
 * 导出PDF报表公共接口
 * 
 * @author liuzy
 * @version 2015年4月29日
 */
public interface IExportReportPDF {

	/**
	 * 导出报表文件的用户
	 */
	public static enum UserType {
		ADMINISTRATOR, INSTITUTION, MERCHANT
	}

	/**
	 * 导出报表文件数据流
	 */
	public ByteArrayOutputStream createReport();
}
