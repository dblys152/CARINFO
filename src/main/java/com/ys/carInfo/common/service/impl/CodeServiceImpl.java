package com.ys.carInfo.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.carInfo.common.mapper.CmnCodeMapper;
import com.ys.carInfo.common.mapper.NtnCodeMapper;
import com.ys.carInfo.common.service.CodeService;
import com.ys.carInfo.common.vo.CmnCodeVo;
import com.ys.carInfo.common.vo.NtnCodeVo;

@Service("codeService")
public class CodeServiceImpl implements CodeService {

	@Autowired private CmnCodeMapper cmnCodeMapper;
	@Autowired private NtnCodeMapper ntnCodeMapper;

	@Override
	public List<CmnCodeVo> selectCmnCdList(Map<String, Object> map) throws Exception {
		return cmnCodeMapper.selectCmnCdList(map);
	}

	@Override
	public List<NtnCodeVo> selectNtnCdList(Map<String, Object> map) throws Exception {
		return ntnCodeMapper.selectNtnCdList(map);
	}


}
