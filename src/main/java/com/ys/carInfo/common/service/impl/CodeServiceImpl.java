package com.ys.carInfo.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.carInfo.common.mapper.CodeMapper;
import com.ys.carInfo.common.service.CodeService;
import com.ys.carInfo.common.vo.CmnCodeVo;
import com.ys.carInfo.common.vo.NtnCodeVo;

@Service("codeService")
public class CodeServiceImpl implements CodeService {

	@Autowired private CodeMapper codeMapper;

	@Override
	public List<CmnCodeVo> selectCmnCodeList(String upCmnCd, String useYn, String cmnCdNm) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("upCmnCd", upCmnCd);
		map.put("useYn", useYn);
		map.put("cmnCdNm", cmnCdNm);
		return codeMapper.selectCmnCodeList(map);
	}

	@Override
	public List<NtnCodeVo> selectNtnCodeList(Map<String, Object> map) throws Exception {
		return codeMapper.selectNtnCodeList(map);
	}


}
