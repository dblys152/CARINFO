package com.ys.carInfo.common.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFPicture;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ys.carInfo.common.service.ExcelService;
import com.ys.carInfo.common.util.ExcelTemplate;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService {

	@Override
	public SXSSFWorkbook createListExcel(String sheetNm, String[] columns, List<Map<String, Object>> mnfList) throws Exception {

		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		ExcelTemplate excelTemplate = new ExcelTemplate(workbook);

		workbook.setCompressTempFiles(true);
		Sheet sheet = workbook.createSheet(sheetNm);

		CellStyle headerStyle = excelTemplate.getHeaderStyle();

		int rowNum = 0;

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

	@Override
	public JsonArray parsingMnfExcel(MultipartFile excel) throws Exception {
		Workbook workbook = WorkbookFactory.create(excel.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);

		List<List<String>> list = new ArrayList<>();

		int rowSize = sheet.getPhysicalNumberOfRows(); // 행개수
		if(rowSize >= 3) {
			for(int i = 2; i < rowSize; i++) {
				Row row = sheet.getRow(i);
				List<String> dataList = new ArrayList<>();
				for(int j = 0; j < 3; j++) {
					if(row.getCell(j) == null)
						dataList.add("");
					else
						dataList.add(String.valueOf(getValueFromCell(row.getCell(j))));
				}
				list.add(dataList);
			}
		}

		JsonArray excelList = null;
		if(list != null && list.size() > 0) {
			Gson gson = new Gson();
			JsonElement element = gson.toJsonTree(list, new TypeToken<ArrayList<String>>(){}.getType());
			System.out.println(element.toString());
			JsonObject jsonObj = element.getAsJsonObject();
			System.out.println(jsonObj.toString());
		}

		return excelList;
	}

	private static Object getValueFromCell(Cell cell) {
		if(cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
		else if(cell.getCellType() == CellType.BOOLEAN) return cell.getBooleanCellValue();
		else if(cell.getCellType() == CellType.NUMERIC) return NumberToTextConverter.toText(cell.getNumericCellValue());
		else if(cell.getCellType() == CellType.FORMULA) return cell.getCellFormula();
		else if(cell.getCellType() == CellType.BLANK) return "";
		else return "";
	}
}
