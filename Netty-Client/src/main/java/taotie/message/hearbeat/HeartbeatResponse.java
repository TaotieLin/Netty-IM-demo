package taotie.message.hearbeat;

import dispatcher.Message;

/**
 * @author lkd
 * @date 2020/7/13 16:42
 */
public class HeartbeatResponse implements Message {

    /**
     * 类型 - 心跳请求
     */
    public static final String TYPE = "HEARTBEAT_REQUEST";

    @Override
    public String toString() {
        return "HeartbeatRequest{}";
    }

}
