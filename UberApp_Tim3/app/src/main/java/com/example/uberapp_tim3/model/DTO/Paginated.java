package com.example.uberapp_tim3.model.DTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Paginated<T> {
    private int totalCount;
    private List<T> results;


    public Paginated(int totalCount) {
        this.totalCount = totalCount;
        this.results = new ArrayList<>();
    }
    public void addResult(T result){
        this.results.add(result);
    }

    public Paginated(int totalCount, List<T> results) {
        this.totalCount = totalCount;
        this.results = results;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void add(T t) {
        this.results.add(t);
    }
}
