package com.ys.carInfo.carMdl.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.carInfo.carMdl.service.CarMdlService;
import com.ys.carInfo.carMdl.service.MnfService;
import com.ys.carInfo.carMdl.vo.CarMdlVo;
import com.ys.carInfo.carMdl.vo.MnfVo;
import com.ys.carInfo.common.service.CodeService;
import com.ys.carInfo.common.service.ExcelService;
import com.ys.carInfo.common.util.MessageBox;
import com.ys.carInfo.common.vo.CmnCodeVo;
import com.ys.carInfo.common.vo.NtnCodeVo;
import com.ys.carInfo.common.vo.SearchVo;
import com.ys.global.error.exception.EntityNotFoundException;
import com.ys.global.error.exception.InvalidValueException;

@Controller
@RequestMapping("/carInfoMng")
public class CarInfoMngController {
	private static final Logger logger = LoggerFactory.getLogger(CarInfoMngController.class);

	@Autowired private CarMdlService carMdlService;
	@Autowired private MnfService mnfService;
	@Autowired private CodeService codeService;
	@Autowired private ExcelService excelService;

	/*** View ***/

	/* ??????????????? ?????? ?????? */
	@RequestMapping(value="/carMdlList", method=RequestMethod.GET)
	public String carMdlList(
			@ModelAttribute(value="searchVo") SearchVo searchVo,
			Model model) throws Exception {

		if(searchVo.getPageNo() == null) {
			searchVo.setPageNo(1);
			searchVo.setOrdDesc(true);
		}

		return "/form/carInfo/carMdlList";
	}
	@RequestMapping(value="/carMdlListCore", method=RequestMethod.GET)
	public String carMdlListCore(
			@ModelAttribute(value="searchVo") SearchVo searchVo,
			Model model) throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("search", searchVo);
		List<Map<String, Object>> carMdlList = carMdlService.selectCarMdlList(map);
		model.addAttribute("carMdlList", carMdlList);

		model.addAttribute("totCnt", (carMdlList != null && carMdlList.size() > 0 ? carMdlList.get(0).get("totCnt") : 0));
		model.addAttribute("listCnt", searchVo.getListCnt());
		model.addAttribute("pageNo", searchVo.getPageNo());

