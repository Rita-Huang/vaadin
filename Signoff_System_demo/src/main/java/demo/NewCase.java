package demo;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.UI;

import demo.component.FormComponent;
import demo.event.ChangeHandler;
import demo.util.DateUtils;
import demo.util.DateValidator;

/**
 * 新增表單View
 */
@SuppressWarnings("serial")
@SpringView(name = NewCase.VIEW_NAME)
public class NewCase extends CustomComponent implements View {
	
	public static final String VIEW_NAME = "NewCase";

	private FormComponent formComponent;
	
	@Autowired
	public NewCase(FormComponent formComponent){
		this.formComponent = formComponent;
		
		setCompositionRoot(formComponent);
		setSizeFull();
		setFormComponentBtnEvent();
		addValidator();
		formComponent.getSubmitButton().setVisible(false);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		formComponent.initData();
	}
	
	/** 增加Validator */
	private void addValidator() {
		addDateValidator(formComponent.getFinishDateField());
		formComponent.getFinishDateField().setRequired(true);
		formComponent.getWorkItemTextArea().setRequired(true);
		formComponent.getWorkItemTextArea().setNullRepresentation("");
	}

	/** 增加日期Validator */
	private void addDateValidator(DateField dateField) {
		dateField.setDateFormat(DateUtils.FMT_YYYYMMDD_SLASH);
		dateField.setImmediate(true);
		dateField.setValidationVisible(true);
		dateField.addValidator(new DateValidator());
	}
	
	/** 設定FormComponent Button Click 事件 */
	public void setFormComponentBtnEvent(){
		formComponent.setSaveBtnClickEvent(new ChangeHandler(){
			public void onChange(Event event) {}
		});
		formComponent.setNewCaseBtnClickEvent(new ChangeHandler(){
			public void onChange(Event event) {}
		});
		formComponent.setCancelBtnClickEvent(new ChangeHandler(){
			public void onChange(Event event) {
				UI.getCurrent().getNavigator().navigateTo("FormView");	
			}
		});
	}
	
	
		
	
	
}