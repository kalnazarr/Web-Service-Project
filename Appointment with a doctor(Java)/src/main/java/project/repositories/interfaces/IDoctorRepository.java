package project.repositories.interfaces;

import project.entities.Appointment;
import project.entities.Doctor;
import project.repositories.interfaces.baseInterface.IRepository;

import java.util.List;

public interface IDoctorRepository extends IRepository<Doctor> {
    List<Appointment> getAllNotApprovedAppointments();
    List<Appointment> getAllAppointments();
    boolean makeChoice(int id, boolean choice);
}