		return "/empty/carInfo/carMdlListCore";
	}

	/* ??????????????? ?????? ??? ?????? ?????? */
	@RequestMapping(value="/carMdlWrite", method=RequestMethod.GET)
	public String mdlWrite(
			@ModelAttribute("carMdlVo") CarMdlVo carMdlVo,
			@RequestParam(value="carMdlNo", required=false) String carMdlNo,
			Model model) throws Exception {

		if(carMdlNo != null && !carMdlNo.trim().equals("")) {
			carMdlVo = carMdlService.selectCarMdl(carMdlNo);
			if(carMdlVo == null) {
				return MessageBox.showMsgAndBack(model, "???????????? ?????? ??????????????????.");
			}
		}
		model.addAttribute("carMdlVo", carMdlVo);

		List<Map<String, Object>> mnfList = mnfService.selectMnfAllList(new HashMap<>());	// ????????? ??????
		model.addAttribute("mnfList", mnfList);

		List<CmnCodeVo> carAprnCdList = codeService.selectCmnCodeList("102", "Y", null);	// ????????????????????? ??????
		model.addAttribute("carAprnCdList", carAprnCdList);
		List<CmnCodeVo> carKnCdList = codeService.selectCmnCodeList("103", "Y", null);	// ????????????????????? ??????
		model.addAttribute("carKnCdList", carKnCdList);

		return "/form/carInfo/carMdlWrite";
	}

	/* ??????????????? ?????? ?????? */
	@RequestMapping(value="carMdlView", method=RequestMethod.GET)
	public String carMdlView(
			@RequestParam(value="carMdlNo") String carMdlNo,
			Model model) throws Exception {

		CarMdlVo carMdlVo = carMdlService.selectCarMdl(carMdlNo);
		if(carMdlVo == null) {
			return MessageBox.showMsgAndBack(model, "???????????? ?????? ???????????????.");
		}
		model.addAttribute("carMdlVo", carMdlVo);

		model.addAttribute("thisYear", LocalDate.now().getYear());	// ????????????

		List<String> carMdlYearList = carMdlService.selectCarMdlYearList(carMdlNo);	// ??????????????? ??????
		model.addAttribute("carMdlYearList", carMdlYearList);
		
		List<CmnCodeVo> carMdlFileClCdList = codeService.selectCmnCodeList("101", "Y", null);	// ????????????????????????????????? ??????
		model.addAttribute("carMdlFileClCdList", carMdlFileClCdList);

		return "/form/carInfo/carMdlView";
	}

	/* ????????? ?????? ?????? */
	@RequestMapping(value="/mnfList", method=RequestMethod.GET)
	public String mnfList(
			@ModelAttribute(value="searchVo") SearchVo searchVo,
			Model model) throws Exception {

		if(searchVo.getPageNo() == null) {
			searchVo.setPageNo(1);
			searchVo.setOrdDesc(true);
		}

		List<NtnCodeVo> ntnCdList = codeService.selectNtnCodeList(new HashMap<>());
		model.addAttribute("ntnCdList", ntnCdList);

		return "/form/carInfo/mnfList";
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

		return "/empty/carInfo/mnfListCore";
	}

	/* ????????? ?????? ??? ?????? ?????? */
	@RequestMapping(value="/mnfWrite", method=RequestMethod.GET)
	public String mnfWrite(
			@ModelAttribute("mnfVo") MnfVo mnfVo,
			@RequestParam(value="mnfNo", required=false) String mnfNo,
			Model model) throws Exception {

		List<NtnCodeVo> ntnCdList = codeService.selectNtnCodeList(new HashMap<>());
		model.addAttribute("ntnCdList", ntnCdList);

		if(mnfNo != null && !mnfNo.trim().equals("")) {
			mnfVo = mnfService.selectMnf(mnfNo);
			if(mnfVo == null) {
				return MessageBox.showMsgAndBack(model, "???????????? ?????? ??????????????????.");
			}
		}
		model.addAttribute("mnfVo", mnfVo);

		return "/form/carInfo/mnfWrite";
	}

	/* ????????? ?????? ?????? */
	@RequestMapping(value="/mnfView", method=RequestMethod.GET)
	public String mnfView(
			@RequestParam(value="mnfNo") String mnfNo,
			Model model) throws Exception {

		MnfVo mnfVo = mnfService.selectMnf(mnfNo);
		if(mnfVo == null) {
			return MessageBox.showMsgAndBack(model, "???????????? ?????? ??????????????????.");
		}
		model.addAttribute("mnfVo", mnfVo);

		return "/form/carInfo/mnfView";
	}

	/* ????????? ???????????? ?????? */
	@RequestMapping(value="/mnfExcelWrite", method=RequestMethod.GET)
	public String mnfExcelWrite(
			@ModelAttribute("mnfVo") MnfVo mnfVo,
			@RequestParam(value="mnfNo", required=false) String mnfNo,
			Model model) throws Exception {

		List<NtnCodeVo> ntnCdList = codeService.selectNtnCodeList(new HashMap<>());
		String ntnJsonList = null;
		if(ntnCdList != null && ntnCdList.size() > 0) {
			ObjectMapper mapper = new ObjectMapper();
			ntnJsonList = mapper.writeValueAsString(ntnCdList);
		}
		model.addAttribute("ntnJsonList", ntnJsonList);

		if(mnfNo != null && !mnfNo.trim().equals("")) {
			mnfVo = mnfService.selectMnf(mnfNo);
		}
		model.addAttribute("mnfVo", mnfVo);

		return "/form/carInfo/mnfExcelWrite";
	}

	/* ???????????? ?????? ?????? */
	@RequestMapping(value="/ntnCdListPopCore", method=RequestMethod.GET)
	public String ntnCdListPopCore(
			@ModelAttribute(value="searchVo") SearchVo searchVo,
			Model model) throws Exception {

		Map<String, Object> map = new HashMap<>();
		map.put("search", searchVo);
		List<NtnCodeVo> ntnCdList = codeService.selectNtnCodeList(map);
		model.addAttribute("ntnCdList", ntnCdList);

		return "/empty/carInfo/ntnCdListPopCore";
	}

	/* ????????? ?????? ?????? */
	@RequestMapping(value="/mnfListPopCore", method=RequestMethod.GET)
	public String mnfListPopCore(Model model) throws Exception {

		List<Map<String, Object>> mnfList = mnfService.selectMnfAllList(new HashMap<>());
		model.addAttribute("mnfList", mnfList);

		return "/empty/carInfo/mnfListPopCore";
	}



	/*** API ***/

	/* ??????????????? ?????? ??? ?????? */
	@RequestMapping(value="/car-mdl", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertCarMdl(
			@RequestBody CarMdlVo carMdlVo) throws Exception {

		Map<String, Object> map = new HashMap<>();
		String carMdlNo = carMdlService.mergeCarMdl(carMdlVo);
		map.put("carMdlNo", carMdlNo);

		return ResponseEntity.ok(map);
	}
	
	/* ????????????????????? ?????? */
	@RequestMapping(value="/car-mdl/year", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertCarMdlYear(
			@RequestBody CarMdlVo carMdlVo) throws Exception {

		Map<String, Object> map = new HashMap<>();
		carMdlService.insertCarMdlYear(carMdlVo);
		
		return ResponseEntity.ok(map);
	}

	/* ????????? ?????? ??? ?????? */
	@RequestMapping(value="/mnf", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertMnf(
			@ModelAttribute MnfVo mnfVo) throws Exception {
		
		if((mnfVo.getMnfNo() == null || mnfVo.getMnfNo().equals("")) && mnfVo.getFile() == null) {
			throw new EntityNotFoundException("????????? ?????? ??? ????????????.");
		} else if(mnfVo.getFile() != null) {	// 1MB ??????
			long bytes = mnfVo.getFile().getSize();
			if((bytes / 1024) / 1024 > 1)
				throw new InvalidValueException("?????? ?????? 1MB??? ?????????????????????.");
		}

		Map<String, Object> map = new HashMap<>();
		try {
			String mnfNo = mnfService.mergeMnf(mnfVo);
			map.put("mnfNo", mnfNo);
		} catch(IOException e) {
			throw e;
		}

		return ResponseEntity.ok(map);
	}

	/* ????????? ?????? */
	@RequestMapping(value="/mnf", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> mnfDel(
			@RequestBody MnfVo mnfVo) throws Exception {

		if(mnfVo.getMnfNo() == null || mnfVo.getMnfNo().equals(""))
			throw new EntityNotFoundException("mnfNo not found");
		mnfService.deleteMnf(mnfVo.getMnfNo());

		return ResponseEntity.ok(new HashMap<>());
	}
	
	/* ????????? ???????????? ?????? */
	@RequestMapping(value="/mnf/srt-ord", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateMnfSrtOrd(
			@RequestBody List<String> mnfNoList) throws Exception {

		mnfService.updateMnfSrtOrd(mnfNoList);

		return ResponseEntity.ok(new HashMap<>());
	}

	/* ????????? ???????????? ?????? ?????? */
	@RequestMapping(value="/mnf/excel-parsing", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertMnf(
			@RequestParam(value="file") MultipartFile excel) throws Exception {

		if(excel == null)
			throw new EntityNotFoundException("?????? ????????? ?????? ??? ????????????.");

		Map<String, Object> map = new HashMap<>();
		String jsonList = excelService.parsingMnfExcel(excel);
		map.put("jsonArr", jsonList);

		return ResponseEntity.ok(map);
	}

	/* ????????? ????????????  */
	@RequestMapping(value="/mnf/excel", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> mnfExcelWrite(
			@RequestBody List<MnfVo> mnfList) throws Exception {

		mnfService.insertMnfList(mnfList);

		return ResponseEntity.ok(new HashMap<>());
	}

	/* ????????? ?????? ???????????? */
	@RequestMapping(value="/mnf/excel", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> mnfExcelDown() throws Exception {

		List<Map<String, Object>> mnfList = mnfService.selectMnfAllList(new HashMap<>());
		String[] columns = {"????????? ??????", "????????????", "????????????", "?????????", "?????????"};
		SXSSFWorkbook workbook = excelService.createListExcel("????????? ??????", columns, mnfList);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		os.close();
		//workbook.dispose();

		String fileNm = "????????? ??????_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String encodedFileNm = URLEncoder.encode(fileNm, "UTF-8").replace("+", "%20");

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + encodedFileNm + ".xls\"")
				.body(os.toByteArray());
	}

}
