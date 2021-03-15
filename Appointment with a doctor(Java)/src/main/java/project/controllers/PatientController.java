package project.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import project.entities.Patient;
import project.services.PatientService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("patients")
public class PatientController {
    @Inject
    private PatientService patientService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatients() {
        List<Patient> patients;

        try {
            patients = patientService.getAll();
        }catch (ServerErrorException e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.ok(patients).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient patient){
        boolean create;
        try {
            create = patientService.create(patient);
        }catch (ServerErrorException e){
            return Response.serverError().entity(e.getMessage()).build();
        }
        if(!create){
            return Response.status(Response.Status.BAD_REQUEST).entity("Can't create patient!").build();
        }
        return Response.status(Response.Status.CREATED).entity("Patient has been created!").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") int id){
        Patient patient;
        try {
            patient = patientService.get(id);
        }catch (ServerErrorException e){
            return Response
                    .status(500).entity(e.getMessage()).build();
        }

        if (patient == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Patient does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(patient)
                .build();
    }
    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id){
        boolean remove;
        try {
            remove = patientService.delete(id);
        }catch (ServerErrorException e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        if(remove) {
            return Response.ok("Patient has successfully deleted!").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("ID not found to delete").build();
        }
    }
    @GET
    @Path("/{username}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@PathParam("username") String username, @PathParam("password") String password){
        boolean logged;
        try {
            logged = patientService.login(username, password);
        }catch (ServerErrorException e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        if (logged){
            return Response.status(Response.Status.OK).entity("Logged in!").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Wrong login or password!").build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/makeAnAppointment/{patientid}/{doctorId}")
    public Response makeAnAppointment(@PathParam("patientid") int patientId, @PathParam("doctorId") int doctorId){
        boolean create;
        try {
            create = patientService.makeAppointment(patientId, doctorId);
        }catch (ServerErrorException e){
            return Response.serverError().entity(e.getMessage()).build();
        }
        if(!create){
            return Response.status(Response.Status.BAD_REQUEST).entity("Can't create an Appointment!").build();
        }
        return Response.status(Response.Status.CREATED).entity("Appointment has been created!").build();
    }
}
