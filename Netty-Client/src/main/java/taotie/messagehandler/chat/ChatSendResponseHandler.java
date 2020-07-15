package taotie.messagehandler.chat;

import dispatcher.MessageHandler;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import taotie.message.chat.ChatSendResponse;

/**
 * @author lkd
 * @date 2020/7/14 16:18
 */
@Slf4j
@Component
public class ChatSendResponseHandler implements MessageHandler<ChatSendResponse> {
    @Override
    public void execute(Channel channel, ChatSendResponse message) {
        log.info("[execute][发送结果：{}]", message);
    }

    @Override
    public String getType() {
        return ChatSendResponse.TYPE;
    }
}
