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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ys.carInfo.service.CarMdlService;
import com.ys.carInfo.service.MnfService;
import com.ys.carInfo.vo.MnfVo;
import com.ys.common.service.CodeService;
import com.ys.common.vo.NtnCodeVo;

@Controller
@RequestMapping("/carInfoMng")
public class CarInfoMngController {

	@Autowired private CarMdlService carMdlService;
	@Autowired private MnfService mnfService;
	@Autowired private CodeService codeService;

	/* 자동차정보 목록 화면 */
	@RequestMapping(value="/mdlList", method=RequestMethod.GET)
	public String mdlList(Model model) throws Exception {

		return "/form/carInfo/carInfoMdlList";
	}

	/* 제조사 목록 화면 */
	@RequestMapping(value="/mnfList", method=RequestMethod.GET)
	public String mnfList(Model model) throws Exception {

		return "/form/carInfo/carInfoMnfList";
	}

	/* 제조사 등록 화면 */
	@RequestMapping(value="/mnfWrite", method=RequestMethod.GET)
	public String mnfWrite(
			@RequestParam(value="mnfNo", required=false) String mnfNo,
			Model model) throws Exception {

		List<NtnCodeVo> ntnCdList = codeService.selectNtnCdList(new HashMap<>());
		model.addAttribute("ntnCdList", ntnCdList);

		Map<String, Object> mnfMap = new HashMap<>();
		if(mnfNo != null && !mnfNo.trim().equals("")) {
			mnfMap = mnfService.selectMnf(mnfNo);
			model.addAttribute("vo", mnfMap);
		}

		return "/form/carInfo/carInfoMnfWrite";
	}

	@RequestMapping(value="/mnfWrite", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertMnf(
			@RequestBody MnfVo mnfVo) throws Exception {

		Map<String, Object> map = new HashMap<>();
		try {
			String mnfNo = mnfService.mergeMnf(mnfVo);
			map.put("mnfNo", mnfNo);
		} catch(Exception e) {

		}

		return map;
	}

	/* 제조사 상세 화면 */
	@RequestMapping(value="/mnfView", method=RequestMethod.GET)
	public String mnfView(
			@RequestParam(value="mnfNo") String mnfNo,
			Model model) throws Exception {

		Map<String, Object> mnfMap = mnfService.selectMnf(mnfNo);
		model.addAttribute("vo", mnfMap);

		return "/form/carInfo/carInfoMnfView";
	}
}
