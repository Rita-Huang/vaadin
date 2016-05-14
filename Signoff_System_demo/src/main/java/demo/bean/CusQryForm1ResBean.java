package demo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class CusQryForm1ResBean  implements Serializable {
	private static final long serialVersionUID = 5080152605462286941L;

	private String _key; //已建立,儲存的表單PK
	private String startUserId; //開單人員編(APPL.CREATE_USER)
	private String startUserName; //開單人英文名稱
	private Timestamp startDate; //開單日期(APPL.CREATE_DATE)
	private String formDesc; //工作項目(APPL.APPL_DESC)
	private Timestamp expectDate; //預計完成日期(APPL.EST_CMPL_DATE)
	private boolean isSubmitFlow=false; //表單是否已送審(true/false)
	
	public String get_key() {
		return _key;
	}
	public void set_key(String _key) {
		this._key = _key;
	}
	public String getStartUserId() {
		return startUserId;
	}
	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}
	public String getStartUserName() {
		return startUserName;
	}
	public void setStartUserName(String startUserName) {
		this.startUserName = startUserName;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public String getFormDesc() {
		return formDesc;
	}
	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}
	public Timestamp getExpectDate() {
		return expectDate;
	}
	public void setExpectDate(Timestamp expectDate) {
		this.expectDate = expectDate;
	}
	public boolean isSubmitFlow() {
		return isSubmitFlow;
	}
	public void setSubmitFlow(boolean isSubmitFlow) {
		this.isSubmitFlow = isSubmitFlow;
	}
}
