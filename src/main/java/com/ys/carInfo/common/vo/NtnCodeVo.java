package com.ys.carInfo.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NtnCodeVo implements Serializable {
	private static final long serialVersionUID = -287985536065225569L;

	private String 	ntnCd;
	private String	ntnCdKrNm;
	private String 	ntnCdEnNm;
    private String	useYn;

}
