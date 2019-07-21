package com.github.mikomw.SubmissionStat;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.Dialogue.DialogueUtterance;

public class DialStat implements SubmissionStat {

    public static Double dialLength(Dialogue dialogue){
        double ans;
        ans = (dialogue.getDialog().size()/2);
        return ans;
    }

    // TODO: This is a sample method. Replace it with some better one.
    public static Double averageUserUtteranceTokenLength(Dialogue dialogue){
        double ans;

        int con = 0;
        int tokenNum = 0;

        for(DialogueUtterance dialogueUtterance :dialogue.getDialog()){
            if(dialogueUtterance.getRole().equals("You")){
                con++;
                tokenNum+=dialogueUtterance.getUtter().split(" ").length;
            }
        }

        ans = tokenNum/con;
        return ans;
    }

}
