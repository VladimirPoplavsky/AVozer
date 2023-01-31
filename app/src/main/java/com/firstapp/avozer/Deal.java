package com.firstapp.avozer;

import java.time.format.DateTimeFormatter;

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


    public Deal(String dealID, String type, String clientUid, String helperUid, String city,
                String timeCreated, String whenNeedHelp,
                boolean helperIsFound, boolean dealIsDone, String comments) {
        this.dealID = dealID;
        this.type = type;
        this.clientUid = clientUid;
        this.helperUid = helperUid;
        this.city = city;
        this.timeCreated = timeCreated;
        this.whenNeedHelp = whenNeedHelp;
        this.helperIsFound = helperIsFound;
        this.dealIsDone = dealIsDone;
        this.comments = comments;
    }
}
