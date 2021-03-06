package com.github.mikomw.samples;

import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.MturkClient;
import com.github.mikomw.SubmissionScorer.SubmissionScorer;
import com.github.mikomw.SubmissionScorer.SubmissionTest.DialogueLengthTest;
import com.github.mikomw.SubmissionScorer.SubmissionTest.WordBlacklsitTest;

import java.util.HashSet;
import java.util.List;

public class ScoreSubmissionSample {

    public static void main(String[] args){

        String hitID = "362E9TQF2JOA4BBAWG4W0VA6VX7GIS";
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

        submissionScorer.addTest(dialogueLengthTest,10);
        submissionScorer.addTest(wordBlacklsitTest,10);
        for(Submission submission : submissions){
            Integer score;
            score = submissionScorer.getSubmissionScore(submission);
            System.out.println("The score for this submission is: " + score);
        }

    }

}
