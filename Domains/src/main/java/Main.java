import services.RequestService;

public class Main {

  public static void main(String[] args) {
    RequestService requestService = new RequestService();
    requestService.search("bounce");
  }
}