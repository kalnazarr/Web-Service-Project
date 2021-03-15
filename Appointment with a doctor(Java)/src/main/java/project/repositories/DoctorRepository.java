package project.repositories;

import project.database.DatabaseConnection;
import project.entities.Appointment;
import project.entities.Doctor;
import project.repositories.interfaces.IDoctorRepository;

import javax.inject.Inject;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DoctorRepository implements IDoctorRepository {
    @Inject
    private DatabaseConnection db;

    @Override
    public boolean create(Doctor doctor) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "INSERT INTO doctor(name, surname, password, speciality, mobilenum) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSurname());
            preparedStatement.setString(3, doctor.getPassword());
            preparedStatement.setString(4, doctor.getSpeciality());
            preparedStatement.setInt(5, doctor.getMobileNum());
            boolean executed = preparedStatement.execute();
            if (executed){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Doctor get(int id) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT * FROM doctor WHERE  id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Doctor doctor = new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("speciality"),
                        resultSet.getInt("mobilenum")
                );
                return doctor;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAll() {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT * FROM doctor";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            List<Doctor> doctors = new LinkedList<>();

            while (resultSet.next()) {
                Doctor doctor = new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("speciality"),
                        resultSet.getInt("mobilenum")
                );
                doctors.add(doctor);
            }
            return doctors;
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
            String query = "DELETE FROM doctor WHERE id = ?";
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
            String query = "SELECT username, password FROM doctor";
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
    public List<Appointment> getAllNotApprovedAppointments() {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT * FROM appointment WHERE approved is NULL";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            List<Appointment> appointments = new LinkedList<>();

            while (resultSet.next()) {
                Appointment appointment = new Appointment(
                        resultSet.getInt("id"),
                        resultSet.getInt("patientid"),
                        resultSet.getInt("doctorid")
                );
                appointments.add(appointment);
            }
            return appointments;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT * FROM appointment WHERE approved is not NULL";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            List<Appointment> appointments = new LinkedList<>();

            while (resultSet.next()) {
                Appointment appointment = new Appointment(
                        resultSet.getInt("id"),
                        resultSet.getInt("patientid"),
                        resultSet.getInt("doctorid")
                );
                appointments.add(appointment);
            }
            return appointments;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean makeChoice(int id, boolean choice) {
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String query = "UPDATE appointment SET approved = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, choice);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate() > 1;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }


}
