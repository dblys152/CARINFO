package com.ys.carInfo.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ys.carInfo.mapper.MnfMapper;
import com.ys.carInfo.service.MnfService;
import com.ys.carInfo.vo.MnfVo;

@Service("mnfService")
public class MnfServiceImpl implements MnfService {

	@Autowired private MnfMapper mnfMapper;

	@Override
	@Transactional
	public String mergeMnf(MnfVo mnfVo) throws Exception {
		mnfVo.setRegNo(0);
		mnfVo.setModNo(0);
		mnfMapper.mergeMnf(mnfVo);
		return mnfVo.getMnfNo();
	}

	@Override
	public Map<String, Object> selectMnf(String mnfNo) throws Exception {
		return mnfMapper.selectMnf(mnfNo);
	}

}
