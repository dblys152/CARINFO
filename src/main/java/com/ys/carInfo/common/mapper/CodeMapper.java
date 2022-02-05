package com.ys.carInfo.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.carInfo.common.vo.CmnCodeVo;
import com.ys.carInfo.common.vo.NtnCodeVo;

@Mapper
public interface CodeMapper {

	public List<CmnCodeVo> selectCmnCodeList(Map<String, Object> map) throws Exception;

	public List<NtnCodeVo> selectNtnCodeList(Map<String, Object> map) throws Exception;
}
