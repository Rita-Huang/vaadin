package demo;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import demo.data.Constant;
import demo.data.DemoData;

@SuppressWarnings("serial")
@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends Panel implements View{
	public static final String VIEW_NAME = "";

	@Override
	public void enter(ViewChangeEvent event) {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		buildAccountButton(layout);
		
		
		this.setContent(layout);
		this.addStyleName(Constant.MyTheme_Panel);
		this.setIcon(FontAwesome.REORDER);
		this.setCaption("選擇登入人員");
		

		
		
		
	}

	private void buildAccountButton(VerticalLayout accountButtons){
		for(String e : DemoData.account){
			Button button = new Button(e);
			button.setStyleName(ValoTheme.BUTTON_PRIMARY);
			button.addClickListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					VaadinSession.getCurrent().setAttribute("loginInfo", event.getButton().getCaption());
					if(getUI() != null && (MyUI)getUI() != null) {
						((MyUI)getUI()).buildMainView();
					}
				}
			}) ;
			accountButtons.addComponent(button);
		}
	}
}
