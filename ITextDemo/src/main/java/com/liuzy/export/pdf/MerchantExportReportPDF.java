package com.liuzy.export.pdf;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * 商户用户导出报表-基本11列
 * 
 * @author liuzy
 * @version 1.0
 * @since 2015年4月28日
 */
public class MerchantExportReportPDF extends ExportReportPDF implements IExportReportPDF {
	/** 列头 */
	public String[] colTitles = { "交易日期", "交易时间", "终端号", "分店名称", "交易金额", "凭证号", "交易类型", "交易卡号", "发卡行名称", "参考号", "身份证号" };
	/** 列头宽比例 */
	public int[] colWidths = { 10, 10, 10, 12, 12, 10, 10, 15, 10, 12, 15 };

	public MerchantExportReportPDF(Map<String, Object> datas) {
		super(datas);
		super.displayName = "商户名称：" + datas.get("merchantName");
		super.displayId = "商户号：" + datas.get("merchantId");
		setColTitles(this.colTitles);
		setColWidths(this.colWidths);
	}

	@Override
	public ByteArrayOutputStream createReport() {
		return super.createReport();
	}

	public static ByteArrayOutputStream getPdfStream(Map<String, Object> datas) {
		return new MerchantExportReportPDF(datas).createReport();
	}
}
