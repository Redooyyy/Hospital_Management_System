package Roles.Pharmacy;

class Pharmacist extends StockManager implements PharmacistActions {

    @Override
    public void verifyPrescription(Prescription p) {
        System.out.println("Verifying prescription for: " + p.medicineName);
        // if (stock.containsKey(p.medicineName)) {
        //     System.out.println(" Medicine exists in stock.");
        // } else {
        //     System.out.println("Medicine not found.");
        // }
    }

    @Override
    public void checkAvailability(String medicineName, int quantity) {
       // Medicine m = stock.get(medicineName);
        // if (m != null && m.stock >= quantity) {
        //     System.out.println("" + quantity + " units of " + medicineName + " available.");
        //     m.stock -= quantity;
        //     totalSales += m.price * quantity;
        // } else {
        //     System.out.println("Not enough stock for " + medicineName);
        // }
    }

    @Override
    public void calculateDailySales() {
        System.out.println(" Total Daily Sales:" + totalSales);
    }
}
