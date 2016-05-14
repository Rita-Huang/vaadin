package demo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Form1Bean implements Serializable {
	private static final long serialVersionUID = 5930473145628901257L;

	private String _key_formDef; //表單定義檔的PK
	private String formCode; //表單別代碼
	private String formName; //表單別名稱
	private String procDefId; //表單對應process definitionID
	private String _key; //已建立,儲存的表單PK
	private String _key_procInst; //已起案成功之實體流程PK
	//表單內容欄位值
	private String startUserId; //開單人員編(APPL.CREATE_USER)
	private String startUserName; //開單人英文名稱
	private Timestamp startDate; //開單日期(APPL.CREATE_DATE)
	private String formDesc; //工作項目(APPL.APPL_DESC)
	private Timestamp expectDate; //預計完成日期(APPL.EST_CMPL_DATE)
	private Timestamp updateDate;//修改表單日期(APPL.UPDATE_DATE)
	private String updateUserId;//修改表單人員編(APPL.UPDATE_USER)
	private Timestamp deleteDate;//刪除表單日期(APPL.DELETE_DATE)
	private String deleteUserId;//刪除表單人員編(APPL.DELETE_USER)
	private String deleteFlag;//刪除表單日期(APPL.DELETE_FLAG)
	
	public String getFormCode() {
		return formCode;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getProcDefId() {
		return procDefId;
	}
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	public String get_key() {
		return _key;
	}
	public void set_key(String _key) {
		this._key = _key;
	}
	public String get_key_procInst() {
		return _key_procInst;
	}
	public void set_key_procInst(String _key_procInst) {
		this._key_procInst = _key_procInst;
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
	public String get_key_formDef() {
		return _key_formDef;
	}
	public void set_key_formDef(String _key_formDef) {
		this._key_formDef = _key_formDef;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public Timestamp getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Timestamp deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getDeleteUserId() {
		return deleteUserId;
	}
	public void setDeleteUserId(String deleteUserId) {
		this.deleteUserId = deleteUserId;
	}
}
