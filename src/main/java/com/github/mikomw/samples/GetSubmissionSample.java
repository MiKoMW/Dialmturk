package com.github.mikomw.samples;

import com.alibaba.fastjson.JSON;
import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.MturkClient;

import java.util.ArrayList;
import java.util.List;

public class GetSubmissionSample {

    public static void main(String[] args){

        String hitID = "33W1NHWFZKQGJ3DGK6444XNKFP1TZE";

        String pathToDialogue = "./src/main/java/com/github/mikomw/config/dialogue.json";
        String pathToSurvey = "./src/main/java/com/github/mikomw/config/survey.json";
        String clusterURL = "https://dialeval.cs.hhu.de/worker_cluster/?ID=5d6642dc0d37e20011bc3772";
        MturkClient mturkClient = new MturkClient(true);
        List<Submission> submissions = new ArrayList<>();
        try {
            submissions = (mturkClient.getSubmissionFromClusterURL(hitID, clusterURL));
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(submissions.size());
        for(Submission submission : submissions){
            System.out.println(JSON.toJSONString(submission.getSubmittedDialogues()));
            System.out.println(JSON.toJSONString(submission.getSubmittedSurvey()));
        }
        for(Submission submission : submissions){
            System.out.println(submission.isHasProblem());
        }

    }

}
