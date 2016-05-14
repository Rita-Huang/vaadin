package demo.component;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import demo.MyUI;
import demo.bean.Form1Bean;
import demo.bean.LoginBean;
import demo.bean.TodoBean;
import demo.data.Constant;
import demo.data.DemoData;
import demo.event.ChangeHandler;
import demo.service.UseFormService;
import demo.util.DateUtils;
import demo.util.StringToTimestampConverter;

/**
 * 簡易表單Component，包含： 1.業務Component 2.業務Bean 3.業務Service
 * 4.欄位Binding(使用@PropertyId) 5.增加Listener 6.增加Validator
 * 7.按鈕執行方法：saveForm()與submitForm()等等 8.欄位Binding方法 9.按鈕enabled or disabled
 */
@SuppressWarnings("serial")
@SpringComponent
@ViewScope
public class FormComponent extends CustomComponent {

	// 1.業務Component
	@PropertyId("startUserName")
	/** 開單人(Sample : Chen, Bonnie) */
	private TextField engNameField;

	@PropertyId("startDate")
	/** 開單日期(Sample : 2016/03/12 15:20) */
	private DateField createDateField;

	@PropertyId("expectDate")
	/** 預計完成日期(必填) */
	private DateField finishDateField;

	@PropertyId("formDesc")
	/** 工作項目(必填) */
	private TextArea workItemTextArea;

	/** 儲存按鈕 */
	private Button saveButton;

	/** 送審按鈕 */
	private Button newCaseButton;

	/** 起案按鈕 */
	private Button submitButton;

	/** 取消按鈕 */
	private Button cancelButton;

	// 2.業務Bean
	private LoginBean loginBean;
	private Form1Bean formBean;
	
	// 3.業務Service
	private UseFormService useFormService;

	// 4.欄位Binding
	private BeanFieldGroup<Form1Bean> fieldGroup;

	

	/** 建構子 */
	@Autowired
	public FormComponent(UseFormService useFormService) {
		this.useFormService = useFormService;
		initComponent();
	}

	/** 初始化Component */
	private void initComponent() {
		Panel formPanel = new Panel(Constant.SIMPLE_FORM);
		formPanel.addStyleName(Constant.MyTheme_Panel);
		formPanel.setIcon(FontAwesome.PENCIL);

		engNameField = new TextField();
		createDateField = new DateField();
		finishDateField = new DateField();
		workItemTextArea = new TextArea();
		saveButton = new Button(Constant.SIMPLE_FORM_SAVE);
		submitButton = new Button(Constant.SIMPLE_FORM_SUBMIT);
		newCaseButton = new Button(Constant.SIMPLE_FORM_CREAT_CASE);
		cancelButton = new Button(Constant.SIMPLE_FORM_CANCEL);

		// 設定按鈕顏色
		saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		newCaseButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		submitButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
		cancelButton.setStyleName(ValoTheme.BUTTON_DANGER);

		// 設定標題
		engNameField.setCaption(Constant.SIMPLE_FORM_INITIATOR);
		createDateField.setCaption(Constant.SIMPLE_FORM_START_DATE);
		finishDateField.setCaption(Constant.SIMPLE_FORM_FINISH_DATE);
		workItemTextArea.setCaption(Constant.SIMPLE_FORM_WORK_ITEM);

		// StringToTimestamp轉換
		createDateField.setDateFormat(DateUtils.FMT_YYYYMMDD_SLASH);// 日期單位
		finishDateField.setDateFormat(DateUtils.FMT_YYYYMMDD_SLASH);// 日期單位
		createDateField.setConverter(StringToTimestampConverter.INSTANCE);
		finishDateField.setConverter(StringToTimestampConverter.INSTANCE);

		FormLayout formLayout = new FormLayout();
		formLayout.setSpacing(true);
		formLayout.addComponent(engNameField);
		formLayout.addComponent(createDateField);
		formLayout.addComponent(finishDateField);
		formLayout.addComponent(workItemTextArea);

		HorizontalLayout subLayout = new HorizontalLayout();
		subLayout.setSpacing(true);
		subLayout.addComponent(saveButton);
		subLayout.addComponent(newCaseButton);
		subLayout.addComponent(submitButton);
		subLayout.addComponent(cancelButton);

		formLayout.addComponent(subLayout);
		formPanel.setContent(formLayout);
		setCompositionRoot(formPanel);
		setSizeFull();
	}
	
