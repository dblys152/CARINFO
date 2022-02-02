package com.ys.carInfo.carMdl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.carInfo.carMdl.vo.MnfVo;

@Mapper
public interface MnfMapper {

	public void mergeMnf(MnfVo mnfVo) throws Exception;

	public MnfVo selectMnf(String mnfNo) throws Exception;

	public List<Map<String, Object>> selectMnfList(Map<String, Object> map) throws Exception;

	public void deleteMnf(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> selectMnfAllList(Map<String, Object> map) throws Exception;

	public void updateMnfSrtOrd(Map<String, Object> map) throws Exception;
}
