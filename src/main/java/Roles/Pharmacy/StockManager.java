package Roles.Pharmacy;

import java.util.HashMap;
import java.util.Map;

public class StockManager {
    double totalSales = 0;

    public void addMedicine(String name, double price, int quantity) {
        // if (stock.containsKey(name)) {
        //     stock.get(name).stock += quantity;
        // } else {
        //     stock.put(name, new Medicine(name, price, quantity));
        // }
        System.out.println("Medicine added: " + name);
    }

    public void showLowStockAlert() {
        System.out.println(" Low Stock Alert:");
        // for (Medicine m : stock.values()) {
        //     if (m.stock < 5) {
        //         System.out.println("- " + m.name + " (Stock: " + m.stock + ")");
        //     }
        // }
    }
}
