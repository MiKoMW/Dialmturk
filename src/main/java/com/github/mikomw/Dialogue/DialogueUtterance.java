package com.github.mikomw.Dialogue;

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
    private long timestamp;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
