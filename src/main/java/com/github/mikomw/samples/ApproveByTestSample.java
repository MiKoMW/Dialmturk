package com.github.mikomw.samples;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.MturkClient;
import com.github.mikomw.SubmissionScorer.SubmissionScorer;
import com.github.mikomw.SubmissionScorer.SubmissionTest.DialogueLengthTest;
import com.github.mikomw.SubmissionScorer.SubmissionTest.WordBlacklsitTest;

import java.lang.reflect.Array;
import java.util.*;

public class ApproveByTestSample {

    public static void main(String[] args){
        String hitID = "3J5XXLQDHO9EW5JM08WVWQBRYTWV3R";
        String pathToDialogue = "./src/main/java/com/github/mikomw/config/dialogue.json";
        String pathToSurvey = "./src/main/java/com/github/mikomw/config/survey.json";
        MturkClient mturkClient = new MturkClient(false);
        List<Submission> submissions = (mturkClient.getSubmission(hitID,pathToDialogue,pathToSurvey));
        System.out.println(submissions.size());

        SubmissionScorer submissionScorer = new SubmissionScorer();
        DialogueLengthTest dialogueLengthTest = new DialogueLengthTest(2.0);

        HashSet<String> blackworldset = new HashSet<>();
        blackworldset.add("JAVA");
        WordBlacklsitTest wordBlacklsitTest = new WordBlacklsitTest(blackworldset);

        submissionScorer.addTest(dialogueLengthTest,10);
        submissionScorer.addTest(wordBlacklsitTest,10);

        Map<String, List<Submission>> temp = submissionScorer.classifySubmission(submissions, 10);

        mturkClient.approveAllSubmission(temp.get("passed"));
        mturkClient.rejectAllSubmission(temp.get("failed"));

    }


}
