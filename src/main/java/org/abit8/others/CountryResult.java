package org.abit8.others;

import java.util.List;

public class CountryResult {
    private String country;
    private int citiesCount;
    private List<String> cities;

    public CountryResult() {
    }

    public CountryResult(String country, int citiesCount, List<String> cities) {
        this.country = country;
        this.citiesCount = citiesCount;
        this.cities = cities;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCitiesCount() {
        return citiesCount;
    }

    public void setCitiesCount(int citiesCount) {
        this.citiesCount = citiesCount;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}

