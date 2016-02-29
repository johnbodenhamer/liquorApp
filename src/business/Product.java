/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author ryan
 */
public class Product {
    private String brand;
    private double beginningWeight;
    private double currentWeight;
    private double cost;
    private double gramsUsed;
    private double liquidWeight;
    private double costPerGram;
    private double singlePour;
    private double unitsSold;
    private double ouncesSold;
    private double waste;
    private double dollarWaste;
    public Integer productID;
    private double fullWeight;
    
    public Product() {
        beginningWeight = 0;
        currentWeight = 0;
        cost = 0;
        unitsSold = 0;
        singlePour = 29.5735;

    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the beginningWeight
     */
    public double getBegginingWeight() {
        return beginningWeight;
    }

    /**
     * @param beginningWeight the beginningWeight to set
     */
    public void setBeginningWeight(double beginningWeight) {
        this.beginningWeight = beginningWeight;
    }

    /**
     * @return the currentWeight
     */
    public double getCurrentWeight() {
        return currentWeight;
    }

    /**
     * @param currentWeight the currentWeight to set
     */
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return the gramsUsed
     */
    public double getGramsUsed() {
        return gramsUsed;
    }

    /**
     * @param gramsUsed the gramsUsed to set
     */
    public void setGramsUsed(double gramsUsed) {
        this.gramsUsed = gramsUsed;
    }
    
    public double getCostPerGram(){
        return cost / liquidWeight;
    }
    
    public void setCostPerGram(double costPerGram){
        this.costPerGram = costPerGram;
    }
    
    /**
     * @return the liquidWeight
     */
    public double getLiquidWeight() {
        return liquidWeight;
    }

    /**
     * @param liquidWeight the liquidWeight to set
     */
    public void setLiquidWeight(double liquidWeight) {
        this.liquidWeight = liquidWeight;
    }

    /**
     * @return the singlePour
     */
    public double getSinglePour() {
        return singlePour;
    }

    /**
     * @param singlePour the singlePour to set
     */
    public void setSinglePour(double singlePour) {
        this.singlePour = singlePour;
    }

    /**
     * @return the unitsSold
     */
    public double getUnitsSold() {
        return unitsSold;
    }

    /**
     * @param unitsSold the unitsSold to set
     */
    public void setUnitsSold(double unitsSold) {
        this.unitsSold = unitsSold;
    }

    /**
     * @return the ouncesSold
     */
    public double getOuncesSold() {
        return singlePour * unitsSold;
    }

    /**
     * @param ouncesSold the ouncesSold to set
     */
    public void setOuncesSold(double ouncesSold) {
        this.ouncesSold = ouncesSold;
    }

    /**
     * @return the waste
     */
    public double getWaste() {
        return ouncesSold - gramsUsed;
    }

    /**
     * @param waste the waste to set
     */
    public void setWaste(double waste) {
        this.waste = waste;
    }

    /**
     * @return the dollarWaste
     */
    public double getDollarWaste() {
        return costPerGram * waste;
    }

    /**
     * @param dollarWaste the dollarWaste to set
     */
    public void setDollarWaste(double dollarWaste) {
        this.dollarWaste = dollarWaste;
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
     * @return the full_weight
     */
    public double getFullWeight() {
        return fullWeight;
    }

    /**
     * @param full_weight the full_weight to set
     */
    public void setFullWeight(double full_weight) {
        this.fullWeight = full_weight;
    }
    
}

    

