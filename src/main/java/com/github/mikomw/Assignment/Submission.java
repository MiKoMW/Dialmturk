package com.github.mikomw.Assignment;

import com.amazonaws.services.mturk.model.Assignment;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.Survey.Survey;

import java.util.ArrayList;
import java.util.List;

/**
 * This class glues the Amazon Mturk Assignment and Dialcrowd Submission together.
 *
 * It contains data submitted bu the turkers/
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

public class Submission {

    public final Assignment assignment;
    public final String surveyCode;

    private List<Dialogue> submittedDialogues;
    private Survey submittedSurvey;

    private boolean hasProblem;

    private boolean isApproved;
    private boolean isRejected;

    public int score;
    private String note;

    public Submission(Assignment assignment){
        this(assignment,new ArrayList<>(),null);
    }

    public Submission(Assignment assignment, List<Dialogue> submittedDialogue, Survey submittedSurvey){
        this.assignment = assignment;
        String temp = assignment.getAnswer();
        // TODO: This code is [Facepalm]. Change it to XML parser.

        String beforeCode = "<Answer><QuestionIdentifier>survey_code</QuestionIdentifier><FreeText>";
        String afterCode = "</FreeText></Answer><Answer><QuestionIdentifier>hasproblem</QuestionIdentifier>";
        this.surveyCode = temp.substring(temp.indexOf(beforeCode) + beforeCode.length(),temp.indexOf(afterCode));

        String beforeProblem = "<Answer><QuestionIdentifier>hasproblem</QuestionIdentifier><FreeText>";
        String afterProblem = "</FreeText></Answer></QuestionFormAnswers>";
        this.hasProblem = Boolean.parseBoolean(temp.substring(temp.indexOf(beforeProblem) + beforeProblem.length(),temp.indexOf(afterProblem)));

        this.submittedDialogues = submittedDialogue;
        this.submittedSurvey = submittedSurvey;
    }

    public List<Dialogue> getSubmittedDialogues() {
        return submittedDialogues;
    }

    public void setSubmittedDialogues(List<Dialogue> submittedDialogues) {
        this.submittedDialogues = submittedDialogues;
    }

    public void addSubmittedDialogue(Dialogue submittedDialogue) {
        if(this.submittedDialogues == null){
            this.submittedDialogues = new ArrayList<>();
            this.submittedDialogues.add(submittedDialogue);
        }else {
            this.submittedDialogues.add(submittedDialogue);
        }

    }


    public Survey getSubmittedSurvey() {
        return submittedSurvey;
    }

    public void setSubmittedSurvey(Survey submittedSurvey) {
        this.submittedSurvey = submittedSurvey;
    }

    public boolean isDecided(){
        return this.isApproved || this.isRejected;
    }

    public boolean isApproved(){
        return this.isApproved;
    }

    public boolean isRejected(){
        return this.isRejected;
    }

    public void approve(){
        this.isApproved = true;
    }

    public void reject(){
        this.isRejected = true;
    }

    public String getNote(){
        return this.note;
    }

    public void addNote(String note){
        this.note += note;
    }

    public boolean isHasProblem() {
        return hasProblem;
    }

    public void setHasProblem(boolean hasProblem) {
        this.hasProblem = hasProblem;
    }

    public boolean isValidSubmission(){
        return (assignment != null && surveyCode != null && submittedDialogues != null && submittedSurvey != null);
    }

}
