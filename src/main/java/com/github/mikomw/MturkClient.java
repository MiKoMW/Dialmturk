package com.github.mikomw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.mturk.AmazonMTurk;
import com.amazonaws.services.mturk.AmazonMTurkClientBuilder;
import com.amazonaws.services.mturk.model.*;
import com.github.mikomw.Assignment.Submission;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.Survey.Survey;
import com.github.mikomw.Task.HITInfo;
import com.github.mikomw.Task.HITask;
import com.github.mikomw.util.HttpRequest;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class contains various functionality provided by Amazon AWS API.
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

public class MturkClient {

    private static final String SANDBOX_ENDPOINT = "mturk-requester-sandbox.us-east-1.amazonaws.com";
    private static final String PRODUCTION_ENDPOINT = "mturk-requester.us-east-1.amazonaws.com";
    private static final String SIGNING_REGION = "us-east-1";
    private final AmazonMTurk client;
    private boolean isProduction;
    public MturkClient() {
        this(false);
    }

    public MturkClient(boolean isProduction) {
        this.isProduction = isProduction;
        if(isProduction){
            System.out.println("Add &amp;mturkProduction=true to your survey URL if you want to deploy to AMT in production. : )");
            this.client = getProductionClient();
        }else {
            this.client = getSandboxClient();
        }
    }


