package client;

import org.junit.Before;
import org.junit.Test;
import rpc.RpcClient;
import rpc.params.RpcRequest;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


public class Client {

    private RpcClient rpcClient;

    @Before
    public void init() {
        String host = "localhost";
        int port = 18866;
        rpcClient = new RpcClient(host, port);
    }

    @Test
    public void testMultiThread() {
        Executor executors = Executors.newFixedThreadPool(4);
        IntStream.range(1, 100)
            .forEach((i) -> {
                    executors.execute(
                        () -> {
                            RpcRequest request = new RpcRequest();
                            try {
                                rpcClient.send(request);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    );
                }
            );

        try {
            Thread.currentThread().join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}