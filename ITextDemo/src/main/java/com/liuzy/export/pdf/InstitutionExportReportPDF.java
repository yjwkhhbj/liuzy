package com.liuzy.export.pdf;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * 机构用户导出报表-12列
 * 
 * @author liuzy
 * @version 1.0
 * @since 2015年4月28日
 */
public class InstitutionExportReportPDF extends ExportReportPDF implements IExportReportPDF {
	/** 列头 */
	public String[] colTitles = { "交易日期", "交易时间", "商户号", "终端号", "分店名称", "交易金额", "凭证号", "交易类型", "交易卡号", "发卡行名称", "参考号", "身份证号" };
	/** 列头宽比例 */
	public int[] colWidths = { 6, 6, 8, 6, 6, 6, 6, 5, 10, 7, 8, 10 };

	public InstitutionExportReportPDF(Map<String, Object> datas) {
		super(datas);
		super.displayName = "机构名称：" + datas.get("institutionName");
		super.displayId = "机构号：" + datas.get("institutionId");
		super.setColTitles(this.colTitles);
		super.setColWidths(this.colWidths);
	}

	@Override
	public ByteArrayOutputStream createReport() {
		return super.createReport();
	}

	public static ByteArrayOutputStream getPdfStream(Map<String, Object> datas) {
		return new InstitutionExportReportPDF(datas).createReport();
	}
}
