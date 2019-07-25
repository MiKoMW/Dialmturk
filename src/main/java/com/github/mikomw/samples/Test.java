package com.github.mikomw.samples;

import com.alibaba.fastjson.JSON;
import com.github.mikomw.Dialogue.Dialogue;
import com.github.mikomw.Survey.Survey;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args){
        String st = "";
        try {
            st = new String(Files.readAllBytes(Paths.get("./src/main/java/com/github/mikomw/config/survey(12).json")));
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Survey> surveyArray = JSON.parseArray(st,Survey.class);

        for(Survey survey : surveyArray){
            System.out.println(survey.toString());
        }


    }
}
