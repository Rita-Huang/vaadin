package demo.service;

import demo.bean.CompleteBean;
import demo.bean.CusQryForm1Bean;
import demo.bean.Form1Bean;
import demo.bean.LoginBean;
import demo.bean.PageInfoBean;
import demo.bean.TodoBean;
import demo.bean.TodoListBean;
import demo.bean.TodoSummaryBean;

public interface UseFormService {	
	/**
	 * 表單:form1資料儲存
	 * 處理邏輯內容:
	 *   1.檢核
	 *     1-1.參數bean必定要有值檢核
	 *     1-2.[FORM_CODE]是否存在<FORM>定對檔中,存在才允許儲存
	 *   2.依據 formBean._key值區分Inser/Update
	 *   3.formBean及loginBea相關資料處理repo.APPL對應的entity
	 * @param formBean
	 * @param loginBean
	 * @return :傳回儲存結果(Form1Bean.resultCode)及儲存成功的(Form1Bean._key)
	 */
	public Form1Bean saveForm(Form1Bean formBean,LoginBean loginBean);
	
	/**
	 * 表單:form1資料儲存及flow engine起案
	 * 處理邏輯內容:
	 *   1.call self saveForm()進行form1資料儲存
	 *   2.針對formBean及loginBean相關資料處理flowRestClient.payloadBean資料轉換
	 *   3.call flowRestClient.startProcess()進行起案處理
	 * @param formBean
	 * @param loginBean
	 * @return :傳回儲存&起案結果(Form1Bean.resultCode)及儲存成功的(Form1Bean._key)
	 */
	public Form1Bean submitForm(Form1Bean formBean,LoginBean loginBean);
	
	/**
	 * 表單:待辦清單中點選[檢視表單]時,查詢表單詳細資訊
	 * 處理邏輯內容:
	 *   1.以當初起案成功記錄於APPL.PROC_INST_CODE找出對應的APPL表單資料
	 *   2.將查詢出來的資料conver到Form1Bean中
	 * @param processInstantId
	 * @return :傳回查詢表單資訊結果(Form1Bean.resultCode)
	 */
	public Form1Bean queryFormDetail(String processInstantId);
	
	/**
	 * 表單:以登入者資訊查詢待辦清單
	 * 處理邏輯內容:
	 *   1.從LoginBean中取得activiti之assginee帳號([公司別compId][登入帳號(aliasName)])
	 *   2.從TodoSummaryBean中取得FormCode代碼
	 *   3.call restClient查詢待辦清單(formCode,assignee)
	 *   4.將查詢出來之<待辦清單>資料整合Ap1表單資料至待辦清單物件中
	 * @param loginBean :登入元件
	 * @param todoSumBean : 待辦彙總物件
	 * @param pageInfo  :分頁物件
	 * @return 符合查詢條件之待辦清單TodoListBean
	 */
	public TodoListBean queryTodoList(LoginBean loginBean,TodoSummaryBean todoSumBean,PageInfoBean pageInfo);
	
	/**
	 * 待辦工作簽核處理
	 * 處理邏輯內容:
	 *   1.從TodoBean中取得taskId及簽核流程參數formCode,call restClient進行表單簽核
	 *   2.將restClient回傳結果轉換到CompleteBean中回傳 
	 * @param loginBean
	 * @param todoBean
	 * @return
	 */
	public CompleteBean completeTask(LoginBean loginBean,TodoBean todoBean);
	
	
	/**
	 * 以登入者資訊查詢待辦清單,依[表單別]小計
	 * 處理邏輯內容:
	 *   1.從LoginBean中取得activiti之assginee帳號([公司別compId][登入帳號(aliasName)])
	 *   2.call restClient查詢待辦清單彙總API
	 *   3.將查詢出來之<待辦清單>資料串連AP1表單名稱及小計處理
	 * @param loginBean
	 * @return 符合查詢條件之待辦清單TodoListBean.todoSummaryBean
	 */
	public TodoListBean queryTodoSummary(LoginBean loginBean);
	
	/**
	 * 刪除表單資料
	 * 處理邏輯內容:
	 *   1.以formBean._key 查詢表單資料是否存在
	 *   2.判斷從未送審才允許刪除
	 *   3.標記刪除註記(loginBean.empNo)
	 * @param loginBean
	 * @param formBean
	 * @return
	 */
	public Form1Bean deleteForm(LoginBean loginBean,Form1Bean formBean);
	
	/**
	 * 查詢某一人所有已儲存/送審之表單資料
	 * 處理邏輯內容:
	 *   1.檢核必填查詢條件值(開單人員編)
	 *   2.依據有輸入之查詢條件值傳入repo查詢
	 *   3.將repo查詢結果轉換為cusQryForm1ResBean中
	 * @param cusQryForm1Bean
	 * @return CusQryForm1Bean.customQryForm1ResList
	 */
	public CusQryForm1Bean customQryFormList(CusQryForm1Bean cusQryForm1Bean);
	
}
