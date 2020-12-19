import java.nio.charset.Charset;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ratis.proto.RaftProtos;
import org.apache.ratis.protocol.Message;
import org.apache.ratis.statemachine.TransactionContext;
import org.apache.ratis.statemachine.impl.BaseStateMachine;
import org.apache.ratis.thirdparty.com.google.protobuf.ByteString;

public class StateMachineImpl extends BaseStateMachine{
    
    private ConcurrentHashMap<Long, ValueModel> values = new ConcurrentHashMap<>();

    @Override
    public CompletableFuture<Message> query(Message request) {
        final String[] opKey = request.getContent().toString(Charset.defaultCharset()).split(":");
        ByteString result = null;
        if(opKey.length > 1 && opKey[0].equals("get")){
            result = values.get(Long.parseLong(opKey[1])).toRatisByteString();
        }
        LOG.debug("{}: {} = {}", opKey[0], opKey[1], result.toString());
        return CompletableFuture.completedFuture(Message.valueOf(result));
    }

    @Override
    public CompletableFuture<Message> applyTransaction(TransactionContext trx) {
        final RaftProtos.LogEntryProto entry = trx.getLogEntry();
        final String[] opKeyValue = entry.getStateMachineLogEntry().getLogData().toString(Charset.defaultCharset()).split(":");
        ByteString result = null;
        if(opKeyValue.length == 2){
            if(opKeyValue[0].equals("remove")){
                result = values.remove(Long.parseLong(opKeyValue[1])).toRatisByteString();
            }
        }
        else if(opKeyValue.length == 3){
            if(opKeyValue[0].equals("put")){
                result = values.put(Long.parseLong(opKeyValue[1]), new ValueModel(ByteString.copyFrom(opKeyValue[2], Charset.defaultCharset()))).toRatisByteString();
            }
            else if(opKeyValue[0].equals("putIfAbsent")){
                values.putIfAbsent(Long.parseLong(opKeyValue[1]), new ValueModel(ByteString.copyFrom(opKeyValue[2], Charset.defaultCharset()))).toRatisByteString();
            }
        }
        return CompletableFuture.completedFuture(Message.valueOf(result));
    }

}