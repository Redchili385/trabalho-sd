import io.grpc.stub.StreamObserver;
import java.util.Hashtable;

public class GrpcServiceImpl extends GrpcServiceGrpc.GrpcServiceImplBase {
    private Hashtable<Long, Grpc.ValueRequest> values;

    @Override
    public void setValue(Grpc.SetRequest request, StreamObserver<Grpc.Response> responseObserver) {
        super.setValue(request, responseObserver);
    }

    @Override
    public void getValue(Grpc.KeyMessage request, StreamObserver<Grpc.Response> responseObserver) {
        Grpc.StatusCode status = Grpc.StatusCode.SUCCESS;
        Grpc.ValueRequest value = null;

        try{
            value = values.get(request.getKey());
            if (value == null){
                status = Grpc.StatusCode.ERROR;
            }
        }
        catch (NullPointerException e){
            status = Grpc.StatusCode.ERROR;
        }

        Grpc.Response response = Grpc.Response.newBuilder().setE(status).setValue(value).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void delValue(Grpc.KeyMessage request, StreamObserver<Grpc.Response> responseObserver) {
        super.delValue(request, responseObserver);
    }

    @Override
    public void delValueWithVersion(Grpc.DeleteWithVersionRequest request, StreamObserver<Grpc.Response> responseObserver) {
        super.delValueWithVersion(request, responseObserver);
    }

    @Override
    public void testAndSetValues(Grpc.TestAndSetRequest request, StreamObserver<Grpc.Response> responseObserver) {
        super.testAndSetValues(request, responseObserver);
    }
}
