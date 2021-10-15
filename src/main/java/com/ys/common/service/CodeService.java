package com.ys.common.service;

import java.util.List;
import java.util.Map;

import com.ys.common.vo.CmnCodeVo;
import com.ys.common.vo.NtnCodeVo;

public interface CodeService {

	/* 공통코드 목록 조회 */
	public List<CmnCodeVo> selectCmnCdList(Map<String, Object> map) throws Exception;

	/* 국가코드 목록 조회 */
	public List<NtnCodeVo> selectNtnCdList(Map<String, Object> map) throws Exception;

}
