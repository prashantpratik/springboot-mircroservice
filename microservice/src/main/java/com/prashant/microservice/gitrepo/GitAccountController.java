package com.prashant.microservice.gitrepo;

import com.prashant.microservice.gitrepo.models.GitHottestRepo;
import com.prashant.microservice.gitrepo.models.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This class exposes an endpoint{oldestAccounts} to fetch data from GIT
 */
@RestController
@DependsOn("gitAccountService")
public class GitAccountController {

    @Autowired
    private GitAccountService gitAccountService;

    /**
     * This method exposes the end point
     *
     * @param count
     * @return
     */
    @RequestMapping(path = "/oldestAccounts/{count}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserAccount> getOldestUserAccounts(@PathVariable("count") int count) {
        return gitAccountService.getOldestUserAccounts(count);
    }

    /**
     *
     * @param count
     * @return
     */

    @RequestMapping(path = "/hottestRepos/{count}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GitHottestRepo> getHottestRepos(@PathVariable("count") int count) {
        return gitAccountService.getHottestRepo(count);
    }
}
