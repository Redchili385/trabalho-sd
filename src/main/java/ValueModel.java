import java.io.Serializable;
import java.util.Date;

import com.google.protobuf.ByteString;

public class ValueModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private long version;
    private long timestamp;
    private byte[] data;

    public ValueModel(){
        version = 1L;
        timestamp = (new Date()).getTime();
        data = new byte[]{}; 
    }

    public ValueModel(long version2, long timestamp2, byte[] data2){
        version = version2;
        timestamp = timestamp2;
        data = data2;
    }

    public ValueModel(long version2, long timestamp2, ByteString data2){
        this(version2,timestamp2,data2.toByteArray());
    }

    public ValueModel(Grpc.ValueRequest value){
        version = value.getVersion();
        timestamp = value.getTimestamp();
        data = value.getData().toByteArray();
    }

    public long getVersion(){
        return version;
    }
    public long getTimestamp(){
        return timestamp;
    }
    public byte[] getData(){
        return data;
    }

    public void setVersion(long version2){
        version = version2;
    }
    public void setTimestamp(long timestamp2){
        timestamp = timestamp2;
    }
    public void setTimestamp(Date date){
        timestamp = date.getTime();
    }
    public void setData(byte[] data2){
        data = data2;
    }

    public Grpc.ValueRequest toGrpc(){
        return Grpc.ValueRequest.newBuilder()
                                .setVersion(version)
                                .setTimestamp(timestamp)
                                .setData(ByteString.copyFrom(data))
                                .build();
    }
}