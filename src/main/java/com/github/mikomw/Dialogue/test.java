package com.github.mikomw.Dialogue;

import com.alibaba.fastjson.JSON;
import com.github.mikomw.Survey.Survey;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class test {

    public static void main(String[] args){
        String st = "";


        try {
            st = new String(Files.readAllBytes(Paths.get("./src/main/java/com/github/mikomw/config/dialogue.json")));
        }catch (Exception e){
            e.printStackTrace();
        }

        List<Dialogue> objectArray = JSON.parseArray(st,Dialogue.class);

        System.out.println(objectArray.size());

        for(Dialogue dialogue:objectArray){
            System.out.println(dialogue.getUserID());
        }

        String temp = objectArray.get(0).getDialog().get(1).getUtter();
        String tempp = objectArray.get(0).getDialog().get(1).getRole();

        System.out.println(temp);
        System.out.println(tempp);
    }


}
