package com.ys.carInfo.carMdl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.carInfo.carMdl.vo.CarMdlVo;

@Mapper
public interface CarMdlMapper {

	public void mergeCarMdl(CarMdlVo carMdlVo) throws Exception;

	public CarMdlVo selectCarMdl(String carMdlNo) throws Exception;

	public List<Map<String, Object>> selectCarMdlList(Map<String, Object> map) throws Exception;

	public List<String> selectCarMdlYearList(String carMdlNo) throws Exception;

	public void insertCarMdlYear(CarMdlVo carMdlVo) throws Exception;

}
