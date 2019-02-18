package com.pansky.util;

import org.springframework.stereotype.Component;

@Component
public class PagingBean {
	private String pageNo;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	
	

}
