package com.ys.carInfo.carMdl.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MnfVo implements Serializable {
	private static final long serialVersionUID = 7052526350829637556L;

	private String	mnfNo;		// 제조사번호
	private String	mnfNm;		// 제조사명
	private String 	ntnCd;		// 국가코드
	private String 	ntnCdKrNm;	// 국가코드한글명
	private String 	ntnCdEnNm;	// 국가코드영문명
	private Integer regNo;		// 등록자번호
	private Integer modNo;		// 수정자번호
	private String 	regDt;		// 등록일자

	private MultipartFile file;	// 파일
	private Integer fileNo;		// 파일번호
	private String 	orgFileNm;	// 원본파일명
	private String 	fileNm;		// 파일명
	private String 	filePathNm;	// 파일저장경로
}