    private static AmazonMTurk getProductionClient() {
        AmazonMTurkClientBuilder builder = AmazonMTurkClientBuilder.standard();
        ProfileCredentialsProvider provider = new ProfileCredentialsProvider("./src/main/java/com/github/mikomw/config/awsKey.secret","mimo");
        builder.setCredentials(provider);
        builder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(PRODUCTION_ENDPOINT, SIGNING_REGION));
        return builder.build();
    }

    private static AmazonMTurk getSandboxClient() {
        AmazonMTurkClientBuilder builder = AmazonMTurkClientBuilder.standard();
        ProfileCredentialsProvider provider = new ProfileCredentialsProvider("./src/main/java/com/github/mikomw/config/awsKey.secret","mimo");
        builder.setCredentials(provider);
        builder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SANDBOX_ENDPOINT, SIGNING_REGION));
        return builder.build();
    }

    public String getAccountBalance() {
        GetAccountBalanceRequest getBalanceRequest = new GetAccountBalanceRequest();
        GetAccountBalanceResult result = client.getAccountBalance(getBalanceRequest);
        return result.getAvailableBalance();
    }

    public HITInfo publishHit(HITask HITask){

        // TODO: Add hit URL. &mturkProduction=true in url &amp;mturkProduction=true

        CreateHITRequest request = new CreateHITRequest();
        request.setMaxAssignments(HITask.getMaxAssignments());
        request.setLifetimeInSeconds(HITask.getLifetimeInSeconds());
        request.setAssignmentDurationInSeconds(HITask.getAssignmentDurationInSeconds());
        // Reward is a USD dollar amount - USD$0.20 in the example below
        request.setReward(HITask.getReward());
        request.setTitle(HITask.getTitle());
        request.setKeywords(HITask.getKeywords());
        request.setDescription(HITask.getDescription());
        request.setQuestion(HITask.getQuestion());
        request.setAutoApprovalDelayInSeconds(HITask.getAutoApprovalDelayInSeconds());

        if(HITask.getQualificationRequirements() != null && HITask.getQualificationRequirements().size() > 0){
            request.setQualificationRequirements(HITask.getQualificationRequirements());
        }
        CreateHITResult result = client.createHIT(request);

        System.out.println("Your HITask has been created. You can see it at this link:");
        if(isProduction){
            System.out.println("https://www.mturk.com/mturk/preview?groupId=" + result.getHIT().getHITTypeId());
        }else{
            System.out.println("https://workersandbox.mturk.com/mturk/preview?groupId=" + result.getHIT().getHITTypeId());
        }
        System.out.println("Your HITask ID is: " + result.getHIT().getHITId());

        return new HITInfo(result.getHIT().getHITId(), result.getHIT().getHITTypeId());

    }

    public List<Assignment> getAssignments(String hitId) {
        GetHITRequest getHITRequest = new GetHITRequest();
        getHITRequest.setHITId(hitId);
        GetHITResult getHITResult = client.getHIT(getHITRequest);
        System.out.println("HIT " + hitId + " status: " + getHITResult.getHIT().getHITStatus());

        ListAssignmentsForHITRequest listHITRequest = new ListAssignmentsForHITRequest();
        listHITRequest.setHITId(hitId);
        listHITRequest.setAssignmentStatuses(Collections.singletonList(AssignmentStatus.Submitted.name()));

        // Get a maximum of 10 completed assignments for this HIT
        //listHITRequest.setMaxResults(10);

        ListAssignmentsForHITResult listHITResult = client.listAssignmentsForHIT(listHITRequest);
        List<Assignment> assignmentList = listHITResult.getAssignments();
        System.out.println("The number of submitted assignments is " + assignmentList.size());

        // Iterate through all the assignments received
        for (Assignment asn : assignmentList) {

            System.out.println("The worker with ID " + asn.getWorkerId() + " submitted assignment "
                    + asn.getAssignmentId() + " and gave the answer " + asn.getAnswer());
        }
        return assignmentList;
    }

    public void approveAllAssignment(List<Assignment> assignments) {
        for (Assignment asn : assignments) {
            approveOneAssignment(asn);
        }
    }


    public void approveOneAssignment(Assignment asn) {
        this.approveOneAssignment(asn,"Good work, thank you!");
    }

    public void approveOneAssignment(Assignment asn, String feedbackMessage) {

        System.out.println("The worker with ID " + asn.getWorkerId() + " submitted assignment "
                + asn.getAssignmentId() + " and gave the answer " + asn.getAnswer());

        // Approve the assignment
        ApproveAssignmentRequest approveRequest = new ApproveAssignmentRequest();
        approveRequest.setAssignmentId(asn.getAssignmentId());
        approveRequest.setRequesterFeedback(feedbackMessage);
        approveRequest.setOverrideRejection(false); // A flag indicating that an assignment should be approved even if it was previously rejected.
        client.approveAssignment(approveRequest);
        System.out.println("Assignment has been approved: " + asn.getAssignmentId());
    }

    public void rejectAllAssignment(List<Assignment> assignments) {
        for (Assignment asn : assignments) {
            rejectOneAssignment(asn);

        }
    }

    // TODO: Check it works or not?
    public void rejectOneAssignment(Assignment asn) {
        this.rejectOneAssignment(asn, "Sorry about this. You submission didn;t meet out requirement.");
    }

    public void rejectOneAssignment(Assignment asn, String feedbackMessage) {
        System.out.println("The worker with ID " + asn.getWorkerId() + " submitted assignment "
                + asn.getAssignmentId() + " and gave the answer " + asn.getAnswer());
        // Approve the assignment
        RejectAssignmentRequest rejectRequest = new RejectAssignmentRequest();
        rejectRequest.setAssignmentId(asn.getAssignmentId());
        rejectRequest.setRequesterFeedback(feedbackMessage);
        client.rejectAssignment(rejectRequest);
        System.out.println("Assignment has been Rejected: " + asn.getAssignmentId());
    }

    public void approveOneSubmission(Submission submission) {
        approveOneAssignment(submission.assignment);
    }

    public void approveAllSubmission(List<Submission> submissions) {
        for(Submission submission : submissions) {
            approveOneAssignment(submission.assignment);
        }
    }


    public void rejectOneSubmission(Submission submission) {
        rejectOneAssignment(submission.assignment);
    }

    public void rejectAllSubmission(List<Submission> submissions) {
        for(Submission submission : submissions) {
            rejectOneAssignment(submission.assignment);
        }
    }

    public List<Submission> getSubmissionFromFile(String hitID, String pathToDialogue, String pathToSurvey){

        ArrayList<Submission> ans = new ArrayList<>();

        List<Assignment> assignments = (this.getAssignments(hitID));

        HashMap<String, List<Dialogue>> dialHashMap = new HashMap<>();
        HashMap<String, Survey> surveyHashMap = new HashMap<>();

        String st = "";
        try {
            st = new String(Files.readAllBytes(Paths.get(pathToDialogue)));
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Dialogue> dialogueArray = JSON.parseArray(st,Dialogue.class);
        try {
            st = new String(Files.readAllBytes(Paths.get(pathToSurvey)));
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Survey> surveyArray = JSON.parseArray(st,Survey.class);

        // TODO: Seeking for imporvement in algorithm.
        List<Dialogue> temp;
        for(Dialogue dial : dialogueArray){
            temp = dialHashMap.getOrDefault(dial.getUserID(),new ArrayList<>());
            temp.add(dial);
            dialHashMap.put(dial.getUserID(),temp);
        }

        for(Survey survey : surveyArray){
            surveyHashMap.put(survey.getUserID(),survey);
        }

        Submission submission;
        for(Assignment ass : assignments){
            submission = new Submission(ass);
            System.out.println("Submission: " + submission.surveyCode + " fetched.");
            String uid = submission.surveyCode;
            submission.setSubmittedDialogues(dialHashMap.get(uid));
            submission.setSubmittedSurvey(surveyHashMap.get(uid));
            ans.add(submission);
        }

        return ans;
    }

    public List<Submission> getSubmissionFromInteractiveURL(String hitID, String interactiveURL) throws Exception{

        ArrayList<Submission> ans = new ArrayList<>();
        List<Assignment> assignments = (this.getAssignments(hitID));
        String taskID = interactiveURL.substring(interactiveURL.indexOf("?ID=") + 4, interactiveURL.indexOf("?ID=") + 28);
        URL aURL = new URL(interactiveURL);

//        System.out.println("protocol = " + aURL.getProtocol());
//        System.out.println("authority = " + aURL.getAuthority());
//        System.out.println("host = " + aURL.getHost());
//        System.out.println("port = " + aURL.getPort());
//        System.out.println("path = " + aURL.getPath());
//        System.out.println("query = " + aURL.getQuery());
//        System.out.println("filename = " + aURL.getFile());
//        System.out.println("ref = " + aURL.getRef());

        String apiURL = aURL.getProtocol()+"://"+aURL.getHost()+"/api/";
        String interactiveQuery = apiURL+"get/result/interactive/" + taskID;
        System.out.println(interactiveQuery);

        String result = HttpRequest.sendGet(interactiveQuery,"");

        JSONObject resultJSON = JSON.parseObject(result);
        JSONArray dialogueJSON = resultJSON.getJSONArray("dialog");
        JSONArray surveyJSON =  resultJSON.getJSONArray("survey");

        HashMap<String, List<Dialogue>> dialHashMap = new HashMap<>();
        HashMap<String, Survey> surveyHashMap = new HashMap<>();
        List<Dialogue> dialogueArray = JSON.parseArray(dialogueJSON.toJSONString(),Dialogue.class);
        List<Survey> surveyArray = JSON.parseArray(surveyJSON.toJSONString(),Survey.class);

        // TODO: Seeking for imporvement in algorithm.
        List<Dialogue> temp;

        for(Dialogue dial : dialogueArray){
            temp = dialHashMap.getOrDefault(dial.getUserID(),new ArrayList<>());
            temp.add(dial);
            dialHashMap.put(dial.getUserID(),temp);
        }

        for(Survey survey : surveyArray){
            surveyHashMap.put(survey.getUserID(),survey);
        }

        Submission submission;

        for(Assignment ass : assignments){
            submission = new Submission(ass);
            System.out.println("Submission: " + submission.surveyCode + " fetched.");
            String uid = submission.surveyCode;
            submission.setSubmittedDialogues(dialHashMap.get(uid));
            submission.setSubmittedSurvey(surveyHashMap.get(uid));
            ans.add(submission);
        }


        return ans;
    }


    public List<Dialogue> getListDialoguesFromURL (String interactiveURL,String password) throws Exception{

        String taskID = interactiveURL.substring(interactiveURL.indexOf("?ID=") + 4, interactiveURL.indexOf("?ID=") + 28);
        URL aURL = new URL(interactiveURL);

        String apiURL = aURL.getProtocol()+"://"+aURL.getHost()+"/api/";
        String interactiveQuery = apiURL+"get/result/interactive/" + taskID + "/" + password;
        System.out.println(interactiveQuery);

        String result = HttpRequest.sendGet(interactiveQuery,"");

        JSONObject resultJSON = JSON.parseObject(result);
        JSONArray dialogueJSON = resultJSON.getJSONArray("dialog");
        JSONArray surveyJSON =  resultJSON.getJSONArray("survey");

        HashMap<String, List<Dialogue>> dialHashMap = new HashMap<>();
        List<Dialogue> dialogueArray = JSON.parseArray(dialogueJSON.toJSONString(),Dialogue.class);

        return dialogueArray;
    }

    public List<Survey> getListSurveysFromURL(String interactiveURL, String password) throws Exception{

        String taskID = interactiveURL.substring(interactiveURL.indexOf("?ID=") + 4, interactiveURL.indexOf("?ID=") + 28);
        URL aURL = new URL(interactiveURL);

        String apiURL = aURL.getProtocol()+"://"+aURL.getHost()+"/api/";
        String interactiveQuery = apiURL+"get/result/interactive/" + taskID + "/"+password;
        System.out.println(interactiveQuery);

        String result = HttpRequest.sendGet(interactiveQuery,"");

        JSONObject resultJSON = JSON.parseObject(result);
        JSONArray dialogueJSON = resultJSON.getJSONArray("dialog");
        JSONArray surveyJSON =  resultJSON.getJSONArray("survey");

        List<Survey> surveyArray = JSON.parseArray(surveyJSON.toJSONString(),Survey.class);

        return surveyArray;
    }




    public List<Submission> getSubmissionFromClusterURL(String hitID, String clusterTaskURL) throws Exception{

        ArrayList<Submission> ans = new ArrayList<>();
        List<Assignment> assignments = (this.getAssignments(hitID));

        MturkClient mturkClient = new MturkClient(false);

        String taskID = clusterTaskURL.substring(clusterTaskURL.indexOf("?ID=") + 4, clusterTaskURL.indexOf("?ID=") + 28);

        URL   aURL = new URL(clusterTaskURL);
        System.out.println("Please enter your admin password:");
        Scanner in = new Scanner(System.in);
        String password = in.next();

        String apiURL = aURL.getProtocol()+"://"+aURL.getHost()+"/api/";
        String clusterQuery = apiURL+"worker/cluster/" + taskID;

        System.out.println(clusterQuery);
        String result = HttpRequest.sendGet(clusterQuery,"");
        JSONObject resultJSON = JSON.parseObject(result);
        JSONArray jsonArray = resultJSON.getJSONArray("Label");
        List<String> systemURLs = new ArrayList<>();
        for (Object jsonObject: jsonArray){
            systemURLs.add((String) jsonObject);
        }

        List<Dialogue> dialogueArray = new ArrayList<>();
        List<Survey> surveyArray = new ArrayList<>();

        for(String url : systemURLs){
            dialogueArray.addAll(mturkClient.getListDialoguesFromURL(url,password));
            surveyArray.addAll(mturkClient.getListSurveysFromURL(url,password));
        }

        HashMap<String, List<Dialogue>> dialHashMap = new HashMap<>();
        HashMap<String, Survey> surveyHashMap = new HashMap<>();

        // TODO: Seeking for imporvement in algorithm.
        List<Dialogue> temp;

        for(Dialogue dial : dialogueArray){
            temp = dialHashMap.getOrDefault(dial.getUserID(),new ArrayList<>());
            temp.add(dial);
            dialHashMap.put(dial.getUserID(),temp);
        }

        for(Survey survey : surveyArray){
            surveyHashMap.put(survey.getUserID(),survey);
        }

        Submission submission;

        for(Assignment ass : assignments){
            submission = new Submission(ass);
            System.out.println("Submission: " + submission.surveyCode + " fetched.");
            String uid = submission.surveyCode;
            submission.setSubmittedDialogues(dialHashMap.get(uid));
            submission.setSubmittedSurvey(surveyHashMap.get(uid));
            ans.add(submission);
        }

        return ans;
    }


}
