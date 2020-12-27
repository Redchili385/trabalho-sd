import java.util.Arrays;
import java.util.stream.Stream;

public class SharedInfo {

    public static int grpcBasePort = 9000;
    public static int ratisBasePort = 3000;
    public static ServerInfo[] listServers = createPossibleServers(5);
    
    private static ServerInfo[] createPossibleServers(int numberOfServers){
        int maxNumberOfServers = Math.abs(grpcBasePort-ratisBasePort);
        if(numberOfServers > maxNumberOfServers){
            System.out.println("Número de servidores acima da distribuição de portas, limitando-se ao máximo: "+ maxNumberOfServers);
        }
        ServerInfo[] servers = new ServerInfo[numberOfServers];
        for(int i = 0; i < numberOfServers; i++){
            servers[i] = new ServerInfo(i, grpcBasePort, ratisBasePort);
        }
        return servers;
    }

    public static String listServerNames(){
        return Arrays.toString(Stream.of(listServers).map(ServerInfo::getName).toArray(String[]::new));
    }

    public static ServerInfo getProcessByGrpcPort(int grpcPort){
        for(ServerInfo serverInfo : listServers){
            if(serverInfo.getGrpcPort() == grpcPort){
                return serverInfo;
            }
        }
        return null;
    }

    public static int grpcPortToRatisPort(int grpcPort){
        return ratisBasePort - grpcBasePort + grpcPort;
    }
}