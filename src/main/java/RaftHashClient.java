import java.io.IOException;
import java.util.concurrent.Callable;

import org.apache.ratis.client.RaftClient;
import org.apache.ratis.protocol.Message;

public class RaftHashClient{
    
    private static RaftClient client; 

    public RaftHashClient(RaftClient c){
        client = c;
    }

    public ValueModel get(Long key){
        System.out.println("Sending GET "+key+" from Ratis Client/GRPC server to Ratis Server");
        System.out.println(client.getId().toString());
        ValueModel v = ioExceptionToNull(() -> new ValueModel(client.sendReadOnly(Message.valueOf("get:"+key)).getMessage().getContent()));
        System.out.println("Value Received GET "+key+":"+v.toString());
        return v;
    }

    public ValueModel remove(Long key){
        return ioExceptionToNull(() -> new ValueModel(client.send(Message.valueOf("remove:"+key)).getMessage().getContent()));
    }

    public ValueModel put(Long key, ValueModel value){
        return ioExceptionToNull(() -> new ValueModel(client.send(Message.valueOf("put:"+key+":"+value.toByteString())).getMessage().getContent()));
    }

    public ValueModel putIfAbsent(Long key, ValueModel value){
        return ioExceptionToNull(() -> new ValueModel(client.send(Message.valueOf("putIfAbsent:"+key+":"+value.toByteString())).getMessage().getContent()));
    }

    private ValueModel ioExceptionToNull(Callable<ValueModel> c){
        try{
            return c.call();
        }
        catch(IOException e){
            return null;
        }
        catch(Exception e){
            throw new IllegalArgumentException("Generic Exception at sending to RATIS");
        }
    }
}