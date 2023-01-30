package Locals;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class that represents a Local
 * Local atributs:
 *      id: identifier of the local
 *      longitude: longitude coordinate
 *      latitude: latitude coordinate
 *      energyAmount: max of energy hold by the portal
 * @author Tiago Lopes
 */
public abstract class Local {

    private int id;
    private double longitude;
    private double latitude;
    private int energyAmount;
    
    
    /**
     *  Returns the id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     *  Assigns the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *  Returns the longitude
     * @return double
     */
    public double getLongitude() {
        return longitude;
    }
    
    /**
     *  Assigns the longitude
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    /**
     *  Returns the latitude
     * @return double
     */
    public double getLatitude() {
        return latitude;
    }
    
    /**
     *  Assigns the longitude
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    /**
     *  Returns the energyAmount
     * @return int
     */
    public int getEnergyAmount() {
        return energyAmount;
    }
    
    /**
     *  Assigns the energyAmount
     * @param energyAmount
     */
    public void setEnergyAmount(int energyAmount) {
        this.energyAmount = energyAmount;
    }
    
    /**
     *  Returns all informtation of the Local in a unique String 
     * @return String
     */
    @Override
    public String toString() {
        return "Local{" + "id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + ", energyAmount=" + energyAmount + '}';
    }

}
