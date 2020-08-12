package taotie.client;


import codec.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import taotie.client.handler.NettyClientHandler;
import taotie.client.handler.NettyClientHandlerInitializer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author lkd
 * @date 2020/7/14 9:56
 */
@Slf4j
@Component
public class NettyClient {
    /**
     * 重连频率，单位秒
     */
    private static final Integer RECONNECT_SECONDS = 20;
    @Value("${netty.server.host}")
    private String serverHost;
    @Value("${netty.server.port}")
    private Integer serverPort;

    @Autowired
    private NettyClientHandlerInitializer nettyClientHandlerInitializer;

    @Autowired
    private NettyClientHandler nettyClientHandler;

    /**
     * 线程组，用于客户端对服务端的链接、数据读写
     */
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    /**
     * Netty Client Channel
     */
    private volatile Channel channel;

    @PostConstruct
    public void start() throws InterruptedException{
        //创建 Bootstrap 对象，用于 Netty Client启动
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(serverHost,serverPort)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(nettyClientHandler)
                .handler(nettyClientHandlerInitializer);
        //链接服务器，并异步等待成功，即启动客户端
        bootstrap.connect().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()){
                    log.error("[start][Netty Client 连接服务器({}:{}) 失败]", serverHost, serverPort);
                    reconnect();
                    return;
                }
                channel = future.channel();
                log.info("[start][Netty Client 连接服务器({}:{}) 成功]", serverHost, serverPort);
            }
        });

    }

    public void reconnect() {
            eventLoopGroup.schedule(new Runnable() {
                @Override
                public void run() {
                    log.info("[reconnect][开始重连]");
                    try{
                        start();
                    }catch (InterruptedException e){
                        log.info("[reconnect][重连失败]",e);
                    }
                }
            },RECONNECT_SECONDS, TimeUnit.SECONDS);
            log.info("[reconnect][{} 秒后将发起重连]", RECONNECT_SECONDS);
    }

    @PreDestroy
    public void shutdown(){
        if (channel !=null){
            channel.close();
        }
        eventLoopGroup.shutdownGracefully();
    }

    public void send(Invocation invocation){
        if (channel == null){
            log.error("[send]链接不存在");
            return;
        }
        if (!channel.isActive()){
            log.error("[send]链接未激活");
            return;
        }
        channel.writeAndFlush(invocation);
    }
}
