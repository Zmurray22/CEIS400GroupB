package dbms;
///store list of equipment requests (cart) and update account and dbms when finished

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class EquipmentRequest {

    private ArrayList<String> order = new ArrayList<String>();
    private ArrayList<String> cart = new ArrayList<String>();
    private String empl_ID;
    private String fname;
    private String lname;
    private String access;

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
    

    public String getCurrentUser() {
        return empl_ID;
    }

    public void setCurrentUser(String currentUser) {
        this.empl_ID = currentUser;
    }

    public EquipmentRequest() {
    }
    
    public ArrayList<String> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<String> order) {
        this.order = order;
    }

    public ArrayList<String> getItems() {
        return cart;
    }

    public void setItems(ArrayList<String> items) {
        this.cart = cart;
    }
    
    public void clearAll(){
        order.clear();
        cart.clear();
    }
    
    public void addToCart(String equipID){
        cart.add(equipID);
    }
    
    public void sendOrder(ArrayList<String> sentOrder) throws ParseException, SQLException{
        String[] orderArr = sentOrder.toArray(new String[0]);
        System.out.println(Arrays.toString(orderArr));
        Account.update(getCurrentUser(), orderArr);
    }
}
