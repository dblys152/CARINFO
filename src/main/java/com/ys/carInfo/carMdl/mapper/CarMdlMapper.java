package com.ys.carInfo.carMdl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.carInfo.carMdl.vo.CarInfoVo;

@Mapper
public interface CarMdlMapper {

	public List<Map<String, Object>> selectCarInfoList(Map<String, Object> map) throws Exception;

	public void insertCarInfo(CarInfoVo carInfoVo) throws Exception;

}
