package project.services.interfaces;

import project.entities.Appointment;
import project.entities.Doctor;

import java.util.List;
public interface IDoctorService {
    List<Doctor> getAll();

    boolean create(Doctor doctor);

    Doctor get(int id);

    boolean delete(int id);

    boolean login(String username, String password);

    List<Appointment> getAllNotApprovedAppointments();

    List<Appointment> getAllAppointments();

    boolean makeChoice(int id, boolean choice);
}
