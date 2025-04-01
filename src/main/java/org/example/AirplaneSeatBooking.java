package org.example;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AirplaneSeatBooking {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/AirplaneDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            int seatNo = i % 10 + 1; // Cycle through seat numbers 1 to 10
            executor.execute(() -> bookSeat(seatNo));
        }

        executor.shutdown();
    }

    private static synchronized void bookSeat(int seatNo) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            connection.setAutoCommit(false);

            // Check if the seat is empty
            String query = "SELECT status FROM seats WHERE seat_no = ? FOR UPDATE";
            try (PreparedStatement checkStmt = connection.prepareStatement(query)) {
                checkStmt.setInt(1, seatNo);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next() && "empty".equals(rs.getString("status"))) {
                    // Book the seat
                    String updateQuery = "UPDATE seats SET status = 'taken' WHERE seat_no = ?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setInt(1, seatNo);
                        int rowsUpdated = updateStmt.executeUpdate();

                        if (rowsUpdated > 0) {
                            connection.commit();
                            System.out.println("Seat " + seatNo + " successfully booked by " + Thread.currentThread().getName());
                        } else {
                            connection.rollback();
                        }
                    }
                } else {
                    System.out.println("Seat " + seatNo + " is already taken.");
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error booking seat " + seatNo + ": " + e.getMessage());
        }
    }


}

