package com.ys.carInfo.service;

import java.util.List;
import java.util.Map;

import com.ys.carInfo.vo.MnfVo;

public interface CarMdlService {

	public List<Map<String, Object>> selectCarInfoList(Map<String, Object> map) throws Exception;

	public void insertCarInfo(Map<String, Object> map) throws Exception;

}
