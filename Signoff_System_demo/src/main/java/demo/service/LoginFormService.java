package demo.service;

import java.util.List;

import demo.bean.LoginBean;
import demo.bean.MenuBean;

public interface LoginFormService {
	public static final String sysId = "AP1";
	
	/**
	 * 登入身份認證
	 * @return
	 */
	public LoginBean loginAutheticate(String compId,String aliasName);
	
	/**
	 * 取得目前登入身份之功能清單
	 * @param loginBean
	 * @return :List<MenuBean>
	 */
	public List<MenuBean> getMenuList(LoginBean loginBean);
	
	/**
	 * 取得該登入者英文姓名
	 * @param aliasName
	 * @return
	 */
	public String getUserEngName(String compId,String aliasName);
	
	/**
	 * 取得該登入者英文姓名
	 * @param empNo
	 * @return
	 */
	public String getUserEngNameByEmpno(String compId,String empNo);
	
	/**
	 * 由員工編號(empNo)取得該登入者帳號(aliasName)
	 * @param compId
	 * @param empNo
	 * @return
	 */
	public String getUserAliasName(String compId,String empNo);
	
}
