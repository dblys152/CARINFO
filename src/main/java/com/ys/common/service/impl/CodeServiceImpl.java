package com.ys.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.common.mapper.CmnCodeMapper;
import com.ys.common.mapper.NtnCodeMapper;
import com.ys.common.service.CodeService;
import com.ys.common.vo.CmnCodeVo;
import com.ys.common.vo.NtnCodeVo;

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
