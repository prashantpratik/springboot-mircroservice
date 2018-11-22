package com.prashant.microservice.gitrepo;

import com.prashant.microservice.gitrepo.models.GitHottestRepo;
import com.prashant.microservice.gitrepo.models.GitRepoResponse;
import com.prashant.microservice.gitrepo.models.GitUserResponse;
import com.prashant.microservice.gitrepo.models.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class defines behaviour to response based on input
 */
@Service("gitAccountService")
public class GitAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GitAccountService.class);
    @Value("${git.search.url}")
    private String gitSearchUserBaseUrl;

    @Value("${git.search.param}")
    private String queryParam;

    @Value("${git.search.repo}")
    private String gitSearchRepoBaseUrl;

    @Value("${git.search.repo.param}")
    private String queryRepoParam;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    /**
     * Returns list of UserAccount objects containing user data
     *
     * @param count
     * @return
     */
    public List<UserAccount> getOldestUserAccounts(int count) {
        UriComponentsBuilder gitUri = UriComponentsBuilder.fromUriString(gitSearchUserBaseUrl).query(queryParam);
        LOGGER.info("Processing User Account request for count: " + count);
        return restTemplate.getForObject(gitUri.toUriString(), GitUserResponse.class).getItems().stream().limit(count).collect(Collectors.toList());
    }

    /**
     * @param count
     * @return
     */
    public List<GitHottestRepo> getHottestRepo(int count) {
        String queryFirstParam = "created:" + getDateInStringFormat(-1);
        UriComponentsBuilder gitUri = UriComponentsBuilder.fromUriString(gitSearchRepoBaseUrl).queryParam("q", queryFirstParam).query(queryRepoParam);
        LOGGER.info("Processing Hottest Repo request for count: " + count);
        return restTemplate.getForObject(gitUri.toUriString(), GitRepoResponse.class).getItems().stream().limit(count).collect(Collectors.toList());
    }

    private String getDateInStringFormat(int noOfWeeksBefore) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, noOfWeeksBefore * 7);
        return sdf.format(now.getTime());
    }
}
