/**
 * FileName: PropertyType.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package Enums;

/**
 * Property Types
 */
public enum PropertyType {
    Apartment("assets/Apartment_Color.png"),
    AttachedHouse("assets/AttachedHouse_Color.png"),
    DetachedHouse("assets/DetachedHouse_Color.png"),
    Condo("assets/Condo_Color.png"),
    Townhouse("assets/Townhouse_Color.png"),
    Mansion("assets/Mansion_Color.png"),
    Cabin("assets/Cabin_Color.png");

    /**
     * Enum Constructor
     * @param path FilePath from Program_Files
     */
    private PropertyType(final String path)
    {
        this.FILEPATH = path;
    }

    /**
     * Enum Fields
     */
    private String FILEPATH;

    /**
     * Returns the filePath to the image corresponding to Type
     */
    public String getFilePath()
    {
        return FILEPATH;
    }
}
