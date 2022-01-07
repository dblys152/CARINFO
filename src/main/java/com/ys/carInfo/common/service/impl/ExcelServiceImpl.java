package com.ys.carInfo.common.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
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
		int mnfSize = mnfList.size();
		
		CellStyle headerStyle = excelTemplate.getHeaderStyle();
		
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 5000);
		
		Row headerRow = sheet.createRow(rowNum++);
		Cell headerCell = headerRow.createCell(0);
		headerCell.setCellValue("제조사 로고");
		headerCell.setCellStyle(headerStyle);
		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("제조사명");
		headerCell.setCellStyle(headerStyle);
		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("제조국");
		headerCell.setCellStyle(headerStyle);
		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("등록일");
		headerCell.setCellStyle(headerStyle);

		for(int i = 0; i < mnfSize; i++) {
			Row row = sheet.createRow(rowNum++);
			Cell cell = row.createCell(0);
			cell.setCellValue("");
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.CENTER));
			cell = row.createCell(1);
			cell.setCellValue(String.valueOf(mnfList.get(i).get("mnfNm")));
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.LEFT));
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(mnfList.get(i).get("ntnCdKrNm")));
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
