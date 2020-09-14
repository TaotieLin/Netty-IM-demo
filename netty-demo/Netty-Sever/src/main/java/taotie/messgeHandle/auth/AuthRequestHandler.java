package taotie.messgeHandle.auth;

import codec.Invocation;
import dispatcher.MessageHandler;
import taotie.message.auth.AuthRequest;
import taotie.message.auth.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.netty.channel.Channel;
import taotie.server.NettyChannelManager;


/**
 * @author lkd
 * @date 2020/7/13 17:07
 */
@Component
public class AuthRequestHandler implements MessageHandler<AuthRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, AuthRequest message) {
        //如果未传递 accessToken
        if (StringUtils.isEmpty(message.getAccessToken())){
            AuthResponse authResponse = new AuthResponse().setCode(1).setMessage("认证 accessToken 未传入");
            channel.writeAndFlush(new Invocation(AuthResponse.TYPE,authResponse));
            return;
        }
        nettyChannelManager.addUser(channel,message.getAccessToken());
        AuthResponse authResponse = new AuthResponse().setCode(0);
        channel.writeAndFlush(new Invocation(AuthResponse.TYPE,authResponse));
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}
