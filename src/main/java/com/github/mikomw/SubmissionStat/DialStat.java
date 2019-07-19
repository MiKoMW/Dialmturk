package com.github.mikomw.SubmissionStat;

import com.github.mikomw.Assignment.Submission;

public class DialStat implements SubmissionStat {

    public static Double dialLength(Submission submission){
        double ans;
        ans = (submission.getSubmittedDialogue().getDialog().size()/2);
        return ans;
    }

}
