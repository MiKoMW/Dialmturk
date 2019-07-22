package com.github.mikomw.Survey;

import java.util.List;

/**
 * Survey Mturker submits to the Dialcrowd. For example, how about the performance of the system.
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

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

    public String toString(){

        String ans;
        ans = "Survey uID: " + this.userID + ". ";
        for(SurveyQuestion sq : this.survey){
            ans = ans + " " +  sq.toString();
        }
        return ans;
    }

}
