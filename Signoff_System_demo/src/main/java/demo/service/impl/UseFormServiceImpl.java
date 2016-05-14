package demo.service.impl;

import org.springframework.stereotype.Service;

import com.vaadin.spring.annotation.SpringComponent;

import demo.bean.CompleteBean;
import demo.bean.CusQryForm1Bean;
import demo.bean.Form1Bean;
import demo.bean.LoginBean;
import demo.bean.PageInfoBean;
import demo.bean.TodoBean;
import demo.bean.TodoListBean;
import demo.bean.TodoSummaryBean;
import demo.service.UseFormService;

@Service("useFormService")
@SpringComponent
public class UseFormServiceImpl implements UseFormService {

	@Override
	public Form1Bean saveForm(Form1Bean formBean, LoginBean loginBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Form1Bean submitForm(Form1Bean formBean, LoginBean loginBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Form1Bean queryFormDetail(String processInstantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TodoListBean queryTodoList(LoginBean loginBean, TodoSummaryBean todoSumBean, PageInfoBean pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompleteBean completeTask(LoginBean loginBean, TodoBean todoBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TodoListBean queryTodoSummary(LoginBean loginBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Form1Bean deleteForm(LoginBean loginBean, Form1Bean formBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CusQryForm1Bean customQryFormList(CusQryForm1Bean cusQryForm1Bean) {
		// TODO Auto-generated method stub
		return null;
	}	
}
