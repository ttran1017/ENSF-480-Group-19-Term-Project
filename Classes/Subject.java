
import java.util.*;

/**
 * 
 */
public interface Subject {

    /**
     * @param o 
     * @return
     */
    public void addObserver(Observer o);

    /**
     * @param o 
     * @return
     */
    public void removeObserver(Observer o);

    /**
     * @return
     */
    public void notifyAllObservers();

}