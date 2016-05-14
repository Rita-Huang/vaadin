package demo.bean;

import java.io.Serializable;

public class TodoSummaryBean implements Serializable {
	private static final long serialVersionUID = 3294374550743087159L;
	
	private String todoFormCode;
	private String todoFormType;
	private String todoNumber;
	
	public String getTodoFormCode() {
		return todoFormCode;
	}
	public void setTodoFormCode(String todoFormCode) {
		this.todoFormCode = todoFormCode;
	}
	public String getTodoFormType() {
		return todoFormType;
	}
	public void setTodoFormType(String todoFormType) {
		this.todoFormType = todoFormType;
	}
	public String getTodoNumber() {
		return todoNumber;
	}
	public void setTodoNumber(String todoNumber) {
		this.todoNumber = todoNumber;
	}
	public TodoSummaryBean(String todoFormType, String todoNumber) {
		this.todoFormType = todoFormType;
		this.todoNumber = todoNumber;
	}
	public TodoSummaryBean() {
	}
}
