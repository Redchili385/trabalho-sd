import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.google.protobuf.ByteString;

public class GrpcTestClient {
    
    public static Charset charset = Charset.forName("iso8859-1");
    public static void main(String[] args){
        
        //Creating first values to send
        System.out.println("Creating first values to send");
        Map<BigInteger,ValueModel> values = new HashMap<BigInteger,ValueModel>();
        for(Long i = 0L; i < 1000; i++){
            ValueModel v = new ValueModel();
            Integer random = ThreadLocalRandom.current().nextInt(0,10000);
            v.setData(random.toString().getBytes());
            values.put(new BigInteger(i.toString()),v);
        }
        //Deleting values of the server;
        System.out.println("Deleting values of the server");
        for(Map.Entry<BigInteger, ValueModel> entry : values.entrySet()){
            GrpcClient.delValue(entry.getKey());
        }
        //Sending first set to the server
        System.out.println("Sending first set to the server");
        for(Map.Entry<BigInteger, ValueModel> entry : values.entrySet()){
            GrpcClient.setValue(entry.getKey(),ByteString.copyFrom(entry.getValue().getData()), (new Date()).getTime());
        }
        //Update values on memory
        System.out.println("Update values on memory");
        for(Map.Entry<BigInteger, ValueModel> entry : values.entrySet()){
            ValueModel value = entry.getValue();
            value.setData(((Integer)ThreadLocalRandom.current().nextInt(0,10000)).toString().getBytes());
            value.setVersion(value.getVersion()+1L);
        }
        //Send update with TestAndSet to the server
        System.out.println("Send update with TestAndSet to the server");
        for(Map.Entry<BigInteger, ValueModel> entry : values.entrySet()){
            ValueModel value = entry.getValue();
            GrpcClient.testAndSetValue(entry.getKey(),value.getVersion()-1L,value.getTimestamp(),ByteString.copyFrom(value.getData()));
        }
        //Getting values and comparing
        System.out.println("Getting values and comparing");
        int errorCount = 0;
        for(Map.Entry<BigInteger, ValueModel> entry : values.entrySet()){
            ValueModel valueOnServer = new ValueModel(GrpcClient.getValue(entry.getKey()).getValue());
            ValueModel valueOnMemory = values.get(entry.getKey());
            System.out.println("Valor no Servidor: "+GrpcClient.parseValueRequest(valueOnServer.toGrpc()));
            System.out.println("Valor no Cliente: "+GrpcClient.parseValueRequest(valueOnMemory.toGrpc()));
            
            if(!Arrays.equals(valueOnServer.getData(),valueOnMemory.getData()) || 
               !(valueOnServer.getVersion() == valueOnMemory.getVersion())){
                System.out.println("Erro na chave "+entry.getKey()+" com valor: "+entry.getValue());
                errorCount++;
            }
            else{
                System.out.println("Sucesso na comparação dos valores do Cliente e Server na chave "+entry.getKey());
            }
        }
        System.out.println("O número de erros totais foi: "+errorCount);
    }



}