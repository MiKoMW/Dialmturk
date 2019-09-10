package com.github.mikomw.samples;

import com.github.mikomw.MturkClient;

public class ApproveAllAssignmentSample {

    public static void main(String[] args){
        MturkClient mturkClient = new MturkClient(true);
        String HIT_ID_TO_APPROVE = "33W1NHWFZKQGJ3DGK6444XNKFP1TZE";
        mturkClient.approveAllAssignment(mturkClient.getAssignments(HIT_ID_TO_APPROVE));
        System.out.println(mturkClient.getAccountBalance());
    }

}
