public class ServerInfo {

    private String name;
    private String grpcAddress;
    private Integer grpcPort;
    private String ratisAddress;
    private Integer ratisPort;

    ServerInfo(int processNumber, int grpcBasePort, int ratisBasePort){
        this.name = "p" + Integer.valueOf(processNumber+1).toString();
        this.grpcAddress = "127.0.0.1";
        this.grpcPort = grpcBasePort + processNumber;
        this.ratisAddress = "127.0.0.1";
        this.ratisPort = ratisBasePort + processNumber;
    }

    ServerInfo(String name, String grpcAddress, Integer grpcPort, String ratisAddress, Integer ratisPort){
        this.name = name;
        this.grpcAddress = grpcAddress;
        this.grpcPort = grpcPort;
        this.ratisAddress = ratisAddress;
        this.ratisPort = ratisPort;
    }

    public String toString(){  //For Buttons
        return this.name + "- port "+this.grpcPort;
    }

    public Integer defaultProcessNameToInt(String pNumber){
        if(pNumber.startsWith("p")){
            pNumber = pNumber.substring(1);
            try{
                return Integer.parseInt(pNumber) - 1;
            }
            catch(NumberFormatException e){
                System.err.println(pNumber+" não é uma string válida de processo default");
                return null;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrpcAddress() {
        return this.grpcAddress;
    }

    public void setGrpcAddress(String grpcAddress) {
        this.grpcAddress = grpcAddress;
    }

    public Integer getGrpcPort() {
        return this.grpcPort;
    }

    public void setGrpcPort(Integer grpcPort) {
        this.grpcPort = grpcPort;
    }

    public String getRatisAddress() {
        return this.ratisAddress;
    }

    public void setRatisAddress(String ratisAddress) {
        this.ratisAddress = ratisAddress;
    }

    public Integer getRatisPort() {
        return this.ratisPort;
    }

    public void setRatisPort(Integer ratisPort) {
        this.ratisPort = ratisPort;
    }

}