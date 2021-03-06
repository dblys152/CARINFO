package com.ys.carInfo.common.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.carInfo.carMdl.vo.MnfVo;
import com.ys.carInfo.common.service.ExcelService;
import com.ys.carInfo.common.service.FileService;
import com.ys.carInfo.common.util.ExcelTemplate;
import com.ys.carInfo.common.vo.FileVo;
import com.ys.global.error.exception.InvalidValueException;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService {

	@Value("${file.updload.path}")
	private	String	rootPath;

	@Value("${file.updload.prefix}")
	private	String	filePrefix;

	@Autowired private FileService fileService;

	@Override
	public SXSSFWorkbook createListExcel(String sheetNm, String[] columns, List<Map<String, Object>> mnfList) throws Exception {

		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		ExcelTemplate excelTemplate = new ExcelTemplate(workbook);

		workbook.setCompressTempFiles(true);
		Sheet sheet = workbook.createSheet(sheetNm);

		CellStyle headerStyle = excelTemplate.getHeaderStyle();

		int rowNum = 0;

		sheet.setColumnWidth(0, 3000);	// ????????? ??????
		sheet.setColumnWidth(1, 8000);	// ????????????
		sheet.setColumnWidth(2, 3000);	// ????????????
		sheet.setColumnWidth(3, 5000);	// ?????????
		sheet.setColumnWidth(4, 5000);	// ?????????

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
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.CENTER));
			cell = row.createCell(2);
			cell.setCellValue(String.valueOf(mnfList.get(i).get("ntnCd")));
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.CENTER));
			cell = row.createCell(3);
			cell.setCellValue(String.valueOf(mnfList.get(i).get("ntnCdKrNm")) + "(" + String.valueOf(mnfList.get(i).get("ntnCdEnNm")) + ")");
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.CENTER));
			cell = row.createCell(4);
			cell.setCellValue(String.valueOf(mnfList.get(i).get("regDt")));
			cell.setCellStyle(excelTemplate.getGnrlStyle(null, null, HorizontalAlignment.CENTER));
		}

		// ????????? flush
        ((SXSSFSheet) sheet).flushRows(mnfSize);

		return workbook;
	}

	@Override
	public String parsingMnfExcel(MultipartFile excel) throws Exception {
		Workbook workbook = WorkbookFactory.create(excel.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);

		int rowSize = sheet.getPhysicalNumberOfRows(); // ?????????
		Integer[] fileNoArr = new Integer[rowSize];
		String[] filePathArr = new String[rowSize];

		XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
		for(XSSFShape shape : drawing.getShapes()) {
            if(shape instanceof XSSFPicture) {
                XSSFPicture xssfPict = (XSSFPicture) shape;
                XSSFPictureData xssfPictData = xssfPict.getPictureData();
                XSSFClientAnchor anchor = (XSSFClientAnchor) shape.getAnchor();
                int row1 = anchor.getRow1();
                int col1 = anchor.getCol1();

                if(row1 >= 1 && col1 == 0) {
                	long bytes = xssfPictData.getData().length;
                	if((bytes / 1024) / 1024 > 1)
        				throw new InvalidValueException("?????? ?????? 1MB??? ?????????????????????.");

                	FileVo fileVo = fileService.uploadExcelImgFile(xssfPictData, "MNF", "100101", "excel");
                	fileNoArr[row1] = fileVo.getFileNo();
                	filePathArr[row1] = fileVo.getFilePathNm()
                			+ "\\" + fileVo.getFileNm()
                			+ "." + fileVo.getFileExtNm();
                }
            }
        }

		List<MnfVo> list = new ArrayList<>();
		if(rowSize >= 2) {
			for(int i = 1; i < rowSize; i++) {
				if(fileNoArr[i] != null && fileNoArr[i] != 0) {		// ????????? ???????????? ??????
					Row row = sheet.getRow(i);
					MnfVo mnfVo = new MnfVo();
					//????????????
					mnfVo.setFileNo(fileNoArr[i]);
					//????????????
					mnfVo.setFilePathNm(filePathArr[i]);
					// ????????????
					mnfVo.setMnfNm(row.getCell(1) == null ? "" : String.valueOf(getValueFromCell(row.getCell(1))));
					// ????????????
					mnfVo.setNtnCd(row.getCell(2) == null ? "" : String.valueOf(getValueFromCell(row.getCell(2))));

					list.add(mnfVo);
				}
			}
		}

		String jsonList = null;
		if(list != null && list.size() > 0) {
			ObjectMapper mapper = new ObjectMapper();
			jsonList = mapper.writeValueAsString(list);
		}

		return jsonList;
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
