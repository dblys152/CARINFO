package com.ys.carInfo.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileVo implements Serializable {
	private static final long serialVersionUID = 2365994559368367986L;

	private Integer fileNo;
	private String 	tblNm;
	private String	fileIdntNo;
	private String	fileTyCd;
	private Integer	fileSrtOrd;
	private String	filePathNm;
	private String	orgFileNm;
	private String	fileNm;
	private String	fileExtNm;
	private String	mimeTy;
	private Integer fileSize;
	private Integer fileDownCnt;
	private String	caption;
	private Integer regNo;
	private Integer modNo;

}
