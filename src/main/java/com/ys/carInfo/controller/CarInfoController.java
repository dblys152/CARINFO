package com.ys.carInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ys.carInfo.service.CarInfoService;

@Controller
@RequestMapping("/carInfo")
public class CarInfoController {

	@Autowired CarInfoService carInfoService;

	@RequestMapping(value="list", method=RequestMethod.GET)
	public String list(Model model) throws Exception {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> carInfoList = carInfoService.selectCarInfoList(map);
		model.addAttribute("carInfoList", carInfoList);

		return "NewFile";
	}

	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save (
			@RequestBody Map<String, Object> map,
			Model model) throws Exception {
		try {
			carInfoService.insertCarInfo(map);
		} catch(Exception e) {

		}

		return map;
	}


}
