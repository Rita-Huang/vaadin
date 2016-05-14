package demo.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class CusQryForm1Bean  implements Serializable {
	private static final long serialVersionUID = -5643656519216457870L;

	//查詢條件
	private String startUserId; //開單人員編(APPL.CREATE_USER)
	private Timestamp startDateS; //開單日期(APPL.CREATE_DATE)
	private Timestamp startDateE; //開單日期(APPL.CREATE_DATE)
	private String formDesc; //工作項目(APPL.APPL_DESC)
	private Timestamp expectDateS; //預計完成日期(APPL.EST_CMPL_DATE)
	private Timestamp expectDateE; //預計完成日期(APPL.EST_CMPL_DATE)
	private PageInfoBean pageInfoBean; //分頁/換頁元件
	
	//查詢結果
	private List<CusQryForm1ResBean> cusQryForm1ResList;

	public String getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}

	public Timestamp getStartDateS() {
		return startDateS;
	}

	public void setStartDateS(Timestamp startDateS) {
		this.startDateS = startDateS;
	}

	public Timestamp getStartDateE() {
		return startDateE;
	}

	public void setStartDateE(Timestamp startDateE) {
		this.startDateE = startDateE;
	}

	public String getFormDesc() {
		return formDesc;
	}

	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}

	public Timestamp getExpectDateS() {
		return expectDateS;
	}

	public void setExpectDateS(Timestamp expectDateS) {
		this.expectDateS = expectDateS;
	}

	public Timestamp getExpectDateE() {
		return expectDateE;
	}

	public void setExpectDateE(Timestamp expectDateE) {
		this.expectDateE = expectDateE;
	}

	public PageInfoBean getPageInfoBean() {
		return pageInfoBean;
	}

	public void setPageInfoBean(PageInfoBean pageInfoBean) {
		this.pageInfoBean = pageInfoBean;
	}

	public List<CusQryForm1ResBean> getCustomQryForm1ResBean() {
		return cusQryForm1ResList;
	}

	public void setCustomQryForm1ResBean(List<CusQryForm1ResBean> cusQryForm1ResList) {
		this.cusQryForm1ResList = cusQryForm1ResList;
	}
}
