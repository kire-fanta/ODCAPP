package gestionevenements.odcEvents.payload.response;

public class MessageResponse {
  private String message;
  private boolean status;

  public MessageResponse(String message,boolean status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }
  public boolean isStatus() {
    return status;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
