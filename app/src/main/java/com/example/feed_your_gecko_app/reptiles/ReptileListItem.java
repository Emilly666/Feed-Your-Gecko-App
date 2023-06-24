package com.example.feed_your_gecko_app.reptiles;


import com.example.feed_your_gecko_app.database.relations.UserReptiles;

public class ReptileListItem {
    private boolean expanded = false;

    public UserReptiles userReptiles;

    public ReptileListItem(UserReptiles userReptiles){
        this.userReptiles = userReptiles;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
