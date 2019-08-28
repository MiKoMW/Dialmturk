package com.github.mikomw.samples;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.mikomw.Task.ClusterProjectTask;
import com.github.mikomw.Task.PyDialTask;
import com.github.mikomw.util.FileIO;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TranslatePydialTasks {
    public static void main(String[] args){

        String pydialTaskPath = "./src/main/java/com/github/mikomw/config/tasks10.json";
        String dialcrowdOutPash = "./src/main/java/com/github/mikomw/config/task_out.json";

        String st = "";
        try {
            st = new String(Files.readAllBytes(Paths.get(pydialTaskPath)));
        }catch (Exception e){
            e.printStackTrace();
        }

        JSONObject tasksObj = JSON.parseObject(st);
        JSONArray possible_domains = tasksObj.getJSONArray("possible_domains");
        JSONObject tasks = tasksObj.getJSONObject("tasks");

        List<ClusterProjectTask> taskList = new ArrayList<>();

        for(String key : tasks.keySet()){
            System.out.println(key);

            ClusterProjectTask clusterProjectTask = new ClusterProjectTask(Integer.parseInt(key));

            JSONObject task = tasks.getJSONObject(key);

            for(String domain : task.keySet()){
                JSONObject temp = task.getJSONObject(domain);
                String cons = temp.getString("Cons");
                String ents = temp.getString("Ents");
                Integer pati = temp.getInteger("Pati");
                String reqs = temp.getString("Reqs");
                String type = temp.getString("Type");
                PyDialTask pyDialTask = new PyDialTask(domain,cons,ents,pati,reqs,type);
                clusterProjectTask.addPyDialTask(pyDialTask);
            }
            taskList.add(clusterProjectTask);
        }

        System.out.println(taskList.size());
        String json = JSON.toJSONString(taskList.get(1));

        ArrayList<String> result = new ArrayList<>();
        for(ClusterProjectTask clusterProjectTask : taskList){
            result.add(JSON.toJSONString(clusterProjectTask));
        }

        try {
            FileIO.writeLine(result, dialcrowdOutPash);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
