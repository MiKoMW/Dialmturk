package com.github.mikomw.SubmissionScorer.SubmissionTest;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.Dialogue.DialogueUtterance;
import com.github.mikomw.SubmissionStat.DialStat;

import java.util.List;
import java.util.Set;

/**
 * This class is a test case, it checks if any user utterances contain blacklist word.
 *
 * It contains data submitted bu the tuckers.
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

public class WordBlacklsitTest implements SubmissionTest{

    // TODO: In case of larger scale tasks, use FSM or other structure to improve time complexity.
    private Set<String>  blacklist;

    public WordBlacklsitTest(Set<String> blacklist){
        this.blacklist = blacklist;
    }

    public boolean isPass(Submission submission){

        List<Dialogue> curDialogues = submission.getSubmittedDialogues();

        if (curDialogues== null) {
            return false;
        }

        if(curDialogues.size() == 0){
            return false;
        }

        // TODO: This algorithm is super slow.
        for(Dialogue dialogue: curDialogues){
            List<DialogueUtterance> utters = dialogue.getDialog();
            for(DialogueUtterance dia : utters){
                String[] segWord = dia.getUtter().split(" ");
                for(String st:segWord){
                    if(blacklist.contains(st)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
