package com.ys.carInfo.common.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

	public SXSSFWorkbook createListExcel(String sheetNm, String[] columns, List<Map<String, Object>> dataList) throws Exception;

	public String parsingMnfExcel(MultipartFile excel) throws Exception;

}
