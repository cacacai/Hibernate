---


---
> 转载自http://wiki.jikexueyuan.com/project/hibernate/
<p>在上一篇中学会了使用session来操作缓存以及数据库，现在来学习更详细的数据库操作。</p>
<h1 id="hql查询">HQL查询</h1>
<p>Hibernate 查询语言（HQL）是一种面向对象的查询语言，类似于 SQL，但不是去对表和列进行操作，而是面向对象和它们的属性。 HQL 查询被 Hibernate 翻译为传统的 SQL 查询从而对数据库进行操作。</p>
<p>尽管你能直接使用本地 SQL 语句，但我还是建议你尽可能的使用 HQL 语句，以避免数据库关于可移植性的麻烦，并且体现了 Hibernate 的 SQL 生成和缓存策略。</p>
<p>在 HQL 中一些关键字比如 SELECT ，FROM 和 WHERE 等，是不区分大小写的，但是一些属性比如表名和列名是区分大小写的。</p>
<h2 id="from-语句">FROM 语句</h2>
<p>如果你想要在存储中加载一个完整并持久的对象,你将使用  <strong>FROM</strong>  语句。以下是 FROM 语句的一些简单的语法：</p>
<pre><code>String hql = "FROM Employee";
Query query = session.createQuery(hql);
List results = query.list();
</code></pre>
<p>如果你需要在 HQL 中完全限定类名，只需要指定包和类名，如下：</p>
<pre><code>String hql = "FROM com.hibernatebook.criteria.Employee";
Query query = session.createQuery(hql);
List results = query.list();
</code></pre>
<h2 id="as-语句">AS 语句</h2>
<p>在 HQL 中  <strong>AS</strong>  语句能够用来给你的类分配别名，尤其是在长查询的情况下。例如，我们之前的例子，可以用如下方式展示：</p>
<pre><code>String hql = "FROM Employee AS E";
Query query = session.createQuery(hql);
List results = query.list();
</code></pre>
<p>关键字  <strong>AS</strong>  是可选择的并且你也可以在类名后直接指定一个别名，如下：</p>
<pre><code>String hql = "FROM Employee E";
Query query = session.createQuery(hql);
List results = query.list();
</code></pre>
<h2 id="select-语句">SELECT 语句</h2>
<p><strong>SELECT</strong>  语句比 from 语句提供了更多的对结果集的控制。如果你只想得到对象的几个属性而不是整个对象你需要使用 SELECT 语句。下面是一个 SELECT 语句的简单语法示例，这个例子是为了得到 Employee 对象的 first_name 字段：</p>
<pre><code>String hql = "SELECT E.firstName FROM Employee E";
Query query = session.createQuery(hql);
List results = query.list();
</code></pre>
<p>值得注意的是  <strong>Employee.firstName</strong>  是 Employee 对象的属性，而不是一个 EMPLOYEE 表的字段。</p>
<h2 id="where-语句">WHERE 语句</h2>
<p>如果你想要精确地从数据库存储中返回特定对象，你需要使用 WHERE 语句。下面是 WHERE 语句的简单语法例子：</p>
<pre><code>String hql = "FROM Employee E WHERE E.id = 10";
Query query = session.createQuery(hql);
List results = query.list();
</code></pre>
<h2 id="order-by-语句">ORDER BY 语句</h2>
<p>为了给 HSQ 查询结果进行排序，你将需要使用  <strong>ORDER BY</strong>  语句。你能利用任意一个属性给你的结果进行排序，包括升序或降序排序。下面是一个使用 ORDER BY 语句的简单示例：</p>
<pre><code>String hql = "FROM Employee E WHERE E.id &gt; 10 ORDER BY E.salary DESC";
Query query = session.createQuery(hql);
List results = query.list();
</code></pre>
<p>如果你想要给多个属性进行排序，你只需要在 ORDER BY 语句后面添加你要进行排序的属性即可，并且用逗号进行分割：</p>
<pre><code>String hql = "FROM Employee E WHERE E.id &gt; 10 " +
             "ORDER BY E.firstName DESC, E.salary DESC ";
Query query = session.createQuery(hql);
List results = query.list();
</code></pre>
<h2 id="group-by-语句">GROUP BY 语句</h2>
<p>这一语句允许 Hibernate 将信息从数据库中提取出来，并且基于某种属性的值将信息进行编组,通常而言,该语句会使用得到的结果来包含一个聚合值。下面是一个简单的使用 GROUP BY 语句的语法:</p>
<pre><code>String hql = "SELECT SUM(E.salary), E.firtName FROM Employee E " +
             "GROUP BY E.firstName";
