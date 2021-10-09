package com.ys.carInfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ys.carInfo.mapper.CarInfoMapper;
import com.ys.carInfo.service.CarInfoService;
import com.ys.carInfo.vo.CarInfoVo;

@Service("carInfoService")
public class CarInfoServiceImpl implements CarInfoService {

	@Autowired CarInfoMapper carInfoMapper;

	@Override
	public List<Map<String, Object>> selectCarInfoList(Map<String, Object> map) throws Exception {
		return carInfoMapper.selectCarInfoList(map);
	}

	@Override
	@Transactional
	public void insertCarInfo(Map<String, Object> map) throws Exception {
		CarInfoVo carInfoVo = new CarInfoVo();
		carInfoMapper.insertCarInfo(carInfoVo);
	}

}
