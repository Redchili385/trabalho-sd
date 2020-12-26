import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.Callable;

import org.apache.ratis.client.RaftClient;
import org.apache.ratis.protocol.Message;
import org.apache.ratis.protocol.RaftClientReply;
import org.apache.ratis.thirdparty.com.google.protobuf.ByteString;

public class RaftHashClient{
    
    private static RaftClient client; 
    private static Charset charset = Charset.forName("iso8859-1");

    public RaftHashClient(RaftClient c){
        client = c;
    }

    public ValueModel get(Long key){
        System.out.println("Sending GET "+key+" from Ratis Client/GRPC server to Ratis Server");
        return ioExceptionToNull(() -> {
            return parseReply(client.sendReadOnly(Message.valueOf("get:"+key)));
        });
    }

    public ValueModel remove(Long key){
        System.out.println("Sending Remove "+key+" from Ratis Client/GRPC server to Ratis Server");
        return ioExceptionToNull(() -> parseReply(client.send(Message.valueOf("remove:"+key))));
    }

    public ValueModel put(Long key, ValueModel value){
        System.out.println("Sending put "+key+" from Ratis Client/GRPC server to Ratis Server");
        return ioExceptionToNull(() -> parseReply(client.send(Message.valueOf(ByteString.copyFrom("put:"+key+":",charset).concat(value.toRatisByteString())))));
    }

    public ValueModel putIfAbsent(Long key, ValueModel value){
        System.out.println("Sending putIfAbsent "+key+" from Ratis Client/GRPC server to Ratis Server");
        return ioExceptionToNull(() -> {
            ByteString requestString = ByteString.copyFrom("putIfAbsent:"+key+":",charset).concat(value.toRatisByteString());
            return parseReply(client.send(Message.valueOf(requestString)));
        });
    }

    private <T> T ioExceptionToNull(Callable<T> c){
        try{
            return c.call();
        }
        catch(IOException e){
            System.out.println("IO Exception at sending to RATIS");
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            System.out.println("Generic Exception at sending to RATIS");
            e.printStackTrace();
            return null;
        }
    }

    private ValueModel parseReply(RaftClientReply reply){
        String response = reply.getMessage().getContent().toString(charset);
        ByteString newParse = ByteString.copyFrom(response.split(":")[1],charset);
        String separatedResponse = newParse.toString(charset);
        if(separatedResponse.equals("null")){
            return null;
        }
        return new ValueModel(newParse);
    }
}