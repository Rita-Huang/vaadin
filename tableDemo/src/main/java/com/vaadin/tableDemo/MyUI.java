package com.vaadin.tableDemo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.vaadin.tableDemo.MyAppWidgetset")
public class MyUI extends UI {

	private VerticalLayout main;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	Panel layout =  new Panel();
        layout.setContent(buildContent());
        setContent(layout);
    }

	private HorizontalLayout buildContent() {
		HorizontalLayout content = new HorizontalLayout();
        content.addComponents(buildButtonsArea(),buildMain());
        content.setMargin(true);
        content.setSpacing(true);
        content.setSizeFull();
        content.setExpandRatio(main, 1);
		return content;
	}

	private VerticalLayout buildMain() {
		main = new VerticalLayout();
		main.setSizeFull();
		return main;
	}

	private HorizontalLayout buildButtonsArea() {
		HorizontalLayout buttons = new HorizontalLayout();
		VerticalLayout  column1 = new VerticalLayout();
		VerticalLayout  column2 = new VerticalLayout();
		column1.setSpacing(true);
		column2.setSpacing(true);
        buttons.addComponents(column1,column2);
		
		String[] contextType = TableExample.contextType;
		for (int i = 0;i<contextType.length;i++) {
			Button btn = new Button(contextType[i]);
			btn.addClickListener(new Button.ClickListener() {
				public void buttonClick(ClickEvent event) {
					String caption = event.getButton().getCaption();
					changeMainContent(caption);
				}
			});
			
			if(i%2==0){
				column1.addComponent(btn);
			}else{
				column2.addComponent(btn);
			}
		}

		return buttons;   
	}

	public void changeMainContent(String caption){
//      layout.addComponents(new TableExample(e));
		int comp = main.getComponentCount();
		if(comp!=0){
			main.removeAllComponents();
		}
		
		TableExample table = new TableExample(caption);
		main.addComponent(new Label(caption));
		main.addComponent(table);
		main.setExpandRatio(table, 1);
	}
	
	
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
