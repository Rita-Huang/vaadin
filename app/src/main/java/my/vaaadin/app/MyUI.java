package my.vaaadin.app;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@Widgetset("my.vaaadin.app.MyAppWidgetset")
public class MyUI extends UI {
	private CustomerService service = CustomerService.getInstance();
	private Grid grid ;
	private CustomerForm form;
	private TextField filterText;

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout layout = new VerticalLayout();
		layout.addComponents(buildToolbar(), buildMain());
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);
	}
	
	public HorizontalLayout buildToolbar(){
		CssLayout filtering = buildFiltering();
		
		Button addCustomerBtn = new Button("Add new customer");
		addCustomerBtn.addClickListener(e -> {
		    grid.select(null);
		    form.setCustomer(new Customer());
		});
		
		HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn);
		toolbar.setSpacing(true);
		return toolbar;
	}

	
	public CssLayout buildFiltering(){
		filterText = new TextField();
		filterText.setInputPrompt("filter by name...");
//		filterText.addTextChangeListener(e -> {
//			grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, service.findAll(e.getText())));
//		});
		filterText.addTextChangeListener(new TextChangeListener() {
			public void textChange(TextChangeEvent event) {
				grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, service.findAll(event.getText())));
				
			}
		});
		
		
		Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
		clearFilterTextBtn.setDescription("Clear the current filter");
//		clearFilterTextBtn.addClickListener(e -> {
//		  filterText.clear();
//		  updateList();
//		});
		clearFilterTextBtn.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				filterText.clear();
				updateList();
			}
		});
		
		CssLayout filtering = new CssLayout();
		filtering.addComponents(filterText, clearFilterTextBtn);
		filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		
		return filtering;
	}
	
	
	
	
	
	public HorizontalLayout buildMain(){
		grid = new Grid();
		grid.setSizeFull();
		gridSelectionListener();
		updateList();
		
		form = new CustomerForm(this);
		form.setVisible(false);
		
		HorizontalLayout main = new HorizontalLayout(grid, form);//
		main.setSpacing(true);
		main.setSizeFull();
//		main.setExpandRatio(grid, 1);
		
		return main;
	}
	
	public void gridSelectionListener(){
		grid.addSelectionListener(new SelectionListener() {
			public void select(SelectionEvent event) {
				 if (event.getSelected().isEmpty()) {
				        form.setVisible(false);
				    } else {
				        Customer customer = (Customer) event.getSelected().iterator().next();
				        form.setCustomer(customer);
				    }
			}
		});
//		grid.addSelectionListener(event -> {
//		    if (event.getSelected().isEmpty()) {
//		        form.setVisible(false);
//		    } else {
//		        Customer customer = (Customer) event.getSelected().iterator().next();
//		        form.setCustomer(customer);
//		    }
//		});
	}
	
	
	public void updateList() {
		// fetch list of Customers from service and assign it to Grid
		List<Customer> customers = service.findAll(filterText.getValue());
		grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, customers));
		grid.setColumns("firstName", "lastName", "email");
	}
	
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
