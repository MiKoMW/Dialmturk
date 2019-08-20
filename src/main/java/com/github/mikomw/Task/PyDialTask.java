package com.github.mikomw.Task;

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

    private String domain;
    private String condition;
    private String ents;
    private Integer pati;
    private String reqs;

    private String type;

    public PyDialTask(String domain, String condition, String ents, Integer pati, String reqs, String type) {
        this.domain = domain;
        this.condition = condition;
        this.ents = ents;
        this.pati = pati;
        this.reqs = reqs;
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getEnts() {
        return ents;
    }

    public void setEnts(String ents) {
        this.ents = ents;
    }

    public Integer getPati() {
        return pati;
    }

    public void setPati(Integer pati) {
        this.pati = pati;
    }

    public String getReqs() {
        return reqs;
    }

    public void setReqs(String reqs) {
        this.reqs = reqs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }





}
