package com.ys.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.common.mapper.NtnCodeMapper;
import com.ys.common.service.NtnCodeService;

@Service("ntnCodeService")
public class NtnCodeServiceImpl implements NtnCodeService {

	@Autowired NtnCodeMapper ntnCodeMapper;

	@Override
	public List<Map<String, Object>> selectNtnCdList(Map<String, Object> map) throws Exception {
		return ntnCodeMapper.selectNtnCdList(map);
	}

}