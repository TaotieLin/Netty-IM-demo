package taotie.messgeHandle.heartbeat;

import codec.Invocation;
import dispatcher.MessageHandler;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import taotie.message.heartbeat.HeartbeatRequest;
import taotie.message.heartbeat.HeartbeatResponse;
import org.springframework.stereotype.Component;

/**
 * @author lkd
 * @date 2020/7/14 9:39
 */
@Slf4j
@Component
public class HeartbeatRequestHandler implements MessageHandler<HeartbeatRequest> {
    @Override
    public void execute(Channel channel, HeartbeatRequest message) {
        log.info("[execute][收到连接({}) 的心跳请求]", channel.id());
        //响应心跳
        HeartbeatResponse response = new HeartbeatResponse();
        channel.writeAndFlush(new Invocation(HeartbeatResponse.TYPE,response));
    }

    @Override
    public String getType() {
        return HeartbeatResponse.TYPE;
    }
}
