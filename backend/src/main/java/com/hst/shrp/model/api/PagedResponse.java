package com.hst.shrp.model.api;

import com.github.pagehelper.Page;

/**
 * @author dlgusrb0808@gmail.com
 */
public class PagedResponse {
	private int page;
	private int size;
	private int totalPages;

	protected void setPageInformation(Page<?> resultPage) {
		this.page = resultPage.getPageNum();
		this.size = resultPage.getPageSize();
		this.totalPages = resultPage.getPages();
	}

	public int getPage() {
		return page;
	}

	public int getSize() {
		return size;
	}

	public int getTotalPages() {
		return totalPages;
	}

}
