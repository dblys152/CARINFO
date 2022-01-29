package com.ys.carInfo.common.service;

import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.springframework.web.multipart.MultipartFile;

import com.ys.carInfo.common.vo.FileVo;

public interface FileService {

	public Integer uploadFile(MultipartFile file, String tblNm, String fileTyCd, String fileIdntNo) throws Exception;

	public FileVo uploadExcelImgFile(XSSFPictureData xssfPictData, String tblNm, String fileTyCd, String fileIdntNo) throws Exception;

	public FileVo selectAtchFile(Integer fileNo) throws Exception;

	public void deleteAtchFileIdntNo(String fileIdntNo) throws Exception;

	public void updateAtchFileIdntNo(Integer fileNo, String fileIdntNo) throws Exception;

}
