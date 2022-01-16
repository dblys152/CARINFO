package com.ys.carInfo.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class SearchVo implements Serializable {
	private static final long serialVersionUID = -7926953500073464929L;

	private Integer pageNo;			// 페이지번호
	private Integer listCnt = 10;	// 목록 수
	private String	schText;		// 검색어


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


}
