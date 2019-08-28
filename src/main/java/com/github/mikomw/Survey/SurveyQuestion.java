package com.github.mikomw.Survey;

import com.alibaba.fastjson.JSON;
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

    public String toString(){
        return JSON.toJSONString(this);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj)
        {
            return true;
        }

        if(!(obj instanceof SurveyQuestion))
        {
            return false;
        }

        final SurveyQuestion surveyQuestion = (SurveyQuestion)obj;

        return JSON.toJSONString(surveyQuestion).equals(JSON.toJSONString(this));
    }

    public int hashCode()
    {
        return this.toString().hashCode();
    }
}
