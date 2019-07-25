package com.github.mikomw.SubmissionScorer.SubmissionTest;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.SubmissionStat.DialStat;
import com.github.mikomw.Survey.Survey;

import java.util.List;

public class EnoughSubmissionTest implements SubmissionTest {


    private Integer numOfDialoguesRequired;

    // TODO: Add this in case of list of survey in the future.
    private Integer numOfSurveyRequired;


    public EnoughSubmissionTest(){
        this.numOfDialoguesRequired = 0;
        this.numOfSurveyRequired =  0;
    }

    public EnoughSubmissionTest(Integer numOfDialoguesRequired){
        this.numOfSurveyRequired = 0;
        this.numOfDialoguesRequired = 0;
    }


    public EnoughSubmissionTest(Integer numOfDialoguesRequired, Integer numOfSurveyRequired){
        this.numOfDialoguesRequired = numOfDialoguesRequired;
        this.numOfSurveyRequired = numOfSurveyRequired;
    }

    public boolean isPass(Submission submission){
        boolean ans = false;

        List<Dialogue> submissionDialogues = submission.getSubmittedDialogues();
        Survey submissionSurvey = submission.getSubmittedSurvey();

        if(submissionDialogues == null || submissionSurvey == null){
            return ans;
        }

        if(submissionDialogues.size() >= numOfDialoguesRequired){
            ans = true;
        }

        return ans;
    }


}
