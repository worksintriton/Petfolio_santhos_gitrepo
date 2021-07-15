package com.triton.bertsproject.Utlity;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicUtils 
{	
	Context m_cContext;
    Dialog dialog;

   	public BasicUtils(Context pContext){
   		
		m_cContext = pContext;
	}	
   	
	public static void showToast(CharSequence message, Context appContext)
    {
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(appContext, message, duration);
		toast.show();
    }
	
	public static void stopWhenMinimized(Context pContext){
	
	}

	public static String getOsInfo() {
		String lValue = android.os.Build.MODEL;
		int lValue2 = android.os.Build.VERSION.SDK_INT;
		switch (lValue2) {
		case 1:
			lValue = lValue+" OS 1.0";
			break;
		case 2:
			lValue = lValue+" OS 1.1";
			break;
		case 3:
			lValue = lValue+" OS 1.5";
			break;
		case 4:
			lValue = lValue+" OS 1.6 DONUT";
			break;
		case 5:
			lValue = lValue+" OS 2.0 ECLAIR";
			break;
		case 6:
			lValue = lValue+" OS 2.0.1 ECLAIR_0_1";
			break;
		case 7:
			lValue = lValue+" OS 2.1 ECLAIR_MR1";
			break;
		case 8:
			lValue = lValue+" OS 2.2 FROYO";
			break;
		case 9:
			lValue = lValue+" OS 2.3 GINGERBREAD";
			break;
		case 10:
			lValue = lValue+" OS 2.3.3 GINGERBREAD";
			break;
		default:
			System.out.println("os version above 2.2");
			break;
		}
		
		return "Android "+lValue;
	}

	public boolean isNetAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) m_cContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			connectivity=null;
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void showSoftKeyboard(EditText edittext, boolean isForced)
	{
		int flag;
		if(isForced)
			flag = InputMethodManager.SHOW_FORCED;
		else
			flag = InputMethodManager.SHOW_IMPLICIT;

 		InputMethodManager imm = (InputMethodManager)m_cContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(edittext, flag);
	}
	
	public void hideSoftKeyboard(View view)
	{
 		InputMethodManager imm = (InputMethodManager)m_cContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
    
	public long getTimestamp(String format, String formattedDateTime) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			return ((Date)formatter.parse(formattedDateTime)).getTime();
		} catch (ParseException e) {
			Log.e("ParseException", " : "+e.toString());
			e.printStackTrace();
		}
		return new Date().getTime();
	}

	public String getFormattedDate(String format, long timestamp)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(timestamp);
	}
	


	
	static String version;
	public static void setAppVersion(String lversion) {
		// TODO Auto-generated method stub
		version=lversion;
	}
	public static String getAppVersion(){
		if(version==null)
			return "2.1.00";
		else
			return version;
	}
}
