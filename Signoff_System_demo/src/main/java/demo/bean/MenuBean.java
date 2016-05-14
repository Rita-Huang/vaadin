package demo.bean;

public class MenuBean {
	
	private String id;
	private String caption;
	private String viewName;
	private String parentId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	
	public boolean hasParent(){
		if(parentId == null){
			return false;
		}
		return true;
	}
	
	public MenuBean(){}
	
	/**
	 * 第一層項目的建構子
	 * @param id
	 * @param caption 列表顯示名稱
	 */
	public MenuBean(String id, String caption) {
		super();
		this.id = id;
		this.caption = caption;
	}
	
	/**
	 * 第一層項目具連結功能的建構子
	 * @param id
	 * @param caption 列表顯示名稱
	 * @param viewName 對應到的viewName
	 */
	public MenuBean(String id, String caption, String viewName) {
		super();
		this.id = id;
		this.caption = caption;
		this.viewName = viewName;
	}
	
	/**
	 * 子項目具連結功能的建構子
	 * @param id
	 * @param caption 列表顯示名稱
	 * @param viewName 對應到的viewName
	 * @param parentId 
	 */
	public MenuBean(String id, String caption, String viewName, String parentId) {
		super();
		this.id = id;
		this.caption = caption;
		this.viewName = viewName;
		this.parentId = parentId;
	}
	@Override
	public String toString() {
		return "MenuBean [id=" + id + ", caption=" + caption + ", viewName=" + viewName + ", parentId=" + parentId
				+ "]";
	}

}
