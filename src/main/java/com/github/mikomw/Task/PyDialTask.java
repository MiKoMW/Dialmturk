package com.github.mikomw.Task;

import com.alibaba.fastjson.annotation.JSONField;

public class PyDialTask {

    /*
    "CamAttractions": {
                "Cons": "pricerange=dontcare, area=south, kind=entertainment",
                "Ents": "nusha, tenpin, cherry hinton hall and grounds",
                "Pati": 5,
                "Reqs": "name",
                "Type": "attraction"
            }
     */

    //{"Dom" : "CamAttractions",  "Cons": "pricerange=dontcare, area=south, kind=entertainment", "Ents": "nusha, tenpin, cherry hinton hall and grounds", "Pati": 5, "Reqs": "name", "Type": "attraction",  "domain_text": null}, {"Dom" : "CamTransport", "Cons": "transtype=airport, area=dontcare",     "Ents": "cambridge airport, london stansted airport, heathrow airport",   "Pati": 5,   "Reqs": "name", "Type": "transport",   "domain_text": null}

    private String Dom;
    private String Cons;
    private String Ents;
    private Integer Pati;
    private String Reqs;

    private String Type;

    public PyDialTask(String Dom, String condition, String ents, Integer pati, String reqs, String type) {
        this.Dom = Dom;
        this.Cons = condition;
        this.Ents = ents;
        this.Pati = pati;
        this.Reqs = reqs;
        this.Type = type;
    }

    @JSONField(name="Dom")
    public String getDom() {
        return Dom;
    }

    public void setDom(String dom) {
        this.Dom = dom;
    }

    @JSONField(name="Cons")
    public String getCons() {
        return Cons;
    }

    public void setCons(String cons) {
        this.Cons = cons;
    }

    @JSONField(name="Ents")
    public String getEnts() {
        return Ents;
    }

    public void setEnts(String ents) {
        this.Ents = ents;
    }

    @JSONField(name="Pati")
    public Integer getPati() {
        return Pati;
    }

    public void setPati(Integer pati) {
        this.Pati = pati;
    }

    @JSONField(name="Reqs")
    public String getReqs() {
        return Reqs;
    }

    public void setReqs(String reqs) {
        this.Reqs = reqs;
    }

    @JSONField(name="Type")
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }





}
