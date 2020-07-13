package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @author lkd
 * @desc
 * @date 2020/7/10 15:38
 */

public class NettySever {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @Value("${netty.port}")
    private Integer port;

    @Autowired
    private NettyServerHandlerInitializer nettyServerHandlerInitializer;

    /**
     * boss线程组
     */
    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    /**
     * worker线程组
     */
    private EventLoopGroup worker = new NioEventLoopGroup();

    /**
     * netty Server channel;
     */
    private Channel channel;

    /**
     * 启动netty
     */
    public void start() throws InterruptedException {

        //创建ServerBootstrap 对象 用于netty启动
        ServerBootstrap bootstrap = new ServerBootstrap();
        //设置服务启动引导各种属性
        bootstrap.group(bossGroup,worker)
                .channel(NioSctpServerChannel.class)
                .localAddress(new InetSocketAddress(port))
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(nettyServerHandlerInitializer);

        //绑定端口，同步等待成功，即启动服务端。
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()){
            channel = future.channel();
            logger.info("[start][Netty Server 启动在{}端口]",port);
        }

    }

    /**
     * 关闭netty,Server
     */
    @PreDestroy
    public void shutdown(){
        //关闭Netty Server

        if (channel !=null){
            channel.close();
        }

        bossGroup.shutdownGracefully();
        worker.shutdownGracefully();
    }

}
