package com.github.mikomw.samples;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.MturkClient;
import com.github.mikomw.SubmissionScorer.SubmissionScorer;
import com.github.mikomw.SubmissionScorer.SubmissionTest.DialogueLengthTest;
import com.github.mikomw.SubmissionScorer.SubmissionTest.EnoughSubmissionTest;
import com.github.mikomw.SubmissionScorer.SubmissionTest.UtteranceLengthTest;
import com.github.mikomw.SubmissionScorer.SubmissionTest.WordBlacklsitTest;

import java.util.*;

public class ApproveByTestSample {

    public static void main(String[] args){

        // If you wanna redirect to file log.
        /*
        try {
            System.setOut(new PrintStream(new File("approve.log")));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        String hitID = "3GV1I4SEOBNAXJUA56SNGKP9I1JL6M";
        String pathToDialogue = "./src/main/java/com/github/mikomw/config/dialogue.json";
        String pathToSurvey = "./src/main/java/com/github/mikomw/config/survey.json";
        MturkClient mturkClient = new MturkClient(false);
        List<Submission> submissions = (mturkClient.getSubmissionFromFile(hitID,pathToDialogue,pathToSurvey));
        System.out.println(submissions.size());

        SubmissionScorer submissionScorer = new SubmissionScorer();
        DialogueLengthTest dialogueLengthTest = new DialogueLengthTest(2.0);

        HashSet<String> blackworldset = new HashSet<>();
        blackworldset.add("JAVA");
        WordBlacklsitTest wordBlacklsitTest = new WordBlacklsitTest(blackworldset);

        UtteranceLengthTest utteranceLengthTest = new UtteranceLengthTest(2.0);

        EnoughSubmissionTest enoughSubmissionTest = new EnoughSubmissionTest(3);

        submissionScorer.addTest(dialogueLengthTest,10);
        submissionScorer.addTest(wordBlacklsitTest,10);
        submissionScorer.addTest(utteranceLengthTest,10);
        submissionScorer.addTest(enoughSubmissionTest,100);


        Map<String, List<Submission>> temp = submissionScorer.classifySubmission(submissions, 120);

        mturkClient.approveAllSubmission(temp.get("passed"));
        mturkClient.rejectAllSubmission(temp.get("failed"));

    }


}
