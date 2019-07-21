package com.github.mikomw.Assignment;

import com.amazonaws.services.mturk.model.Assignment;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.Survey.Survey;

// Should be called Assignment, rename it to solve ambiguity.
public class Submission {

    public final Assignment assignment;
    public final String surveyCode;
    private Dialogue submittedDialogue;
    private Survey submittedSurvey;

    public int score;

    private boolean isApproved;
    private boolean isRejected;

    private String note;

    public Submission(Assignment assignment){
        this(assignment,null,null);
    }

    public Submission(Assignment assignment, Dialogue submittedDialogue, Survey submittedSurvey){
        this.assignment = assignment;
        String temp = assignment.getAnswer();
        // TODO: This code is [Facepalm]. Change it to XML parser.
        this.surveyCode = temp.substring(temp.indexOf("<FreeText>") + "<FreeText>".length(),temp.indexOf("</FreeText>"));
        this.submittedDialogue = submittedDialogue;
        this.submittedSurvey = submittedSurvey;
    }

    public Dialogue getSubmittedDialogue() {
        return submittedDialogue;
    }

    public void setSubmittedDialogue(Dialogue submittedDialogue) {
        this.submittedDialogue = submittedDialogue;
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

    public boolean isValidSubmission(){
        return (assignment != null && surveyCode != null && submittedDialogue != null && submittedSurvey != null);
    }

}
