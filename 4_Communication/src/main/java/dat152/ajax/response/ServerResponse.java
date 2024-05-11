package dat152.ajax.response;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServerResponse {
    private Boolean responseStatus = false;

    public Boolean getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Boolean responseStatus) {
        this.responseStatus = responseStatus;
    }
}
