import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ratis.proto.RaftProtos;
import org.apache.ratis.protocol.Message;
import org.apache.ratis.statemachine.TransactionContext;
import org.apache.ratis.statemachine.impl.BaseStateMachine;
import org.apache.ratis.thirdparty.com.google.protobuf.ByteString;

public class StateMachineImpl extends BaseStateMachine{
    
    private ConcurrentHashMap<BigInteger, ValueModel> values = new ConcurrentHashMap<>();
    public static Charset charset = Charset.forName("iso8859-1");

    @Override
    public CompletableFuture<Message> query(Message request) {
        System.out.println("Receiving message Query"+ request.getContent().toString(charset) +" from client on StateMachine");
        final String[] opKey = request.getContent().toString(charset).split(":");
        ByteString result = ByteString.copyFrom(opKey[0] + ":",charset);
        ValueModel value = null;
        try{
            if(opKey.length > 1 && opKey[0].equals("get")){
                value = values.get(new BigInteger(ByteString.copyFrom(opKey[1], charset).toByteArray()));
            }
        }
        catch(Exception e){
            System.out.println("Exceção dentro do if na Maquina de Estados do GET");
            e.printStackTrace();
        }
        if(value == null){
            result = result.concat(ByteString.copyFrom("null",charset));
        }
        else{
            result = result.concat(value.toRatisByteString());
        }
        return CompletableFuture.completedFuture(Message.valueOf(result));
    }

    @Override
    public CompletableFuture<Message> applyTransaction(TransactionContext trx) {
        System.out.println("Receiving message ApplyTransaction "+ trx.getLogEntry().getStateMachineLogEntry().getLogData().toString(charset)+" from client on StateMachine");
        final RaftProtos.LogEntryProto entry = trx.getLogEntry();
        final String[] opKeyValue = entry.getStateMachineLogEntry().getLogData().toString(charset).split(":");
        ByteString result = ByteString.copyFrom(opKeyValue[0] + ":",charset);
        ValueModel value = null;
        try{
            if(opKeyValue.length == 2){
                if(opKeyValue[0].equals("remove")){
                    value = values.remove(new BigInteger(ByteString.copyFrom(opKeyValue[1], charset).toByteArray()));
                }
            }
            else if(opKeyValue.length == 3){
                //System.out.println("opKeyValue.length == 3 true");
                String valueString = ByteString.copyFrom(opKeyValue[2],charset).toString(charset);
                //System.out.println(valueString);
                if(!valueString.equals("null")){
                    if(opKeyValue[0].equals("put")){ //if Present and with correct version
                        ValueModel newValue = new ValueModel(ByteString.copyFrom(opKeyValue[2], charset));
                        //value = values.put(new BigInteger(ByteString.copyFrom(opKeyValue[1], charset).toByteArray()), newValue);
                        value = values.computeIfPresent(new BigInteger(ByteString.copyFrom(opKeyValue[1], charset).toByteArray()), (k,v) -> {
                            if(v.getVersion() + 1L == newValue.getVersion()){
                                return newValue;
                            }
                            return v;
                        });
                    }
                    else if(opKeyValue[0].equals("putIfAbsent")){
                        //System.out.println(opKeyValue[2]);
                        //System.out.println(ByteString.copyFrom(opKeyValue[2], charset).toString());
                        value = values.putIfAbsent(new BigInteger(ByteString.copyFrom(opKeyValue[1], charset).toByteArray()), new ValueModel(ByteString.copyFrom(opKeyValue[2], charset)));
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Exceção dentro do if na Maquina de Estados do "+opKeyValue[0]+", ApplyTransaction");
            e.printStackTrace();
        }
        if(value == null){
            result = result.concat(ByteString.copyFrom("null",charset));
        }
        else{
            //System.out.println("Values desserializado");
            //System.out.println(Arrays.toString(value.getData()));
            result = result.concat(value.toRatisByteString()); 
        }
        return CompletableFuture.completedFuture(Message.valueOf(result));
    }

}