	/** 初始化data **/
	public void initData() {
		loginBean = new LoginBean();
		MyUI.setLoginBean(loginBean);	
		
		if(VaadinSession.getCurrent().getAttribute("loginInfo") != null) {
			String aliasName = ((LoginBean)VaadinSession.getCurrent().getAttribute("loginInfo")).getAliasName();
			Form1Bean form1Bean = new Form1Bean();
			
			if(VaadinSession.getCurrent().getSession().getAttribute("todoBean") != null) {
				TodoBean todoBean = (TodoBean)VaadinSession.getCurrent().getSession().getAttribute("todoBean");
				form1Bean = useFormService.queryFormDetail(todoBean.getTask_procInstId());
				
				binding(form1Bean);
				setSubmitButtonEnabled();
			}else {
				form1Bean.setFormCode(DemoData.formCode);
				form1Bean.setStartUserId(DemoData.empNoMap.get(aliasName));
				form1Bean.setStartUserName(DemoData.engNameMap.get(aliasName));
				form1Bean.setStartDate(new Timestamp(new Date().getTime()));
				
				binding(form1Bean);
				setSaveNewCaseButtonEnabled();
			}
		}
	}
	
	
	// 5.增加Listener
	public void setSaveBtnClickEvent(final ChangeHandler h) {
		saveButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				saveForm();
				h.onChange(clickEvent);
			}
		});
	}

	public void setNewCaseBtnClickEvent(final ChangeHandler h) {
		newCaseButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				newCaseForm();
				h.onChange(clickEvent);
			}
		});
	}

	public void setSubmitBtnClickEvent(final ChangeHandler h) {
		submitButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				
				submitForm();
				h.onChange(clickEvent);
			}
		});
	}

	public void setCancelBtnClickEvent(final ChangeHandler h) {
		cancelButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent clickEvent) {
				VaadinSession.getCurrent().getSession().removeAttribute("todoBean");
				h.onChange(clickEvent);
			}
		});
	}

	/** 儲存按鈕、起案按鈕enabled */
	public void setSaveNewCaseButtonEnabled() {
		saveButton.setEnabled(true);
		newCaseButton.setEnabled(true);
	}

	/** 起案按鈕enabled、儲存按鈕not enabled */
	public void setSubmitButtonEnabled() {
		saveButton.setEnabled(false);
		newCaseButton.setEnabled(true);
	}

	// 8.欄位Binding方法
	/** 欄位Binding並重設新增表單Component狀態 */
	public void binding(Form1Bean formBean) {
		this.formBean = formBean;
		// Binding Fields to a Bean
		fieldGroup = new BeanFieldGroup<Form1Bean>(Form1Bean.class);
		fieldGroup.setItemDataSource(formBean);
		fieldGroup.bindMemberFields(this);

		// Binding後才設定唯讀
		engNameField.setReadOnly(true);
		createDateField.setReadOnly(true);

		// 欄位不要立即validation
		finishDateField.setValidationVisible(false);
		workItemTextArea.setValidationVisible(false);
	}

	/** 儲存方法 */
	private void saveForm() {
		try {
			validateData();
			Form1Bean result = useFormService.saveForm(formBean, loginBean);
//			Notification.show(result.getResultMsg());
			saveButton.setEnabled(false);
		} catch (Exception e) {
			Notification.show("輸入資料有誤", Notification.Type.ERROR_MESSAGE);
		}
	}
	/** 起案方法 */
	private void newCaseForm() {
		try {
			validateData();
			Form1Bean result = useFormService.submitForm(formBean, loginBean);
//			Notification.show(result.getResultMsg());
			saveButton.setEnabled(false);
			newCaseButton.setEnabled(false);
		} catch (Exception e) {
			Notification.show("輸入資料有誤", Notification.Type.ERROR_MESSAGE);
		}
	}
	/** 送審方法 */
	private void submitForm() {
		TodoBean todoBean = (TodoBean) VaadinSession.getCurrent().getSession().getAttribute("todoBean");
		todoBean.setTask_completeFlowType(TodoBean.task_compeleteFlowType_approve);
//		CompleteBean result = useFormService.completeTask(loginBean, todoBean);
//		Notification.show(result.getResultMsg());
		VaadinSession.getCurrent().getSession().removeAttribute("todoBean");
	}
	

	/** 驗證與同步綁定資料 */
	public void validateData() {
		finishDateField.setRequiredError("請輸入預計完成日期");
		workItemTextArea.setRequiredError("請輸入工作項目內容");
		finishDateField.setValidationVisible(true);
		workItemTextArea.setValidationVisible(true);
		finishDateField.validate();
		workItemTextArea.validate();

		// 記得執行fieldGroup.commit()同步綁定資料
		try {
			fieldGroup.commit();
		} catch (CommitException e) {
			e.printStackTrace();
		}
	}

	public TextField getEngNameField() {
		return engNameField;
	}

	public void setEngNameField(TextField engNameField) {
		this.engNameField = engNameField;
	}

	public DateField getCreateDateField() {
		return createDateField;
	}

	public void setCreateDateField(DateField createDateField) {
		this.createDateField = createDateField;
	}

	public DateField getFinishDateField() {
		return finishDateField;
	}

	public void setFinishDateField(DateField finishDateField) {
		this.finishDateField = finishDateField;
	}

	public TextArea getWorkItemTextArea() {
		return workItemTextArea;
	}

	public void setWorkItemTextArea(TextArea workItemTextArea) {
		this.workItemTextArea = workItemTextArea;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public Button getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(Button submitButton) {
		this.submitButton = submitButton;
	}

	public Button getNewCaseButton() {
		return newCaseButton;
	}

	public void setNewCaseButton(Button newCaseButton) {
		this.newCaseButton = newCaseButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(Button cancelButton) {
		this.cancelButton = cancelButton;
	}

}