package com.ys.carInfo.carMdl.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ys.carInfo.carMdl.mapper.MnfMapper;
import com.ys.carInfo.carMdl.service.MnfService;
import com.ys.carInfo.carMdl.vo.MnfVo;
import com.ys.carInfo.common.service.FileService;
import com.ys.global.error.exception.EntityNotFoundException;

@Service("mnfService")
public class MnfServiceImpl implements MnfService {

	@Autowired private FileService fileService;
	@Autowired private MnfMapper mnfMapper;

	@Override
	@Transactional
	public String mergeMnf(MnfVo mnfVo) throws Exception {
		int userNo = 0;
		mnfVo.setRegNo(userNo);
		mnfVo.setModNo(userNo);
		mnfMapper.mergeMnf(mnfVo);			// 제조사 등록
		String mnfNo = mnfVo.getMnfNo();

		if(mnfVo.getFileNo() == null) {
			fileService.deleteAtchFileIdntNo(mnfNo);	// 로고 삭제
			try {
				fileService.uploadFile(mnfVo.getFile(), "MNF", "100101", mnfNo);	// 로고 등록
			} catch(IOException e) {
				throw e;
			}
		}

		return mnfNo;
	}

	@Override
	@Transactional
	public void insertMnfList(List<MnfVo> mnfList) throws Exception {
		int userNo = 0;
		for(MnfVo mnfVo : mnfList) {
			mnfVo.setRegNo(userNo);
			mnfVo.setModNo(userNo);
			mnfMapper.mergeMnf(mnfVo);			// 제조사 등록
			String mnfNo = mnfVo.getMnfNo();

			//파일 업데이트
			fileService.updateAtchFileIdntNo(mnfVo.getFileNo(), mnfNo);
		}
	}

	@Override
	public MnfVo selectMnf(String mnfNo) throws Exception {
		return mnfMapper.selectMnf(mnfNo);
	}

	@Override
	public List<Map<String, Object>> selectMnfList(Map<String, Object> map) throws Exception {
		return mnfMapper.selectMnfList(map);
	}

	@Override
	@Transactional
	public void deleteMnf(String mnfNo) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("modNo", 0);
		map.put("mnfNo", mnfNo);
		fileService.deleteAtchFileIdntNo(mnfNo);
		mnfMapper.deleteMnf(map);
	}

	@Override
	public List<Map<String, Object>> selectMnfAllList(Map<String, Object> map) throws Exception {
		return mnfMapper.selectMnfAllList(map);
	}

}
