package com.ys.carInfo.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.carInfo.common.vo.FileVo;


@Mapper
public interface FileMapper {

	public void insertFile(FileVo fileVo) throws Exception;

	public FileVo selectFile(Integer fileNo) throws Exception;

	public void deleteFile(Map<String, Object> map) throws Exception;

}
