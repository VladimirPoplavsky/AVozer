package com.firstapp.avozer;

public class Deal {
    public String dealID;
    public String type;
    public String clientUid;
    public String helperUid;
    public String city;
    public String timeCreated;
    public String whenNeedHelp;
    public boolean helperIsFound;
    public boolean dealIsDone;
    public String comments;

//    public boolean isExpanded = false;


    public Deal(String city, String dealID, String type, String clientUid, String helperUid,
                String timeCreated, String whenNeedHelp,
                boolean helperIsFound, boolean dealIsDone, String comments) {
        this.city = city;
        this.dealID = dealID;
        this.type = type;
        this.clientUid = clientUid;
        this.helperUid = helperUid;
        this.timeCreated = timeCreated;
        this.whenNeedHelp = whenNeedHelp;
        this.helperIsFound = helperIsFound;
        this.dealIsDone = dealIsDone;
        this.comments = comments;
    }


    public Deal(){}

//    public boolean isExpanded() {
//        return isExpanded;
//    }
//    public void  setExpanded(boolean expanded){
//        isExpanded=expanded;
//    }
}
