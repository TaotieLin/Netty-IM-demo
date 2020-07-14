package messgeHandle.chat;

import codec.Invocation;
import dispatcher.MessageHandler;
import io.netty.channel.Channel;
import message.chat.ChatRedirectToUserRequest;
import message.chat.ChatSendResponse;
import message.chat.ChatSendToOneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.NettyChannelManager;

/**
 * @author lkd
 * @date 2020/7/14 9:29
 */
@Component
public class ChatSendToOneHandler implements MessageHandler<ChatSendToOneRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, ChatSendToOneRequest message) {
        ChatSendResponse response = new ChatSendResponse().setMsgId(message.getMsgId()).setCode(0);
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE,response));
        // 创建转发的消息，发送给指定用户
        ChatRedirectToUserRequest toUserRequest = new ChatRedirectToUserRequest().setMsgId(message.getMsgId())
                .setContent(message.getContent());
        nettyChannelManager.send(message.getToUser(),new Invocation(ChatRedirectToUserRequest.TYPE,toUserRequest));
    }

    @Override
    public String getType() {
        return ChatSendToOneRequest.TYPE;
    }
}
