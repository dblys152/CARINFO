package com.ys.common.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.common.mapper.CmnCodeMapper;
import com.ys.common.service.CmnCodeService;
import com.ys.config.MyBatisSupport;

@Service("cmnCodeService")
public class CmnCodeServiceImpl extends MyBatisSupport implements CmnCodeService {

	@Autowired CmnCodeMapper cmnCodeMapper;

	@Override
	public List<Map<String, Object>> selectCmnCdList(Map<String, Object> map) throws Exception {
		return cmnCodeMapper.selectCmnCdList(map);
	}

}
