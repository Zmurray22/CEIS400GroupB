package dbms;
///store list of equipment requests (cart) and update account and dbms when finished

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class EquipmentRequest {

    private ArrayList<String> cart = new ArrayList<String>();
    private String userID;
    private String userName;
    private String fname;
    private String lname;
    private String access;

    public EquipmentRequest() {
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
    
    public String userID() {
        return userID;
    }

    public void userID(String currentUser) {
        this.userID = currentUser;
    }
 
    public ArrayList<String> getCart() {
        return cart;
    }

    public void setCart(ArrayList<String> cart) {
        this.cart = cart;
    }
    
    public void clearAll(){
        cart.clear();
    }
    
    public void addToCart(String equipID){
        cart.add(equipID);
    }
    
    public void sendOrder() throws ParseException, SQLException{
        String[] orderArr = cart.toArray(new String[0]);
        System.out.println(Arrays.toString(orderArr));
        Account.update(getUserName(), orderArr);
    }
}
