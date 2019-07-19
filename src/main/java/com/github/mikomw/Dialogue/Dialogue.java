package com.github.mikomw.Dialogue;

import java.util.List;

public class Dialogue {

    private String _id;
    private String subId;
    private String userID;
    private String name_of_dialog;
    private String feedback;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName_of_dialog() {
        return name_of_dialog;
    }

    public void setName_of_dialog(String name_of_dialog) {
        this.name_of_dialog = name_of_dialog;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<DialogueUtterance> getDialog() {
        return dialog;
    }

    public void setDialog(List<DialogueUtterance> dialog) {
        this.dialog = dialog;
    }

    private List<DialogueUtterance> dialog;



}
