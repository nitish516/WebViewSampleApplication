package com.example.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    String url ="http://www.pratilipi.com/api.pratilipi/pratilipi/content?pratilipiId=4843865324388352&pageNo=5";
    WebView webView;
    String contentString;

    private String IndexURL = "index.html";

//    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

//        listView = (ListView)findViewById(R.id.scrollView);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        parseJson(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                "jobj_req");
    }

    void parseJson(final JSONObject response) {
        try {

            webView.setWebViewClient(new WebViewClient(){
                public void onPageFinished(WebView view, String url){
                    try {
                        contentString = response.getString("pageContent");
                        Log.d(TAG,IndexURL);
                        webView.loadUrl();

                        webView.setWebChromeClient(new WebChromeClient(){
                            public void onPageFinished(WebView view,String URL)
                            {
                                String javascript =  contentString;
                                webView.loadUrl("javascript:init('"+javascript+"')");
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            //"<img src=\"http://10.pratilipi.info/pratilipi-cover/150/pratilipi\">"+
//            webView.loadData(response.getString("pageContent"), "text/html; charset=UTF-8", null);

//            webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

    }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
