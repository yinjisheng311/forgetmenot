package com.example.g.forgetmenot;

/**
 * Created by G on 13/12/16.
 */
public class RowItem {
    private String item_name;

    private String time;

    public RowItem(String item_name, String time){
        this.item_name = item_name;
        this.time = time;
    }

    public String getItem_name() {return item_name;}

    public void setItem_name(String member_name){ this.item_name = member_name;}

    public String getTime() {return time;}

    public void setTime(String time){
        this.time = time;
    }

}