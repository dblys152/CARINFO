package com.ys.carInfo.common.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ys.carInfo.common.service.FileService;
import com.ys.carInfo.common.vo.FileVo;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired FileService fileService;

	@RequestMapping(value="/images/{fileNo}", method=RequestMethod.GET)
	public ResponseEntity<Resource> viewImg(
			@PathVariable("fileNo")	Integer	fileNo) throws Exception {
		FileVo fileVo = fileService.selectFile(fileNo);
		String fileFullPath = fileVo.getFilePathNm() + "\\" + fileVo.getFileNm() + "." + fileVo.getFileExtNm();
		Resource file = new FileSystemResource(new File(fileFullPath).toPath());
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(fileVo.getMimeTy()))
				.body(file);
	}

	@RequestMapping(value="/download", method=RequestMethod.GET)
    public ResponseEntity<Resource> serveFile(
    		@RequestParam(value="fileNo") Integer fileNo) throws Exception {

		FileVo fileVo = fileService.selectFile(fileNo);
		String fileFullPath = fileVo.getFilePathNm() + "\\" + fileVo.getFileNm() + "." + fileVo.getFileExtNm();
		Resource file = new FileSystemResource(new File(fileFullPath).toPath());
        return ResponseEntity.ok()
        		.header(HttpHeaders.CONTENT_DISPOSITION,
        				"attachment; filename=\"" + file.getFilename() + "\"")
        		.body(file);
    }

}
