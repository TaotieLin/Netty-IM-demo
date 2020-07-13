package message.chat;

import dispatcher.Message;
import lombok.Data;

/**
 * @author lkd
 * @date 2020/7/13 16:38
 */
@Data
public class ChatSendToAllRequest implements Message {

    public static final String TYPE = "CHAT_SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

    @Override
    public String toString() {
        return "ChatSendToAllRequest{" +
                "msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
