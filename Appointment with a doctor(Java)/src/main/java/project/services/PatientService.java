package project.services;

import project.entities.Patient;
import project.repositories.interfaces.IPatientRepository;
import project.services.interfaces.IPatientService;

import javax.inject.Inject;
import java.util.List;

public class PatientService implements IPatientService {
    @Inject
    private IPatientRepository patientRepository;

    @Override
    public List<Patient> getAll() {
        return patientRepository.getAll();
    }

    @Override
    public boolean create(Patient patient) {
        return patientRepository.create(patient);
    }

    @Override
    public Patient get(int id) {
        return patientRepository.get(id);
    }

    @Override
    public boolean delete(int id) {
        return patientRepository.delete(id);
    }

    @Override
    public boolean login(String username, String password) {
        return patientRepository.login(username, password);
    }

    @Override
    public boolean makeAppointment(int patientId, int doctorId) {
        return patientRepository.makeAppointment(patientId, doctorId);
    }
}
