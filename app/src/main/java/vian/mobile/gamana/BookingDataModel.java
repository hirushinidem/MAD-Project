package vian.mobile.gamana;

public class BookingDataModel {

    // variables defining vehicle details
    private int vehicleID;
    private int vehicleImage;
    private String vehicleName;
    private String vehicleColour;
    private double engineCapacity;
    private double vehiclePrice;

    private String pickUpDate;
    private String dropOffDate;
    private int noOfDays;
    private double totalCost;

    // Parameterized Constructor
    public BookingDataModel(int vehicleID, int vehicleImage, String vehicleName, String vehicleColour, double engineCapacity, double vehiclePrice, String pickUpDate, String dropOffDate, int noOfDays, double totalCost) {
        this.vehicleID = vehicleID;
        this.vehicleImage = vehicleImage;
        this.vehicleName = vehicleName;
        this.vehicleColour = vehicleColour;
        this.engineCapacity = engineCapacity;
        this.vehiclePrice = vehiclePrice;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.noOfDays = noOfDays;
        this.totalCost = totalCost;
    }

    // getters and setters
    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(int vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(String vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(String dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

}
