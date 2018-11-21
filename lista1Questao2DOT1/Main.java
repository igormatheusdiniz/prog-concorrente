package lista1Questao2DOT1;
public class Main {

    public static void main(String[] args) throws InterruptedException {
        
    	
    	APIWeb API0 = new APIWeb("mirror1.com");
    	APIWeb API1 = new APIWeb("mirror2.br");
    	APIWeb API2 = new APIWeb("mirror3.edu");
    	
    	Requester requester = new Requester(API0, API1, API2);
    	
    	requester.request("mirror1.com");
    	requester.request("mirror2.br");
    	requester.request("mirror3.edu");

    }

    public static class Server implements Runnable {

        private final APIWeb idMaker;
        private final String serviceId;
        private String currentServer="";

        public Server(APIWeb idMaker, String serviceId) {
            this.idMaker = idMaker;
            this.serviceId = serviceId;
        }

        @Override
        public void run() {
            while(currentServer.equals("")) {
            	this.currentServer = idMaker.reliableRequest();
                System.err.println("ServiceId=" + this.serviceId + " requestId=" + idMaker.reliableRequest());
            	
            }
        }
    }

}