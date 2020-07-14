package client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author lkd
 * @date 2020/7/14 9:56
 */
@Slf4j
@Component
public class NettyClient {
    /**
     * 重连频率，单位秒
     */
    private static final Integer RECONNECT_SECONDS = 20;

    @Value("${netty.server.host}")
    private String serverHost;
    @Value("${netty.server.port}")
    private Integer serverPort;


}
