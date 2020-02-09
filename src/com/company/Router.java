package com.company;

public class Router implements Comparable<Router> {
    private int ports;
    private int usedPorts;
    private int number;

    {
        usedPorts = 0;
    }

    /**
     * set number of ports
     * @param ports
     */
    private void setPorts(int ports) {
        this.ports = ports;
    }

    /**
     * set router position in list from user
     * @param number
     */
    private void setNumber(int number) {
        this.number = number;
    }

    /**
     * get number of ports
     * @return
     */
    public int getPorts() {
        return ports;
    }

    /**
     * get router position in list from user
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * constructor
     * @param ports
     * @param number
     */
    public Router(int ports, int number) {
        setPorts(ports);
        setNumber(number);
    }

    /**
     * method for compare and sort routers
     * @param router
     * @return
     */
    @Override
    public int compareTo(Router router) {
        return  router.getPorts() - this.getPorts();
    }

    /**
     * denote one port as occupied
     */
    public void usePort() {
        usedPorts++;
    }

    /**
     * check if router have free ports
     * @return
     */
    public boolean isHaveFreePort() {
        return ports > usedPorts;
    }
}
