package project.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import project.entities.Appointment;
import project.entities.Doctor;
import project.services.DoctorService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("doctors")
public class DoctorController {
    @Inject
    private DoctorService doctorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        List<Doctor> doctors;
        try {
            doctors = doctorService.getAll();
        }catch (ServerErrorException e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.ok(doctors).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor doctor){
        boolean create;
        try {
            create = doctorService.create(doctor);
        }catch (ServerErrorException e){
            return Response.serverError().entity(e.getMessage()).build();
        }
        if(!create){
            return Response.status(Response.Status.BAD_REQUEST).entity("Can't create doctor!").build();
        }
        return Response.status(Response.Status.CREATED).entity("Doctor has been created!").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctor(@PathParam("id") int id){
        Doctor doctor;
        try {
            doctor = doctorService.get(id);
        }catch (ServerErrorException e){
            return Response
                    .status(500).entity(e.getMessage()).build();
        }

        if (doctor == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Doctor does not exist!")
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(doctor)
                .build();
    }
    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id){
        boolean remove;
        try {
            remove = doctorService.delete(id);
        }catch (ServerErrorException e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        if(remove) {
            return Response.ok("Doctor has successfully deleted!").build();
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
            logged = doctorService.login(username, password);
        }catch (ServerErrorException e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        if (logged){
            return Response.status(Response.Status.OK).entity("Logged in!").build();
        }else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Wrong login or password!").build();
        }
    }
    @GET
    @Path("/getAllNotApprovedAppointments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotApprovedAppointments() {
        List<Appointment> appointments;

        try {
            appointments = doctorService.getAllNotApprovedAppointments();
        }catch (ServerErrorException e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.ok(appointments).build();
    }

    @GET
    @Path("/getAllAppointments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        List<Appointment> appointments;

        try {
            appointments = doctorService.getAllAppointments();
        }catch (ServerErrorException e){
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.ok(appointments).build();
    }

    @POST
    @Path("/{id}/{approved}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeChoice(@PathParam("id") int id, @PathParam("approved") boolean approved) {
        boolean update;
        try {
            update = doctorService.makeChoice(id, approved);
        }catch (ServerErrorException e){
            return Response.serverError().entity(e.getMessage()).build();
        }
        if(!update){
            return Response.status(Response.Status.BAD_REQUEST).entity("Can't update the appointment!").build();
        }
        return Response.status(Response.Status.CREATED).entity("Appointment has been updated!").build();
    }
}

