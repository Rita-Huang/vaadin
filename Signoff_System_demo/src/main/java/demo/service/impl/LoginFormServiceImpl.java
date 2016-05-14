package demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vaadin.spring.annotation.SpringComponent;

import demo.bean.LoginBean;
import demo.bean.MenuBean;
import demo.data.DemoData;
import demo.service.LoginFormService;

@Service("loginFormService")
@SpringComponent
public class LoginFormServiceImpl implements LoginFormService {

	@Override
	public LoginBean loginAutheticate(String compId, String aliasName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuBean> getMenuList(LoginBean loginBean) {
		List<MenuBean> menu = new ArrayList<>();
		menu.add(new MenuBean("01","功能選單",null,null));
		
		Integer id = 2;
		for(int i = 0 ; i< DemoData.viewName.length ; i++){
			MenuBean bean = new MenuBean();
			bean.setId(id.toString());
			bean.setCaption(DemoData.viewCaption[i]);
			bean.setViewName(DemoData.viewName[i]);
			bean.setParentId("01");
			menu.add(bean);
			id++;
		}
		return menu;
	}

	@Override
	public String getUserEngName(String compId, String aliasName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserEngNameByEmpno(String compId, String empNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserAliasName(String compId, String empNo) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
}
