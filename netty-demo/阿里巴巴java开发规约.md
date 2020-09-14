#单元测试
测试数据入库统一前缀
单元测试数据需要回滚
测试使用独立数据库
#并发编程
线程使用线程池
锁使用最小粒度
线程创建带名称
#ROM
.iBATIS自带的queryForList(String statementName,int start,int size)分页接口有性能隐患，不允许使用。
#java基础
NULL与任何值的比较结果都为NULL。
当某一列数据全为null时，count（）结果为null。
hashMap KV都能为null
ConcurrentHashMap KV都不能为null

List<E> subList(int fromIndex, int toIndex) 返回的是当前List的视图。你可以通过这种方式选当前list的一部分进行操作。
但不能将此方法返回的list拿来序列化。

抛出异常要做到认知对等,即异常抛出方和接受方都知道并如何处理这个异常
代码中需要注意防范ReDOS-正则输入源攻击
ScheduledExecutorService 多线程执行时 某个线程抛出异常不会影响其他线程执行。
#代码测试
语句覆盖/代码行覆盖：每个语句执行一次
判定覆盖、分支覆盖:每个判定的可能结果都执行一次
条件覆盖: 每个初始条件的所有可能执行一次
条件组合覆盖: 全部条件的所有可能的所有组合执行一次
路径覆盖: 每个执行路径执行一次
