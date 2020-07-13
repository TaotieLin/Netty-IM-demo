package message.chat;

import dispatcher.Message;
import lombok.Data;

/**
 * @author lkd
 * @date 2020/7/13 14:46
 */
@Data
public class ChatSendResponse implements Message {

    public static final String TYPE = "CHAT_SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;



    @Override
    public String toString() {
        return "ChatSendResponse{" +
                "msgId='" + msgId + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