Query query = session.createQuery(hql);
List results = query.list();
</code></pre>
<h2 id="使用命名参数">使用命名参数</h2>
<p>Hibernate 的 HQL 查询功能支持命名参数。这使得 HQL 查询功能既能接受来自用户的简单输入，又无需防御 SQL 注入攻击。下面是使用命名参数的简单的语法:</p>
<pre><code>String hql = "FROM Employee E WHERE E.id = :employee_id";
Query query = session.createQuery(hql);
query.setParameter("employee_id",10);
List results = query.list();
</code></pre>
<h2 id="update-语句">UPDATE 语句</h2>
<p>HQL Hibernate 3 较 HQL Hibernate 2，新增了批量更新功能和选择性删除工作的功能。查询接口包含一个 executeUpdate() 方法，可以执行 HQL 的 UPDATE 或 DELETE 语句。</p>
<p><strong>UPDATE</strong>  语句能够更新一个或多个对象的一个或多个属性。下面是使用 UPDATE 语句的简单的语法:</p>
<pre><code>String hql = "UPDATE Employee set salary = :salary "  + 
             "WHERE id = :employee_id";
Query query = session.createQuery(hql);
query.setParameter("salary", 1000);
query.setParameter("employee_id", 10);
int result = query.executeUpdate();
System.out.println("Rows affected: " + result);
</code></pre>
<h2 id="delete-语句">DELETE 语句</h2>
<p><strong>DELETE</strong>  语句可以用来删除一个或多个对象。以下是使用 DELETE 语句的简单语法：</p>
<pre><code>String hql = "DELETE FROM Employee "  + 
             "WHERE id = :employee_id";
Query query = session.createQuery(hql);
query.setParameter("employee_id", 10);
int result = query.executeUpdate();
System.out.println("Rows affected: " + result);
</code></pre>
<h2 id="insert-语句">INSERT 语句</h2>
<p>HQL 只有当记录从一个对象插入到另一个对象时才支持  <strong>INSERT INTO</strong>  语句。下面是使用 INSERT INTO 语句的简单的语法:</p>
<pre><code>String hql = "INSERT INTO Employee(firstName, lastName, salary)"  + 
             "SELECT firstName, lastName, salary FROM old_employee";
Query query = session.createQuery(hql);
int result = query.executeUpdate();
System.out.println("Rows affected: " + result);
</code></pre>
<h2 id="聚合方法">聚合方法</h2>
<p>HQL 类似于 SQL，支持一系列的聚合方法,它们以同样的方式在 HQL 和 SQL 中工作，以下列出了几种可用方法：</p>

<table>
<thead>
<tr>
<th>S.N.方法</th>
<th>描述</th>
</tr>
</thead>
<tbody>
<tr>
<td>1</td>
<td>avg(property name)属性的平均值</td>
</tr>
<tr>
<td>2</td>
<td>count(property name or *)属性在结果中出现的次数</td>
</tr>
<tr>
<td>3</td>
<td>max(property name)属性值的最大值</td>
</tr>
<tr>
<td>4</td>
<td>min(property name)属性值的最小值</td>
</tr>
<tr>
<td>5</td>
<td>sum(property name)属性值的总和</td>
</tr>
</tbody>
</table><p><strong>distinct</strong>  关键字表示只计算行集中的唯一值。下面的查询只计算唯一的值:</p>
<pre><code>String hql = "SELECT count(distinct E.firstName) FROM Employee E";
Query query = session.createQuery(hql);
List results = query.list();
//distinct 为不重复
</code></pre>
<h2 id="使用分页查询">使用分页查询</h2>
<p>以下为两种分页查询界面的方法：</p>

