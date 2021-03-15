package project.repositories.interfaces;

import project.entities.Patient;
import project.repositories.interfaces.baseInterface.IRepository;

public interface IPatientRepository extends IRepository<Patient> {
    boolean makeAppointment(int patientId, int doctorId);
}