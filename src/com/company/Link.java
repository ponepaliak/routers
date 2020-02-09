package com.company;

public class Link {
    private Router firstPoint;
    private Router secondPoint;

    /**
     * setter for first point
     * @param firstPoint
     */
    private void setFirstPoint(Router firstPoint) {
        this.firstPoint = firstPoint;
    }

    /**
     * setter for second point
     * @param secondPoint
     */
    private void setSecondPoint(Router secondPoint) {
        this.secondPoint = secondPoint;
    }

    /**
     * get number position of first point
     * @return
     */
    public int getFistPointNumber() {
        return firstPoint.getNumber();
    }

    /**
     * get number position of second point
     * @return
     */
    public int getSecondPointNumber() {
        return secondPoint.getNumber();
    }

    /**
     * constructor
     * @param firstPoint
     * @param secondPoint
     */
    public Link(Router firstPoint, Router secondPoint) {
        setFirstPoint(firstPoint);
        setSecondPoint(secondPoint);
    }
}
