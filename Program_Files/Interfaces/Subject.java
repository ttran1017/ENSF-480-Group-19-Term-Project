/**
 * FileName: Subject.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package Interfaces;
import java.util.ArrayList;

import Models.Property;

/**
 * Subject Interface
 */
public interface Subject {
    /**
     * Adds Observer
     * @param o
     */
    public void addObserver(Observer o);

    /**
     * Removes Observer
     * @param o
     */
    public void removeObserver(Observer o);

    /**
     * Notify all Observers of new data
     * @param properties Property list
     */
    public void notifyAllObservers(int propertyID);

}