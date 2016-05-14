package demo.bean;

import java.io.Serializable;

public class PageInfoBean implements Serializable {
	private static final long serialVersionUID = 6504188009558504054L;
	
	protected int pageTotals; //總筆數
	protected int pageToNum; //到第幾頁
	protected int pageCurNum; //目前頁數
	protected int pagePerCnt; //每頁幾筆
	protected int pages; //總頁數
	
	public int getPageTotals() {
		return pageTotals;
	}
	public void setPageTotals(int pageTotals) {
		this.pageTotals = pageTotals;
	}
	public int getPageToNum() {
		return pageToNum;
	}
	public void setPageToNum(int pageToNum) {
		this.pageToNum = pageToNum;
	}
	public int getPageCurNum() {
		return pageCurNum;
	}
	public void setPageCurNum(int pageCurNum) {
		this.pageCurNum = pageCurNum;
	}
	public int getPagePerCnt() {
		return pagePerCnt;
	}
	public void setPagePerCnt(int pagePerCnt) {
		this.pagePerCnt = pagePerCnt;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
}
