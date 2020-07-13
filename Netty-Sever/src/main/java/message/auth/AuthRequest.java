package message.auth;

import dispatcher.Message;

/**
 * @author lkd
 * @desc
 * @date 2020/7/13 10:51
 */
public class AuthRequest implements Message {

    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认证token
     */
    public String accessToken;


    public String getAccessToken(){
        return accessToken;
    }

    public AuthRequest setAccessToken(String accessToken){
        this.accessToken = accessToken;
        return this;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
