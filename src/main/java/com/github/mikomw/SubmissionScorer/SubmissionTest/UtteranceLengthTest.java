package com.github.mikomw.SubmissionScorer.SubmissionTest;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.SubmissionStat.DialStat;

public class UtteranceLengthTest implements SubmissionTest{

    private Double requiredAverageLength;

    public UtteranceLengthTest(Double length){
        this.requiredAverageLength = length;
    }

    public boolean isPass(Submission submission){
        boolean ans = false;

        Dialogue curDialogue = submission.getSubmittedDialogue();

        if (curDialogue == null) {
            return false;
        }
        if(requiredAverageLength <= DialStat.averageUserUtteranceTokenLength(curDialogue)){
            ans = true;
        }
        return ans;
    }

}
