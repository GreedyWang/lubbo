package com.client;


import remote.exchange.msg.AskMsg;
import remote.exchange.msg.LoginMsg;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class RequestMaker {

    /**
     * N个客户端请求,每个请求1个Thread
     */
    public void makeRequest() {
        IntStream.range(1, 10).forEach(
            (i) -> {
                Thread t = new Thread(
                    () -> {
                        invoke(new Request(i + Thread.currentThread().getName()));
//                        try {
//                            invoke(new Request(i + ""));
//                        }
//                        catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                );
                t.start();
            }
        );
    }

    private void invoke(Request request) {
//        TimeUnit.SECONDS.sleep(3);
        AskMsg askMsg = new AskMsg();
        askMsg.setAuth("authToken");
        askMsg.setClientId(request.getParams());
        bootstrap.socketChannel.writeAndFlush(askMsg);
    }

    NettyClientBootstrap bootstrap;

    /**
     * consumer to provider , one long connection
     * @throws InterruptedException
     */
    private  void connect() throws InterruptedException {
        String clientId = "001";
        bootstrap = new NettyClientBootstrap(9999, "localhost");
        LoginMsg loginMsg = new LoginMsg();
        loginMsg.setPassword("");
        loginMsg.setUserName("");
        loginMsg.setClientId(clientId);
        bootstrap.socketChannel.writeAndFlush(loginMsg);
//        while (true) {
//            TimeUnit.SECONDS.sleep(3);
//            AskMsg askMsg = new AskMsg();
//            askMsg.setAuth("authToken");
//            askMsg.setClientId(clientId);
//            bootstrap.socketChannel.writeAndFlush(askMsg);
//       }
    }

    public static void main(String[] args) throws InterruptedException {
        RequestMaker requestMaker = new RequestMaker();
        requestMaker.connect();
        requestMaker.makeRequest();
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }
}
