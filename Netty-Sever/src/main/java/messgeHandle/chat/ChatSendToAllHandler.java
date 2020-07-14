package messgeHandle.chat;

import codec.Invocation;
import dispatcher.MessageHandler;
import io.netty.channel.Channel;
import message.chat.ChatRedirectToUserRequest;
import message.chat.ChatSendResponse;
import message.chat.ChatSendToAllRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.NettyChannelManager;

/**
 * @author lkd
 * @date 2020/7/14 9:19
 */
@Component
public class ChatSendToAllHandler implements MessageHandler<ChatSendToAllRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, ChatSendToAllRequest message) {
        //假装直接成功
        ChatSendResponse response = new ChatSendResponse().setMessage(message.getMsgId()).setCode(0);
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE,response));

        //创建转发的消息，并广播发送
        ChatRedirectToUserRequest toUserRequest = new ChatRedirectToUserRequest().setContent(message.getMsgId())
                .setContent(message.getContent());
        nettyChannelManager.sendAll(new Invocation(ChatRedirectToUserRequest.TYPE,toUserRequest));
    }

    @Override
    public String getType() {
        return ChatSendToAllRequest.TYPE;
    }
}
