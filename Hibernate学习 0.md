

# Hibernate（开放源代码的对象关系映射框架）
[TOC]
## 简介
Hibernate是一个开放源代码的对象关系映射框架，它对JDBC进行了非常轻量级的对象封装，它将POJO与数据库表建立映射关系，是一个全自动的orm框架，hibernate可以自动生成SQL语句，自动执行，使得Java程序员可以随心所欲的使用对象编程思维来操纵数据库。 Hibernate可以应用在任何使用JDBC的场合，既可以在Java的客户端程序使用，也可以在Servlet/JSP的Web应用中使用，最具革命意义的是，Hibernate可以在应用EJB的J2EE架构中取代CMP，完成数据持久化的重任。

##  Hibernate工作原理
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/67943209.jpg)
![](http://markdownpic.oss-cn-shenzhen.aliyuncs.com/18-4-21/67943209.jpg)
1. 配置好hibernate的配置文件和与类对应的配置文件后，启动服务器

2. 服务器通过实例化Configeration对象，读取hibernate.cfg.xml文件的配置内容，并根据相关的需求建好表或者和表建立好映射关系

3. 通过实例化的Configeration对象就可以建立sessionFactory实例，进一步，通过sessionFactory实例可以创建session对象

4. 得到session之后，便可以对数据库进行增删改查操作了，除了比较复杂的全文搜索外，简单的操作都可以通过hibernate封装好的session内置方法来实现

5. 此外，还可以通过事物管理，表的关联来实现较为复杂的数据库设计

> 优点：hibernate相当于java类和数据库表之间沟通的桥梁，通过这座桥我们就可以做很多事情了

### Hibernate是如何连接数据库
主要是通过hibernate.cfg.xml配置文件中的配置

在这个文件中定义了数据库进行连接所需要的信息，包括JDBC驱动、用户名、密码、数据库方言等，configuration类借助dom4j的XML解析器解析设置环境，然后使用这些环境属性来生成 SessionFactory。这样这个sessionFactory生成的session就能成功获得数据库的连接。

### Hibernate是如何进行数据库写操作  
对数据库的写操作包括保存、更新和删除，当保存一个POJO持久对象时，触发Hibernate的保存事件监听器

进行处理。Hibernate通过映射文件获得对象对应数据库表名以及属性所对应的表中的列名，然后通过反射机制

持久化对象（实体对象）的各个属性，最终组织成向数据库插入新对象的SQL insert语句。调用了session.save()方法后，这个对象会标识成持久化状态存放在session中，对于Hibernate来说它就是一个持久化了的对象，但这个时候Hibernate还不会真正的执行insert语句，当进行session的刷新同部或事务提交时，Hibernate会把session缓存中的所有SQL语句一起执行，对于更新、删除操作也是采用类似的机制。

然后，提交事务并事务提交成功后，这些写操作就会被永久地保存进数据库中，所以，使用session对数据库操作还依赖于Hibernate事务的处理。如果设置了二级缓存，那么这些操作会被同步到二级缓存中，Hibernate对数据库最终操作也是依赖于底层JDBC对数据库进行。

### Hibernate 如何从数据库中载入对象

当使用session.load()载入对象时，可以设置是否采用延迟加载，如果延迟加载，那么load返回的对象实际是CGLIB或javassist返回的代理类，它的非主键属性都是空的，这对于对象集合属性很有效。 Hibernate以此来节约内存，当真正需要读取对象时，Hibernate会先尝试从session缓存中读取，如果session缓存中数据不存在或者是脏数据并且配置了二级缓存，Hibernate尝试从二级缓存中检索数据，否则Hibernate会根据对象类型，主键等信息组织select语句到数据中读取，再把select结果组织成对象返回。

### Hibernate如何进行数据库查询操作

Hibernate提供SQL HQL Criteria查询方式。HQL是其中运用最广泛的查询方式。用户使用session.createQuery()方法以一条HQL语句为参数创建 Query查询对象后，Hibernate会使用Anltr库把HQL语句解析成JDBC可以识别的SQL语句，如果设置了查询缓存，那么执行 Query.list()时，Hibernate会先对查询缓存进行查询，如果查询缓存不存在，再使用select语句查询数据库。
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTk1NDk3NTQyXX0=
-->