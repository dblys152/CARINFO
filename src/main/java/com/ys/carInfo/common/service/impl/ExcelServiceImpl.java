package com.ys.carInfo.common.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ys.carInfo.common.service.ExcelService;

@Service("excelService")
public class ExcelServiceImpl implements ExcelService {

	@Override
	public SXSSFWorkbook createExcelMnf(List<Map<String, Object>> mnfList) throws Exception {
		SXSSFWorkbook wb = new SXSSFWorkbook(100);
		
		wb.setCompressTempFiles(true);
		Sheet sh = wb.createSheet("test Sheet");

		int i=0;
		int n=-1;

		while ( n++ < 10 ) {
        	 for(i=n*10000;i<(n+1)*10000;i++) {
	              setCellValue(sh,i,0,"Test_col"+i);
	              setCellValue(sh,i,1,"Test_col_"+i);
	              setCellValue(sh,i,2,"Test_col_"+i);
	              setCellValue(sh,i,3,"Test_col_"+i);
	              setCellValue(sh,i,4,"Test_col_"+i);
        	 }

             // 메모리 flush
             ((SXSSFSheet)sh).flushRows(10000);
		}

		return wb;
	}


	public Row getRow(Sheet sh, int i){
		Row r = sh.getRow(i);
		if(r==null)
			r = sh.createRow(i);
		return r;
	}

	public Cell getCell(Sheet sh, int row,int cell){
		Row r = getRow(sh,row);
		Cell c = r.getCell(cell);
		if(c==null)
			c = r.createCell(cell);
		return c;
	}

	public void setCellValue(Sheet sh, int row, int cell, String cellvalue){
		Cell c = getCell(sh,row,cell);
		c.setCellValue(cellvalue);
	}

}
