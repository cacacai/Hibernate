# hibernate之session
[TOC]
Session 用于获取与数据库的物理连接。 Session 对象是轻量级的，并且设计为在每次需要与数据库进行交互时被实例化。持久态对象被保存，并通过 Session 对象检索找回。

该 Session 对象不应该长时间保持开放状态，因为它们通常不能保证线程安全，而应该根据需求被创建和销毁。Session 的主要功能是为映射实体类的实例提供创建，读取和删除操作。这些实例可能在给定时间点时存在于以下三种状态之一：

-   **瞬时状态(transient)**: 一种新的持久性实例，被 Hibernate 认为是瞬时的，它不与 Session 相关联，在数据库中没有与之关联的记录且无标识符值。
-   **持久状态(persistent)**：可以将一个瞬时状态实例通过与一个 Session 关联的方式将其转化为持久状态实例。持久状态实例在数据库中没有与之关联的记录，有标识符值，并与一个 Session 关联。
-   **脱管状态(detached)**：一旦关闭 Hibernate Session，持久状态实例将会成为脱管状态实例。

若 Session 实例的持久态类别是序列化的，则该 Session 实例是序列化的。一个典型的事务应该使用以下语法：

```
Session session = factory.openSession();\
//Session session = factory.getSession();
Transaction tx = null;
try {
   tx = session.beginTransaction();
   // do some work
   ...
   tx.commit();
}
catch (Exception e) {
   if (tx!=null) tx.rollback();
   e.printStackTrace(); 
}finally {
   session.close();
}
```
如果 Session 引发异常，则事务必须被回滚，该 session 必须被丢弃。

## Session 接口方法

**Session**  接口提供了很多方法，以下只是仅列出几个重要方法。您可以查看 Hibernate 文件，查询与  **Session**  及  **SessionFactory**  相关的完整方法目录。

序号 |Session 方法及说明
--------|--------
1|**Transaction beginTransaction()**  开始工作单位，并返回关联事务对象。
2|**void cancelQuery()**  取消当前的查询执行。
3|**void clear()**  完全清除该会话。
4|**Connection close()**  通过释放和清理 JDBC 连接以结束该会话。
5|**Criteria createCriteria(Class persistentClass)**  为给定的实体类或实体类的超类创建一个新的 Criteria 实例。
6|**Criteria createCriteria(String entityName)**  为给定的实体名称创建一个新的 Criteria 实例。
7|**Serializable getIdentifier(Object object)**  返回与给定实体相关联的会话的标识符值。
8|**Query createFilter(Object collection, String queryString)**  为给定的集合和过滤字符创建查询的新实例。
9|**Query createQuery(String queryString)**  为给定的 HQL 查询字符创建查询的新实例。
10|**SQLQuery createSQLQuery(String queryString)**  为给定的 SQL 查询字符串创建 SQLQuery 的新实例。
11|**void delete(Object object)**  从数据存储中删除持久化实例。
12|**void delete(String entityName, Object object)**  从数据存储中删除持久化实例。
13|**Session get(String entityName, Serializable id)**  返回给定命名的且带有给定标识符或 null 的持久化实例（若无该种持久化实例）。
14|**SessionFactory getSessionFactory()**  获取创建该会话的 session 工厂。
15|**void refresh(Object object)**  从基本数据库中重新读取给定实例的状态。
16|**Transaction getTransaction()**  获取与该 session 关联的事务实例。
17|**boolean isConnected()**  检查当前 session 是否连接。
18|**boolean isDirty()**  该 session 中是否包含必须与数据库同步的变化？
19|**boolean isOpen()**  检查该 session 是否仍处于开启状态。
**20**|**Serializable save(Object object)**  先分配一个生成的标识以保持给定的瞬时状态实例。
21|**void saveOrUpdate(Object object)**  保存（对象）或更新（对象）给定的实例。
22|**void update(Object object)**  更新带有标识符且是给定的处于脱管状态的实例的持久化实例。
23|**void update(String entityName, Object object)**  更新带有标识符且是给定的处于脱管状态的实例的持久化实例。

- Session的save()方法:
Session的save()方法使一个临时对象转变为持久化对象
(1)将临时对象加入到Session缓存中，使其进入持久化状态。  
(2)选用映射文件指定的标识符生成器，为持久化对象分配唯一的ID。  
(3)计划执行一个insert语句
注意：session的save()方法是用来持久化临时对象的。不应将持久化对象或游离对象传递给save()方法

> 若将持久化对象传递给save()方法，则该步保存操作是多余的。  
若将游离对象传递给save()方法，则会重新生成ID，再保存一次

- Session的update()方法:
Session的update()方法使一个脱管对象转变为持久化对象。它完成以下操作：  
(1)将游离对象加入到Session缓存中，使其转变为持久化对象。  
(2)计划执行一个update语句。  

- Session的saveOrUpdate()方法：
Session的saveOrUpdate()方法同时包含了save()方法与update()方法的功能，如果传入的参数是临时对象，就调用save()方法；如果传入的参数是游离对象，就调用update()方法。  

**Session的delete()方法**：  

Session的delete()方法用于从数据库中删除一个java对象。delete()方法既可以删除持久化对象，也可以删除脱管对象。其处理过程如下：  
(1)如果传入的参数是脱管对象，则先使脱管对象与Session关联，使它变为持久化对象。如果参数是持久化对象，则忽略该步。  
(2)计划执行一个delete语句。  
(3)把对象从Session缓存中删除，该对象进入删除状态。
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTEwNTM0MjUyODAsMTUxNjc0ODI1MSwtND
EwOTExNjY3LDE4Njk5MzQ0MzUsLTEwODE2MjE1MjFdfQ==
-->