package com.ys.carInfo.carMdl.service;

import java.util.List;
import java.util.Map;

public interface CarMdlService {

	public List<Map<String, Object>> selectCarInfoList(Map<String, Object> map) throws Exception;

	public void insertCarInfo(Map<String, Object> map) throws Exception;

}
