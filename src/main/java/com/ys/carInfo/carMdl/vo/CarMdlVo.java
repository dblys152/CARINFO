package com.ys.carInfo.carMdl.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarMdlVo implements Serializable {
	private static final long serialVersionUID = 4907907636568951352L;

	private String 	carMdlNo;		// 자동차모델번호
	private String	carMdlNm;		// 자동차모델명
    private String 	mnfNo;			// 제조사번호
    private String 	carAprnCd;		// 자동차외형코드(세단, 해치백, 컨버터블, SUV, 쿠페, 왜건, RV, 벤, 트럭)
    private String 	carAprnNm;
    private String 	carKnCd;		// 자동차종류코드(경형, 소형, 준중형, 중형, 중대형, 대형, 스포츠카)
    private String 	carKnNm;
    private Integer srtOrd;			// 정렬순서
    private Integer regNo;			// 등록자번호
	private Integer modNo;			// 수정자번호

	private String 	rlsYear;		// 출시년도

	private Integer mnfFileNo; 		// 제조사 파일번호
	private String 	regDt; 			// 등록일
}
