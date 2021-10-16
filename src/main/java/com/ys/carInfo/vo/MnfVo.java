package com.ys.carInfo.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MnfVo {

	private String	mnfNo;
	private String	mnfNm;
	private String 	ntnCd;
	private Integer regNo;
	private Integer modNo;

	private MultipartFile file;
}
