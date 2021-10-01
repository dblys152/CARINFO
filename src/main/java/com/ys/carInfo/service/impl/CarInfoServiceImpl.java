package com.ys.carInfo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ys.carInfo.mapper.CarInfoMapper;
import com.ys.carInfo.service.CarInfoService;
import com.ys.carInfo.vo.CarInfoVo;
import com.ys.common.MyBatisSupport;
import com.ys.common.MyBatisTransactionManager;


@Service("carInfoService")
public class CarInfoServiceImpl extends MyBatisSupport implements CarInfoService {

	@Autowired CarInfoMapper carInfoMapper;

	@Override
	public List<Map<String, Object>> selectCarInfoList(Map<String, Object> map) throws Exception {
		return carInfoMapper.selectCarInfoList(map);
	}

	@Override
	public void insertCarInfo(Map<String, Object> map) throws Exception {
		MyBatisTransactionManager transaction = getTransactionManager();

		try {
			transaction.start();

			CarInfoVo carInfoVo = new CarInfoVo();
			carInfoMapper.insertCarInfo(carInfoVo);

			transaction.commit();
		} catch(Exception e) {
			transaction.rollback();
		} finally {
			transaction.end();
		}




	}

}
