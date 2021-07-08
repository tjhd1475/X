package com.qiashe.domain;

public class Opinion {
    private Integer id;
    private String content;
    private String solution;
    private Integer type;
    private Integer result;

    public Opinion(Integer id, String content, String solution, Integer type, Integer result) {
        this.id = id;
        this.content = content;
        this.solution = solution;
        this.type = type;
        this.result = result;
    }

    public Opinion() {
    }

    public Opinion(String content, String solution, Integer type, Integer result) {
        this.content = content;
        this.solution = solution;
        this.type = type;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
