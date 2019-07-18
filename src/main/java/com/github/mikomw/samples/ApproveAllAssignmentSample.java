package com.github.mikomw.samples;

import com.github.mikomw.MturkClient;

public class ApproveAllAssignmentSample {

    public static void main(String[] args){
        MturkClient mturkClient = new MturkClient();
        String HIT_ID_TO_APPROVE = "3I7KR83SNCBMVWEQIDJYLU3WIMSK92";
        mturkClient.rejectAllAssignment(mturkClient.getAssignments(HIT_ID_TO_APPROVE));
        System.out.println(mturkClient.getAccountBalance());
    }

}
