package com.example.g.forgetmenot;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class InventoryList extends AppCompatActivity{


    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    ArrayList<String> arrays = new ArrayList<>();
    ArrayList<String> timearray = new ArrayList<>();
    ArrayList<String> subtractedTime = new ArrayList<>();
    List<RowItem> rowItems;
    String[] item_names;
    String[] times;
    ListView myListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);
        JodaTimeAndroid.init(this);
        //ListView listView = (ListView)findViewById(R.id.list);
        //String[] items = {"Apple", "Banana", "Grape"};
        //arrayList = new ArrayList<>(Arrays.asList(items));
        //adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.member_name,arrayList);
        //listView.setAdapter(adapter);
        //availableObjects(new ArrayAdapter<>(this, R.layout.list_item, R.id.timeTV,subtractedTime));
        randomClass();





    }
    public void randomClass() {
        RequestParams requestParams = new RequestParams();
        SpeechActivityClient.get("api/item/fetch", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {

            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                //ArrayList<String> arrays = new ArrayList<>();
                try {
                    for (int i = 0; i < existingArray.length(); i++) {
                        JSONObject a_json_object = existingArray.getJSONObject(i);
                        arrays.add(a_json_object.getString("item"));
                        timearray.add(a_json_object.getString("created_at"));
                        DateTime now = new org.joda.time.DateTime();
                        String pattern = "yyyy-MM-dd HH:mm:ss";
                        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
                        String formatted = formatter.print(now);
                        DateTime nowTime = DateTime.parse(formatted, formatter);
                        DateTime arrayTime = DateTime.parse(timearray.get(i), formatter);
                        Duration duration = new Duration(arrayTime, nowTime);
                        System.out.println(HumanTime.approximately(duration.toString()));
                        subtractedTime.add(HumanTime.approximately(duration.toString()));

                        rowItems = new ArrayList<RowItem>();
                        item_names = getResources().getStringArray(R.array.item_names);
                        times = getResources().getStringArray(R.array.times);

                        for (int j = 0; j<arrays.size();j++){
                            RowItem item = new RowItem(arrays.get(j), subtractedTime.get(j));
                            rowItems.add(item);
                        }



                        myListView = (ListView) findViewById(R.id.list);
                        CustomAdapter adapter = new CustomAdapter(InventoryList.this, rowItems);
                        myListView.setAdapter(adapter);


                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("not in server");


                }
            }
        });

    }



    public void availableObjects(final ArrayAdapter adapter) {
        RequestParams requestParams = new RequestParams();
        SpeechActivityClient.get("api/item/fetch", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onStart() {

            }
            public void onSuccess(int statusCode, Header[] headers, JSONArray existingArray) {
                //ArrayList<String> arrays = new ArrayList<>();
                try{
                    for (int i = 0; i<existingArray.length();i++) {
                        JSONObject a_json_object = existingArray.getJSONObject(i);
                        arrays.add(a_json_object.getString("item"));
                        timearray.add(a_json_object.getString("created_at"));
                        DateTime now = new org.joda.time.DateTime();
                        String pattern = "yyyy-MM-dd HH:mm:ss";
                        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
                        String formatted = formatter.print(now);
                        DateTime nowTime = DateTime.parse(formatted, formatter);
                        DateTime arrayTime = DateTime.parse(timearray.get(i), formatter);
                        Duration duration = new Duration(arrayTime, nowTime);
                        System.out.println(HumanTime.approximately(duration.toString()));
                        subtractedTime.add(HumanTime.approximately(duration.toString()));
                    }
                    Timestamp stamp = new Timestamp(System.currentTimeMillis());
                    Date date = new Date(stamp.getTime());
                    System.out.println(date);

                    System.out.println(arrays);
                    System.out.println(timearray);


                    adapter.add(arrays.toArray());
                    //autoComplete = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
                    ListView listView = (ListView)findViewById(R.id.list);
                    listView.setAdapter(adapter);
                    //autoComplete.setThreshold(1);



                }catch (Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("not in server");

                }

            }

            @Override
            /*/public void onSuccess(int statusCode, Header[] headers, JSONObject existingObject) {
                try {

                    //JSONObject newObject = existingObject.getJSONObject("something");
                    //System.out.println(newObject);
                    System.out.println("Works");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("no");
                    //Intent notHere = new Intent(SpeechTakeActivity.this, TakeFailure.class);
                    //startActivity(notHere);
                }

            }/*/

            public void onRetry(int retryNo) {

            }

            public void onFailure(int x, Header[] y, String z, Throwable l) {
                System.out.println(z);
            }

        });

    }
}

