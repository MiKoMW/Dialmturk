package com.github.mikomw.Dialogue;

import com.alibaba.fastjson.JSON;
import com.github.mikomw.Survey.Survey;

/**
 * This is one sentence said by turkers or system during one dialogue.
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

public class DialogueUtterance {

    private String role;
    private String utter;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUtter() {
        return utter;
    }

    public void setUtter(String utter) {
        this.utter = utter;
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

        if(!(obj instanceof DialogueUtterance))
        {
            return false;
        }

        final DialogueUtterance dialogueUtterance = (DialogueUtterance)obj;

        return JSON.toJSONString(dialogueUtterance).equals(JSON.toJSONString(this));
    }

    public int hashCode()
    {
        return this.toString().hashCode();
    }

}
