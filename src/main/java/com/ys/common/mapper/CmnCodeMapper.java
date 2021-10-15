package com.ys.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.common.vo.CmnCodeVo;

@Mapper
public interface CmnCodeMapper {

	public List<CmnCodeVo> selectCmnCdList(Map<String, Object> map) throws Exception;

}
