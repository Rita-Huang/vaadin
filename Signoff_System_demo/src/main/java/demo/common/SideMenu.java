package demo.common;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Tree.ExpandEvent;
import com.vaadin.ui.Tree.ExpandListener;
import com.vaadin.ui.Tree.ItemStyleGenerator;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import demo.MyUI;
import demo.bean.LoginBean;
import demo.bean.MenuBean;
import demo.service.LoginFormService;

public class SideMenu extends VerticalLayout {
	final static String MENU_PROPERTY_NAME = "name";
	final static String MENU_PROPERTY_VIEW_NAME = "viewName";
	List<String> hasChildrenItemId;
	Tree tree ;
	
	LoginFormService loginFormService;
	LoginBean loginBean;
	
	
	public SideMenu(LoginFormService loginFormService) {
		this.loginFormService = loginFormService;
		loginBean = new LoginBean();
		MyUI.setLoginBean(loginBean);	
		
		tree = new Tree();
		tree.setContainerDataSource(getMenuContainer());
        //關閉unselected的功能
        tree.setNullSelectionAllowed(false);
        // Add ItemClickListener to the tree
        tree.setImmediate(true);
		// Set tree to show the 'name' property as caption for items
		tree.setItemCaptionMode(ItemCaptionMode.PROPERTY);
		tree.setItemCaptionPropertyId(MENU_PROPERTY_NAME);
		
		//若有其他item是展開的狀態 先將其他item子項列表收合
		tree.addExpandListener(new ExpandListener() {
			public void nodeExpand(ExpandEvent event) {
				collapseOthersItem((String)event.getItemId());
			}
		});
		
		//item click事件: 有下一層展開menu,沒下一層轉view
		tree.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
				Boolean itemHasChildren = tree.hasChildren(event.getItemId());
				if(itemHasChildren){
					tree.expandItem(event.getItemId());
				}else{
					String viewName = event.getItem().getItemProperty(MENU_PROPERTY_VIEW_NAME).toString();
					if(viewName!=null){
						UI.getCurrent().getNavigator().navigateTo(viewName);
					}
				}
			}
		});
		
        // Expand the first node
        for (Object itemId: tree.getItemIds()){
            if(tree.expandItem(itemId))
            {
            	break;
            }
        }
		tree.setStyleName("tree-body");
		tree.setItemStyleGenerator(new ItemStyleGenerator() {
            @Override
            public String getStyle(Tree source, Object itemId) {
            		return "item_general";
            }
        });
		this.addComponent(tree);
		this.setStyleName("tree-body-layout");
		this.setWidth(300, Unit.PIXELS);
	}

	
	@SuppressWarnings("unchecked")
	public HierarchicalContainer getMenuContainer() {
		hasChildrenItemId = new ArrayList<String>();
		Item item = null;

		// Create new container
		HierarchicalContainer menuContainer = new HierarchicalContainer();
		// Create container property for caption and viewName
		menuContainer.addContainerProperty(MENU_PROPERTY_NAME, String.class, null);
		menuContainer.addContainerProperty(MENU_PROPERTY_VIEW_NAME, String.class, null);

		List<MenuBean> menu = loginFormService.getMenuList(loginBean);
		
		//user有權限進入的view
		List<String> availableViews = new ArrayList<String>();
		VaadinSession.getCurrent().setAttribute("availableView", availableViews);

		for (int i = 0; i < menu.size(); i++) {
			MenuBean itemBean = menu.get(i);
			String itemId = itemBean.getId();
			
			String viewName = itemBean.getViewName();
			if(viewName!=null){
				availableViews.add(viewName);
			}

			// Add new item
			item = menuContainer.addItem(itemId);
			// Add name property for item
			item.getItemProperty(MENU_PROPERTY_NAME).setValue(itemBean.getCaption());
			item.getItemProperty(MENU_PROPERTY_VIEW_NAME).setValue(itemBean.getViewName());

			// Allow children
			if (itemBean.getViewName() == null) { // 沒有對應到的viewName 表示有子項目
				menuContainer.setChildrenAllowed(itemId, true);
				hasChildrenItemId.add(itemId);
				tree.expandItem(itemId);
			} else {
				menuContainer.setChildrenAllowed(itemId, false);
			}

			if (itemBean.hasParent()) {
				menuContainer.setParent(itemId, itemBean.getParentId());
			}
		}
		return menuContainer;
	}
	
	private void collapseOthersItem(String expandItemId) {
		for(int i = 0; i<hasChildrenItemId.size(); i++){
			String eachItemId = hasChildrenItemId.get(i);
			if(!eachItemId.equals(expandItemId)){
				tree.collapseItem(eachItemId);
			}
		}
	}
}
