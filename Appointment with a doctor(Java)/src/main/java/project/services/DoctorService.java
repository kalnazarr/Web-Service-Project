package project.services;


import project.entities.Appointment;
import project.entities.Doctor;
import project.repositories.DoctorRepository;
import project.services.interfaces.IDoctorService;
import javax.inject.Inject;
import java.util.List;

public class DoctorService implements IDoctorService {
    @Inject
    DoctorRepository doctorRepository;
    @Override
    public List<Doctor> getAll() {
        return doctorRepository.getAll();

    }

    @Override
    public boolean create(Doctor doctor) {
        return doctorRepository.create(doctor);
    }

    @Override
    public Doctor get(int id) {
        return doctorRepository.get(id);
    }

    @Override
    public boolean delete(int id) {
        return doctorRepository.delete(id);
    }

    @Override
    public boolean login(String username, String password) {
        return doctorRepository.login(username, password);
    }

    @Override
    public List<Appointment> getAllNotApprovedAppointments() {
        return doctorRepository.getAllNotApprovedAppointments();
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return doctorRepository.getAllAppointments();
    }

    @Override
    public boolean makeChoice(int id, boolean choice) {
        return doctorRepository.makeChoice(id, choice);
    }
}
