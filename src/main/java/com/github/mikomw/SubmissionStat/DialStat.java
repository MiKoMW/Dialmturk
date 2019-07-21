package com.github.mikomw.SubmissionStat;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;

public class DialStat implements SubmissionStat {

    public static Double dialLength(Dialogue dialogue){


        double ans;
        ans = (dialogue.getDialog().size()/2);
        return ans;
    }

}
