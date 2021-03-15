package project;

import project.repositories.DoctorRepository;
import project.repositories.PatientRepository;
import project.repositories.interfaces.IDoctorRepository;
import project.repositories.interfaces.IPatientRepository;
import project.services.DoctorService;
import project.services.PatientService;
import project.services.interfaces.IDoctorService;
import project.services.interfaces.IPatientService;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(PatientRepository.class).to(IPatientRepository.class);
        bind(DoctorRepository.class).to(IDoctorRepository.class);
        bind(PatientService.class).to(IPatientService.class);
        bind(DoctorService.class).to(IDoctorService.class);
    }
}
