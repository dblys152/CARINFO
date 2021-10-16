package com.ys.carInfo.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.carInfo.vo.MnfVo;

@Mapper
public interface MnfMapper {

	public void mergeMnf(MnfVo mnfVo) throws Exception;

	public Map<String, Object> selectMnf(String mnfNo) throws Exception;
}
