package dat152.ajax.services;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import dat152.ajax.dataaccess.StudentEAO;
import dat152.ajax.model.Student;
import dat152.ajax.response.AddStudentResponse;
import dat152.ajax.response.DeleteStudentResponse;
import dat152.ajax.response.GetStudentlistResponse;

@Singleton
@Path("/services")
public class WebServices {

    @EJB
    private StudentEAO studentEAO = new StudentEAO();
    
    @Path("/student")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public AddStudentResponse addStudent (Student student) {
    	AddStudentResponse response = new AddStudentResponse();
    	if (student != null) {
    		studentEAO.addStudent(student);

    		response.setStudent(student);
            response.setResponseStatus(true);
    	}
        return response;
    }
    
    @Path("/studentlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetStudentlistResponse getStudentlist () {
    	GetStudentlistResponse response = new GetStudentlistResponse();

		List<Student> students = studentEAO.getStudents();
		response.setStudents(students);
        response.setResponseStatus(true);
        return response;
    }
    
    @Path("/student/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON )
    public DeleteStudentResponse deleteStudent(@PathParam("id") Integer id) {
        DeleteStudentResponse response = new DeleteStudentResponse();
        if (id != null) {
            if (id >= 1) {
            	studentEAO.deleteStudent(id);
                response.setId(id);
                response.setResponseStatus(true);
            }
        }
        return response;
    }
}
