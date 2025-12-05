package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddRequest {

    public static void addAppointmentRequest(int patientId, int doctorId) {
        String sql = "INSERT INTO appointment_request (patient_id, doctor_id) VALUES (?, ?)";

        try (Connection connection = DB_connect.getConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);
            statement.setInt(2, doctorId);

            statement.executeUpdate();

            System.out.println("Appointment request added successfully"); // debug purpose
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
