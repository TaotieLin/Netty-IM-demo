# Netty-IM-demo
敲一下别人项目 熟悉一下Netty

#1.归纳框架要点
	spring-boot
	spring:ioc,aop,动态代理，生命周期，加载过程
	spring-事务transactional
		1，除非特殊配置（比如使用 AspectJ 静态织入实现 AOP），否则只有定义在 public 方法上的 @Transactional 才能生效。原因是，Spring 默认通过动态代理的方式实现 AOP，对目标方法进行增强，private 方法无法代理到，Spring 自然也无法动态增强事务处理逻辑。
		2、必须通过代理过的外部类调用才能生效。
		3、只有异常传播出了标记了 @Transactional 注解的方法，事务才能回滚
		4、默认情况出现RuntimeException非受检异常或者Error的时候Spring才会回滚。
		5.请确认事务传播配置是否符合自己的业务逻辑
	springMvc:路由
	mybatis：三级缓存，类型处理器，多数据源，动态数据源
	mybatisPuls：基础mapper，条件构造器。
	netty，底层原理，demo实现。
	日志框架了解
#2.分布式框架了解
	nignx常用配置，底层原理
	spring-cloud
		Feign 声明式调用
			连接超时时间应该配置的较短一些，因为TCP连接特别快。
			Feign和Ribbon配合使用如何配置超时。
				Feign 配置超时参数的复杂之处在于，Feign 自己有两个超时参数，它使用的负载均衡组件 Ribbon 本身还有相关配置。
				结论一，默认情况下 Feign 的读取超时是 1 秒，如此短的读取超时算是坑点一。
				结论二，也是坑点二，如果要配置 Feign 的读取超时，就必须同时配置连接超时，才能生效。
				结论三，单独的超时可以覆盖全局超时，这符合预期，不算坑。
				结论四，除了可以配置 Feign，也可以配置 Ribbon 组件的参数来修改两个超时时间。这里的坑点三是，参数首字母要大写，和 Feign 的配置不同。
				结论五，同时配置 Feign 和 Ribbon 的超时，以 Feign 为准。
	dubbo了解，demo实现
	消息队列了解，demo实现
	redis缓存。
#3.数据库相关知识
	sql高级语法
	sql性能优化
	建表设计
	NoSql
	MyCat了解
#4.java核心知识
	数据结构：map，list，hashMap,并发hashMap
	多线程
		不能使用Executors提供的两种快捷线程池
			1、我们需要根据自己的场景、并发情况来评估线程池的几个核心参数。包括核心线程数、最大线程数、线程回收策略、工作队列、以及拒绝策略。
			一般需要有界队列和可控的线程数。
			2、任何时候都应该为自定义线程指定有意义名称，方便排查问题。当出现线程数量暴增、线程死锁、线程占用大量CPU、线程执行异常等问题，有意义的线程名称可以方便我们排查问题。
			3、确保线程池复用。
			4、根据任务性质来选用不同线程池。特别注意IO绑定任务和CPU绑定任务对于线程池属性偏好。
			5、线程池作为应用程序内部核心组件往往缺乏监控。需注意。
	Lombok 默认实现了set get toString hashCode equals 等方法，但他们的实现逻辑可能与你所期望的有所不同，需要注意。
	使用 BigDecimal 表示和计算浮点数，且务必使用字符串的构造方法来初始化 BigDecimal
	JVM优化
	代理动态代理，cglib
	连接池
		数据库连接池
		Redis连接池
		HTTP连接池
	文件读写
	    文件读写需要确保字符编码一致
	    FileReader 是以当前机器的默认字符集来读取文件的，如果希望指定字符集的话，需要直接使用 InputStreamReader 和 FileInputStream。
	    使用 Files 类静态方法进行文件操作注意释放文件句柄
	    FileChannel 的 transfreTo 方法进行流的复制。在一些操作系统（比如高版本的 Linux 和 UNIX）上可以实现 DMA（直接内存访问），也就是数据从磁盘经过总线直接发送到目标文件，无需经过内存和 CPU 进行数据中转
#5.项目维护
	linux项目维护，常用命令
	docker项目维护日志查询命令，
	Jenkins项目部署
#6.底层代码通读
	hashmap
	
	
	