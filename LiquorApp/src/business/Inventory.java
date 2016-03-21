/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Timestamp;

/**
 *
 * @author ryan
 */
public class Inventory {
    private Integer productID;
    private Integer inventoryID;
    private Timestamp tstamp;
    private int fullBottles;
    private double partialWeight;
    
    public Inventory() {
        
    }

    /**
     * @return the productID
     */
    public Integer getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    /**
     * @return the inventoryID
     */
    public Integer getInventoryID() {
        return inventoryID;
    }

    /**
     * @param inventoryID the inventoryID to set
     */
    public void setInventoryID(Integer inventoryID) {
        this.inventoryID = inventoryID;
    }

    /**
     * @return the tstamp
     */
    public Timestamp getTstamp() {
        return tstamp;
    }

    /**
     * @param tstamp the tstamp to set
     */
    public void setTstamp(Timestamp tstamp) {
        this.tstamp = tstamp;
    }

    /**
     * @return the fullBottles
     */
    public int getFullBottles() {
        return fullBottles;
    }

    /**
     * @param fullBottles the fullBottles to set
     */
    public void setFullBottles(int fullBottles) {
        this.fullBottles = fullBottles;
    }

    /**
     * @return the partialWeight
     */
    public double getPartialWeight() {
        return partialWeight;
    }

    /**
     * @param partialWeight the partialWeight to set
     */
    public void setPartialWeight(double partialWeight) {
        this.partialWeight = partialWeight;
    }
    
}
