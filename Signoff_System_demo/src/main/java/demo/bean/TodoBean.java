package demo.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class TodoBean implements Serializable {
	private static final long serialVersionUID = 4978278704366932807L;
	
	public static final String task_compeleteFlowType_approve="next";
	public static final String task_compeleteFlowType_back="previous";
	public static final String task_compeleteFlowType_reject="first";
	
	private String task_procInstId; //流程實體ID
	private String task_taskId;  //待辦關卡ID
	private String task_taskName; //待辦關卡名稱
	private String form_startUserId; //開單人員編
	private String form_startUserName; //開單人中文姓名
	private Timestamp form_startDate; //開單日
	private Timestamp task_createDate; //進件日
	private String task_completeFlowType; //審核關卡流程參數(approve/back/reject)
	
	public String getTask_procInstId() {
		return task_procInstId;
	}
	public void setTask_procInstId(String task_procInstId) {
		this.task_procInstId = task_procInstId;
	}
	public String getTask_taskId() {
		return task_taskId;
	}
	public void setTask_taskId(String task_taskId) {
		this.task_taskId = task_taskId;
	}
	public String getTask_taskName() {
		return task_taskName;
	}
	public void setTask_taskName(String task_taskName) {
		this.task_taskName = task_taskName;
	}
	public String getForm_startUserId() {
		return form_startUserId;
	}
	public void setForm_startUserId(String form_startUserId) {
		this.form_startUserId = form_startUserId;
	}
	public String getForm_startUserName() {
		return form_startUserName;
	}
	public void setForm_startUserName(String form_startUserName) {
		this.form_startUserName = form_startUserName;
	}
	public Timestamp getForm_startDate() {
		return form_startDate;
	}
	public void setForm_startDate(Timestamp form_startDate) {
		this.form_startDate = form_startDate;
	}
	public Timestamp getTask_createDate() {
		return task_createDate;
	}
	public void setTask_createDate(Timestamp task_createDate) {
		this.task_createDate = task_createDate;
	}
	public String getTask_completeFlowType() {
		return task_completeFlowType;
	}
	public void setTask_completeFlowType(String task_completeFlowType) {
		this.task_completeFlowType = task_completeFlowType;
	}
}
