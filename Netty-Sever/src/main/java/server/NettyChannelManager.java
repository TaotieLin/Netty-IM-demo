package server;

/**
 * @author lkd
 * @desc
 * @date 2020/7/13 10:34
 */

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.AttributeKey;
import org.aopalliance.intercept.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.management.Attribute;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 客户端Channel 管理器. 提供两种功能
 * 1.客户端Channel 的管理
 * 2.向客户端 channel发送消息
 * @author Administrator
 */
@Component
public class NettyChannelManager {

    /**
     *
     */

    private static final AttributeKey<String> CHANNEL_ATTR_KEY_USER = AttributeKey.newInstance("user");

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Channel 映射
     */
    private ConcurrentMap<ChannelId, Channel> channels = new ConcurrentHashMap<>();

    /**
     * 用户与Channel
     * 通过它，可以获取用户对应的 Channel。这样，我们可以向指定用户发送消息。
     */
    private ConcurrentMap<String,Channel> userChannels = new ConcurrentHashMap<>();

    /**添加 Channel 到 {@link #channels} 中
     *
     * @param channel Channel
     */
    public void add(Channel channel){
        channels.put(channel.id(),channel);
        logger.info("[add][一个连接({})加入]", channel.id());
    }

    /**
     * 添加指定用户到 {@link #userChannels} 中
     *
     * @param channel Channel
     * @param user 用户
     */
    public void addUser(Channel channel, String user) {
        Channel existChannel = channels.get(channel.id());
        if (existChannel == null) {
            logger.error("[addUser][连接({}) 不存在]", channel.id());
            return;
        }
        // 设置属性
        channel.attr(CHANNEL_ATTR_KEY_USER).set(user);
        // 添加到 userChannels
        userChannels.put(user, channel);
    }

    /**
     * 向指定用户发送消息
     *
     *
     */
    public void send(String user, Invocation invocation){

        Channel channel = userChannels.get(user);
        if (channel == null){
            logger.error("[send][用户{}连接不存在]",user);
        }
        if (!channel.isActive()){
            logger.error("[send][用户{}连接未激活]",user);
        }
        channel.writeAndFlush(invocation);
    }

    /**
     * 向所有用户发送消息
     */
    public void sendAll(Invocation invocation){
        for (Channel channel : channels.values()) {
            if (!channel.isActive()) {
                logger.error("[send][连接({})未激活]", channel.id());
                return;
            }
            // 发送消息
            channel.writeAndFlush(invocation);
        }
    }


}
