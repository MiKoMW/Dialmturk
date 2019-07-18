package com.github.mikomw.samples;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.mturk.AmazonMTurk;
import com.amazonaws.services.mturk.AmazonMTurkClientBuilder;
import com.amazonaws.services.mturk.model.*;

import java.util.Collections;
import java.util.List;

public class MturkClient {

    private static final String SANDBOX_ENDPOINT = "mturk-requester-sandbox.us-east-1.amazonaws.com";
    private static final String PRODUCTION_ENDPOINT = "mturk-requester.us-east-1.amazonaws.com";
    private static final String SIGNING_REGION = "us-east-1";
    private final AmazonMTurk client;

    public MturkClient() {
        this(false);
    }

    public MturkClient(boolean isProduction) {
        if(isProduction){
            this.client = getProductionClient();
        }else {
            this.client = getSandboxClient();
        }
    }


    private static AmazonMTurk getProductionClient() {
        AmazonMTurkClientBuilder builder = AmazonMTurkClientBuilder.standard();
        ProfileCredentialsProvider provider = new ProfileCredentialsProvider("./src/main/java/com/github/mikomw/samples/awsKey.secret","mimo");
        builder.setCredentials(provider);
        builder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(PRODUCTION_ENDPOINT, SIGNING_REGION));
        return builder.build();
    }

    private static AmazonMTurk getSandboxClient() {
        AmazonMTurkClientBuilder builder = AmazonMTurkClientBuilder.standard();
        ProfileCredentialsProvider provider = new ProfileCredentialsProvider("./src/main/java/com/github/mikomw/samples/awsKey.secret","mimo");
        builder.setCredentials(provider);
        builder.setEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(SANDBOX_ENDPOINT, SIGNING_REGION));
        return builder.build();
    }

    private String getAccountBalance() {
        GetAccountBalanceRequest getBalanceRequest = new GetAccountBalanceRequest();
        GetAccountBalanceResult result = client.getAccountBalance(getBalanceRequest);
        return result.getAvailableBalance();
    }

    public HITInfo publishHit(HITask HITask){

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

        if(HITask.getQualificationRequirement() != null){
            request.setQualificationRequirements(Collections.singletonList(HITask.getQualificationRequirement()));
        }
        CreateHITResult result = client.createHIT(request);

        System.out.println("Your HITask has been created. You can see it at this link:");
        System.out.println("https://workersandbox.mturk.com/mturk/preview?groupId=" + result.getHIT().getHITTypeId());
        // System.out.println("https://www.mturk.com/mturk/preview?groupId=" + hitInfo.getHITTypeId());
        System.out.println("Your HITask ID is: " + result.getHIT().getHITId());
        return new HITInfo(result.getHIT().getHITId(), result.getHIT().getHITTypeId());

    }

    private List<Assignment> getAssignments(String hitId) {
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
//
//            // Approve the assignment
//            ApproveAssignmentRequest approveRequest = new ApproveAssignmentRequest();
//            approveRequest.setAssignmentId(asn.getAssignmentId());
//            approveRequest.setRequesterFeedback("Good work, thank you!");
//            approveRequest.setOverrideRejection(false);
//            client.approveAssignment(approveRequest);
//            System.out.println("Assignment has been approved: " + asn.getAssignmentId());
//        }
    }

    private void approveAllAssignment(List<Assignment> assignments) {


        for (Assignment asn : assignments) {
            System.out.println("The worker with ID " + asn.getWorkerId() + " submitted assignment "
                    + asn.getAssignmentId() + " and gave the answer " + asn.getAnswer());

            // Approve the assignment
            ApproveAssignmentRequest approveRequest = new ApproveAssignmentRequest();
            approveRequest.setAssignmentId(asn.getAssignmentId());
            approveRequest.setRequesterFeedback("Good work, thank you!");
            approveRequest.setOverrideRejection(false);
            client.approveAssignment(approveRequest);
            System.out.println("Assignment has been approved: " + asn.getAssignmentId());

        }
    }



    public static void main(String[] args){
        MturkClient mturkClient = new MturkClient();
        String HIT_ID_TO_APPROVE = "3I7KR83SNCBMVWEQIDJYLU3WIMSK92";
        mturkClient.approveAllAssignment(mturkClient.getAssignments(HIT_ID_TO_APPROVE));

        System.out.println(mturkClient.getAccountBalance());
    }

}
