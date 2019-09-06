package com.github.mikomw.samples;

import com.amazonaws.services.mturk.model.Comparator;
import com.amazonaws.services.mturk.model.Locale;
import com.amazonaws.services.mturk.model.QualificationRequirement;
import com.github.mikomw.MturkClient;
import com.github.mikomw.Task.HITInfo;
import com.github.mikomw.Task.HITask;

import java.util.ArrayList;
import java.util.List;

public class CreatHITWithQualificationSample {

    private static final String QUESTION_XML_FILE_NAME = "./src/main/java/com/github/mikomw/config/external.xml";

    public static void main(final String[] argv) {

        MturkClient mturkClient = new MturkClient(true);

        HITask HITask = new HITask("Dialogue System Evaluation","dialogue system, chatbot","Talk to our dialogue system and evaluate it.",
                QUESTION_XML_FILE_NAME,"0.0",24,1,7*24,2);

        // QualificationRequirement: Locale IN (US, CA, GB, DE)
        QualificationRequirement localeRequirement = new QualificationRequirement();
        localeRequirement.setQualificationTypeId("00000000000000000071");
        localeRequirement.setComparator(Comparator.In);
        List<Locale> localeValues = new ArrayList<>();
        //localeValues.add(new Locale().withCountry("US"));
        //localeValues.add(new Locale().withCountry("CA"));
        //localeValues.add(new Locale().withCountry("GB"));
        localeValues.add(new Locale().withCountry("DE"));

        localeRequirement.setLocaleValues(localeValues);
        localeRequirement.setRequiredToPreview(true);

        HITask.addQualificationRequirement(localeRequirement);


        HITInfo hitInfo = mturkClient.publishHit(HITask);

    }

}


