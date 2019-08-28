package com.github.mikomw.Task;

import java.util.ArrayList;
import java.util.List;

public class ClusterProjectTask {

    //{"taskID": 10000, "tasks":[{"Dom" : "CamAttractions",  "Cons": "pricerange=dontcare, area=south, kind=entertainment", "Ents": "nusha, tenpin, cherry hinton hall and grounds", "Pati": 5, "Reqs": "name", "Type": "attraction",  "domain_text": null}, {"Dom" : "CamTransport", "Cons": "transtype=airport, area=dontcare",     "Ents": "cambridge airport, london stansted airport, heathrow airport",   "Pati": 5,   "Reqs": "name", "Type": "transport",   "domain_text": null}]}



    private Integer taskID;
    private List<PyDialTask> tasks;

    public ClusterProjectTask(Integer id){

        this.taskID = id;
        this.tasks = new ArrayList<>();

    }

    public ClusterProjectTask(){
        this(0);
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public List<PyDialTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<PyDialTask> tasks) {
        this.tasks = tasks;
    }

    public void addPyDialTask(PyDialTask pyDialTask){
        this.tasks.add(pyDialTask);
    }


}
