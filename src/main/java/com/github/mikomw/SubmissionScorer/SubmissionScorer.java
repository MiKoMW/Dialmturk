package com.github.mikomw.SubmissionScorer;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.SubmissionScorer.SubmissionTest.SubmissionTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubmissionScorer {

    Map<SubmissionTest,Integer> test;

    public SubmissionScorer(){
        this.test = new HashMap<>();
    }

    public void addTest(SubmissionTest submissionTest,Integer score){
        this.test.put(submissionTest,score);
    }

    public void addTest(Map<SubmissionTest,Integer> testScorePairs){
        this.test.putAll(testScorePairs);
    }

    public Integer getSubmissionScore(Submission submission){
        Integer score = 0;

        for(SubmissionTest currentTest: test.keySet()){
            if(currentTest.isPass(submission)) {
                score += test.get(currentTest);
            }
        }
        return score;
    }

    public Map<String,List<Submission>> classifySubmission(List<Submission> submissions, Integer inclusiveThreshold){
        List<Submission> passed = new ArrayList<>();
        List<Submission> failed = new ArrayList<>();

        for(Submission submission :submissions){
            if(submission.isValidSubmission() &&this.getSubmissionScore(submission) >= inclusiveThreshold){
                passed.add(submission);
            }else {
                failed.add(submission);
            }
        }

        Map<String,List<Submission>> ans = new HashMap<>();
        ans.put("passed",passed);
        ans.put("failed",failed);
        return ans;
    }

    public static void main(String[] args){

        System.out.println("Hi!");

    }

}
