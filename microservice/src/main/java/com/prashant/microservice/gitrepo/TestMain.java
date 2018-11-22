package com.prashant.microservice.gitrepo;

import java.util.*;
import java.util.stream.Collectors;

public class TestMain {

    private Map<String, Integer> countMap = new HashMap<>();

    public static void main(String[] args) {
        TestMain main = new TestMain();
        List<String> data = Arrays.asList(new String[]{"a.html", "b.html", "a.html", "c.html", "b.html", "b.html"});
        data.forEach(e -> main.execute(e));
        System.out.println(main.topVisitedPages());
    }

    private void execute(String a) {
        if (a == null)
            throw new IllegalArgumentException("Not a valid argument");
        if (countMap.containsKey(a))
            countMap.put(a, countMap.get(a) + 1);
        else
            countMap.put(a, 1);
    }

    private List<String> topVisitedPages() {
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(countMap.entrySet());
        Collections.sort(entryList, Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()));
        return entryList.stream().map(e -> e.getKey()).collect(Collectors.toList());
    }
}
