package com.ys.carInfo.carMdl.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarInfoVo implements Serializable {
	private static final long serialVersionUID = 4907907636568951352L;

	private Integer mdlNo;		// 모델번호
	private String	mdlNm;		// 모델명
	private String 	mdlClCd;	// 모델분류코드(대표모델, 연식모델, 등급모델, 상세모델)
    private Integer upMdlNo;	// 상위모델번호
    private String 	mnfNo;		// 제조사번호
    private String 	carAprnCd;	// 자동차외형코드(세단, 해치백, 컨버터블, 쿠페, 왜건, SUV, RV, 벤)
    private String 	carKnCd;	// 자동차종류코드(경형, 소형, 준중형, 중형, 중대형, 대형, 스포츠카)
    private String 	rlsYear;	// 출시년도
    private Integer rlsPrice;	// 출시가 
    private Integer srtOrd;		// 정렬순서

}
