package com.prashant.microservice.gitrepo.models;

import java.util.List;

public class GitRepoResponse extends GitResponse {

    List<GitHottestRepo> items;

    public List<GitHottestRepo> getItems() {
        return items;
    }

    public void setItems(List<GitHottestRepo> items) {
        this.items = items;
    }

}
