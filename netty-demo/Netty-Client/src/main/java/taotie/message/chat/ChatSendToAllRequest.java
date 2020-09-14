package taotie.message.chat;

import dispatcher.Message;
import lombok.Data;

/**
 * @author lkd
 * @date 2020/7/13 16:38
 */

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

    public static String getTYPE() {
        return TYPE;
    }

    public String getMsgId() {
        return msgId;
    }

    public ChatSendToAllRequest setMsgId(String msgId) {
        this.msgId = msgId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ChatSendToAllRequest setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        return "ChatSendToAllRequest{" +
                "msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
