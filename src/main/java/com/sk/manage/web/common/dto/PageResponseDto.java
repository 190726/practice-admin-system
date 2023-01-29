package com.sk.manage.web.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class PageResponseDto {
	
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	@Builder
	public PageResponseDto(int pageNumber, int pageSize, int totalPages) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPages = totalPages;
		this.startPage = Math.floorDiv(pageNumber , pageSize) * pageSize + 1;
		this.endPage = (startPage + pageSize - 1) > totalPages ? totalPages : (startPage + pageSize - 1);
	}

	public void setPageInfo(PageResponseDto page) {
		this.pageNumber = page.getPageNumber();
		this.pageSize = page.getPageSize();
		this.totalPages = page.getTotalPages();
		this.startPage = page.getStartPage();
		this.endPage = page.getEndPage();
	}
	
}