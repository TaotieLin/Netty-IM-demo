package codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lkd
 * @date 2020/7/13 13:39
 * {@link Invocation} 编码器
 *
 */

public class InvocationEncoder  extends MessageToByteEncoder<Invocation> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void encode(ChannelHandlerContext ctx, Invocation msg, ByteBuf out) throws Exception {
        //将 Invocation 转换成 byte[] 数组
        byte[] content = JSON.toJSONBytes(msg);
        //写入长度
        out.writeInt(content.length);
        //写入内容
        out.writeBytes(content);
        logger.info("[encode][连接({}) 编码了一条消息({})]", ctx.channel().id(), msg.toString());
    }
}
