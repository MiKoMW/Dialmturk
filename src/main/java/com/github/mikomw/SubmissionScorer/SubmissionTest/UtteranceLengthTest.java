package com.github.mikomw.SubmissionScorer.SubmissionTest;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.SubmissionStat.DialStat;

import java.util.List;

/**
 * This class is a test case, which test if the average token number of all user dialogue turns of a submission is inclusively larger than the requirement.
 *
 * This test count the system turns.
 *
 * It contains data submitted bu the tuckers.
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

public class UtteranceLengthTest implements SubmissionTest{

    private Double requiredAverageLength;

    public UtteranceLengthTest(Double length){
        this.requiredAverageLength = length;
    }

    public boolean isPass(Submission submission){
        boolean ans = false;

        List<Dialogue> curDialogues = submission.getSubmittedDialogues();

        if (curDialogues == null) {
            return false;
        }

        if (curDialogues.size() == 0){
            return false;
        }

        int con = 0;
        int dialLength = 0;
        for(Dialogue dialogue : curDialogues){
            con++;
            dialLength += DialStat.averageUserUtteranceTokenLength(dialogue);
        }

        if(requiredAverageLength <= (dialLength/con)){
            ans = true;
        }

        return ans;
    }

}
