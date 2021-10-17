package com.ys.carInfo.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CmnCodeVo implements Serializable {
	private static final long serialVersionUID = -5361170674921840991L;

	private String 	cmnCd;
	private String	cmnCdNm;
	private String 	upCmnCd;
	private String 	useYn;
    private Integer	srtOrd;
    private String 	note;

}
