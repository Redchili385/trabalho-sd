import io.grpc.stub.StreamObserver;
import java.util.concurrent.ConcurrentHashMap;

public class GrpcServiceImpl extends GrpcServiceGrpc.GrpcServiceImplBase {
    private ConcurrentHashMap<Long, ValueModel> values = new ConcurrentHashMap<>();

    @Override
    public void setValue(Grpc.SetRequest request, StreamObserver<Grpc.Response> responseObserver) {
        System.out.println(request.toString());
        Grpc.StatusCode status = Grpc.StatusCode.SUCCESS;
        ValueModel oldValue = values.putIfAbsent(request.getKey(), new ValueModel(1L,request.getTimestamp(),request.getData()));
        Grpc.ValueRequest returnValue = null;

        if(oldValue != null){
            status = Grpc.StatusCode.ERROR;
            returnValue = oldValue.toGrpc();
        }

        sendResponse(responseObserver, status, returnValue);
    }

    @Override
    public void getValue(Grpc.KeyMessage request, StreamObserver<Grpc.Response> responseObserver) {
        Grpc.StatusCode status = Grpc.StatusCode.SUCCESS;
        Grpc.ValueRequest returnValue = null;

        try{
            ValueModel value = values.get(request.getKey());
            if (value == null){
                status = Grpc.StatusCode.ERROR;
            }
            else{
                returnValue = value.toGrpc();
            }
        }
        catch (NullPointerException e){
            status = Grpc.StatusCode.ERROR;
        }

        sendResponse(responseObserver, status, returnValue);
    }

    @Override
    public void delValue(Grpc.KeyMessage request, StreamObserver<Grpc.Response> responseObserver) {
        Grpc.StatusCode status = Grpc.StatusCode.ERROR;
        ValueModel valueRemoved = values.remove(request.getKey());
        Grpc.ValueRequest returnValue = null;

        if(valueRemoved != null){
            returnValue = valueRemoved.toGrpc();
            status = Grpc.StatusCode.SUCCESS;
        }
        
        sendResponse(responseObserver, status, returnValue);
    }

    @Override
    public void delValueWithVersion(Grpc.DeleteWithVersionRequest request, StreamObserver<Grpc.Response> responseObserver) {
        Grpc.StatusCode status = Grpc.StatusCode.ERROR_NE;
        Grpc.ValueRequest returnValue = null;

        ValueModel oldValue = values.get(request.getKey());

        if(oldValue != null){
            returnValue = oldValue.toGrpc();
            if(oldValue.getVersion() == request.getVersion()){
                values.remove(request.getKey());
                status = Grpc.StatusCode.SUCCESS;
            }
            else{
                status = Grpc.StatusCode.ERROR_WV;
            }
        }
        
        sendResponse(responseObserver, status, returnValue);
    }

    @Override
    public void testAndSetValues(Grpc.TestAndSetRequest request, StreamObserver<Grpc.Response> responseObserver) {
        Grpc.StatusCode status = Grpc.StatusCode.ERROR_NE;
        Grpc.ValueRequest returnValue = null;

        ValueModel oldValue = values.get(request.getKey());

        if(oldValue != null){
            returnValue = oldValue.toGrpc();
            if(oldValue.getVersion() == request.getVersion()){
                values.put(request.getKey(), new ValueModel(request.getValue()));
                status = Grpc.StatusCode.SUCCESS;
            }
            else{
                status = Grpc.StatusCode.ERROR_WV;
            }
        }

        sendResponse(responseObserver, status, returnValue);
    }

    private void sendResponse(StreamObserver<Grpc.Response> responseObserver, Grpc.StatusCode status, Grpc.ValueRequest returnValue){
        Grpc.Response.Builder semiResponse = Grpc.Response.newBuilder().setE(status);
        Grpc.Response response = null;
        if(returnValue == null){
            response = semiResponse.build();
        }
        else{
            response = semiResponse.setValue(returnValue).build();
        }
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
