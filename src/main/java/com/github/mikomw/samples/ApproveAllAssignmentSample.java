package com.github.mikomw.samples;

import com.github.mikomw.MturkClient;

public class ApproveAllAssignmentSample {

    public static void main(String[] args){
        MturkClient mturkClient = new MturkClient();
        String HIT_ID_TO_APPROVE = "362E9TQF2JOA4BBAWG4W0VA6VX7GIS";
        mturkClient.approveAllAssignment(mturkClient.getAssignments(HIT_ID_TO_APPROVE));
        System.out.println(mturkClient.getAccountBalance());
    }

}
