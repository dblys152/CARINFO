package com.ys.carInfo.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileVo implements Serializable {
	private static final long serialVersionUID = 2365994559368367986L;

	private Integer fileNo;			// 파일번호
	private String 	tblNm;			// 테이블명
	private String	fileIdntNo;		// 파일식별번호
	private String	fileTyCd;		// 파일유형코드(첨부파일, 이미지)
	private Integer	fileSrtOrd;		// 파일정렬순서
	private String	filePathNm;		// 파일저장경로
	private String	orgFileNm;		// 원본파일명
	private String	fileNm;			// 파일명
	private String	fileExtNm;		// 파일확장자명
	private String	mimeTy;			// 마임타입
	private long 	fileSize;		// 파일크기(byte) 
	private Integer fileDownCnt;	// 파일다운로드수
	private String	caption;		// 캡션
	private Integer regNo;			// 등록자번호
	private Integer modNo;			// 수정자번호

}
