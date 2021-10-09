package com.ys.carInfo.controller;

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

import com.ys.carInfo.service.CarInfoService;
import com.ys.common.service.CmnCodeService;
import com.ys.common.service.NtnCodeService;

@Controller
@RequestMapping("/carInfo")
public class CarInfoController {

	private static final Logger logger = LoggerFactory.getLogger(CarInfoController.class);

	@Autowired CarInfoService carInfoService;
	@Autowired CmnCodeService cmnCodeService;
	@Autowired NtnCodeService ntnCodeService;

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> carInfoList = carInfoService.selectCarInfoList(map);
		model.addAttribute("carInfoList", carInfoList);

		List<Map<String, Object>> cmnCdList = cmnCodeService.selectCmnCdList(map);
		model.addAttribute("cmnCdList", cmnCdList);

		List<Map<String, Object>> ntnCdList = ntnCodeService.selectNtnCdList(map);
		model.addAttribute("ntnCdList", ntnCdList);

		return "/form/carInfo/carInfoMainView";
	}

	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save (
			@RequestBody Map<String, Object> map,
			Model model) throws Exception {
		try {
			carInfoService.insertCarInfo(map);
		} catch(Exception e) {
			logger.error(e.getMessage());
		}

		return map;
	}


}
