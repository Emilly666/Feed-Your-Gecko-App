package com.example.feed_your_gecko_app.tasks;

import java.util.Date;

public class Task {
    private boolean expanded;

    private int task_id;
    private final int type; //0 = FEED //1 = VITAMINS
    private Date date;              // task's date
    private String reptileNickname;   // plants nickname
    private int reptileUser_id;       // user plant's id
    private String vitaminsType;  // short description, null if type == feed


    public Task(int type){
        this.expanded = false;
        this.type = type;
    }
    public int getType(){
        return type;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
