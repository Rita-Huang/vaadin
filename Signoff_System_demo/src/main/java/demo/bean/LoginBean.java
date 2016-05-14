package demo.bean;

import java.io.Serializable;
import java.util.List;

public class LoginBean implements Serializable {
	private static final long serialVersionUID = 7851107195138758481L;

	private static final String sysId="AP1";
	private String compId ="default";//公司別
	private String compName="default"; //公司名稱
	private String aliasName="default";//登入者帳號
	private String empNo="default"; //員工編號
	private String deptNo="default"; //部門編號
	private String engName="default"; //英文名稱
	private String chName="default"; //中文名稱
	private String ext="default"; //分機
	private List<MenuBean> menuList;
	
	public static String getSysid() {
		return sysId;
	}
	public String getCompId() {
		return compId;
	}
	public void setCompId(String compId) {
		this.compId = compId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public List<MenuBean> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuBean> menuList) {
		this.menuList = menuList;
	}
	
	
	public LoginBean(String aliasName) {
		super();
		this.aliasName = aliasName;
	}
	public LoginBean(){};
	
	
}
