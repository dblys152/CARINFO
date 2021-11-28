package com.ys.carInfo.common.service;

import org.springframework.web.multipart.MultipartFile;

import com.ys.carInfo.common.vo.FileVo;

public interface FileService {

	public Integer uploadFile(MultipartFile file, String tblNm, String fileTyCd, String fileIdnfNo) throws Exception;

	public FileVo selectFile(Integer fileNo) throws Exception;

	public void deleteFile(String fileIdntNo) throws Exception;

}
