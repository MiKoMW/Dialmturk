package com.github.mikomw.Assignment;

import com.amazonaws.services.mturk.model.Assignment;
import com.github.mikomw.MturkClient;

import java.util.List;

public class Test {

    public static void main(String[] args){

        String hitID = "3Z33IC0JC2K0B782HHX5N3EDYLG9VW";

        MturkClient mturkClient = new MturkClient(false);
        List<Assignment> assignments = (mturkClient.getAssignments(hitID));

        String temp = "";
        for(Assignment ass : assignments){
            Submission submission = new Submission(ass);
            System.out.print(submission.surveyCode);
        }

    }

}
