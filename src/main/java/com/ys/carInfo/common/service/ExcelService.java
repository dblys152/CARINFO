package com.ys.carInfo.common.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
	/**
	 * 목록 엑셀파일 생성
	 * @param sheetNm, columns, dataList
	 * @return SXSSFWorkbook
	 * @throws Exception
	 */
	public SXSSFWorkbook createListExcel(String sheetNm, String[] columns, List<Map<String, Object>> dataList) throws Exception;

	/**
	 * 엑셀 데이터 파싱
	 * @param excel
	 * @return json string
	 * @throws Exception
	 */
	public String parsingMnfExcel(MultipartFile excel) throws Exception;

}
