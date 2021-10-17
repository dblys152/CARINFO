package com.ys.carInfo.carMdl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ys.carInfo.carMdl.mapper.CarMdlMapper;
import com.ys.carInfo.carMdl.service.CarMdlService;
import com.ys.carInfo.carMdl.vo.CarInfoVo;

@Service("carMdlService")
public class CarMdlServiceImpl implements CarMdlService {

	@Autowired private CarMdlMapper carMdlMapper;

	@Override
	public List<Map<String, Object>> selectCarInfoList(Map<String, Object> map) throws Exception {
		return carMdlMapper.selectCarInfoList(map);
	}

	@Override
	@Transactional
	public void insertCarInfo(Map<String, Object> map) throws Exception {
		CarInfoVo carInfoVo = new CarInfoVo();
		carMdlMapper.insertCarInfo(carInfoVo);
	}


}
