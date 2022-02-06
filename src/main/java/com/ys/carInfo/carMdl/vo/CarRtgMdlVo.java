package com.ys.carInfo.carMdl.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CarRtgMdlVo implements Serializable {
	private static final long serialVersionUID = 8861599061225583019L;

	private String	carRtgMdlNo;	// 자동차등급모델번호
	private String 	carTrgMdlNm; 	// 자동차등급모델명
	private String 	carMdlNo;		// 자동차모델번호
	private String 	rlsYear;		// 출시년도
	private Integer srtOrd;			// 정렬순서
    private Integer regNo;			// 등록자번호
	private Integer modNo;			// 수정자번호

	private String	fuelKnCd;		//  연료종류코드(가솔린, 디젤, 하이브리드, LPG, 전기, 수소)
	private String	engnTyCd;       //  엔진유형코드(I3, I4, I6, V6, V8, V10, V12, F4, F6)
	private String	sprcMthdCd;     //  과급방식코드(싱글터보, 트윈터보, 자연흡기)
	private String	amnExh;         //  배기량
	private String	fuelEfcn;       //  연비
	private String	psngNum;        //  승차인원
	private String	drvMthCd;       //  구동방식코드(전륜구동, 후륜구동, 풀타임 사륜구동)
	private String	grbxTyCd;       //  변속기유형코드(수동6단, 자동5단, 자동6단, 자동7단, 자동8단, 자동10단, 토크컨버터8단, DCT7단, DCT8단)
	private String	grbxStag;       //  변속기단계
	private String	maxOtpt;        //  최대출력
	private String	maxTrq;         //  최대토크
}
