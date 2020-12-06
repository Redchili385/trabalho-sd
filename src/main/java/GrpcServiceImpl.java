import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class GrpcServiceImpl extends GrpcServiceGrpc.GrpcServiceImplBase {
    private String hashPath = "./hashmap.txt";
    private ConcurrentHashMap<Long, ValueModel> values = loadHashMap(hashPath);
    private Boolean canStore = true;
    private Date horaDeInicio = new Date();
    private long intervaloEntreSalvamentos = 5000; //ms
    private SaveTimer timer = new SaveTimer("save timer", new TimerTask() {

        @Override
        public void run() {
            storeHashMap(hashPath);
            System.out.println((new Date()).getTime() - horaDeInicio.getTime());
        }
    }, 0, intervaloEntreSalvamentos );
    
    @Override
    public void setValue(Grpc.SetRequest request, StreamObserver<Grpc.Response> responseObserver) {
        System.out.println(request.toString());
        Grpc.StatusCode status = Grpc.StatusCode.SUCCESS;
        if(!verifyAvailability()){
            sendResponse(responseObserver, Grpc.StatusCode.ERROR, null);
            return;
        }
        ValueModel oldValue = values.putIfAbsent(request.getKey(), new ValueModel(1L,request.getTimestamp(),request.getData()));
        changedHashMap();
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

        //if(!verifyAvailability()) return;   Disponível para gets!
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
        if(!verifyAvailability()){
            sendResponse(responseObserver, Grpc.StatusCode.ERROR, null);
            return;
        }
        ValueModel valueRemoved = values.remove(request.getKey());
        changedHashMap();
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

        if(!verifyAvailability()){
            sendResponse(responseObserver, Grpc.StatusCode.ERROR, null);
            return;
        }
        ValueModel oldValue = values.get(request.getKey());

        if(oldValue != null){
            returnValue = oldValue.toGrpc();
            if(oldValue.getVersion() == request.getVersion()){
                values.remove(request.getKey());
                status = Grpc.StatusCode.SUCCESS;
                changedHashMap();
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

        if(!verifyAvailability()){
            sendResponse(responseObserver, Grpc.StatusCode.ERROR, null);
            return;
        }
        ValueModel oldValue = values.get(request.getKey());

        if(oldValue != null){
            returnValue = oldValue.toGrpc();
            if(oldValue.getVersion() == request.getVersion()){
                values.put(request.getKey(), new ValueModel(request.getValue()));
                status = Grpc.StatusCode.SUCCESS;
                changedHashMap();
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

    private void storeHashMap(String path){
        canStore = false;
        try{
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(values);
            oos.close();
            System.out.println("HashMap armazenado em "+path);
        }
        catch(Exception e){
            System.err.println("Erro na criação/abertura do arquivo");
            e.printStackTrace();
        }
        canStore = true;
    }

    private ConcurrentHashMap<Long, ValueModel> loadHashMap(String path){
        try{
            File hashFile = new File(path);
            FileInputStream fis = new FileInputStream(hashFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            @SuppressWarnings("unchecked")
            ConcurrentHashMap<Long, ValueModel> hashMap = (ConcurrentHashMap<Long, ValueModel>)ois.readObject();
            ois.close();
            fis.close();
            System.out.println("HashMap carregado de "+path+" com sucesso!");
            return hashMap;
        }
        catch(Exception e){
            System.err.println("Erro no carregamento do arquivo, criando um novo HashMap (Pode ocorrer se não existe um banco de dados ainda)");
            //e.printStackTrace();
            return new ConcurrentHashMap<>();
        }
    }

    //TODO implementar forma de requisições esperarem ao invés de retornar erro imediatamente
    private boolean verifyAvailability(){   
        if(!canStore){
            System.out.println("HashMap não pode ser utilizada no momento, ignorando entrada");
        }
        return canStore;
    }

    private void changedHashMap(){
        
    }
}
