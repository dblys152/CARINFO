package com.ys.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NtnCodeMapper {

	public List<Map<String, Object>> selectNtnCdList(Map<String, Object> map) throws Exception;

}