<table>
<thead>
<tr>
<th>S.N.</th>
<th>方法&amp;描述</th>
</tr>
</thead>
<tbody>
<tr>
<td>1</td>
<td>Query setFirstResult(int startPosition)  该方法以一个整数表示结果中的第一行,从 0 行开始。</td>
</tr>
<tr>
<td>2</td>
<td>Query setMaxResults(int maxResult)  这个方法告诉 Hibernate 来检索固定数量，即  <strong>maxResults</strong>  个对象。</td>
</tr>
</tbody>
</table><p>使用以上两种方法，我们可以在我们的 web 或 Swing 应用程序中构造一个分页组件。下面是示例,您可以扩展到每次取 10 行:</p>
<pre><code>String hql = "FROM Employee";
Query query = session.createQuery(hql);
query.setFirstResult(1);
query.setMaxResults(10);
List results = query.list();
</code></pre>
<h1 id="原生sql查询">原生SQL查询</h1>
<p>如果你想使用数据库特定的功能如查询提示或 Oracle 中的 CONNECT 关键字的话，你可以使用原生 SQL 数据库来表达查询。Hibernate 3.x 允许您为所有的创建，更新，删除，和加载操作指定手写 SQL ，包括存储过程。<br>
您的应用程序会在会话界面用  <strong>createSQLQuery()</strong>  方法创建一个原生 SQL 查询：</p>
<pre><code>public SQLQuery createSQLQuery(String sqlString) throws HibernateException
</code></pre>
<p>当你通过一个包含 SQL 查询的 createsqlquery() 方法的字符串时，你可以将 SQL 的结果与现有的 Hibernate 实体，一个连接，或一个标量结果分别使用 addEntity(), addJoin(), 和 addScalar() 方法进行关联。</p>
<h2 id="标量查询">标量查询</h2>
<p>最基本的 SQL 查询是从一个或多个列表中获取一个标量（值）列表。以下是使用原生 SQL 进行获取标量的值的语法：</p>
<pre><code>String sql = "SELECT first_name, salary FROM EMPLOYEE";
SQLQuery query = session.createSQLQuery(sql);
query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
List results = query.list();
</code></pre>
<h2 id="实体查询">实体查询</h2>
<p>以上的查询都是关于返回标量值的查询，只是基础性地返回结果集中的“原始”值。以下是从原生 SQL 查询中通过 addEntity() 方法获取实体对象整体的语法：</p>
<pre><code>String sql = "SELECT * FROM EMPLOYEE";
SQLQuery query = session.createSQLQuery(sql);
query.addEntity(Employee.class);
List results = query.list(); 
</code></pre>
<h2 id="指定-sql-查询">指定 SQL 查询</h2>
<p>以下是从原生 SQL 查询中通过 addEntity() 方法和使用指定 SQL 查询来获取实体对象整体的语法：</p>
<pre><code>String sql = "SELECT * FROM EMPLOYEE WHERE id = :employee_id";
SQLQuery query = session.createSQLQuery(sql);
query.addEntity(Employee.class);
query.setParameter("employee_id", 10);
List results = query.list();  
</code></pre>
<h2 id="原生-sql-的例子">原生 SQL 的例子</h2>
<p>考虑下面的 POJO 类：</p>
<pre><code>public class Employee {
   private int id;
   private String firstName; 
   private String lastName;   
   private int salary;  

   public Employee() {}
   public Employee(String fname, String lname, int salary) {
      this.firstName = fname;
      this.lastName = lname;
      this.salary = salary;
   }
   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
   }
   public String getFirstName() {
      return firstName;
   }
   public void setFirstName( String first_name ) {
      this.firstName = first_name;
   }
   public String getLastName() {
      return lastName;
   }
   public void setLastName( String last_name ) {
      this.lastName = last_name;
   }
   public int getSalary() {
      return salary;
   }
   public void setSalary( int salary ) {
      this.salary = salary;
   }
}
</code></pre>
<p>让我们创建以下 EMPLOYEE 表来存储 Employee 对象：</p>
<pre><code>create table EMPLOYEE (
   id INT NOT NULL auto_increment,
   first_name VARCHAR(20) default NULL,
   last_name  VARCHAR(20) default NULL,
   salary     INT  default NULL,
   PRIMARY KEY (id)
);

</code></pre>
<p>以下是映射文件：</p>
<pre><code>&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;!DOCTYPE hibernate-mapping PUBLIC 
 "-//Hibernate/Hibernate Mapping DTD//EN"
 "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd"&gt; 

&lt;hibernate-mapping&gt;
   &lt;class name="Employee" table="EMPLOYEE"&gt;
      &lt;meta attribute="class-description"&gt;
         This class contains the employee detail. 
      &lt;/meta&gt;
      &lt;id name="id" type="int" column="id"&gt;
         &lt;generator class="native"/&gt;
      &lt;/id&gt;
      &lt;property name="firstName" column="first_name" type="string"/&gt;
      &lt;property name="lastName" column="last_name" type="string"/&gt;
      &lt;property name="salary" column="salary" type="int"/&gt;
   &lt;/class&gt;
&lt;/hibernate-mapping&gt;
</code></pre>
<p>最后，我们将用 main() 方法创建应用程序类来运行应用程序，我们将使用<strong>原生 SQL</strong>  查询：</p>
<pre><code>import java.util.*; 

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      ManageEmployee ME = new ManageEmployee();

      /* Add few employee records in database */
      Integer empID1 = ME.addEmployee("Zara", "Ali", 2000);
      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
      Integer empID3 = ME.addEmployee("John", "Paul", 5000);
      Integer empID4 = ME.addEmployee("Mohd", "Yasee", 3000);

      /* List down employees and their salary using Scalar Query */
      ME.listEmployeesScalar();

      /* List down complete employees information using Entity Query */
      ME.listEmployeesEntity();
   }
   /* Method to CREATE an employee in the database */
   public Integer addEmployee(String fname, String lname, int salary){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer employeeID = null;
      try{
         tx = session.beginTransaction();
         Employee employee = new Employee(fname, lname, salary);
         employeeID = (Integer) session.save(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return employeeID;
   }

   /* Method to  READ all the employees using Scalar Query */
   public void listEmployeesScalar( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "SELECT first_name, salary FROM EMPLOYEE";
         SQLQuery query = session.createSQLQuery(sql);
         query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
         List data = query.list();

         for(Object object : data)
         {
            Map row = (Map)object;
            System.out.print("First Name: " + row.get("first_name")); 
            System.out.println(", Salary: " + row.get("salary")); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }

   /* Method to  READ all the employees using Entity Query */
   public void listEmployeesEntity( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         String sql = "SELECT * FROM EMPLOYEE";
         SQLQuery query = session.createSQLQuery(sql);
         query.addEntity(Employee.class);
         List employees = query.list();

         for (Iterator iterator = 
                           employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next(); 
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary()); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
}
</code></pre>

