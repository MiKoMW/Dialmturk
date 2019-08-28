package com.github.mikomw.Dialogue;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * This class contains a dialogue perform between our turkers and a dialogue system during one survey.
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

public class Dialogue {

    private String _id;
    private String subId;
    private String userID;
    private String name_of_dialog;
    private String[] feedback;
    private List<DialogueUtterance> dialog;

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

    public String[] getFeedback() {
        return feedback;
    }

    public void setFeedback(String[] feedback) {
        this.feedback = feedback;
    }

    public List<DialogueUtterance> getDialog() {
        return dialog;
    }

    public void setDialog(List<DialogueUtterance> dialog) {
        this.dialog = dialog;
    }

    public String toString(){
        return JSON.toJSONString(this);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
        {
            return true;
        }

        if(!(obj instanceof Dialogue))
        {
            return false;
        }

        final Dialogue dialogue = (Dialogue)obj;

        return JSON.toJSONString(dialogue).equals(JSON.toJSONString(this));
    }

    public int hashCode()
    {
        return this.toString().hashCode();
    }

}
