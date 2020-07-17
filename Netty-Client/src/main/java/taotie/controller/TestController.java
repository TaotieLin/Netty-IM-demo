package taotie.controller;


import codec.Invocation;
import dispatcher.Message;
import dispatcher.MessageHandlerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import taotie.client.NettyClient;
import taotie.message.auth.AuthRequest;
import taotie.message.auth.AuthResponse;
import taotie.message.chat.ChatRedirectToUserRequest;
import taotie.message.chat.ChatSendResponse;
import taotie.message.chat.ChatSendToAllRequest;
import taotie.message.chat.ChatSendToOneRequest;
import taotie.message.hearbeat.HeartbeatRequest;
import taotie.message.hearbeat.HeartbeatResponse;


/**
 * @author lkd
 * @date 2020/7/14 16:26
 */
@RestController
public class TestController {

    @Autowired
    private NettyClient nettyClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MessageHandlerContainer messageHandlerContainer;

    @PostMapping("/mock")
    public String mock(String type, String message) {

        getMessge(type,message);
        // 创建 Invocation 对象
        Invocation invocation = new Invocation(type, message);

        // 发送消息
        nettyClient.send(invocation);
        return "success";
    }

    private Message getMessge(String type, String message) {
        if (AuthRequest.TYPE.equals(type)) {
            return new AuthRequest().setAccessToken(message);
        } else if (AuthResponse.TYPE.equals(type)) {
            return new AuthResponse().setMessage(message).setCode(100);
        } else if (ChatRedirectToUserRequest.TYPE.equals(type)) {
            return new ChatRedirectToUserRequest().setContent(message);
        } else if (ChatSendResponse.TYPE.equals(type)) {
            return new ChatSendResponse().setMessage(message);
        } else if (ChatSendToAllRequest.TYPE.equals(type)) {
            return new ChatSendToAllRequest().setContent(message);
        } else if (ChatSendToOneRequest.TYPE.equals(type)) {
            return new ChatSendToOneRequest().setContent(message);
        } else if (HeartbeatRequest.TYPE.equals(type)) {
            return new HeartbeatRequest();
        } else if (HeartbeatResponse.TYPE.equals(type)) {
            return new HeartbeatResponse();
        }
        return null;
    }


}
