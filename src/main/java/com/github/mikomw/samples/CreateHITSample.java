package com.github.mikomw.samples;

import com.github.mikomw.MturkClient;
import com.github.mikomw.Task.HITask;

public class CreateHITSample {

    private static final String QUESTION_XML_FILE_NAME = "./src/main/java/com/github/mikomw/config/external.xml";

    public static void main(final String[] argv) {

        MturkClient mturkClient = new MturkClient(false);

        HITask HITask = new HITask("Dialogue System Evaluation","dialogue system, chatbot","Talk to our dialogue system and evaluate it.",
                QUESTION_XML_FILE_NAME,"0.0",24,10,480,2);

        mturkClient.publishHit(HITask);

    }

}
