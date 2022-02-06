package com.ys.carInfo.carMdl.service;

import java.util.List;
import java.util.Map;

import com.ys.carInfo.carMdl.vo.CarMdlVo;

public interface CarMdlService {
	/**
	 * 자동차모델 정보 조회
	 * @param carMdlNo
	 * @return carMdlVo
	 * @throws Exception
	 */
	public CarMdlVo selectCarMdl(String carMdlNo) throws Exception;

	/**
	 * 자동차모델 등록 및 수정
	 * @param carMdlVo
	 * @return carMdlNo
	 * @throws Exception
	 */
	public String mergeCarMdl(CarMdlVo carMdlVo) throws Exception;

	/**
	 * 자동차모델 목록 조회
	 * @param map
	 * @return list
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectCarMdlList(Map<String, Object> map) throws Exception;

	/**
	 * 자동차모델연식 목록 조회
	 * @param carMdlNo
	 * @return list
	 * @throws Exception
	 */
	public List<String> selectCarMdlYearList(String carMdlNo) throws Exception;

}
