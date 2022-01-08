package com.ys.carInfo.common.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFPicture;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ys.carInfo.common.service.ExcelService;
import com.ys.carInfo.common.util.ExcelTemplate;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService {

	@Override
	public SXSSFWorkbook createExcelMnf(String sheetNm, List<Map<String, Object>> mnfList) throws Exception {

		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		ExcelTemplate excelTemplate = new ExcelTemplate(workbook);

		workbook.setCompressTempFiles(true);
		Sheet sheet = workbook.createSheet(sheetNm);

		int rowNum = 0;

		String[] columns = {"제조사 로고", "제조사명", "제조국", "등록일"};

		CellStyle headerStyle = excelTemplate.getHeaderStyle();

		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);

		int colSize = columns.length;
		Row headerRow = sheet.createRow(rowNum++);
		headerRow.setHeight((short) 500);
		for(int i = 0; i < colSize; i++) {
			Cell headerCell = headerRow.createCell(i);
			headerCell.setCellValue(columns[i]);
			headerCell.setCellStyle(headerStyle);
		}

		int mnfSize = mnfList.size();
		for(int i = 0; i < mnfSize; i++) {
			Row row = sheet.createRow(rowNum++);
			row.setHeight((short) 1200);
			Cell cell = row.createCell(0);
			if(mnfList.get(i).get("fileNo") == null) {
				cell.setCellValue("");
			} else {
				String fileFullPath = String.valueOf(mnfList.get(i).get("filePathNm"))
						+ "\\" + String.valueOf(mnfList.get(i).get("fileNm"))
						+ "." + String.valueOf(mnfList.get(i).get("fileExtNm"));
				InputStream inputStream = new FileInputStream(fileFullPath);
				byte[] bytes = IOUtils.toByteArray(inputStream);
				int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
				inputStream.close();
				SXSSFDrawing drawing = (SXSSFDrawing) sheet.createDrawingPatriarch();
				ClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
				anchor.setCol1(0);
				anchor.setRow1(rowNum - 1);
				anchor.setCol2(1);
				anchor.setRow2(rowNum);

				SXSSFPicture pict = drawing.createPicture(anchor, pictureIdx);
				pict.resize(1.0, 1.0);
			}
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.CENTER));
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(mnfList.get(i).get("mnfNm")));
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.LEFT));
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(mnfList.get(i).get("ntnCdKrNm")) + "(" + String.valueOf(mnfList.get(i).get("ntnCdEnNm")) + ")");
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.CENTER));
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(mnfList.get(i).get("regDt")));
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.CENTER));
		}

		// 메모리 flush
        ((SXSSFSheet) sheet).flushRows(mnfSize);

		return workbook;
	}

}
