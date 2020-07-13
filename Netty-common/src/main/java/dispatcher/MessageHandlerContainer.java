package dispatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lkd
 * @desc
 * @date 2020/7/13 11:09
 */
public class MessageHandlerContainer implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 消息类型与 MessageHandler 的映射
     */
    private final Map<String, MessageHandler> handlers = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansOfType(MessageHandler.class).values()
                .forEach(messageHandler -> handlers.put(messageHandler.getType(),messageHandler));
        logger.info("[afterPropertiesSet][消息处理器数量：{}]", handlers.size());
    }

    /**
     * 获得类型对应的 MessageHandler
     */
    MessageHandler<?> getMessageHandler(String type){
        MessageHandler<?> handler = handlers.get(type);
        if (handler == null){
            throw new IllegalArgumentException(String.format("类型(%s) 找不到匹配的 MessageHandler 处理器", type));
        }
        return handler;
    }

    /**
     * 获得 MessageHandler
     */
    static Class<? extends Message> getMessageClass(MessageHandler<?> handler){
        //获得 Bean 对应的Class类名
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        // 此处，是以父类的接口为准
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)){
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)){
            for (Type type : interfaces){
                //要求type是泛型参数
                if (type instanceof ParameterizedType){
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    if (Objects.equals(parameterizedType.getRawType(),MessageHandler.class)){
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        //取首个元素
                        if(Objects.nonNull(actualTypeArguments) && actualTypeArguments.length >0){
                            return (Class<Message>) actualTypeArguments[0];
                        } else {
                            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
    }

}
