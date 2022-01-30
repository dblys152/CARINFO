package com.ys.carInfo.carMdl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ys.carInfo.carMdl.service.CarMdlService;
import com.ys.carInfo.common.service.CodeService;
import com.ys.carInfo.common.vo.CmnCodeVo;

@Controller
@RequestMapping("/carInfo")
public class CarInfoController {

	private static final Logger logger = LoggerFactory.getLogger(CarInfoController.class);

	@Autowired private CarMdlService carMdlService;
	@Autowired private CodeService codeService;

	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main(Model model) throws Exception {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> carInfoList = carMdlService.selectCarInfoList(map);
		model.addAttribute("carInfoList", carInfoList);

		List<CmnCodeVo> cmnCdList = codeService.selectCmnCdList(map);
		model.addAttribute("cmnCdList", cmnCdList);

		return "/form/carInfo/mainView";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save (
			@RequestBody Map<String, Object> map,
			Model model) throws Exception {
		try {
			carMdlService.insertCarInfo(map);
		} catch(Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}


}
