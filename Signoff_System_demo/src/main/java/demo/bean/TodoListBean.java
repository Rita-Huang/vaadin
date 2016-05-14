package demo.bean;

import java.io.Serializable;
import java.util.List;

public class TodoListBean implements Serializable {
	private static final long serialVersionUID = 6574778488885821250L;
	
	private List<TodoBean> todoListBean; //第2層.待辦清單
	private int actTotalcnt; //activitiRest查詢待辦總筆數
	private int formNoMapcnt; //對應不到AP1表單送簽資料.總筆數
	private int todoCnt; //對應到AP1表單送簽資料.待辦清單總筆數
	private PageInfoBean pageInfo; //分頁物件
	private List<TodoSummaryBean> todoSummaryBean; //第1層.待辦清單

	public List<TodoSummaryBean> getTodoSummaryBean() {
		return todoSummaryBean;
	}

	public void setTodoSummaryBean(List<TodoSummaryBean> todoSummaryBean) {
		this.todoSummaryBean = todoSummaryBean;
	}

	public List<TodoBean> getTodoListBean() {
		return todoListBean;
	}

	public void setTodoListBean(List<TodoBean> todoListBean) {
		this.todoListBean = todoListBean;
	}
	
	public void addTodoBean(TodoBean todoBean) {
		this.todoListBean.add(todoBean);
	}

	public int getActTotalcnt() {
		return actTotalcnt;
	}

	public void setActTotalcnt(int actTotalcnt) {
		this.actTotalcnt = actTotalcnt;
	}

	public int getFormNoMapcnt() {
		return formNoMapcnt;
	}

	public void setFormNoMapcnt(int formNoMapcnt) {
		this.formNoMapcnt = formNoMapcnt;
	}

	public int getTodoCnt() {
		return todoCnt;
	}

	public void setTodoCnt(int todoCnt) {
		this.todoCnt = todoCnt;
	}

	public PageInfoBean getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfoBean pageInfo) {
		this.pageInfo = pageInfo;
	}
}
