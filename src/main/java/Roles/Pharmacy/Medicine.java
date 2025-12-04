package Roles.Pharmacy;

import Database.GetFrom_DB;
import Database.Update_DB;

public class Medicine {
    String name;
    double price;
    int stock;

    Medicine(String name, double price, int stock) {
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
}
