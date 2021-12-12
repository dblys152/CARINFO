package com.ys.carInfo.carMdl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ys.carInfo.carMdl.vo.MnfVo;

public interface MnfService {

	/* 제조사 등록 및 수정 */
	public String mergeMnf(MnfVo mnfVo) throws Exception;

	/* 제조사 조회 */
	public MnfVo selectMnf(String mnfNo) throws Exception;

	/* 제조사 목록 조회 */
	public List<Map<String, Object>> selectMnfList(Map<String, Object> map) throws Exception;

	/* 제조사 삭제 */
	public void deleteMnf(String mnfNo) throws Exception;

	/* 제조사 모든 목록 조회 */
	public List<Map<String, Object>> selectMnfAllList(Map<String, Object> map) throws Exception;

}
