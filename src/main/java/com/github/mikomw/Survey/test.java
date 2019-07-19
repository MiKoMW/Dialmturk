package com.github.mikomw.Survey;

import com.alibaba.fastjson.JSON;
import com.github.mikomw.Survey.Survey;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class test {

    public static void main(String[] args){
        String st = "";


        try {
            st = new String(Files.readAllBytes(Paths.get("./src/main/java/com/github/mikomw/Survey/survey.json")));
        }catch (Exception e){
            e.printStackTrace();
        }

        List<Survey> objectArray = JSON.parseArray(st,Survey.class);

        System.out.println(objectArray.size());

        for(Survey survey:objectArray){
            System.out.println(survey.get_id());
        }

        String temp = objectArray.get(0).getSurvey().get(1).getA();
        System.out.println(temp);
    }

}
