package com.ys.carInfo.carMdl.service;

import java.util.List;
import java.util.Map;

import com.ys.carInfo.carMdl.vo.MnfVo;

public interface MnfService {
	/**
	 * 제조사 등록 및 수정
	 * @param mnfVo
	 * @return mnfNo
	 * @throws Exception
	 */
	public String mergeMnf(MnfVo mnfVo) throws Exception;

	/**
	 * 제조사 일괄 등록
	 * @param mnfList
	 * @throws Exception
	 */
	public void insertMnfList(List<MnfVo> mnfList) throws Exception;

	/**
	 * 제조사 정보 조회
	 * @param mnfNo
	 * @return mnfVo
	 * @throws Exception
	 */
	public MnfVo selectMnf(String mnfNo) throws Exception;

	/**
	 * 제조사 목록 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMnfList(Map<String, Object> map) throws Exception;

	/**
	 * 제조사 삭제
	 * @param mnfNo
	 * @throws Exception
	 */
	public void deleteMnf(String mnfNo) throws Exception;

	/**
	 * 제조사 모든 목록 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectMnfAllList(Map<String, Object> map) throws Exception;

}
