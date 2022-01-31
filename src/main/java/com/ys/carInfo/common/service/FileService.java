package com.ys.carInfo.common.service;

import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.springframework.web.multipart.MultipartFile;

import com.ys.carInfo.common.vo.FileVo;

public interface FileService {
	/**
	 * 파일 업로드
	 * @param file, tblNm, fileTyCd, fileIdntNo
	 * @return fileNo
	 * @throws Exception
	 */
	public Integer uploadFile(MultipartFile file, String tblNm, String fileTyCd, String fileIdntNo) throws Exception;

	/**
	 * 엑셀 이미지 업로드
	 * @param xssfPictData, tblNm, fileTyCd, fileIdntNo
	 * @return fileVo
	 * @throws Exception
	 */
	public FileVo uploadExcelImgFile(XSSFPictureData xssfPictData, String tblNm, String fileTyCd, String fileIdntNo) throws Exception;

	/**
	 * 파일 정보 조회
	 * @param fileNo
	 * @return fileVo
	 * @throws Exception
	 */
	public FileVo selectAtchFile(Integer fileNo) throws Exception;

	/**
	 * 파일 삭제
	 * @param fileIdntNo
	 * @throws Exception
	 */
	public void deleteAtchFileIdntNo(String fileIdntNo) throws Exception;

	/**
	 * 파일 식별번호 수정
	 * @param fileNo, fileIdntNo
	 * @throws Exception
	 */
	public void updateAtchFileIdntNo(Integer fileNo, String fileIdntNo) throws Exception;

}
