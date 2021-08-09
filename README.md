# ssm

## 日志

### log4j2

log4j2不支持properties后缀的文件配置方式。log4j2版本配置文件后缀只能为xml、json或jsn。

系统选择配置文件的优先级如下：

* 1、classpath下的名为log4j2-test.json或者log4j-test.jsn；
* 2、classpath下的名为log4j2-test.xml；
* 3、classpath下名为log4j2.json或者log4j2.jsn的文件；
* 4、classpath下名为log4j2.xml；

#### log4j2配置文件详解

* Configuration节点中有两个属性：status和monitorInterval；
  * status表示log4j自己的日志打印级别；
  * monitorInterval表示每隔多少秒自动检测配置文件的更改，单位是秒，最小的时间间隔是5秒；
* Configuration有两个子节点Appenders和Loggers
  * Appenders节点，常见的有三种子节点：Console、RollingFile、File；
    * Console节点用来定义输出控制台的Appender；
      * name：指定Appender的名字；
      * target：SYSTEM_OUT或SYSTEM_ERROR，一般只设置默认：SYSTEM_OUT；
      * PatternLayout：输出格式，不设置默认为%m%n；
    * File节点用来定义输出到指定位置的文件
      * name：指定Appender的名字；
      * fileName：指定输出日志的目的文件带全路径的文件名；
      * PatternLayout：输出格式，不设置默认为：%m%n；
    * RollingFile节点用来定义超过指定大小自动删除旧的创建新的Appender
      * name：指定Appender名字；
      * fileName：指定输出日志的目的文件带全路径的文件名；
      * PatternLayout：RollingFile子节点，输出格式，不设置默认为%m%n；
      * filePattern：指定新建日志文件的名称格式；
      * Policies：指定滚动日志的策略，就是什么时候进行新建日志文件输出日志；
        * TimeBasedTriggeringPolicy：基于时间的滚动策略，interval属性用来指定多久滚动一次，默认是1hour。
        * SizeBasedTriggeringPolicy：基于指定文件大小的滚动策略，size属性用来定义每个日志文件的大小；
        * DefaultRolloverStrategy：用来指定同一个文件夹下最多有几个日志文件时开始删除最旧的，创建新的（通过max）；
  * Loggers节点，常见的两种：Root和Logger
    * Root节点用来指定项目的根日志，如果没有单独指定Logger，那么就会默认使用Root日志输出
      * levle：日志输出级别，共有8个级别，按照从低到高：ALL-Trace-Debug-Info-Warn-Error-Fatal-OFF
      * name：用来指定该日志输出到哪个Appender
    * Logger节点用来单独指定日志的形式，比如要为指定包下的class指定不同的日志级别等
      * levle：日志输出级别；
      * name：用来指定该Logger所适用的类或类所在的包路径；
      * AppenderRef：用来指定该日志输出到哪个Appender，如果没有指定，就会默认继承自Root；

## 事务

* MySQL

  支持4中隔离级别，默认是可重复读隔离级别；

* Oracle

  只能支持读提交、序列化两种隔离级别，默认为读提交；

### Spring事务实效

#### 失效场景1

注解@Transactional的底层实现是Spring AOP技术，而Spring AOP技术使用的是动态代理，这就意味着对应静态方法和非public方法，注解@Transactional是失效的。

#### 失效场景2

自调用：一个类的一个方法调用自身另外一个方法的过程。

#### 失效场景3

代码吞没异常造成

```java
@Autowired
private ProductService productService;

@Autowired
private TransactionService transactionService;

@Override
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public int doTransaction(TransactionBean trans) {
    int result = 0;
    
    try {
        // 减少库存
        int result = productService.decreaseStock(trans.getProductId, trans.getQuantity());
        
        // 如果减少库存成功则保持记录
        if (result > 0) {
            transactionService.save(trans);
        }
    } catch (Exception ex) {
        // 自行处理异常代码
        // 记录异常日志
        log.info(ex);
    }
    
    return result;
}
```

代码写的是有问题的，由于自行加入了try...catch...语句，所以在Spring数据库事务所约定的流程中再也得不到任何异常信息了，此时Spring就会提交事务，这样会出现库存减少，而交易记录却没有的糟糕情况。

正确写法如下

```java
@Autowired
private ProductService productService;

@Autowired
private TransactionService transactionService;

@Override
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public int doTransaction(TransactionBean trans) {
    int result = 0;
    
    try {
        // 减少库存
        int result = productService.decreaseStock(trans.getProductId, trans.getQuantity());
        
        // 如果减少库存成功则保持记录
        if (result > 0) {
            transactionService.save(trans);
        }
    } catch (Exception ex) {
        // 自行处理异常代码
        // 记录异常日志
        log.info(ex);
        // 记录异常日志后，抛出异常
        throw new RuntimeException(e);
    }
    
    return result;
}
```

