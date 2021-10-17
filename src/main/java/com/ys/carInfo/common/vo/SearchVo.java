package com.ys.carInfo.common.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchVo implements Serializable {
	private static final long serialVersionUID = -7926953500073464929L;

	private Integer cp = 1;
	private Integer listCnt = 10;
	private String	searchText;

}
