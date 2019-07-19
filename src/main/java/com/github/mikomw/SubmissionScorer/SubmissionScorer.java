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

    public static void main(String[] args){

        System.out.println("Hi!");

    }

}
