package com.example.apiexampleinservices;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.softprodigy.librarymsspro.MobileConnectivity;
import com.softprodigy.librarymsspro.Webservices;


public class ServiceClass extends Service {
	
	
	//tag
	private static final String TAG = "BroadcastService";
	
	public  static  List<SubViewChildModel> customListchild = new ArrayList<SubViewChildModel>();
	//broadcast action 
	public static final String BROADCAST_ACTION = "com.example.apiexampleinservices.displayevent";
	Context context;
	
	MobileConnectivity mobile;
	//initialize handler
	private final Handler handler = new Handler();
	

	//init intent
	Intent intent;
	
	//init counter
	int counter = 0;
//on bind method override when extend service class
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//oncreate method
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
//		mobile = MobileConnectivity.checkNetworkConnections(context);
		
		
		
		intent = new Intent(BROADCAST_ACTION);
		
		
		
		
	}
	//runnable method
	private Runnable sendUpdatesToUI = new Runnable() {
    	public void run() {
    		//do what ever you want to do here	
    		
    		
//    		if (mobile.isIntenetConnectionactive) {
//    			System.out.println(">>>CHECKPOINT 1>>>>>>>");
    			new BackThreadAsyncTask().execute();

//    		} else {
//    			
//    		}
    		
    		
    	    handler.postDelayed(this, 10000); // 10 seconds
    	}
    };    
	//onstart method
	
@Override
public void onStart(Intent intent, int startId) {
	// TODO Auto-generated method stub
	super.onStart(intent, startId);
	
	 handler.removeCallbacks(sendUpdatesToUI);
     handler.postDelayed(sendUpdatesToUI, 1000); // 1 second
}

//ondestroy method 
@Override
public void onDestroy() {		
    handler.removeCallbacks(sendUpdatesToUI);		
	super.onDestroy();
}

private void DisplayLoggingInfo() {
	Log.d(TAG, "entered DisplayLoggingInfo");

	intent.putExtra("time", "time");
	intent.putExtra("counter", String.valueOf(++counter));
	sendBroadcast(intent);
}



//async class for get categories

			class BackThreadAsyncTask extends AsyncTask<Void, String, Void> {
				String result = "";
				String resultText = "", Result;
		
			

				
			
		
				protected void onPreExecute() {
//				
					
					
					
					
				}
		
				@Override
				protected Void doInBackground(Void... unused) {
		
//					if (customList != null) {
//						customList.clear();
//					}
					 if (customListchild != null) {
					 customListchild.clear();
					 }
		
					try {
						JSONObject json = new JSONObject();
						json.put("Id", "");
						String url = "URL url here" + "GetSubcat";
		
						System.out.println("<<<<<<<<@@@<<<url>>>>>>>>>>>>>>>>>>>>>"
								+ url);
						System.out.println("<<<<<<<<@@@<<<json>>>>>>>>>>>>>>>>>>>>>"
								+ json);
		
						result = Webservices.ApiCall(url, json, context);
		
						System.out.println("<<<<<<<<@@@<<<result>>>>>>>>>>>>>>>>>>>>>"
								+ result);
						if (!result.equals("true")) {
							JSONObject jsonObject = new JSONObject(result);
							JSONArray cast = jsonObject.getJSONArray("response");
							System.out.println(">>>>>LENGHT OF outer JSON ARRAY>>>>"
									+ cast.length());
							 int countforResult=cast.length();
							for (int i = 0; i < cast.length(); i++) {
								JSONObject categories = cast.getJSONObject(i);
								
		

										 String subproductID = categories
												.getString("productid");
										 String subproductname = categories
												.getString("productname");
										 String	subproductsku = categories.getString("Sku");
										 String	subproductshrtdesc = categories
												.getString("Short Desc");
										 String subproductimage = categories.getString("Image");
										 String subproductprice = categories.getString("Price");
										
										String subproductAppprice = categories.getString("AppPrice");
										subproductimage = subproductimage.replace(" ",
												"%20");
										System.out.println(">>>productid>>>"
												+ subproductID);
										System.out.println(">>>subproductname>>>"
												+ subproductname);
										System.out.println(">>>subproductsku>>>"
												+ subproductsku);
										System.out.println(">>>subproductshrtdesc>>>"
												+ subproductshrtdesc);
										System.out.println(">>>subproductimage>>>"
												+ subproductimage);
										System.out.println(">>>subproductprice>>>"
												+ subproductprice);
										
										if(!(subproductAppprice.equalsIgnoreCase("")|| subproductAppprice.equalsIgnoreCase("null"))){
											subproductprice=subproductAppprice;
										
										}else{
											subproductprice=subproductprice;
										}
										
		
						
		
									
										customListchild.add(new SubViewChildModel(
												subproductID, subproductname,
												subproductsku, subproductshrtdesc,
												subproductimage, subproductprice));
										
										
										
										
		
									}
						
						
		

		
						
				
							JSONObject sCode1 = jsonObject.getJSONObject("returnCode");
							Result = sCode1.getString("resultText");
						}
					} catch (Exception e) {
		
						e.printStackTrace();
					}
		
					return null;
				}
		
				

				@Override
				protected void onPostExecute(Void unused) {
					
		
					try {
		
						if (!result.equals("true")) {
							if ((Result).equals("Success")) {
								DisplayLoggingInfo();
							} else if (Result.equals("Failure")) {
								
							} else {
								
							}
						} else {
							
						}
		
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						
					}
		
				}
			}










}
