package com.example.fetchrewardstest.Models;

import android.util.Log;

import com.google.common.collect.ComparisonChain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Item {

    public static final String TAG = "ItemModel";

    int itemID;
    int itemListID;
    String fullItemName;
    int itemName;


    public int getItemID() {
        return itemID;
    }

    public int getItemListID() {
        return itemListID;
    }

    public int getItemName() {
        return itemName;
    }

    public Item(JSONObject jsonObject) throws JSONException {
        itemID = jsonObject.getInt("id");
        itemListID = jsonObject.getInt("listId");
        fullItemName = jsonObject.getString("name");
        itemName = splitString(fullItemName);
    }

    public static List<Item> fromJsonArray(JSONArray itemsJsonArray) throws JSONException {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < itemsJsonArray.length(); i++) {
            JSONObject obj = itemsJsonArray.getJSONObject(i);
            if (obj.isNull("name") || obj.getString("name").equals(""))
                continue;
            items.add(new Item(obj));
        }

        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                 return ComparisonChain.start()
                         .compare(o1.getItemListID(), o2.getItemListID())
                         .compare(o1.getItemName(), o2.getItemName())
                         .result();
            }
        });

        //Log.d(TAG, "fromJsonArray: " + items.toString());

        return items;
    }

    private static int splitString (String input) {
        String[] part = input.split("(?<=\\D)(?=\\d)");
        return Integer.parseInt(part[1]);
    }


}
