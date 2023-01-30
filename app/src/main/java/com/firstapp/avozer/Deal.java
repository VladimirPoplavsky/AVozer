package com.firstapp.avozer;

import java.time.format.DateTimeFormatter;

public class Deal {
    enum Type {
        BABYSITTER,
        DOGWALKER,
        CLEANER
    }

    public String dealID;
    public Type type;
    public String clientUid;
    public String helperUid;
    public String city;
    DateTimeFormatter timeCreated;
    DateTimeFormatter whenNeedHelp;
    boolean helperIsFound;
    boolean dealIsDone;
    String comments;


    public Deal(String dealID, Type type, String clientUid, String helperUid, String city,
                DateTimeFormatter timeCreated, DateTimeFormatter whenNeedHelp,
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

    public Deal(String dealID, String city, boolean helperIsFound, boolean dealIsDone, String comments) {
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
