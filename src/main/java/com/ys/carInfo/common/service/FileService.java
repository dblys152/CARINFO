package com.ys.carInfo.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ys.carInfo.common.vo.CmnCodeVo;
import com.ys.carInfo.common.vo.NtnCodeVo;

public interface FileService {

	public void uploadFile(MultipartFile file, String tblNm, String fileTyCd, String fileIdnfNo) throws Exception;

}
