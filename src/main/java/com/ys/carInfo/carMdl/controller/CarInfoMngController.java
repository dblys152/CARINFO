package com.ys.carInfo.carMdl.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ys.carInfo.carMdl.service.CarMdlService;
import com.ys.carInfo.carMdl.service.MnfService;
import com.ys.carInfo.carMdl.vo.MnfVo;
import com.ys.carInfo.common.service.CodeService;
import com.ys.carInfo.common.service.ExcelService;
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
	@Autowired private ExcelService excelService;

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

	/* 제조사 등록 및 수정 화면 */
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

	/* 제조사 등록 및 수정 */
	@RequestMapping(value="/mnfWrite", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertMnf(
			@ModelAttribute MnfVo mnfVo) throws Exception {

		Map<String, Object> map = new HashMap<>();
		try {
			String mnfNo = mnfService.mergeMnf(mnfVo);
			map.put("mnfNo", mnfNo);
		} catch(EntityNotFoundException e) {
			throw e;
		} catch(IOException e) {
			throw e;
		}

		return ResponseEntity.ok(map);
	}

	/* 제조사 일괄등록 화면 */
	@RequestMapping(value="/mnfExcelWrite", method=RequestMethod.GET)
	public String mnfExcelWrite(
			@ModelAttribute("mnfVo") MnfVo mnfVo,
			@RequestParam(value="mnfNo", required=false) String mnfNo,
			Model model) throws Exception {

		List<NtnCodeVo> ntnCdList = codeService.selectNtnCdList(new HashMap<>());
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

		return "/form/carInfo/carInfoMnfExcelWrite";
	}

	@RequestMapping(value="/mnfExcelParser", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertMnf(
			@RequestParam(value="file") MultipartFile excel) throws Exception {

		if(excel == null)
			throw new EntityNotFoundException("Excel not found");

		Map<String, Object> map = new HashMap<>();
		String jsonArr = excelService.parsingMnfExcel(excel);
		map.put("jsonArr", jsonArr);

		return ResponseEntity.ok(map);
	}

	/* 제조사 상세 화면 */
	@RequestMapping(value="/mnfView", method=RequestMethod.GET)
	public String mnfView(
			@RequestParam(value="mnfNo") String mnfNo,
			Model model) throws Exception {

		MnfVo mnfVo = mnfService.selectMnf(mnfNo);
		if(mnfVo == null) {

		}
		model.addAttribute("mnfVo", mnfVo);

		return "/form/carInfo/carInfoMnfView";
	}

	/* 제조사 삭제 */
	@RequestMapping(value="/mnfDel", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> mnfDel(
			@ModelAttribute MnfVo mnfVo) throws Exception {

		if(mnfVo.getMnfNo() == null)
			throw new EntityNotFoundException("mnfNo not found");
		mnfService.deleteMnf(mnfVo.getMnfNo());

		return ResponseEntity.ok(new HashMap<>());
	}

	/* 제조사 엑셀 다운로드 */
	@RequestMapping(value="/mnfExcelDown", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> mnfExcelDown() throws Exception {

		List<Map<String, Object>> mnfList = mnfService.selectMnfAllList(new HashMap<>());
		String[] columns = {"제조사 로고", "제조사명", "제조국", "등록일"};
		SXSSFWorkbook workbook = excelService.createListExcel("제조사 목록", columns, mnfList);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		os.close();
		//workbook.dispose();

		String fileNm = "제조사 목록_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String encodedFileNm = URLEncoder.encode(fileNm, "UTF-8").replace("+", "%20");

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + encodedFileNm + ".xls\"")
				.body(os.toByteArray());
	}

}
