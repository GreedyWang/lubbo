package com.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.nio.channels.SocketChannel;

public class ServerBootStrap {

    private int port;
    private SocketChannel socketChannel;

    public ServerBootStrap(int port) {
        this.port = port;
        init();
    }

    private void init() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.childHandler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {
            @Override
            protected void initChannel(io.netty.channel.socket.SocketChannel socketChannel) throws Exception {
                ChannelPipeline p = socketChannel.pipeline();
                p.addLast(new ObjectEncoder());
                p.addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                p.addLast(new NettyServerHandler());
            }
        });
        ChannelFuture f = null;
        try {
            f = bootstrap.bind(port).sync();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (f.isSuccess()) {
            System.out.println("server start---------------");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ServerBootStrap bootstrap = new ServerBootStrap(9999);
//        while (true) {
//            io.netty.channel.socket.SocketChannel channel = (io.netty.channel.socket.SocketChannel) NettyChannelMap.get("001");
//            if (channel != null) {
//                AskMsg askMsg = new AskMsg();
//                channel.writeAndFlush(askMsg);
//            }
//            TimeUnit.SECONDS.sleep(10);
//        }
    }
}
