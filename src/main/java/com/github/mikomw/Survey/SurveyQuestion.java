package com.github.mikomw.Survey;

import com.amazonaws.services.dynamodbv2.xspec.S;

/**
 * Each question Mturk submitted to the Dialogue Survey
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

public class SurveyQuestion {

    private String Type;
    private String A;
    private String Name;
    private String Q;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q = q;
    }

    public String toString() {
        /*
        Sample Survey Question.
        "Type": "SubOpen",
        "A": "Yes!",
        "Name": "Noobot",
        "Q": "Is it working?"
      },
        */

        String ans;
        ans = "Type: " + this.Type + ". Name: " + this.Name + ". Q: " + this.Q + ". A: " + this.A + ".";
        return ans;
    }


}
