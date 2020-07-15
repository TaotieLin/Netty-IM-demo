package taotie.messagehandler.auth;

import dispatcher.MessageHandler;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import taotie.message.auth.AuthRequest;
import taotie.message.auth.AuthResponse;


/**
 * @author lkd
 * @date 2020/7/13 17:07
 */
@Component
public class AuthRequestHandler implements MessageHandler<AuthRequest> {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void execute(Channel channel, AuthRequest message) {

        logger.info("[execute][认证结果：{}]", message);
    }

    @Override
    public String getType() {
        return AuthResponse.TYPE;
    }
}
