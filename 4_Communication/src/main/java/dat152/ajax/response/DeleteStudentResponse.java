package dat152.ajax.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeleteStudentResponse extends ServerResponse {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
