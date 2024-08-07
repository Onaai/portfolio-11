package org.example;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseInserter {

    // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/portfolio-10";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        // Crear un escáner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa el usuario:");
        String usuario = scanner.nextLine();

        System.out.println("Ingresa la contraseña:");
        String contrasena = scanner.nextLine();

        // Convertir la contraseña a hash utilizando SHA-256
        String contrasenaHash = convertirSHA256(contrasena);

        // Consulta SQL para insertar datos
        String insertSQL = "INSERT INTO users (usuario, contrasena) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            // Configurar los valores de los parámetros
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contrasenaHash);

            // Ejecutar la consulta
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("¡Inserción exitosa!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    public static String convertirSHA256(String mensaje) {
        try {
            // Crear una instancia del digestor SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Calcular el hash
            byte[] encodedhash = digest.digest(mensaje.getBytes());

            // Convertir el hash a una representación en hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
