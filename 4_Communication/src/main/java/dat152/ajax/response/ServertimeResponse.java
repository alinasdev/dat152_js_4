package dat152.ajax.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServertimeResponse extends ServerResponse {
	private Long servertime;

	public Long getServertime() {
		return servertime;
	}

	public void setServertime(Long servertime) {
		this.servertime = servertime;
	}
}
