package com.ys.carInfo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.carInfo.vo.CarInfoVo;

@Mapper
public interface CarInfoMapper {

	public List<Map<String, Object>> selectCarInfoList(Map<String, Object> map) throws Exception;

	public void insertCarInfo(CarInfoVo carInfoVo);
}
