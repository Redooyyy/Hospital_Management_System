package Roles.Pharmacy;

import Database.GetFrom_DB;
import Database.Update_DB;

public class Medicine {
    private String name;
    private double price;
    private int stock;

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public Medicine(String name, int stock, double price) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static boolean removeFromStock(String medicine, int dose){
        int newStock = GetFrom_DB.getMedStock(medicine) - dose;
        if(newStock < 0) return false;
        else {
            Update_DB.updateStock(medicine, newStock);
            return true;
        }
    }

    public void addMedicine(){
        Update_DB.addMed(this.name, this.stock, this.price);
    }
}
