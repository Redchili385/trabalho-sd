import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: grpc.proto")
public final class GrpcServiceGrpc {

  private GrpcServiceGrpc() {}

  public static final String SERVICE_NAME = "GrpcService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Grpc.SetRequest,
      Grpc.Response> getSetValueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetValue",
      requestType = Grpc.SetRequest.class,
      responseType = Grpc.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Grpc.SetRequest,
      Grpc.Response> getSetValueMethod() {
    io.grpc.MethodDescriptor<Grpc.SetRequest, Grpc.Response> getSetValueMethod;
    if ((getSetValueMethod = GrpcServiceGrpc.getSetValueMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getSetValueMethod = GrpcServiceGrpc.getSetValueMethod) == null) {
          GrpcServiceGrpc.getSetValueMethod = getSetValueMethod =
              io.grpc.MethodDescriptor.<Grpc.SetRequest, Grpc.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetValue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.SetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.Response.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("SetValue"))
              .build();
        }
      }
    }
    return getSetValueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Grpc.KeyMessage,
      Grpc.Response> getGetValueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetValue",
      requestType = Grpc.KeyMessage.class,
      responseType = Grpc.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Grpc.KeyMessage,
      Grpc.Response> getGetValueMethod() {
    io.grpc.MethodDescriptor<Grpc.KeyMessage, Grpc.Response> getGetValueMethod;
    if ((getGetValueMethod = GrpcServiceGrpc.getGetValueMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getGetValueMethod = GrpcServiceGrpc.getGetValueMethod) == null) {
          GrpcServiceGrpc.getGetValueMethod = getGetValueMethod =
              io.grpc.MethodDescriptor.<Grpc.KeyMessage, Grpc.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetValue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.KeyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.Response.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("GetValue"))
              .build();
        }
      }
    }
    return getGetValueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Grpc.KeyMessage,
      Grpc.Response> getDelValueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelValue",
      requestType = Grpc.KeyMessage.class,
      responseType = Grpc.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Grpc.KeyMessage,
      Grpc.Response> getDelValueMethod() {
    io.grpc.MethodDescriptor<Grpc.KeyMessage, Grpc.Response> getDelValueMethod;
    if ((getDelValueMethod = GrpcServiceGrpc.getDelValueMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getDelValueMethod = GrpcServiceGrpc.getDelValueMethod) == null) {
          GrpcServiceGrpc.getDelValueMethod = getDelValueMethod =
              io.grpc.MethodDescriptor.<Grpc.KeyMessage, Grpc.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelValue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.KeyMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.Response.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("DelValue"))
              .build();
        }
      }
    }
    return getDelValueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Grpc.DeleteWithVersionRequest,
      Grpc.Response> getDelValueWithVersionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DelValueWithVersion",
      requestType = Grpc.DeleteWithVersionRequest.class,
      responseType = Grpc.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Grpc.DeleteWithVersionRequest,
      Grpc.Response> getDelValueWithVersionMethod() {
    io.grpc.MethodDescriptor<Grpc.DeleteWithVersionRequest, Grpc.Response> getDelValueWithVersionMethod;
    if ((getDelValueWithVersionMethod = GrpcServiceGrpc.getDelValueWithVersionMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getDelValueWithVersionMethod = GrpcServiceGrpc.getDelValueWithVersionMethod) == null) {
          GrpcServiceGrpc.getDelValueWithVersionMethod = getDelValueWithVersionMethod =
              io.grpc.MethodDescriptor.<Grpc.DeleteWithVersionRequest, Grpc.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DelValueWithVersion"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.DeleteWithVersionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.Response.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("DelValueWithVersion"))
              .build();
        }
      }
    }
    return getDelValueWithVersionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Grpc.TestAndSetRequest,
      Grpc.Response> getTestAndSetValuesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "TestAndSetValues",
      requestType = Grpc.TestAndSetRequest.class,
      responseType = Grpc.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Grpc.TestAndSetRequest,
      Grpc.Response> getTestAndSetValuesMethod() {
    io.grpc.MethodDescriptor<Grpc.TestAndSetRequest, Grpc.Response> getTestAndSetValuesMethod;
    if ((getTestAndSetValuesMethod = GrpcServiceGrpc.getTestAndSetValuesMethod) == null) {
      synchronized (GrpcServiceGrpc.class) {
        if ((getTestAndSetValuesMethod = GrpcServiceGrpc.getTestAndSetValuesMethod) == null) {
          GrpcServiceGrpc.getTestAndSetValuesMethod = getTestAndSetValuesMethod =
              io.grpc.MethodDescriptor.<Grpc.TestAndSetRequest, Grpc.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "TestAndSetValues"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.TestAndSetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Grpc.Response.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcServiceMethodDescriptorSupplier("TestAndSetValues"))
              .build();
        }
      }
    }
    return getTestAndSetValuesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcServiceStub>() {
        @java.lang.Override
        public GrpcServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcServiceStub(channel, callOptions);
        }
      };
    return GrpcServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcServiceBlockingStub>() {
        @java.lang.Override
        public GrpcServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcServiceBlockingStub(channel, callOptions);
        }
      };
    return GrpcServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcServiceFutureStub>() {
        @java.lang.Override
        public GrpcServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcServiceFutureStub(channel, callOptions);
        }
      };
    return GrpcServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GrpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void setValue(Grpc.SetRequest request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getSetValueMethod(), responseObserver);
    }

    /**
     */
    public void getValue(Grpc.KeyMessage request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getGetValueMethod(), responseObserver);
    }

    /**
     */
    public void delValue(Grpc.KeyMessage request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getDelValueMethod(), responseObserver);
    }

    /**
     */
    public void delValueWithVersion(Grpc.DeleteWithVersionRequest request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getDelValueWithVersionMethod(), responseObserver);
    }

    /**
     */
    public void testAndSetValues(Grpc.TestAndSetRequest request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnimplementedUnaryCall(getTestAndSetValuesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetValueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Grpc.SetRequest,
                Grpc.Response>(
                  this, METHODID_SET_VALUE)))
          .addMethod(
            getGetValueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Grpc.KeyMessage,
                Grpc.Response>(
                  this, METHODID_GET_VALUE)))
          .addMethod(
            getDelValueMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Grpc.KeyMessage,
                Grpc.Response>(
                  this, METHODID_DEL_VALUE)))
          .addMethod(
            getDelValueWithVersionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Grpc.DeleteWithVersionRequest,
                Grpc.Response>(
                  this, METHODID_DEL_VALUE_WITH_VERSION)))
          .addMethod(
            getTestAndSetValuesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Grpc.TestAndSetRequest,
                Grpc.Response>(
                  this, METHODID_TEST_AND_SET_VALUES)))
          .build();
    }
  }

  /**
   */
  public static final class GrpcServiceStub extends io.grpc.stub.AbstractAsyncStub<GrpcServiceStub> {
    private GrpcServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void setValue(Grpc.SetRequest request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetValueMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getValue(Grpc.KeyMessage request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetValueMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delValue(Grpc.KeyMessage request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelValueMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delValueWithVersion(Grpc.DeleteWithVersionRequest request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDelValueWithVersionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void testAndSetValues(Grpc.TestAndSetRequest request,
        io.grpc.stub.StreamObserver<Grpc.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTestAndSetValuesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GrpcServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<GrpcServiceBlockingStub> {
    private GrpcServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Grpc.Response setValue(Grpc.SetRequest request) {
      return blockingUnaryCall(
          getChannel(), getSetValueMethod(), getCallOptions(), request);
    }

    /**
     */
    public Grpc.Response getValue(Grpc.KeyMessage request) {
      return blockingUnaryCall(
          getChannel(), getGetValueMethod(), getCallOptions(), request);
    }

    /**
     */
    public Grpc.Response delValue(Grpc.KeyMessage request) {
      return blockingUnaryCall(
          getChannel(), getDelValueMethod(), getCallOptions(), request);
    }

    /**
     */
    public Grpc.Response delValueWithVersion(Grpc.DeleteWithVersionRequest request) {
      return blockingUnaryCall(
          getChannel(), getDelValueWithVersionMethod(), getCallOptions(), request);
    }

    /**
     */
    public Grpc.Response testAndSetValues(Grpc.TestAndSetRequest request) {
      return blockingUnaryCall(
          getChannel(), getTestAndSetValuesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GrpcServiceFutureStub extends io.grpc.stub.AbstractFutureStub<GrpcServiceFutureStub> {
    private GrpcServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Grpc.Response> setValue(
        Grpc.SetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSetValueMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Grpc.Response> getValue(
        Grpc.KeyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getGetValueMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Grpc.Response> delValue(
        Grpc.KeyMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getDelValueMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Grpc.Response> delValueWithVersion(
        Grpc.DeleteWithVersionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDelValueWithVersionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Grpc.Response> testAndSetValues(
        Grpc.TestAndSetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getTestAndSetValuesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_VALUE = 0;
  private static final int METHODID_GET_VALUE = 1;
  private static final int METHODID_DEL_VALUE = 2;
  private static final int METHODID_DEL_VALUE_WITH_VERSION = 3;
  private static final int METHODID_TEST_AND_SET_VALUES = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GrpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_VALUE:
          serviceImpl.setValue((Grpc.SetRequest) request,
              (io.grpc.stub.StreamObserver<Grpc.Response>) responseObserver);
          break;
        case METHODID_GET_VALUE:
          serviceImpl.getValue((Grpc.KeyMessage) request,
              (io.grpc.stub.StreamObserver<Grpc.Response>) responseObserver);
          break;
        case METHODID_DEL_VALUE:
          serviceImpl.delValue((Grpc.KeyMessage) request,
              (io.grpc.stub.StreamObserver<Grpc.Response>) responseObserver);
          break;
        case METHODID_DEL_VALUE_WITH_VERSION:
          serviceImpl.delValueWithVersion((Grpc.DeleteWithVersionRequest) request,
              (io.grpc.stub.StreamObserver<Grpc.Response>) responseObserver);
          break;
        case METHODID_TEST_AND_SET_VALUES:
          serviceImpl.testAndSetValues((Grpc.TestAndSetRequest) request,
              (io.grpc.stub.StreamObserver<Grpc.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrpcServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Grpc.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrpcService");
    }
  }

  private static final class GrpcServiceFileDescriptorSupplier
      extends GrpcServiceBaseDescriptorSupplier {
    GrpcServiceFileDescriptorSupplier() {}
  }

  private static final class GrpcServiceMethodDescriptorSupplier
      extends GrpcServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GrpcServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GrpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcServiceFileDescriptorSupplier())
              .addMethod(getSetValueMethod())
              .addMethod(getGetValueMethod())
              .addMethod(getDelValueMethod())
              .addMethod(getDelValueWithVersionMethod())
              .addMethod(getTestAndSetValuesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
