package com.ys.carInfo.carMdl.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarRtgTrimVo implements Serializable {
	private static final long serialVersionUID = -663872740201730540L;

	private String	carRtgTrimNo;	// 자동차등급트림번호
	private String	carRtgMdlNo;	// 자동차등급모델번호
	private String 	carRtgTrimNm; 	// 자동차등급트림명
	private Integer rlsPrice; 		// 출시가
	private Integer regNo;			// 등록자번호
	private Integer modNo;			// 수정자번호
}
