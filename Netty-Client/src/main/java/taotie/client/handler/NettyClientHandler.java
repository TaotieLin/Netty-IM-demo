package client.handler;


import client.NettyClient;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lkd
 * @date 2020/7/14 14:24
 */

@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private NettyClient nettyClient;


    @Override
    public void channelInactive(ChannelHandlerContext context) throws Exception{
        //发起重连
        nettyClient.reconnect();
        //继续触发事件
        super.channelInactive(context);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("[exceptionCaught][连接({}) 发生异常]", ctx.channel().id(), cause);
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context,Object object) throws Exception{
        //空闲时，向服务器发起一次心跳
        if (object instanceof IdleStateEvent){
            log.info("[userEventTriggered][发起一次心跳]");

        }
    }

}
