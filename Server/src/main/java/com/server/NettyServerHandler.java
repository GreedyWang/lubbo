package com.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;
import remote.exchange.BaseMsg;
import remote.exchange.MsgType;
import remote.exchange.ReplyClientBody;
import remote.exchange.ReplyServerBody;
import remote.exchange.msg.AskMsg;
import remote.exchange.msg.LoginMsg;
import remote.exchange.msg.PingMsg;
import remote.exchange.msg.ReplyMsg;


public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {


    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
        if (MsgType.LOGIN.equals(baseMsg.getType())) {
            LoginMsg loginMsg = (LoginMsg) baseMsg;
            if ("".equals(loginMsg.getUserName()) && "".equals(loginMsg.getPassword())) {
                //登录成功,把channel存到服务端的map中
                NettyChannelMap.add(loginMsg.getClientId(), (SocketChannel) channelHandlerContext.channel());
                System.out.println("client" + loginMsg.getClientId() + " 登录成功");
            }
        }
        else {
            if (NettyChannelMap.get(baseMsg.getClientId()) == null) {
                //说明未登录，或者连接断了，服务器向客户端发起登录请求，让客户端重新登录
                LoginMsg loginMsg = new LoginMsg();
                channelHandlerContext.channel().writeAndFlush(loginMsg);
            }
        }
        switch (baseMsg.getType()) {
            case PING: {
                PingMsg pingMsg = (PingMsg) baseMsg;
                PingMsg replyPing = new PingMsg();
                NettyChannelMap.get(pingMsg.getClientId()).writeAndFlush(replyPing);
            }
            break;
            case ASK: {
                //收到客户端的请求
                AskMsg askMsg = (AskMsg) baseMsg;
                //if ("authToken".equals(askMsg.getAuth())) {
                    ReplyServerBody replyBody = new ReplyServerBody("server response" + askMsg.getClientId());
                    ReplyMsg replyMsg = new ReplyMsg();
                    replyMsg.setBody(replyBody);
                    NettyChannelMap.get(askMsg.getClientId()).writeAndFlush(replyMsg);
               // }
            }
            break;
            case REPLY: {
                //收到客户端回复
                ReplyMsg replyMsg = (ReplyMsg) baseMsg;
                ReplyClientBody clientBody = (ReplyClientBody) replyMsg.getBody();
                System.out.println("receive client msg: " + clientBody.getClientInfo());
            }
            break;
            default:
                break;
        }
        ReferenceCountUtil.release(baseMsg);
    }
}
