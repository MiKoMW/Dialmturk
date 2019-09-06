package com.github.mikomw.Task;

import com.amazonaws.services.mturk.model.QualificationRequirement;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a HIT in Amazon Mechanical Turk.
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

public class HITask {

    private String QUESTION_XML_FILE_NAME = "./src/main/java/com/github/mikomw/config/external.xml";
    private int maxAssignments;
    private long lifetimeInSeconds;
    private long assignmentDurationInSeconds;

    private long autoApprovalDelayInSeconds;

    private String reward;
    private String title;
    private String keywords;
    private String description;
    private String question;
    private String hitUrl;
    private String hitID;


    private boolean beenPublished;


    // TODO: Think about this,
    private List<QualificationRequirement> qualificationRequirements;

    // TODO: Add stuff like task results.

    // TODO: Set auto approve time.

    public HITask(String title, String keywords, String description, String question_XML_file_name, String reward, long lifetimeInHours, long assignmentDurationInMintues, long autoApprovalDelayInHours, int maxAssignments) {
        this.title = title;
        this.keywords = keywords;
        this.description = description;
        this.QUESTION_XML_FILE_NAME = question_XML_file_name;

        try {
            this.question = new String(Files.readAllBytes(Paths.get(QUESTION_XML_FILE_NAME)));
        }catch (Exception e){
            e.printStackTrace();
        }

        this.reward = reward;
        this.lifetimeInSeconds =lifetimeInHours * 60 * 60;
        this.assignmentDurationInSeconds = assignmentDurationInMintues * 60;
        this.autoApprovalDelayInSeconds = autoApprovalDelayInHours * 60 * 60;
        this.maxAssignments = maxAssignments;
        this.hitID = null;
        this.hitUrl = null;
        this.beenPublished = false;
        this.qualificationRequirements = new ArrayList<>();
    }


    public String getQUESTION_XML_FILE_NAME() {
        return QUESTION_XML_FILE_NAME;
    }

    public void setQUESTION_XML_FILE_NAME(String QUESTION_XML_FILE_NAME) {
        this.QUESTION_XML_FILE_NAME = QUESTION_XML_FILE_NAME;
    }

    public int getMaxAssignments() {
        return maxAssignments;
    }

    public void setMaxAssignments(int maxAssignments) {
        this.maxAssignments = maxAssignments;
    }

    public long getLifetimeInSeconds() {
        return lifetimeInSeconds;
    }

    public void setLifetimeInSeconds(long lifetimeInSeconds) {
        this.lifetimeInSeconds = lifetimeInSeconds;
    }

    public long getAssignmentDurationInSeconds() {
        return assignmentDurationInSeconds;
    }

    public void setAssignmentDurationInSeconds(long assignmentDurationInSeconds) {
        this.assignmentDurationInSeconds = assignmentDurationInSeconds;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QualificationRequirement> getQualificationRequirements() {
        return this.qualificationRequirements;
    }

    public void setQualificationRequirements(List<QualificationRequirement> qualificationRequirements) {
        this.qualificationRequirements = qualificationRequirements;
    }

    public void addQualificationRequirement(QualificationRequirement qualificationRequirement) {
        this.qualificationRequirements.add(qualificationRequirement);
    }

    public String getHitUrl() {
        return hitUrl;
    }

    public void setHitUrl(String hitUrl) {
        this.hitUrl = hitUrl;
    }

    public String getHitID() {
        return hitID;
    }

    public void setHitID(String hitID) {
        this.hitID = hitID;
    }

    public long getAutoApprovalDelayInSeconds() {
        return autoApprovalDelayInSeconds;
    }

    public void setAutoApprovalDelayInSeconds(long autoApprovalDelayInSeconds) {
        this.autoApprovalDelayInSeconds = autoApprovalDelayInSeconds;
    }

    public boolean isBeenPublished(){
        return this.beenPublished;
    }

    public void setBeenPublished(){
        this.beenPublished = true;
    }


}
