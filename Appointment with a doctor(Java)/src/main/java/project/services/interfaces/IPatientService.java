package project.services.interfaces;

import project.entities.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> getAll();

    boolean create(Patient patient);

    Patient get(int id);

    boolean delete(int id);

    boolean login(String username, String password);

    boolean makeAppointment(int patientId, int doctorId);

}
