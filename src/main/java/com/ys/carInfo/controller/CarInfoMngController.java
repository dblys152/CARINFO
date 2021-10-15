package com.ys.carInfo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ys.common.service.CodeService;
import com.ys.common.vo.NtnCodeVo;

@Controller
@RequestMapping("/carInfoMng")
public class CarInfoMngController {

	@Autowired CodeService codeService;

	@RequestMapping(value="/mdlList", method=RequestMethod.GET)
	public String mdlList(Model model) throws Exception {

		return "/form/carInfo/carInfoMdlList";
	}

	@RequestMapping(value="/mnfList", method=RequestMethod.GET)
	public String mnfList(Model model) throws Exception {

		return "/form/carInfo/carInfoMnfList";
	}

	@RequestMapping(value="/mnfWrite", method=RequestMethod.GET)
	public String mnfWrite(Model model) throws Exception {

		List<NtnCodeVo> ntnCdList = codeService.selectNtnCdList(new HashMap<>());
		model.addAttribute("ntnCdList", ntnCdList);

		return "/form/carInfo/carInfoMnfWrite";
	}
}
