package com.ys.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CmnCodeMapper {

	public List<Map<String, Object>> selectCmnCdList(Map<String, Object> map) throws Exception;

}
