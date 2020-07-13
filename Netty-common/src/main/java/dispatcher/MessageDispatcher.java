package dispatcher;

import codec.Invocation;
import com.sun.corba.se.impl.protocol.giopmsgheaders.MessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lkd
 * @desc
 * @date 2020/7/13 10:56
 */

public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {

    @Autowired
    private MessageHandlerContainer messageHandlerContainer;

    private final ExecutorService executor = Executors.newFixedThreadPool(200);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation msg) throws Exception {
        //获得type对应的 MessageHandler 处理器

    }
}
