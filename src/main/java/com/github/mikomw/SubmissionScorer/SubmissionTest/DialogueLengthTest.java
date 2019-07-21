package com.github.mikomw.SubmissionScorer.SubmissionTest;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.SubmissionStat.DialStat;
import com.github.mikomw.SubmissionStat.SubmissionStat;

public class DialogueLengthTest implements SubmissionTest {
    private Double reqiuredDialogueTurn;

    public DialogueLengthTest(Double length){
        this.reqiuredDialogueTurn = length;
    }

    public boolean isPass(Submission submission){
        boolean ans = false;
        Dialogue curDialogue = submission.getSubmittedDialogue();
        if (curDialogue == null) {
            return false;
        }
        if(reqiuredDialogueTurn <= DialStat.dialLength(curDialogue)){
            ans = true;
        }

        return ans;
    }

}
