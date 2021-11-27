package com.ys.carInfo.carMdl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ys.carInfo.carMdl.service.CarMdlService;
import com.ys.carInfo.carMdl.service.MnfService;
import com.ys.carInfo.carMdl.vo.MnfVo;
import com.ys.carInfo.common.service.CodeService;
import com.ys.carInfo.common.vo.NtnCodeVo;
import com.ys.carInfo.common.vo.SearchVo;
import com.ys.global.error.exception.EntityNotFoundException;

@Controller
@RequestMapping("/carInfoMng")
public class CarInfoMngController {
	 private static final Logger logger = LoggerFactory.getLogger(CarInfoMngController.class);

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
	public String mnfList(
			@ModelAttribute(value="searchVo") SearchVo searchVo,
			Model model) throws Exception {

		if(searchVo.getPageNo() == null) {
			searchVo.setPageNo(1);
		}

		return "/form/carInfo/carInfoMnfList";
	}
	@RequestMapping(value="/mnfListCore", method=RequestMethod.GET)
	public String mnfListCore(
			@ModelAttribute(value="searchVo") SearchVo searchVo,
			Model model) throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("search", searchVo);
		List<Map<String, Object>> mnfList = mnfService.selectMnfList(map);
		model.addAttribute("mnfList", mnfList);

		model.addAttribute("totCnt", (mnfList != null && mnfList.size() > 0 ? mnfList.get(0).get("totCnt") : 0));
		model.addAttribute("listCnt", searchVo.getListCnt());
		model.addAttribute("pageNo", searchVo.getPageNo());

		return "/empty/carInfo/carInfoMnfListCore";
	}

	/* 제조사 등록 화면 */
	@RequestMapping(value="/mnfWrite", method=RequestMethod.GET)
	public String mnfWrite(
			@ModelAttribute("mnfVo") MnfVo mnfVo,
			@RequestParam(value="mnfNo", required=false) String mnfNo,
			Model model) throws Exception {

		List<NtnCodeVo> ntnCdList = codeService.selectNtnCdList(new HashMap<>());
		model.addAttribute("ntnCdList", ntnCdList);

		if(mnfNo != null && !mnfNo.trim().equals("")) {
			mnfVo = mnfService.selectMnf(mnfNo);
		}
		model.addAttribute("mnfVo", mnfVo);

		return "/form/carInfo/carInfoMnfWrite";
	}

	@RequestMapping(value="/mnfWrite", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertMnf(
			@ModelAttribute MnfVo mnfVo) throws Exception {

		Map<String, Object> map = new HashMap<>();
		try {
			String mnfNo = mnfService.mergeMnf(mnfVo);
			map.put("mnfNo", mnfNo);
		} catch(EntityNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		return ResponseEntity.ok(map);
	}

	/* 제조사 상세 화면 */
	@RequestMapping(value="/mnfView", method=RequestMethod.GET)
	public String mnfView(
			@RequestParam(value="mnfNo") String mnfNo,
			Model model) throws Exception {

		MnfVo mnfVo = mnfService.selectMnf(mnfNo);
		model.addAttribute("mnfVo", mnfVo);

		return "/form/carInfo/carInfoMnfView";
	}
}