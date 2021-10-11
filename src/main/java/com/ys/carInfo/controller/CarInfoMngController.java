package com.ys.carInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/carInfoMng")
public class CarInfoMngController {

	@RequestMapping(value="/setting", method=RequestMethod.GET)
	public String setting(Model model) throws Exception {

		return "/form/carInfo/carInfoSettingView";
	}
}
