package com.ys.carInfo.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NtnCodeVo implements Serializable {
	private static final long serialVersionUID = -287985536065225569L;

	private String 	ntnCd;		// 국가코드
	private String	ntnCdKrNm;	// 국가코드한글명
	private String 	ntnCdEnNm;	// 국가코드영문
    private String	useYn;		// 사용여부

}
