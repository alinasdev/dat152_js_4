package dat152.ajax.response;

import jakarta.xml.bind.annotation.XmlRootElement;

import dat152.ajax.model.Student;

@XmlRootElement
public class AddStudentResponse extends ServerResponse {

	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
