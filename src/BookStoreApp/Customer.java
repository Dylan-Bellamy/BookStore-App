package BookStoreApp;

/**
 *
 * @author picku
 */
public class Customer {
    
    private String name;
    private String password;
    private int points;
    private String status;
    
    public Customer(String name, String password, int points, String status){
        this.name = name;
        this.password = password;
        this.points = points;
        this.status = status;
    }
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public int getPoints(){
        return points;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
}