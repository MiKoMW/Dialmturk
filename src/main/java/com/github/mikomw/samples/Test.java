package com.github.mikomw.samples;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.mikomw.Task.PyDialTask;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args){

        String st = "";
        try {
            st = new String(Files.readAllBytes(Paths.get("./src/main/java/com/github/mikomw/config/tasks10.json")));
        }catch (Exception e){
            e.printStackTrace();
        }

        JSONObject tasksObj = JSON.parseObject(st);

        JSONArray possible_domains = tasksObj.getJSONArray("possible_domains");
        JSONObject tasks = tasksObj.getJSONObject("tasks");

        List<PyDialTask> taskList = new ArrayList<>();

        for(String key : tasks.keySet()){
            JSONObject task = tasks.getJSONObject(key);

            for(String domain : task.keySet()){
                JSONObject temp = task.getJSONObject(domain);
                String cons = temp.getString("Cons");
                String ents = temp.getString("Ents");
                Integer pati = temp.getInteger("Pati");
                String reqs = temp.getString("Reqs");
                String type = temp.getString("Type");
                PyDialTask pyDialTask = new PyDialTask(domain,cons,ents,pati,reqs,type);
                taskList.add(pyDialTask);
            }

        }

        System.out.println(taskList.size());

        System.out.println(taskList.get(1).getCondition());

    }
}
