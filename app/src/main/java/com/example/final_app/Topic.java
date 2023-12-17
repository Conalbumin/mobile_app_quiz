package com.example.final_app;

import java.util.ArrayList;
import java.util.Date;

public class Topic {
    private String setId, setTitle, setDes, userId, userName;
    private Date dateCreated;
    private ArrayList<Term> terms;

    public Topic() {
    }

    public Topic(String setId, String setTitle, String setDes, String userId, String userName, Date dateCreated, ArrayList<Term> terms) {
        this.setId = setId;
        this.setTitle = setTitle;
        this.setDes = setDes;
        this.userId = userId;
        this.userName = userName;
        this.dateCreated = dateCreated;
        this.terms=terms;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public String getSetId() {
        return setId;
    }

    public void setSetId(String setId) {
        this.setId = setId;
    }

    public String getSetTitle() {
        return setTitle;
    }

    public void setSetTitle(String setTitle) {
        this.setTitle = setTitle;
    }

    public String getSetDes() {
        return setDes;
    }

    public void setSetDes(String setDes) {
        this.setDes = setDes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
