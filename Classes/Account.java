
import java.util.*;

/**
 * 
 */
public interface Account {

    /**
     * 
     */
    private int accountType;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String email;




    /**
     * @return
     */
    public void sendEmail();

    /**
     * @return
     */
    public void viewProperties();

}