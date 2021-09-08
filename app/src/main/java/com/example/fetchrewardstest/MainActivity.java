package com.example.fetchrewardstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.fetchrewardstest.Adapters.ItemAdapter;
import com.example.fetchrewardstest.Models.Item;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    public static final String TAG = "MainActivity";

    private ArrayList<Item> itemList;
    private ArrayList<Integer> listIds;
    private ItemAdapter adapter;
    private RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvItems = findViewById(R.id.rvItems);
        itemList = new ArrayList<>();
        listIds = new ArrayList<>();
        createListItems();

        adapter = new ItemAdapter(this, itemList, listIds);
        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createListItems() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONArray items = json.jsonArray;
                try {
                    itemList.addAll(Item.fromJsonArray(items));
                    adapter.notifyDataSetChanged();
                    removeDuplicateItemIds(itemList);
                    Log.d(TAG, "createListItems onSuccess() " + itemList.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.e(TAG, "onFailure: " + response, throwable);
            }
        });
    }

    private void removeDuplicateItemIds (ArrayList<Item> currentList) {
        Log.d(TAG, "removeDuplicateItemIds: " + currentList.size());
        for (int i = 0; i < currentList.size(); i++) {
            Item element = currentList.get(i);
            if (!listIds.contains(element.getItemListID()))
                listIds.add(element.getItemListID());
        }
        Log.d(TAG, "removeDuplicateItemIds: " + listIds.size());

    }

}