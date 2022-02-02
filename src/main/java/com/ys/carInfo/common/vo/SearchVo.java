package com.ys.carInfo.common.vo;

import java.io.Serializable;
import java.util.List;

public class SearchVo implements Serializable {
	private static final long serialVersionUID = -7926953500073464929L;

	private Integer pageNo;			// 페이지번호
	private Integer listCnt = 10;	// 목록 수
	private String	schText;		// 검색어
	private Boolean ordDesc;		// 정렬순서 역순
	private String 	ordDescStr;		
	private List<String> ntnCdList; 	// 국가코드 목록


	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getListCnt() {
		return listCnt;
	}
	public void setListCnt(Integer listCnt) {
		this.listCnt = listCnt;
	}
	public String getSchText() {
		return schText;
	}
	public void setSchText(String schText) {
		this.schText = schText.trim();
	}
	
	public String getOrdDescStr() {
		return ordDescStr;
	}
	public Boolean getOrdDesc() {
		return ordDesc;
	}
	public void setOrdDesc(Boolean ordDesc) {
		this.ordDesc = ordDesc;
		this.ordDescStr = (ordDesc == true ? "DESC" : "ASC");
	}
	public void setOrdDescStr(String ordDescStr) {
		this.ordDescStr = ordDescStr;
	}
	public List<String> getNtnCdList() {
		return ntnCdList;
	}
	public void setNtnCdList(List<String> ntnCdList) {
		this.ntnCdList = ntnCdList;
	}
	

}
