package com.ys.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.common.vo.NtnCodeVo;

@Mapper
public interface NtnCodeMapper {

	public List<NtnCodeVo> selectNtnCdList(Map<String, Object> map) throws Exception;

}
