package com.ys.carInfo.common.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ys.carInfo.common.mapper.FileMapper;
import com.ys.carInfo.common.service.FileService;
import com.ys.carInfo.common.vo.FileVo;
import com.ys.global.error.exception.EntityNotFoundException;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Value("${file.updload.path}")
	private	String	rootPath;

	@Value("${file.updload.prefix}")
	private	String	filePrefix;

	@Autowired private FileMapper fileMapper;

	@Override
	public Integer uploadFile(MultipartFile file, String tblNm, String fileTyCd, String fileIdntNo) throws Exception {
		if(file == null)
			throw new EntityNotFoundException("file not found");

		LocalDate date = LocalDate.now();
		String orgFileNm = file.getOriginalFilename();
		String fileNm = filePrefix + date.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + UUID.randomUUID();
		String extNm = StringUtils.getFilenameExtension(orgFileNm);
		String mimeTy = file.getContentType();

		StringBuilder folder = new StringBuilder(date.format(DateTimeFormatter.ofPattern("/yyyy/MM")));
		if(mimeTy.contains("image"))
			folder.append("/images");
		else
			folder.append("/files");

		FileVo fileVo = new FileVo();
		fileVo.setRegNo(0);
		fileVo.setModNo(0);
		fileVo.setTblNm(tblNm);
		fileVo.setFileIdntNo(fileIdntNo);
		fileVo.setFileTyCd(fileTyCd);
		fileVo.setOrgFileNm(orgFileNm);
		fileVo.setFileNm(fileNm);
		fileVo.setFileSize(file.getSize());
		fileVo.setFileExtNm(extNm);
		fileVo.setMimeTy(mimeTy);

		Path fullPath = Paths.get(rootPath, folder.toString());
		fileVo.setFilePathNm(fullPath.toString());

		try {
			if(!Files.exists(fullPath)) {
	        	Files.createDirectories(fullPath);
	        }
			try(InputStream inputStream = file.getInputStream()) {
	            Files.copy(inputStream, fullPath.resolve(fileNm + "." + extNm),
	                StandardCopyOption.REPLACE_EXISTING);
	        }
		} catch(IOException e){
			throw e;
		}

		fileMapper.insertAtchFile(fileVo);
		return fileVo.getFileNo();
	}

	@Override
	public FileVo uploadExcelImgFile(XSSFPictureData xssfPictData, String tblNm, String fileTyCd, String fileIdntNo) throws Exception {
		PackagePart packagePart = xssfPictData.getPackagePart();

		LocalDate date = LocalDate.now();
		String orgFileNm = packagePart.getPartName().toString();
		String fileNm = filePrefix + date.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + UUID.randomUUID();
		String extNm = StringUtils.getFilenameExtension(orgFileNm);
		String mimeTy = xssfPictData.getMimeType();
		String folder = date.format(DateTimeFormatter.ofPattern("/yyyy/MM")) + "/images";

		FileVo fileVo = new FileVo();
		fileVo.setRegNo(0);
		fileVo.setModNo(0);
		fileVo.setTblNm(tblNm);
		fileVo.setFileIdntNo(fileIdntNo);
		fileVo.setFileTyCd(fileTyCd);
		fileVo.setOrgFileNm(orgFileNm);
		fileVo.setFileNm(fileNm);
		fileVo.setFileSize(xssfPictData.getData().length);
		fileVo.setFileExtNm(extNm);
		fileVo.setMimeTy(mimeTy);

		Path fullPath = Paths.get(rootPath, folder);
		fileVo.setFilePathNm(fullPath.toString());

		try {
			if(!Files.exists(fullPath)) {
	        	Files.createDirectories(fullPath);
	        }
			try(InputStream inputStream = packagePart.getInputStream()) {
	            Files.copy(inputStream, fullPath.resolve(fileNm + "." + extNm),
	                StandardCopyOption.REPLACE_EXISTING);
	        }
		} catch(IOException e){
			throw e;
		}

		fileMapper.insertAtchFile(fileVo);
		return fileVo;
	}

	@Override
	public FileVo selectAtchFile(Integer fileNo) throws Exception {
		return fileMapper.selectAtchFile(fileNo);
	}

	@Override
	public void deleteAtchFileIdntNo(String fileIdntNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("modNo", 0);
		map.put("fileIdntNo", fileIdntNo);
		fileMapper.deleteAtchFileIdntNo(map);
	}

	@Override
	public void updateAtchFileIdntNo(Integer fileNo, String fileIdntNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("modNo", 0);
		map.put("fileNo", fileNo);
		map.put("fileIdntNo", fileIdntNo);
		fileMapper.updateAtchFileIdntNo(map);
	}


}
