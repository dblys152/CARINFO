package com.ys.carInfo.carMdl.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarMdlFileVo implements Serializable {
	private static final long serialVersionUID = 5161590043573893336L;

	private String 	carMdlNo;		// 자동차모델번호
	private String 	rlsYear;		// 출시년도
	private String 	fileSeq;		// 파일순번
	private String 	carMdlFileClCd;	// 자동차모델파일분류코드(익스테리어, 인테리어, 컬러)
	private String 	fileTyCd;       // 파일유형코드(첨부파일, 이미지파일)
	private String 	fileSrtOrd;     // 파일정렬순서
	private String 	filePathNm;     // 파일경로명
	private String 	orgFileNm;      // 원본파일명
	private String 	fileNm;         // 파일명
	private String 	fileExtNm;      // 파일확장자명
	private String 	mimeTy;         // 마임타입
	private String 	fileSize;       // 파일크기(byte)
	private String 	fileDownCnt;    // 파일다운횟수
	private Integer regNo;			// 등록자번호
	private Integer modNo;			// 수정자번호
}
