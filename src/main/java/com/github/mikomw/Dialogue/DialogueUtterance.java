package com.github.mikomw.Dialogue;

public class DialogueUtterance {
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

    private String role;
    private String utter;

}
