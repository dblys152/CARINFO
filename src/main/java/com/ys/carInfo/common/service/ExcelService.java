package com.ys.carInfo.common.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonArray;

public interface ExcelService {

	public SXSSFWorkbook createListExcel(String sheetNm, String[] columns, List<Map<String, Object>> dataList) throws Exception;

	public JsonArray parsingMnfExcel(MultipartFile excel) throws Exception;

}
