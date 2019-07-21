package com.github.mikomw.SubmissionScorer.SubmissionTest;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.Dialogue.DialogueUtterance;
import com.github.mikomw.SubmissionStat.DialStat;

import java.util.List;
import java.util.Set;

public class WordBlacklsitTest implements SubmissionTest{

    Set<String>  blacklist;

    public WordBlacklsitTest(Set<String> blacklist){
        this.blacklist = blacklist;
    }


    public boolean isPass(Submission submission){
        Dialogue curDialogue = submission.getSubmittedDialogue();
        if (curDialogue == null) {
            return false;
        }
        List<DialogueUtterance> utters = curDialogue.getDialog();
        for(DialogueUtterance dia : utters){
            String[] segWord = dia.getUtter().split(" ");
            for(String st:segWord){
                if(blacklist.contains(st)){
                    return false;
                }
            }
        }
        return true;
    }
}
