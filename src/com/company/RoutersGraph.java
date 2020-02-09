package com.company;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RoutersGraph {
    private List<Router> unusedRouters;
    private List<Router> usingRouters;
    private List<Router> connectingRouters;
    private List<Link> links;
    private boolean isIncorrectGraph;

    {
        unusedRouters = new LinkedList<Router>();
        usingRouters = new LinkedList<Router>();
        connectingRouters = new LinkedList<Router>();
        links = new LinkedList<Link>();
        isIncorrectGraph = false;
    }

    /**
     *Add router from user
     * @param routerPorts
     */
    private void setUnusedRouter(int routerPorts) {
        Router router = new Router(routerPorts, unusedRouters.size());
        unusedRouters.add(router);
    }

    /**
     * Add routers from user
     * @param routers
     */
    public void setUnusedRouters(int[] routers) {
        for (int routerPorts : routers) setUnusedRouter(routerPorts);
    }

    /**
     * Sort entered routers bu count of their interfaces
     */
    private void sortUnusedRouters() {
        Collections.sort(unusedRouters);
    }

    /**
     * Put links between two routers. In the end of the program, we show this links
     * @param firstRouter
     * @param secondRouter
     */
    private void setLink(Router firstRouter, Router secondRouter) {
        firstRouter.usePort();
        secondRouter.usePort();
        links.add(new Link(firstRouter, secondRouter));
    }

    /**
     * Method, where we call logic that build and check our routers graph:
     * -sort routers
     * -set routers with most number of interface as first in using
     * -put links between this first using router and other
     * -put links between connected routers for the first router and other unused routers
     * -iteratively continue running the algorithm until the end of the routers
     */
    public void buildGraph() {
        sortUnusedRouters();
        setFirstUsingRouter();
        building();
    }

    /**
     * put links between connected routers and other unused routers
     * iteratively continue running the algorithm until the end of the routers
     */
    private void building() {
        while (isExistUnusedRouter()) {
            if (isExistUnusedRouter() && isNotExistUsingRouters()) setFirstUsingRouter();
            layLinksFromUsingRouters();
            setUsingRouters();
            clearConnectingRouters();
            if (isIncorrectGraph) break;
        }
    }

    /**
     * put first router from unused router list to using list
     */
    private void setFirstUsingRouter() {
        usingRouters.add(unusedRouters.remove(0));
    }

    /**
     * set routers that was connected in previous tree level as using routers
     */
    private void setUsingRouters() {
        usingRouters.clear();
        usingRouters.addAll(connectingRouters);
    }

    /**
     * clear list of connecting routers
     */
    private void clearConnectingRouters() {
        connectingRouters.clear();
    }

    /**
     * lay links from using routers to unused routers
     */
    private void layLinksFromUsingRouters() {
        for (Router router : usingRouters) {
            if (!router.isHaveFreePort()) continue;
            layLinksFromUsingRouter(router);
            if (isIncorrectGraph) break;
        }
    }

    /**
     * lay links between using router and unused routers
     * @param router
     */
    private void layLinksFromUsingRouter(Router router) {
        while (router.isHaveFreePort()) {
            checkIfCorrectGraph();
            if (isIncorrectGraph) break;
            getUnusedRouterWithFrePort();
            setLink(router, getLastConnectingRouters());
        }
    }

    /**
     * if routers does not have any port we pas them and put needing router to connecting routers list
     */
    private void getUnusedRouterWithFrePort() {
        while (isExistUnusedRouter()) {
            connectingRouters.add(unusedRouters.remove(0));
            if (!getLastConnectingRouters().isHaveFreePort()) continue;
            break;
        }
    }

    /**
     * graph is wrong if unused routers list is empty of the moment of checking
     */
    private void checkIfCorrectGraph() {
        isIncorrectGraph = unusedRouters.size() == 0;
    }

    /**
     * check if exist unused router
     * @return
     */
    private boolean isExistUnusedRouter() {
        return unusedRouters.size() > 0;
    }

    /**
     * check if exist using routers
     * @return
     */
    private boolean isNotExistUsingRouters() {
        return usingRouters.size() == 0;
    }

    /**
     * get last router that was connected
     * @return
     */
    private Router getLastConnectingRouters() {
        return  connectingRouters.get(connectingRouters.size() - 1);
    }

    /**
     * get answer for current task
     * @return
     */
    public String getAnswer() {
        return isIncorrectGraph ? writeErrorAnswer() : writeRoutePairs();
    }

    /**
     * we send error message if graph is wrong
     * @return
     */
    public String writeErrorAnswer() {
        return "can't connect";
    }

    /**
     * we send list of edges
     * @return
     */
    private String writeRoutePairs() {
        String answer = "connect pairs: ";
        for (Link link : links) answer += writeRouterPair(link);
        return answer.substring(0, answer.length() - 2);
    }

    /**
     * write routers pair of graphs edge
     * @param link
     * @return
     */
    private String writeRouterPair(Link link) {
        return link.getFistPointNumber() + "-" + link.getSecondPointNumber() + ", ";
    }
}
