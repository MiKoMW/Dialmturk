package com.github.mikomw.samples;

import com.amazonaws.services.cloudwatch.model.HistoryItemType;
import com.amazonaws.services.mturk.model.Comparator;
import com.amazonaws.services.mturk.model.Locale;
import com.amazonaws.services.mturk.model.QualificationRequirement;
import com.github.mikomw.MturkClient;
import com.github.mikomw.Task.HITask;

import java.util.ArrayList;
import java.util.List;

public class CreateHITSample {

    private static final String QUESTION_XML_FILE_NAME = "./src/main/java/com/github/mikomw/config/external.xml";

    public static void main(final String[] argv) {

        MturkClient mturkClient = new MturkClient(true);

        HITask HITask = new HITask("Dialogue System Evaluation","dialogue system, chatbot","Talk to our dialogue system and evaluate it.",
                QUESTION_XML_FILE_NAME,"0.0",24,10,480,2);

        mturkClient.publishHit(HITask);

    }

}
