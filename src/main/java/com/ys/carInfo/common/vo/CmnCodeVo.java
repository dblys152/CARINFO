package com.ys.carInfo.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CmnCodeVo implements Serializable {
	private static final long serialVersionUID = -5361170674921840991L;

	private String 	cmnCd;		// 공통코드
	private String	cmnCdNm;	// 공통코드명
	private String 	upCmnCd;	// 상위공통코드
	private String 	useYn;		// 사용여부
    private Integer	srtOrd;		// 정렬순서
    private String 	note;		// 비고

}
