package project.repositories;

import project.database.DatabaseConnection;
import project.entities.Patient;
import project.repositories.interfaces.IPatientRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PatientRepository implements IPatientRepository {
    @Inject
    DatabaseConnection db;
    @Override
    public boolean create(Patient patient) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "INSERT INTO patient(name, surname, age, gender, username, password) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getSurname());
            preparedStatement.setInt(3, patient.getAge());
            preparedStatement.setBoolean(4, patient.isGender());
            preparedStatement.setString(5, patient.getUsername());
            preparedStatement.setString(6, patient.getPassword());
            return preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Patient get(int id) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT * FROM patient WHERE  id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return new Patient(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getBoolean("gender"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Patient> getAll() {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT * FROM patient";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            List<Patient> patients = new LinkedList<>();

            if (resultSet.next()){
                Patient patient = new Patient(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age"),
                        resultSet.getBoolean("gender"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
                patients.add(patient);
            }
            return patients;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "DELETE FROM patient WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT username, password FROM patient";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                String uName = resultSet.getString("username");
                String uPassword = resultSet.getString("password");
                if (uName.equals(username) && uPassword.equals(password)){
                    return true;
                }else {
                    System.out.println("Wrong username or password");
                    return false;
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean makeAppointment(int patientId, int doctorId) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String query = "INSERT INTO appointment (patientid, doctorid) VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);
            return preparedStatement.execute();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}

