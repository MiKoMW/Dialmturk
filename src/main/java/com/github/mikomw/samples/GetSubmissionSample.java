package com.github.mikomw.samples;

import com.alibaba.fastjson.JSON;
import com.amazonaws.services.mturk.model.Assignment;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.MturkClient;

import java.util.List;

public class GetSubmissionSample {

    public static void main(String[] args){

        String hitID = "3Z33IC0JC2K0B782HHX5N3EDYLG9VW";
        String pathToDialogue = "./src/main/java/com/github/mikomw/config/dialogue.json";
        String pathToSurvey = "./src/main/java/com/github/mikomw/config/survey.json";
        MturkClient mturkClient = new MturkClient(false);
        List<Submission> submissions = (mturkClient.getSubmission(hitID,pathToDialogue,pathToSurvey));
        System.out.println(submissions.size());
        for(Submission submission : submissions){
            System.out.println(JSON.toJSONString(submission.getSubmittedDialogue()));
            System.out.println(JSON.toJSONString(submission.getSubmittedSurvey()));
        }
    }

}
