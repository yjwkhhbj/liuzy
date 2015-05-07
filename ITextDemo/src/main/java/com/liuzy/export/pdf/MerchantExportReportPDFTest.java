package com.liuzy.export.pdf;

import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * 商户用户导出报表-所有15列
 * 
 * @author liuzy
 * @version 1.0
 * @since 2015年4月28日
 */
public class MerchantExportReportPDFTest extends ExportReportPDF implements IExportReportPDF {
	/** 列头 */
	public String[] colTitles = { "清算日期", "交易日期", "交易时间", "终端号", "分店名称", "交易金额", "结算金额", "手续费", "凭证号", "交易类型", "卡号", "卡种", "发卡行名称", "参考号", "身份证号" };
	/** 列头宽比例 */
	public int[] colWidths = { 6, 6, 6, 6, 8, 8, 8, 6, 6, 6, 10, 5, 7, 8, 6 };

	public MerchantExportReportPDFTest(Map<String, Object> datas) {
		super(datas);
		super.displayName = "商户名称：" + datas.get("merchantName");
		super.displayId = "商户号：" + datas.get("merchantId");
		super.setColTitles(this.colTitles);
		super.setColWidths(this.colWidths);
	}

	@Override
	public ByteArrayOutputStream createReport() {
		return super.createReport();
	}

	public static ByteArrayOutputStream getPdfStream(Map<String, Object> datas) {
		return new MerchantExportReportPDFTest(datas).createReport();
	}
}
