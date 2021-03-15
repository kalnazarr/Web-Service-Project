package project.entities;

public class Appointment {
    private int appId;
    private int patientId;
    private int doctorId;
    private boolean approved;

    public Appointment(int patientId, int doctorId, boolean approved) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.approved = approved;
    }

    public Appointment(int appId, int patientId, int doctorId, boolean approved) {
        this.appId = appId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.approved = approved;
    }

    public Appointment(int appId, int patientId, int doctorId) {
        this.appId = appId;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}

