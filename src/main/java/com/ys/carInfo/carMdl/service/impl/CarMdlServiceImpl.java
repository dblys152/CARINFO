package com.ys.carInfo.carMdl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ys.carInfo.carMdl.mapper.CarMdlMapper;
import com.ys.carInfo.carMdl.service.CarMdlService;
import com.ys.carInfo.carMdl.vo.CarMdlVo;

@Service("carMdlService")
public class CarMdlServiceImpl implements CarMdlService {

	@Autowired private CarMdlMapper carMdlMapper;

	@Override
	public String mergeCarMdl(CarMdlVo carMdlVo) throws Exception {
		int userNo = 0;
		carMdlVo.setRegNo(userNo);
		carMdlVo.setModNo(userNo);
		carMdlMapper.mergeCarMdl(carMdlVo);
		return carMdlVo.getCarMdlNo();
	}

	@Override
	public CarMdlVo selectCarMdl(String carMdlNo) throws Exception {
		return carMdlMapper.selectCarMdl(carMdlNo);
	}

	@Override
	public List<Map<String, Object>> selectCarMdlList(Map<String, Object> map) throws Exception {
		return carMdlMapper.selectCarMdlList(map);
	}

	@Override
	public List<String> selectCarMdlYearList(String carMdlNo) throws Exception {
		return carMdlMapper.selectCarMdlYearList(carMdlNo);
	}

	@Override
	public void insertCarMdlYear(CarMdlVo carMdlVo) throws Exception {
		int userNo = 0;
		carMdlVo.setRegNo(userNo);
		carMdlVo.setModNo(userNo);
		carMdlMapper.insertCarMdlYear(carMdlVo);
	}


}
