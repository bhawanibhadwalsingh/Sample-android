package com.androidxmlparsing;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.androidxmlparsing.adapter.CustomListViewAdapter;
import com.androidxmlparsing.adapter.util.XMLDOMParser;
import com.androidxmlparsing.model.SongsList;
public class MainActivity extends Activity 
implements OnClickListener,OnItemClickListener {
ListView listView;
Button button;
ArrayList<SongsList> employees;
CustomListViewAdapter listViewAdapter;

// All static variables
static final String URL = "http://api.androidhive.info/music/music.xml";

// XML node names
static final String KEY_SONG = "song"; // parent node
static final String KEY_ID = "id";
static final String KEY_TITLE = "title";
static final String KEY_ARTIST = "artist";
static final String KEY_DURATION = "duration";
static final String KEY_THUMB_URL = "thumb_url";

/** Called when the activity is first created. */
@Override 
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

findViewsById();
button.setOnClickListener(this);
listView.setOnItemClickListener(this);
}

private void findViewsById() {
button = (Button) findViewById(R.id.button);
listView = (ListView) findViewById(R.id.employeeList);
}

public void onClick(View view) {
GetXMLTask task = new GetXMLTask(this);
task.execute(new String[] { URL });
}

public void onItemClick(AdapterView<?> adapter, View view, int position,
    long id) {
Toast.makeText(getApplicationContext(),
    employees.get(position).toString(), 
    Toast.LENGTH_SHORT).show();
}

//private inner class extending AsyncTask
private class GetXMLTask extends AsyncTask<String, Void, String> {
private Activity context;

public GetXMLTask(Activity context) {
    this.context = context;
}

@Override
protected String doInBackground(String... urls) {
    String xml = null;
    for (String url : urls) {
        xml = getXmlFromUrl(url);
    }
    return xml;
}
 
@Override
protected void onPostExecute(String xml) {
     
    XMLDOMParser parser = new XMLDOMParser();
    InputStream stream = new ByteArrayInputStream(xml.getBytes());
    Document doc = parser.getDocument(stream);

    NodeList nodeList = doc.getElementsByTagName(KEY_SONG);

    employees = new ArrayList<SongsList>();
    SongsList employee = null;
    for (int i = 0; i < nodeList.getLength(); i++) {
    	 employee = new SongsList();
        Element e = (Element) nodeList.item(i);
     
        
        
        employee.setKEY_ID(parser.getValue(e, KEY_ID));
        employee.setKEY_TITLE(parser.getValue(e, KEY_TITLE));
        employee.setKEY_ARTIST(parser.getValue(e, KEY_ARTIST));
        employee.setKEY_DURATION(parser.getValue(e, KEY_DURATION));
        employee.setKEY_THUMB_URL(parser.getValue(e, KEY_THUMB_URL));
        
        
        
        
        employees.add(employee);
    }

    listViewAdapter = new CustomListViewAdapter(context, employees);
    listView.setAdapter(listViewAdapter);
}

/* uses HttpURLConnection to make Http request from Android to download
 the XML file */
private String getXmlFromUrl(String urlString) {
    StringBuffer output = new StringBuffer("");

    InputStream stream = null;
    URL url;
    try {
        url = new URL(urlString);
        URLConnection connection = url.openConnection();

        HttpURLConnection httpConnection = (HttpURLConnection) connection;
        httpConnection.setRequestMethod("GET");
        httpConnection.connect();

        if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            stream = httpConnection.getInputStream();
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(stream));
            String s = "";
            while ((s = buffer.readLine()) != null)
                output.append(s);
        }
    } catch (MalformedURLException e) {
        Log.e("Error", "Unable to parse URL", e);
    } catch (IOException e) {
        Log.e("Error", "IO Exception", e);
    }
             
    return output.toString();

    //For applications targeting Froyo and previous versions
    /*  
    String xml = null;
    try {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        xml = EntityUtils.toString(httpEntity);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return xml;*/
}   
}

//@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		// TODO Auto-generated method stub
//		super.onConfigurationChanged(newConfig);
//		  listViewAdapter = new CustomListViewAdapter(MainActivity.this, employees);
//		    listView.setAdapter(listViewAdapter);
//	}
}