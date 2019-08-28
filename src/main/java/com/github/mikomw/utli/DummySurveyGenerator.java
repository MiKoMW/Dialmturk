package com.github.mikomw.utli;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.github.mikomw.Survey.Survey;
import com.github.mikomw.Survey.SurveyQuestion;

import java.util.ArrayList;
import java.util.List;

public class DummySurveyGenerator {

    /*
    Field from Survey.
    private String _id;
    private List<SurveyQuestion> survey;
    private String userID;
    private String subId;
    private long timestamp;
     */

    public static Survey generateOneSurvey(String userID, Integer numOfSurveyQestions){
        Survey survey = new Survey();
        survey.set_id("Mi");
        List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
        for(int con = 0; con < numOfSurveyQestions; con++){
            SurveyQuestion surveyQuestion = new SurveyQuestion();
            surveyQuestion.setA("Hi!");
            surveyQuestion.setQ("How are you?");
            surveyQuestion.setName("Noobot");
            surveyQuestion.setType("SubOpen");
            surveyQuestionList.add(surveyQuestion);
        }

        survey.setSurvey(surveyQuestionList);
        survey.setTimestamp(10086);
        survey.setSubId("XOXO");
        return survey;
    }



}

/*
    Example Survey JSON.
  {
    "_id": "5d2f09fd05cf8c00116aeb5e",
    "survey": [
      {
        "Type": "SubOpen",
        "A": "Yes!",
        "Name": "Noobot",
        "Q": "Is it working?"
      },
      {
        "Type": "SubOpen",
        "A": "Yes!",
        "Name": "PyDial",
        "Q": "Is it working?"
      }
    ],
    "userID": "1563363795846",
    "subId": "5d2dbc0125693a0011ffe83b",
    "timestamp": 1563363837311
  },
 */