package com.liuzy.export.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 导出PDF的抽象父类
 * 
 * @author liuzy
 * @version 2015年4月28日
 */
public abstract class ExportReportPDF implements IExportReportPDF {
	
	private Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);

	private BaseFont chinese = null;
	private Font titleFont = null;
	private Font tableHeadFont = null;
	private Font tableCellFont = null;

	protected String title;
	protected String displayName;
	protected String displayId;
	protected String startDate;
	protected String endDate;
	protected String businesType;
	protected String[] colTitles;// 列头
	protected int[] colWidths;// 每列宽度比例
	protected List<String[]> dataList;// 表内容
	protected String totalNumber;// 总笔数
	protected String transAmount;// 交易金额
	protected String settlementAmount;// 结算金额
	protected String poundageAmount;// 手续费

	@SuppressWarnings("unchecked")
	public ExportReportPDF(Map<String, Object> datas) {
		try {
			this.chinese = BaseFont.createFont("/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			this.titleFont = new Font(chinese, 12, Font.NORMAL);
			this.tableHeadFont = new Font(chinese, 10, Font.NORMAL);
			this.tableCellFont = new Font(chinese, 8, Font.NORMAL);

			this.title = (String) datas.get("title");
			this.startDate = (String) datas.get("startDate");
			this.endDate = (String) datas.get("endDate");
			this.businesType = (String) datas.get("businesType");
			this.colTitles = (String[]) datas.get("colTitles");
			this.colWidths = (int[]) datas.get("rowWidths");
			this.dataList = (List<String[]>) datas.get("dataList");
			this.totalNumber = (String) datas.get("totalNumber");
			this.transAmount = (String) datas.get("transAmount");
			this.settlementAmount = (String) datas.get("settlementAmount");
			this.poundageAmount = (String) datas.get("poundageAmount");
		} catch (Exception e) {
			System.out.println("Loading font file error!");
			e.printStackTrace();
		}
	}

	public ByteArrayOutputStream createReport() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			// 非测试请注释下面这行
			PdfWriter.getInstance(document, new FileOutputStream("D:\\IText.pdf"));
			PdfWriter.getInstance(document, bos);
			document.open();
			this.addTitle();// 添加标题
			this.addHeadData();// 添加表列头
			this.addBodyData();// 添加表内容
			this.addFootData();// 添加表尾
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos;
	}

	private void addTitle() throws Exception {
		Paragraph paragraph = new Paragraph(title, titleFont);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setFont(titleFont);
		document.add(paragraph);
		document.add(new Paragraph("\n"));
	}

	private void addHeadData() throws Exception {
		PdfPTable headTable = new PdfPTable(2);
		headTable.setWidths(new int[] { 1, 2 });
		headTable.setWidthPercentage(100);
		headTable.addCell(createCellNoBorder(displayName, Element.ALIGN_LEFT, tableHeadFont));
		headTable.addCell(createCellNoBorder(displayId, Element.ALIGN_LEFT, tableHeadFont));
		headTable.addCell(createCellNoBorder("清算日期：从  " + startDate + "  至  " + endDate, Element.ALIGN_LEFT, tableHeadFont));
		headTable.addCell(createCellNoBorder("业务类型：" + businesType, Element.ALIGN_RIGHT, tableHeadFont));
		document.add(headTable);
	}

	private void addBodyData() throws Exception {
		PdfPTable bodyTable = new PdfPTable(colWidths.length);
		bodyTable.setWidths(colWidths);// 设置每列宽度比例
		bodyTable.setWidthPercentage(100);// 表占页面宽比例
		// 添加列表头
		if (colTitles != null) {
			for (int i = 0; i < colTitles.length; i++) {
				bodyTable.addCell(createCell(colTitles[i], Element.ALIGN_CENTER, tableHeadFont));
			}
		}
		// 添加表内容
		for (String[] row : dataList) {
			for (int i = 0; i < row.length; i++) {
				if (i < 2) {
					bodyTable.addCell(createCell(row[i], Element.ALIGN_CENTER, tableCellFont));
				} else {
					bodyTable.addCell(createCell(row[i], Element.ALIGN_RIGHT, tableCellFont));
				}
			}
		}
		document.add(bodyTable);
	}

	private void addFootData() throws Exception {
		PdfPTable footTable = new PdfPTable(4);
		footTable.setWidthPercentage(100);
		footTable.addCell(createCellNoBorder("总笔数：" + totalNumber, Element.ALIGN_LEFT, tableHeadFont));
		footTable.addCell(createCellNoBorder("交易金额：" + transAmount, Element.ALIGN_LEFT, tableHeadFont));
		footTable.addCell(createCellNoBorder("结算金额：" + settlementAmount, Element.ALIGN_LEFT, tableHeadFont));
		footTable.addCell(createCellNoBorder("手续费：" + poundageAmount, Element.ALIGN_RIGHT, tableHeadFont));
		document.add(footTable);
	}

	private PdfPCell createCell(String context, int alignType, Font font) throws BadElementException {
		PdfPCell cell = new PdfPCell(new Phrase(context, font));
		cell.setHorizontalAlignment(alignType);
		return cell;
	}

	private PdfPCell createCellNoBorder(String context, int alignType, Font font) throws BadElementException {
		PdfPCell cell = new PdfPCell(new Phrase(context, font));
		cell.setBorder(0);
		cell.setHorizontalAlignment(alignType);
		return cell;
	}

	protected void setColTitles(String[] colTitles) {
		this.colTitles = colTitles;
	}

	protected void setColWidths(int[] colWidths) {
		this.colWidths = colWidths;
	}

}
