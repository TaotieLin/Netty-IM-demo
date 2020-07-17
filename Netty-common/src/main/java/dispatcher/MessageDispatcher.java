package dispatcher;

import codec.Invocation;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lkd
 * @desc
 * @date 2020/7/13 10:56
 */

@ChannelHandler.Sharable
public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {

    @Resource
    private MessageHandlerContainer messageHandlerContainer;

    private final ExecutorService executor = Executors.newFixedThreadPool(200);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Invocation invocation) {
        // 获得 type 对应的 MessageHandler 处理器
        MessageHandler messageHandler = messageHandlerContainer.getMessageHandler(invocation.getType());
        // 获得  MessageHandler 处理器 的消息类
        Class<? extends Message> messageClass = MessageHandlerContainer.getMessageClass(messageHandler);
        // 解析消息
        Message message = JSON.parseObject(invocation.getMessage(), messageClass);
        // 执行逻辑
        executor.submit(() -> {
            // noinspection unchecked
            messageHandler.execute(ctx.channel(), message);
        });
    }
}
