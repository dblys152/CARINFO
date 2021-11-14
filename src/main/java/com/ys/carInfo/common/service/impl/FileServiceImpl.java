package com.ys.carInfo.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ys.carInfo.common.mapper.CmnCodeMapper;
import com.ys.carInfo.common.mapper.FileMapper;
import com.ys.carInfo.common.mapper.NtnCodeMapper;
import com.ys.carInfo.common.service.CodeService;
import com.ys.carInfo.common.service.FileService;
import com.ys.carInfo.common.vo.CmnCodeVo;
import com.ys.carInfo.common.vo.NtnCodeVo;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Value("${file.updload.path}")
	private	String	rootPath;

	@Value("${file.updload.prefix}")
	private	String	filePrefix;

	@Autowired FileMapper fileMapper;

	@Override
	public void uploadFile(MultipartFile file, String tblNm, String fileTyCd, String fileIdntNo) {


	}

}
