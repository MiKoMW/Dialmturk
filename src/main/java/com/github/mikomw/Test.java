package com.github.mikomw;

import com.alibaba.fastjson.JSON;
import com.amazonaws.services.mturk.model.Assignment;
import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.Survey.Survey;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Test {

    public static void main(String[] args){


        String hitID = "3Z33IC0JC2K0B782HHX5N3EDYLG9VW";

        MturkClient mturkClient = new MturkClient(false);
        List<Assignment> assignments = (mturkClient.getAssignments(hitID));




        HashMap<String,Dialogue> dialHashMap = new HashMap<>();
        HashMap<String,Survey> surveyHashMap = new HashMap<>();
        String st = "";
        try {
            st = new String(Files.readAllBytes(Paths.get("./src/main/java/com/github/mikomw/config/dialogue.json")));
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Dialogue> dialgoueArray = JSON.parseArray(st,Dialogue.class);
        try {
            st = new String(Files.readAllBytes(Paths.get("./src/main/java/com/github/mikomw/config/survey.json")));
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Survey> surveyArray = JSON.parseArray(st,Survey.class);


        for(Dialogue dial : dialgoueArray){
            dialHashMap.put(dial.getUserID(),dial);
        }
        for(Survey survey : surveyArray){
            surveyHashMap.put(survey.getUserID(),survey);
        }

        String temp = "";
        for(Assignment ass : assignments){
            Submission submission = new Submission(ass);
            System.out.println("Submission: " + submission.surveyCode + " fetched.");
            String uid = submission.surveyCode;
            submission.setSubmittedDialogue(dialHashMap.get(uid));
            submission.setSubmittedSurvey(surveyHashMap.get(uid));
        }



    }

}
