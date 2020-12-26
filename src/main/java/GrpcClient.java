import java.awt.Color;
import java.awt.FlowLayout;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import com.google.protobuf.ByteString;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class GrpcClient {

    private static GrpcServiceGrpc.GrpcServiceBlockingStub blockingStub = getBlockingStub();
    public static Charset charset = Charset.forName("iso8859-1");

    public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setTitle("gRPC Client GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500,580);
        frame.setLayout(new FlowLayout());
        
        frame.getContentPane().setBackground(new Color(211,211,211));
        
        frame.add(new functionPanel("Set Value: ", new ArrayList<String>(Arrays.asList("Key","Data")), new ArrayList<String>(Arrays.asList("Status","Value")), l -> setValue(l)));
        frame.add(new functionPanel("Get Value: ", new ArrayList<String>(Arrays.asList("Key")), new ArrayList<String>(Arrays.asList("Status","Value")), l -> getValue(l)));
        frame.add(new functionPanel("Delete Value: ", new ArrayList<String>(Arrays.asList("Key")), new ArrayList<String>(Arrays.asList("Status","Value")), l -> delValue(l)));
        frame.add(new functionPanel("Delete Value With Version: ", new ArrayList<String>(Arrays.asList("Key","Version")), new ArrayList<String>(Arrays.asList("Status","Value")), l -> delValueWithVersion(l)));
        frame.add(new functionPanel("Test and Set Value: ", new ArrayList<String>(Arrays.asList("Key","Value","Version")), new ArrayList<String>(Arrays.asList("Status","Value")), l -> testAndSetValue(l)));
    
        frame.setVisible(true);
    }

    private static GrpcServiceGrpc.GrpcServiceBlockingStub getBlockingStub(){
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:"+GrpcServer.grpcBasePort).usePlaintext().build();
        return GrpcServiceGrpc.newBlockingStub(channel);
    }

    private static void printGrpcException(StatusRuntimeException e){
        System.out.println("Erro no envio do comando");
        System.out.println(e.getMessage());
        System.out.println(e.getStatus());
        System.out.println(e.getStackTrace().toString());
    }

    static List<String> getValue(List<String> inputs){
        System.out.println("Enviando valores: "+inputs.toString());
        if(inputs.size() != 1){
            throw new IllegalArgumentException("Número de argumentos incorretos: "+ inputs.size());
        }
        String keyString = inputs.get(0);
        Long key = null;
        try{
            key = Long.parseLong(keyString);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Formato incorreto da entrada 0");
        }
        Grpc.Response response = getValue(key);
        return new ArrayList<String>(Arrays.asList(response.getE().toString(),parseValueRequest(response.getValue())));
    }

    static Grpc.Response getValue(Long key){
        Grpc.KeyMessage request = Grpc.KeyMessage.newBuilder().setKey(key).build();
        try{
            return blockingStub.getValue(request);
        }
        catch(StatusRuntimeException e){
            printGrpcException(e);
            return null;
        }
    }

    static List<String> setValue(List<String> inputs){
        System.out.println("Enviando valores: "+inputs.toString());
        if(inputs.size() != 2){
            throw new IllegalArgumentException("Número de argumentos incorretos: "+ inputs.size());
        }
        String keyString = inputs.get(0);
        String valueString = inputs.get(1);
        
        Long key = null;
        try{
            key = Long.parseLong(keyString);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Formato incorreto da entrada 0");
        }

        ByteString value = ByteString.copyFrom(valueString, charset);
        long timestamp = (new Date()).getTime();

        Grpc.Response response = setValue(key, value, timestamp);
        return new ArrayList<String>(Arrays.asList(response.getE().toString(),parseValueRequest(response.getValue())));
    }

    static Grpc.Response setValue(Long key, ByteString value, Long timestamp){
        Grpc.SetRequest request = Grpc.SetRequest.newBuilder().setKey(key).setData(value).setTimestamp(timestamp).build();
        try{
            //System.out.println(request.toString());
            return blockingStub.setValue(request);
        }
        catch(StatusRuntimeException e){
            printGrpcException(e);
            return null;
        }
    }

    static List<String> delValue(List<String> inputs){
        System.out.println("Enviando valores: "+inputs.toString());
        if(inputs.size() != 1){
            throw new IllegalArgumentException("Número de argumentos incorretos: "+ inputs.size());
        }
        String keyString = inputs.get(0);
        Long key = null;
        try{
            key = Long.parseLong(keyString);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Formato incorreto da entrada 0");
        }
        Grpc.Response response = delValue(key);
        return new ArrayList<String>(Arrays.asList(response.getE().toString(),parseValueRequest(response.getValue())));
       
    }

    static Grpc.Response delValue(Long key){
        Grpc.KeyMessage request = Grpc.KeyMessage.newBuilder().setKey(key).build();
        try{
            return blockingStub.delValue(request);
        }
        catch(StatusRuntimeException e){
            printGrpcException(e);
            return null;
        }
    }

    static List<String> delValueWithVersion(List<String> inputs){
        System.out.println("Enviando valores: "+inputs.toString());
        if(inputs.size() != 2){
            throw new IllegalArgumentException("Número de argumentos incorretos: "+ inputs.size());
        }
        String keyString = inputs.get(0);
        String versionString = inputs.get(1);

        Long key = null;
        Long version = null;
        try{
            key = Long.parseLong(keyString);
            version = Long.parseLong(versionString);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Formato incorreto da entrada 0 ou 1");
        }
        Grpc.Response response = delValueWithVersion(key, version);
        return new ArrayList<String>(Arrays.asList(response.getE().toString(),parseValueRequest(response.getValue())));
    }

    static Grpc.Response delValueWithVersion(Long key, Long version){
        Grpc.DeleteWithVersionRequest request = Grpc.DeleteWithVersionRequest.newBuilder().setKey(key).setVersion(version).build();
        try{
            return blockingStub.delValueWithVersion(request);
        }
        catch(StatusRuntimeException e){
            printGrpcException(e);
            return null;
        }
    }

    static List<String> testAndSetValue(List<String> inputs){
        System.out.println("Enviando valores: "+inputs.toString());
        if(inputs.size() != 3){
            throw new IllegalArgumentException("Número de argumentos incorretos: "+ inputs.size());
        }
        String keyString = inputs.get(0);
        String valueString = inputs.get(1);
        String versionString = inputs.get(2);
        
        Long key = null;
        Long version = null;
        try{
            key = Long.parseLong(keyString);
            version = Long.parseLong(versionString);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Formato incorreto da entrada 0 ou 2");
        }

        ByteString value = ByteString.copyFrom(valueString, charset);
        long timestamp = (new Date()).getTime();

        Grpc.Response response = testAndSetValue(key, version, timestamp, value);
        return new ArrayList<String>(Arrays.asList(response.getE().toString(),parseValueRequest(response.getValue())));
    }

    static Grpc.Response testAndSetValue(Long key, Long version, Long timestamp, ByteString value){
        Grpc.TestAndSetRequest request = Grpc.TestAndSetRequest.newBuilder().setKey(key)
            .setValue(Grpc.ValueRequest.newBuilder().setVersion(version+1L).setTimestamp(timestamp).setData(value).build())
            .setVersion(version).build();
        try{
            //System.out.println(request.toString());
            return blockingStub.testAndSetValues(request);
        }
        catch(StatusRuntimeException e){
            printGrpcException(e);
            return null;
        }
    }

    static public String parseValueRequest(Grpc.ValueRequest request){
        if(request.getData().isEmpty()){
            return "NULL";
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        return "( Version: "+request.getVersion()+", Timestamp: "+
                newFormat.format(new Date(request.getTimestamp()))+", Data: "+ request.getData().toString(charset) + ")";
    }
    
}