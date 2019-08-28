package com.github.mikomw.samples;

import com.alibaba.fastjson.JSON;
import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.MturkClient;

import java.util.List;

public class GetSubmissionSample {

    public static void main(String[] args){

        String hitID = "3D17ECOUOGTYAT3OTUC6P24BA4C13Q";
        String pathToDialogue = "./src/main/java/com/github/mikomw/config/dialogue.json";
        String pathToSurvey = "./src/main/java/com/github/mikomw/config/survey.json";
        MturkClient mturkClient = new MturkClient(false);
        List<Submission> submissions = (mturkClient.getSubmissionFromFile(hitID,pathToDialogue,pathToSurvey));
        System.out.println(submissions.size());
        for(Submission submission : submissions){
            System.out.println(JSON.toJSONString(submission.getSubmittedDialogues()));
            System.out.println(JSON.toJSONString(submission.getSubmittedSurvey()));
        }
    }

}
