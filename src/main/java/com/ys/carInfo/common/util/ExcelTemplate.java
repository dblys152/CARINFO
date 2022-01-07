package com.ys.carInfo.common.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelTemplate {
	
	SXSSFWorkbook workbook;

	public ExcelTemplate(SXSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public CellStyle getWarnStyle() {
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeight((short) 340);
		headerFont.setFontName("맑은 고딕");
		CellStyle warnStyle = workbook.createCellStyle();
		warnStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		warnStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		warnStyle.setFont(headerFont);
		warnStyle.setBorderLeft(BorderStyle.THIN);
		warnStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		warnStyle.setBorderRight(BorderStyle.THIN);
		warnStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		warnStyle.setBorderTop(BorderStyle.THIN);
		warnStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		warnStyle.setBorderBottom(BorderStyle.THIN);
		warnStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		warnStyle.setAlignment(HorizontalAlignment.LEFT);
		warnStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		warnStyle.setWrapText(true);

		return warnStyle;
	}
	
	
	public CellStyle getHeaderStyle() {
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeight((short) 170);
		headerFont.setFontName("맑은 고딕");
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFont(headerFont);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		headerStyle.setWrapText(true);

		return headerStyle;
	}

	public CellStyle getGnrlStyle(String color, String formatStr, HorizontalAlignment hAlign) {
		Font font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight((short) 170);
		font.setFontName("맑은 고딕");
		CellStyle gnrlStyle = workbook.createCellStyle();
		gnrlStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		gnrlStyle.setFont(font);
		gnrlStyle.setBorderLeft(BorderStyle.THIN);
		gnrlStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		gnrlStyle.setBorderRight(BorderStyle.THIN);
		gnrlStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		gnrlStyle.setBorderTop(BorderStyle.THIN);
		gnrlStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		gnrlStyle.setBorderBottom(BorderStyle.THIN);
		gnrlStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		gnrlStyle.setWrapText(true);
		gnrlStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		

		if(color != null) {
			if(color.equals("light_green")) {
				gnrlStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			} else if(color.equals("gray")) {
				gnrlStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			} else if(color.equals("search")) {
				gnrlStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
			}
			gnrlStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		} else {
			gnrlStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
			gnrlStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			gnrlStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		}
		if(formatStr != null) {
			DataFormat format = workbook.createDataFormat();
			gnrlStyle.setDataFormat(format.getFormat(formatStr));
		}
		if(hAlign != null) {
			if( hAlign.equals(HorizontalAlignment.LEFT) ) {
				gnrlStyle.setAlignment(HorizontalAlignment.LEFT);
			} else if( hAlign.equals(HorizontalAlignment.RIGHT) ) {
				gnrlStyle.setAlignment(HorizontalAlignment.RIGHT);
			} else if( hAlign.equals(HorizontalAlignment.CENTER) ) {
				gnrlStyle.setAlignment(HorizontalAlignment.CENTER);
			}
		}

		return gnrlStyle;
	}

	public void setNumberCell(Row row, int colNum, CellStyle style, Integer val){
		Cell cell = row.createCell(colNum, CellType.NUMERIC);
		cell.setCellStyle(style);
		if(val != null){
			cell.setCellValue(val);
		}
	}

	public void setPercentCell(Row row, int colNum, CellStyle style, Double val){
		Cell cell = row.createCell(colNum, CellType.NUMERIC);

		cell.setCellStyle(style);
		if(val != null){
			cell.setCellValue(val*0.01);
		}
	}

	public void setStringCell(Row row, int colNum, CellStyle style, String val){
		Cell cell = row.createCell(colNum, CellType.STRING);
		cell.setCellStyle(style);
		cell.setCellValue(val);
	}

	public void setDoubleCell(Row row, int colNum, CellStyle style, Double val){
		Cell cell = row.createCell(colNum, CellType.NUMERIC);
		cell.setCellStyle(style);
		if(val != null){
			cell.setCellValue(val);
		}
	}
}
