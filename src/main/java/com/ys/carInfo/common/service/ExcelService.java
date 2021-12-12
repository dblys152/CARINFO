package com.ys.carInfo.common.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public interface ExcelService {

	SXSSFWorkbook createExcelMnf(List<Map<String, Object>> mnfList) throws Exception;

}
