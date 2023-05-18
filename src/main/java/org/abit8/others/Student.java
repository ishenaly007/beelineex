package org.abit8.others;

import java.util.List;

public class Student {
    private String name;
    private String phone;
    private String githubUrl;
    private List<CountryResult> result;

    // Геттеры и сеттеры

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public List<CountryResult> getResult() {
        return result;
    }

    public void setResult(List<CountryResult> result) {
        this.result = result;
    }
}

