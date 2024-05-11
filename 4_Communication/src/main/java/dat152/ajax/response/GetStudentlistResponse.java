package dat152.ajax.response;

import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

import dat152.ajax.model.Student;

@XmlRootElement
public class GetStudentlistResponse extends ServerResponse {
	private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
