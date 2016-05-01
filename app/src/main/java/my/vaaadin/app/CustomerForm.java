package my.vaaadin.app;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class CustomerForm extends FormLayout{
	private CustomerService service = CustomerService.getInstance();
	private Customer customer;
	private MyUI myUI;
	
	private TextField firstName = new TextField("First name");
	private TextField lastName = new TextField("Last name");
	private TextField email = new TextField("Email");
	private NativeSelect status = new NativeSelect("Status");
	private PopupDateField birthdate = new PopupDateField("Birthday");
	private Button save = new Button("Save");
	private Button delete = new Button("Delete");
	
	HorizontalLayout name= new HorizontalLayout();
	HorizontalLayout btns = new HorizontalLayout();
	
	public CustomerForm(){
		
		name.addComponents(firstName,lastName);
		name.setCaption("Name");
		name.setSpacing(true);
		
		status.addItems(CustomerStatus.values());
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);
//		save.addClickListener(e->this.save());
		save.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				save();
			}
		});
		
		
		delete.setStyleName(ValoTheme.BUTTON_DANGER);
//		delete.addClickListener(e->this.delete());
		delete.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				delete();
			}
		});
		
		btns.addComponents(save,delete);
		btns.setSpacing(true);
		
		addComponents(name,email,status,birthdate,btns);
		setSizeUndefined();
	}
	
	public CustomerForm(MyUI myUI) {
		this();
		this.myUI = myUI;
	}
	
	public void setCustomer(Customer customer) {
	    this.customer = customer;
	    BeanFieldGroup.bindFieldsUnbuffered(customer, this);

	    // Show delete button for only customers already in the database
	    delete.setVisible(customer.isPersisted());
	    setVisible(true);
	    firstName.selectAll();
	}
	
	private void delete() {
	    service.delete(customer);
	    myUI.updateList();
	    setVisible(false);
	}

	private void save() {
	    service.save(customer);
	    myUI.updateList();
	    setVisible(false);
	}
	
}
