package com.ys.carInfo.carMdl.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarInfoVo implements Serializable {
	private static final long serialVersionUID = 4907907636568951352L;

	private Integer mdlNo;
	private String	mdlNm;
	private String 	mdlClCd;
    private Integer upMdlNo;
    private String 	rlsYear;
    private Integer rlsPrice;
    private String 	mnfNo;
    private String 	carAprnCd;
    private String 	carKnCd;
    private Integer srtOrd;

}
