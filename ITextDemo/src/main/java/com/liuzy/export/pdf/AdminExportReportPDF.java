package com.liuzy.export.pdf;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * 管理员导出报表,包括"机构号","商户号","终端号"...
 * 
 * @author liuzy
 * @version 10.0
 * @since 2015年4月28日
 */
public class AdminExportReportPDF extends ExportReportPDF implements IExportReportPDF {
	/** 列头 */
	public String[] colTitles = { "交易日期", "交易时间", "机构号", "商户号", "终端号", "分店名称", "交易金额", "凭证号", "交易类型", "交易卡号", "发卡行名称", "参考号", "身份证号" };
	/** 列头宽比例 */
	public int[] colWidths = { 10, 10, 12, 14, 10, 12, 10, 10, 10, 16, 10, 10, 15 };

	public AdminExportReportPDF(Map<String, Object> datas) {
		super(datas);
		super.displayName = "管理员名称：" + datas.get("adminName");
		super.displayId = "管理员编号：" + datas.get("adminId");
		super.setColTitles(this.colTitles);
		super.setColWidths(this.colWidths);
	}

	@Override
	public ByteArrayOutputStream createReport() {
		return super.createReport();
	}

	public static ByteArrayOutputStream getPdfStream(Map<String, Object> datas) {
		return new AdminExportReportPDF(datas).createReport();
	}
}
