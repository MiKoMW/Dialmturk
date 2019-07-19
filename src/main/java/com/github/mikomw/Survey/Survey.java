package com.github.mikomw.Survey;

import java.util.List;

public class Survey {
    private String _id;
    private List<SurveyQuestion> survey;
    private String userID;
    private String subId;
    private long timestamp;
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<SurveyQuestion> getSurvey() {
        return survey;
    }

    public void setSurvey(List<SurveyQuestion> survey) {
        this.survey = survey;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }




}
