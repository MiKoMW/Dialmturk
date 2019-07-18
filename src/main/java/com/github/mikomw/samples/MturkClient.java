package com.github.mikomw.samples;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.mturk.AmazonMTurk;
import com.amazonaws.services.mturk.AmazonMTurkClientBuilder;
import com.amazonaws.services.mturk.model.GetAccountBalanceRequest;
import com.amazonaws.services.mturk.model.GetAccountBalanceResult;

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



    public static void main(String[] args){
        MturkClient mturkClient = new MturkClient();
        System.out.println(mturkClient.getAccountBalance());
    }

}
