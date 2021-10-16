package com.ys.carInfo.service;

import java.util.Map;

import com.ys.carInfo.vo.MnfVo;

public interface MnfService {

	public String mergeMnf(MnfVo mnfVo) throws Exception;

	public Map<String, Object> selectMnf(String mnfNo) throws Exception;

}
