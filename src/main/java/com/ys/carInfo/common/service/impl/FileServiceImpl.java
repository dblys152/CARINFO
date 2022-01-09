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

	@Autowired FileMapper fileMapper;

	@Override
	public Integer uploadFile(MultipartFile file, String tblNm, String fileTyCd, String fileIdntNo) throws Exception {
		if(file == null)
			throw new EntityNotFoundException("file not found");

		LocalDate date = LocalDate.now();

		String orgFileNm = file.getOriginalFilename();
		String fileNm = filePrefix + date.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + UUID.randomUUID();
		String extNm = StringUtils.getFilenameExtension(orgFileNm);
		String mimeTy = file.getContentType();

		String folder = date.format(DateTimeFormatter.ofPattern("/yyyy/MM"));
		if(mimeTy.contains("image")) folder += "/images";
		else folder += "/files";

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

		Path fullPath = Paths.get(rootPath, folder);
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

		fileMapper.insertFile(fileVo);
		return fileVo.getFileNo();
	}

	@Override
	public FileVo selectFile(Integer fileNo) throws Exception {
		return fileMapper.selectFile(fileNo);
	}

	@Override
	public void deleteFile(String fileIdntNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("modNo", 0);
		map.put("fileIdntNo", fileIdntNo);
		fileMapper.deleteFile(map);
	}


}
