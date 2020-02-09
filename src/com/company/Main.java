package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        int[] routers = new int[] {4, 3, 3, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5};
        System.out.println(buildGraph(routers));
    }

    public static String buildGraph(int[] routers) {
        RoutersGraph graph = new RoutersGraph();
        graph.setUnusedRouters(routers);
        graph.buildGraph();
        return graph.getAnswer();
    }
}
