package com.github.mikomw.util;

import com.amazonaws.services.mturk.model.Assignment;
import com.github.mikomw.Assignment.Submission;

public class DummySubmissionGenerator {

    /*
    Fields from Submission Class.
    public final Assignment assignment;
    public final String surveyCode;

    private List<Dialogue> submittedDialogues;
    private Survey submittedSurvey;

    private boolean isApproved;
    private boolean isRejected;

    public int score;
    private String note;
     */

    /**
     * This method generate a Dummy Submission.
     *
     * @param uID This is the survey code. It is the unique identifier for a submission.
     *
     * @return a double which give a distance between two position.
     */
    public static Submission generateOneSubmission(String uID,Integer numOfDialogueUtterances, Integer lengthOfUtterance,boolean addEndBotUtterance, Integer numOfDialogues, Integer numOfSurveyQestions){

        Assignment assignment = new Assignment();
        assignment.setAnswer("<?xml version=\"1.0\" encoding=\"ASCII\"?><QuestionFormAnswers xmlns=\"" +
                "http://mechanicalturk.amazonaws.com/AWSMechanicalTurkDataSchemas/2005-10-01/QuestionFormAnswers.xsd\">" +
                "<Answer><QuestionIdentifier>survey_code</QuestionIdentifier><FreeText>" + uID +
                "</FreeText></Answer></QuestionFormAnswers>");

        Submission submission = new Submission(assignment);
        submission.setSubmittedSurvey(DummySurveyGenerator.generateOneSurvey(uID,numOfSurveyQestions));
        submission.setSubmittedDialogues(DummyDialogueGenerator.generateListDialogues(uID, numOfDialogueUtterances, lengthOfUtterance, addEndBotUtterance, numOfDialogues));
        return new Submission(assignment);

    }
}
