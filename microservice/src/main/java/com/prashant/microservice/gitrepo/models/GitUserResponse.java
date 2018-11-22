package com.prashant.microservice.gitrepo.models;

import java.util.List;

public class GitUserResponse extends GitResponse {
    List<UserAccount> items;

    public List<UserAccount> getItems() {
        return items;
    }

    public void setItems(List<UserAccount> items) {
        this.items = items;
    }

}
