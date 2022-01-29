package com.ys.carInfo.common.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.carInfo.common.vo.FileVo;


@Mapper
public interface FileMapper {

	public void insertAtchFile(FileVo fileVo) throws Exception;

	public FileVo selectAtchFile(Integer fileNo) throws Exception;

	public void deleteAtchFileIdntNo(Map<String, Object> map) throws Exception;

	public void updateAtchFileIdntNo(Map<String, Object> map) throws Exception;
}
