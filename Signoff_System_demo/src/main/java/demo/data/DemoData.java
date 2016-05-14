package demo.data;

import java.util.HashMap;
import java.util.Map;

public class DemoData {
	public static final String defaultPage = "NewCase";
	
	public static final String[] viewName = {"SimpleView","NewCase"};
	public static final String[] viewCaption = {"SimpleView","新增表單"};
	
	public static final String[] account = {"Albert","Bonnie","MowMow"};
	
	public static String formCode = "acr";
	static final String[] aliasNameList = new String[] {"Albert", "Bonnie", "MowMow"};
	static final String[] empNoList = new String[] {"80001", "80002", "80003"};
	static final String[] engNameList = new String[] {"Albert,Chen", "Bonnie,Mou", "MowMow,Ju"};
	
	public static Map<String, String> empNoMap = new HashMap<String, String>();
	static{
		for (int i = 0; i < aliasNameList.length; i++) {
			empNoMap.put(aliasNameList[i], empNoList[i]);
		}
	}
	public static Map<String, String> engNameMap = new HashMap<String, String>();
	static{
		for (int i = 0; i < aliasNameList.length; i++) {
			engNameMap.put(aliasNameList[i], engNameList[i]);
		}
	}
	
}
