package com.liuzy.export;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liuzy.export.pdf.ExportReportPDFFactory;
import com.liuzy.export.pdf.IExportReportPDF;
import com.liuzy.export.pdf.IExportReportPDF.UserType;

/**
 * 报表导出测试
 * 
 * @author liuzy
 * @version 2015年4月29日
 */
public class TestExport {

	public static void main(String[] args) {
		UserType userType = UserType.MERCHANT;
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("title", "对账明细查询结果（仅显示1000条记录）");
		datas.put("adminName", "admin");
		datas.put("adminId", "1000000");
		datas.put("institutionName", "中国银联");
		datas.put("institutionId", "1000000");
		datas.put("merchantName", "王琪");
		datas.put("merchantId", "123456789001");
		datas.put("startDate", "2015-01-01");
		datas.put("endDate", "2015-04-28");
		datas.put("businesType", "代付业务");
		datas.put("dataList", createDatas(userType));//表数据
		datas.put("totalNumber", "123");// 总笔数
		datas.put("transAmount", "13,542,154.00");// 交易金额
		datas.put("settlementAmount", "315,461.00");// 结算金额
		datas.put("poundageAmount", "2,145.00");// 手续费
		IExportReportPDF exportReport = ExportReportPDFFactory.newInstance(userType, datas);
		exportReport.createReport();
		System.out.println("文件已生成至D盘！");
	}
	
	public static List<String[]> createDatas(UserType userType) {
		if (UserType.ADMINISTRATOR.equals(userType)) {
			return createAdminDatas();
		} else if (UserType.INSTITUTION.equals(userType)) {
			return createInstitutionDatas();
		} else if (UserType.MERCHANT.equals(userType)) {
			return createMerchantDatas();
		}
		return null;
	}
	
	/**
	 * 生成商户用户数据
	 * 11列:"交易日期", "交易时间", "终端号", "分店名称", "交易金额", "凭证号", "交易类型", "交易卡号", "发卡行名称", "参考号", "身份证号"
	 */
	public static List<String[]> createMerchantDatas() {
		List<String[]> dataList = new ArrayList<String[]>();
		for (int rowCount = 0; rowCount < 25; rowCount++) {
			String[] row = { "2015-01-02", "10:10:44", "12345601", "支付研究院", "45,414.00", "123445", "代付", "622799*********6223", "中国银行", "123452114522", "085613" };
			dataList.add(row);
		}
		return dataList;
	}
	
	/**
	 * 生成机构用户数据
	 * 12列:"交易日期", "交易时间", "商户号", "终端号", "分店名称", "交易金额", "凭证号", "交易类型", "交易卡号", "发卡行名称", "参考号", "身份证号"
	 */
	public static List<String[]> createInstitutionDatas() {
		List<String[]> dataList = new ArrayList<String[]>();
		for (int rowCount = 0; rowCount < 45; rowCount++) {
			String[] row = { "2015-01-02", "10:10:44", "1234567890001", "12345601", "支付研究院", "45,414.00", "123445", "代付", "622799*********6223", "中国银行", "123452114522", "085613" };
			dataList.add(row);
		}
		return dataList;
	}
	
	/**
	 * 生成管理员用户数据
	 * 13列:"交易日期", "交易时间", "机构号", "商户号", "终端号", "分店名称", "交易金额", "凭证号", "交易类型", "交易卡号", "发卡行名称", "参考号", "身份证号"
	 */
	public static List<String[]> createAdminDatas() {
		List<String[]> dataList = new ArrayList<String[]>();
		for (int rowCount = 0; rowCount < 55; rowCount++) {
			String[] row = { "2015-01-02", "10:10:44", "10000000", "1234567890001", "12345601", "支付研究院", "45,414.00", "123445", "代付", "622799*********6223", "中国银行", "123452114522", "085613" };
			dataList.add(row);
		}
		return dataList;
	}
}
