package com.liuzy.export.pdf;

import java.util.Map;

import com.liuzy.export.pdf.IExportReportPDF.UserType;

public class ExportReportPDFFactory {

	private static IExportReportPDF exportReportPDF;

	private ExportReportPDFFactory() {

	}

	public static IExportReportPDF newInstance(UserType userType, Map<String, Object> datas) {
		if (UserType.ADMINISTRATOR.equals(userType)) {
			exportReportPDF = new AdminExportReportPDF(datas);
		} else if (UserType.INSTITUTION.equals(userType)) {
			exportReportPDF = new InstitutionExportReportPDF(datas);
		} else if (UserType.MERCHANT.equals(userType)) {
			exportReportPDF = new MerchantExportReportPDF(datas);
		}
		return exportReportPDF;
	}
}
