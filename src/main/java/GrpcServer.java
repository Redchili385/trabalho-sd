import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class GrpcServer {
    private static final int PORT = 9090;
    private Server server;

    public void start() throws IOException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        server = ServerBuilder.forPort(PORT)
                .addService(new GrpcServiceImpl())
                .executor(threadPool)
                .build()
                .start();

    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server == null) {
            return;
        }

        server.awaitTermination();
    }

    public static void main(String[] args)
            throws InterruptedException, IOException {
        GrpcServer server = new GrpcServer();
        server.start();

        System.out.println("Server connected to "+PORT+" port");
        server.blockUntilShutdown();
    }
}
