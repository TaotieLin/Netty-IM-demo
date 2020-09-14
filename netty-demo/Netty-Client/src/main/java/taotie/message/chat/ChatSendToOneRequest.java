package taotie.message.chat;

import dispatcher.Message;
import lombok.Data;

/**
 * @author lkd
 * @date 2020/7/13 16:39
 */

public class ChatSendToOneRequest implements Message {

    public static final String TYPE = "CHAT_SEND_TO_ONE_REQUEST";

    /**
     * 发送给的用户
     */
    private String toUser;
    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;

    public static String getTYPE() {
        return TYPE;
    }

    public String getToUser() {
        return toUser;
    }

    public ChatSendToOneRequest setToUser(String toUser) {
        this.toUser = toUser;
        return this;
    }

    public String getMsgId() {
        return msgId;
    }

    public ChatSendToOneRequest setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ChatSendToOneRequest setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "ChatSendToOneRequest{" +
                "toUser='" + toUser + '\'' +
                ", msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
