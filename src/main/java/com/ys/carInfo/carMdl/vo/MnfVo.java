package com.ys.carInfo.carMdl.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MnfVo implements Serializable {
	private static final long serialVersionUID = 7052526350829637556L;

	private String	mnfNo;
	private String	mnfNm;
	private String 	ntnCd;
	private String 	ntnCdKrNm;
	private String 	ntnCdEnNm;
	private Integer regNo;
	private Integer modNo;
	private String 	regDt;

	private MultipartFile file;
}
