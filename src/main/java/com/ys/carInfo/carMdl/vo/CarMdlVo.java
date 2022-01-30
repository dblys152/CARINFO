package com.ys.carInfo.carMdl.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarMdlVo implements Serializable {
	private static final long serialVersionUID = 4907907636568951352L;

	private String 	carMdlNo;		// 자동차모델번호
	private String	carMdlNm;		// 자동차모델명
	private String 	carMdlClCd;		// 자동차모델분류코드(대표모델, 등급모델)
    private Integer upCarMdlNo;		// 상위모델번호
    private String 	mnfNo;			// 제조사번호
    private String 	carAprnCd;		// 자동차외형코드(세단, 해치백, 컨버터블, 쿠페, 왜건, SUV, RV, 벤)
    private String 	carKnCd;		// 자동차종류코드(경형, 소형, 준중형, 중형, 중대형, 대형, 스포츠카)
    private String 	rlsYear;		// 출시년도
    private Integer srtOrd;			// 정렬순서
    private Integer regNo;			// 등록자번호
	private Integer modNo;			// 수정자번호

	private String 	carRtgTrimNm; 	// 자동차등급트림명
	private Integer rlsPrice; 		// 출시가


}
