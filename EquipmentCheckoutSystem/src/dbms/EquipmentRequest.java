package dbms;
///store list of equipment requests (cart) and update account and dbms when finished

import java.util.ArrayList;

public class EquipmentRequest {

    private ArrayList<String> order = new ArrayList<String>();
    private ArrayList<String> items = new ArrayList<String>();

    public EquipmentRequest() {
    }
    
    public ArrayList<String> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<String> order) {
        this.order = order;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }
}
