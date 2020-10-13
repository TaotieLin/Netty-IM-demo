#Mysql Explain
概要描述
id:选择标识符
select_type:查询类型

(1) SIMPLE(简单SELECT，不使用UNION或子查询等)

(2) PRIMARY(子查询中最外层查询，查询中若包含任何复杂的子部分，最外层的select被标记为PRIMARY)

(3) UNION(UNION中的第二个或后面的SELECT语句)

(4) DEPENDENT UNION(UNION中的第二个或后面的SELECT语句，取决于外面的查询)

(5) UNION RESULT(UNION的结果，union语句中第二个select开始后面所有select)

(6) SUBQUERY(子查询中的第一个SELECT，结果不依赖于外部查询)

(7) DEPENDENT SUBQUERY(子查询中的第一个SELECT，依赖于外部查询)

(8) DERIVED(派生表的SELECT, FROM子句的子查询)

(9) UNCACHEABLE SUBQUERY(一个子查询的结果不能被缓存，必须重新评估外链接的第一行)

table:表名称
显示这一步所访问数据库中表名称（显示这一行的数据是关于哪张表的），有时不是真实的表名字，可能是简称，例如上面的e，d，也可能是第几步执行的结果的简称

partitions:
type:对表访问类型，访问方式。

(1) ALL:全表扫描。

(2) index: 遍历扫描。

(3) range: 只检索给定范围的行，使用一个索引来选择行。

(4) ref: 表示上述表的连接匹配条件，即哪些列或常量被用于查找索引列上的值

(5) eq_ref: 类似ref，区别是使用唯一索引。

(6) const、system: 当MySQL对查询某部分进行优化，并转换为一个常量时，使用这些类型访问。如将主键置于where列表中，MySQL就能将该查询转换为一个常
量，system是const类型的特例，当查询的表只有一行的情况下，使用system

(7) null: MySQL在优化过程中分解语句，执行时甚至不用访问表或索引，例如从一个索引列里选取最小值可以通过单独索引查找完成。

possible_keys: 指出MySQL能使用哪个索引在表中找到记录，查询涉及到的字段上若存在索引，则该索引将被列出，但不一定被查询使用（该查询可以利用的索引，
如果没有任何索引显示 null）

key: key列显示MySQL实际决定使用的键（索引），必然包含在possible_keys中
如果没有选择索引，键是NULL。要想强制MySQL使用或忽视possible_keys列中的索引，在查询中使用FORCE INDEX、USE INDEX或者IGNORE INDEX。

key_len: 表示索引中使用的字节数，可通过该列计算查询中使用的索引的长度（key_len显示的值为索引字段的最大可能长度，并非实际使用长度，即key_len是根据表定义计算而得，不是通过表内检索出的）

ref: 列与索引的比较，表示上述表的连接匹配条件，即哪些列或常量被用于查找索引列上的值
rows:  估算出结果集行数，表示MySQL根据表统计信息及索引选用情况，估算的找到所需的记录所需要读取的行数
filtered: 按表条件过滤的行百分比
Extra:
Using where:不用读取表中所有信息，仅通过索引就可以获取所需数据，这发生在对表的全部的请求列都是同一个索引的部分的时候，表示mysql服务器将在存储引擎检索行后再进行过滤      
Using temporary：表示MySQL需要使用临时表来存储结果集，常见于排序和分组查询，常见 group by ; order by    
Using filesort：当Query中包含 order by 操作，而且无法利用索引完成的排序操作称为“文件排序”
-- 测试Extra的filesort
explain select * from emp order by name;
Using join buffer：改值强调了在获取连接条件时没有使用索引，并且需要连接缓冲区来存储中间结果。如果出现了这个值，那应该注意，根据查询的具体情况可能需要添加索引来改进能。
Impossible where：这个值强调了where语句会导致没有符合条件的行（通过收集统计信息不可能存在结果）。
Select tables optimized away：这个值意味着仅通过使用索引，优化器可能仅从聚合函数结果中返回一行
No tables used：Query语句中使用from dual 或不含任何from子句

#Filesort
这个 filesort 并不是说通过磁盘文件进行排序，而只是告诉我们进行了一个排序操作。即在MySQL Query Optimizer 所给出的执行计划(通过 EXPLAIN 命令
查看)中被称为文件排序（filesort）
  文件排序是通过相应的排序算法,将取得的数据在内存中进行排序: MySQL需要将数据在内存中进行排序，所使用的内存区域也就是我们通过
sort_buffer_size 系统变量所设置的排序区。这个排序区是每个Thread 独享的，所以说可能在同一时刻在MySQL 中可能存在多个 sort buffer 内存区域。

filesort分两种

双路排序：是首先根据相应的条件取出相应的排序字段和可以直接定位行数据的行指针信息，然后在sort buffer 中进行排序。排序后再吧查询字段依照行指针取出，
共执行两次磁盘io。

单路排序：是一次性取出满足条件行的所有字段，然后在sort buffer中进行排序。 执行一次磁盘io。

MySQL主要通过比较我们所设定的系统参数 max_length_for_sort_data的大小和Query 语句所取出的字段类型大小总和来判定需要使用哪一种排序算法。
如果 max_length_for_sort_data更大，则使用第二种优化后的算法，反之使用第一种算法。所以如果希望 ORDER BY 操作的效率尽可能的高，一定要主义
max_length_for_sort_data 参数的设置。曾经就有同事的数据库出现大量的排序等待，造成系统负载很高，而且响应时间变得很长，最后查出正是因为MySQL 
使用了传统的第一种排序算法而导致，在加大了max_length_for_sort_data 参数值之后，系统负载马上得到了大的缓解，响应也快了很多。
如果order by的子句只引用了联接中的第一个表，MySQL会先对第一个表进行排序，然后进行联接。也就是expain中的Extra的Using Filesort.否则MySQL
先把结果保存到临时表(Temporary Table),然后再对临时表的数据进行排序.此时expain中的Extra的显示Using temporary Using Filesort.


#查询的优化
使用索引的语句也有可能是慢查询，我们的查询优化的过程，往往就是减少扫描行数的过程。
慢查询归纳起来大概有这么几种情况：

全表扫描
全索引扫描
索引过滤性不好
频繁回表的开销

使用虚拟列优化你的索引。
