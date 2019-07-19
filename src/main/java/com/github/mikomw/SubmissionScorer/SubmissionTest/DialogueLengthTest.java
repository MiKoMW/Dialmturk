package com.github.mikomw.SubmissionScorer.SubmissionTest;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.SubmissionStat.DialStat;
import com.github.mikomw.SubmissionStat.SubmissionStat;

public class DialogueLengthTest implements SubmissionTest {
    Double reqiuredDialogueTurn;

    public DialogueLengthTest(Double length){
        this.reqiuredDialogueTurn = length;
    }

    public boolean isPass(Submission submission){
        boolean ans = false;

        if(reqiuredDialogueTurn <= DialStat.dialLength(submission)){
            ans = true;
        }

        return ans;
    }

}
