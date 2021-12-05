package SystemControllers;

public class propertyInfo {
    private String address;
    private String type;
    private int numBedrooms;
    private int numBathrooms;
    private boolean isFurnished;
    private String cityQuadrant;
    private int days;
    private String status;

    public propertyInfo(String address, String type, int numBedrooms
            , int numBathrooms, boolean isFurnished, String cityQuadrant
            , int days, String status) {
        this.address = address;
        this.type = type;
        this.numBedrooms = numBedrooms;
        this.numBathrooms = numBathrooms;
        this.isFurnished = isFurnished;
        this.cityQuadrant = cityQuadrant;
        this.days = days;
        this.status = status;
    }

    public String getAddress() {
        return this.address;
    }

    public String getType() {
        return this.type;
    }

    public int getNumBedrooms() {
        return this.numBedrooms;
    }

    public int getNumBathrooms() {
        return this.numBathrooms;
    }

    public boolean isFurnished() {
        return this.isFurnished;
    }

    public String getCityQuadrant() {
        return this.cityQuadrant;
    }

    public int getDays() {
        return this.days;
    }

    public String getStatus() {
        return this.status;
    }
}
