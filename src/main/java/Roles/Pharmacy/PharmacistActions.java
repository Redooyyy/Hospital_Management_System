package Roles.Pharmacy;

interface PharmacistActions {
    void verifyPrescription(Prescription p);
    void checkAvailability(String medicineName, int quantity);
    void calculateDailySales();
}