package demo;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import demo.data.Constant;
import demo.data.DemoData;

/**
 *
 */
@Theme("mytheme")
@SpringUI
public class MyUI extends UI {

	@Autowired
	private SpringViewProvider viewProvider;

	private VerticalLayout layout;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		if (VaadinSession.getCurrent().getAttribute("loginInfo") == null) {
			buildLoginView();
		} else {
			buildMainView();
		}

	}

	private void buildLoginView() {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setSizeFull();
		setContent(layout);
		
		HorizontalLayout navArea = new HorizontalLayout();
		navArea.setSpacing(true);
		layout.addComponent(navArea);
		
		CssLayout viewArea = new CssLayout();
		viewArea.setWidth(50.0f, Unit.PERCENTAGE);
		layout.addComponent(viewArea);
		layout.setExpandRatio(viewArea, 1.0f);
		Navigator navigator = new Navigator(this, viewArea);
		setNavigator(navigator);
		navigator.addProvider(viewProvider);

		getNavigator().navigateTo(LoginView.VIEW_NAME);
	}
	
	
	public void buildMainView() {
		layout = new VerticalLayout();
		setContent(layout);
		
		buildHeader(layout);
		

		HorizontalLayout navBar = new HorizontalLayout();
		buildNavBar(navBar);
		Panel viewContainer = new Panel();
		layout.addComponents(navBar, viewContainer);

		Navigator navigator = new Navigator(this, viewContainer);
		navigator.addProvider(viewProvider);
		getUI().getNavigator().navigateTo(DemoData.defaultPage);
	}
	
	private void buildHeader(VerticalLayout layout){
		HorizontalLayout header = new HorizontalLayout();
		header.addStyleName(Constant.header);
		
		Label account = new Label();
		account.setValue("unknown user");
		Button logout = new Button("登出");
		logout.setStyleName(ValoTheme.BUTTON_BORDERLESS);
		logout.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getSession().close();
				getUI().getPage().setLocation(getLogoutPageLocation());
				
			}
		});
		
		if(VaadinSession.getCurrent().getAttribute("loginInfo")!=null){
			String loginAccount = (String) VaadinSession.getCurrent().getAttribute("loginInfo");
			account.setValue(loginAccount);
		}
		
		header.addComponents(account,logout);
		header.setSpacing(true);
		
		layout.addComponent(header);
	
	}
	private String getLogoutPageLocation(){
		return "http://localhost:8080/";
	}
	

	@SuppressWarnings("serial")
	private void buildNavBar(HorizontalLayout navBar) {
		for (String e : DemoData.viewName) {
			Button button = new Button(e);
			button.addStyleName(ValoTheme.BUTTON_PRIMARY);
			button.addClickListener(new ClickListener() {
				public void buttonClick(ClickEvent event) {
					getUI().getNavigator().navigateTo(e);
				}
			});
			navBar.addComponent(button);

		}
	}

	// @SuppressWarnings("serial")
	// @WebServlet(urlPatterns = { "/*" }, asyncSupported = true)
	// public static class DefaultUIServlet extends SpringVaadinServlet {
	// }
}