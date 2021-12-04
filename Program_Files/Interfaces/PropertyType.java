package Interfaces;

public enum PropertyType {
    Apartment("assets/Apartment_Color.png"),
    AttachedHouse("assets/AttachedHouse_Color.png"),
    DetachedHouse("assets/DetachedHouse_Color.png"),
    Condo("assets/Condo_Color.png"),
    Townhouse("assets/Townhouse_Color.png");

    private PropertyType(final String path)
    {
        this.FILEPATH = path;
    }

    private String FILEPATH;

    public String getFilePath()
    {
        return FILEPATH;
    }
}
