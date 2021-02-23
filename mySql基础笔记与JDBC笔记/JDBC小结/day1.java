import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.time.Instant;
import java.util.Properties;

public class day1 {
    public static void main(String[] args) throws Exception {
        day1 d = new day1();
        d.test7();
    }

    //获取数据库的连接，方式一
    //直接造Driver对象
    //使用Driver.connect(String url, Properties user) 获取连接
    void test1() throws SQLException {
        com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "  jdbc:mysql://localhost:3306/test  ".trim();
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "123456");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    //获取数据库的连接，方式二
    //反射造Driver对象
    //使用Driver.connect(String url, Properties user) 获取连接
    void test2() throws Exception {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        com.mysql.cj.jdbc.Driver driver = (com.mysql.cj.jdbc.Driver) clazz.getDeclaredConstructor().newInstance();
        String url = "  jdbc:mysql://localhost:3306/test  ".trim();
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "123456");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    //获取数据库的连接，方式三
    //反射造Driver对象
    //调用静态方法DriverManager.registerDriver(Driver driver)注册驱动
    //调用静态方法DriverManager.getConnection(String url, String user, String passWord) 获取连接
    void test3() throws Exception {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        com.mysql.cj.jdbc.Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "root", "123456");
        System.out.println(connect);
    }

    //获取数据库的连接，方式四
    //反射造Driver对象
    //调用静态方法DriverManager.registerDriver(Driver driver)注册驱动
    //调用静态方法DriverManager.getConnection(String url, String user, String passWord) 获取连接
    void type4() throws Exception {
        //Driver类中有一个静态代码块，在该Driver类加载时，自动造自己的对象，并注册;
        //DriverManager.registerDriver(new Driver());
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");//可注释，最好不要注释
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "root", "123456");
        System.out.println(connect);
    }

    //获取数据库的连接，方式五
    //数据与代码的分离
    //修改配置文件，不需要重新打包
    void test() throws Exception {
        try {
            //通过配置文件获取数据库连接，方式一
            FileInputStream inputStream = new FileInputStream("JDBC\\src\\jdbc.properties");
            //通过配置文件获取数据库连接，方式二
            InputStream resourceAsStream = day1.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //造properties对象
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            //读取配置文件
            String DriverName = properties.getProperty("DriverName");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            //造连接对象
            Class.forName(DriverName);
            Connection connection = DriverManager.getConnection(url, user, password);

            //Connection.PreparedStatement(String sql)操作数据库
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `order` VALUE (?,?,?);");
            //填充占位符
            //blob类型的填充需要输入流,setBlob(位置,流);
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "e");
            preparedStatement.setDate(3, new Date(Instant.now().toEpochMilli()));
            preparedStatement.execute();
            System.out.println("eee");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//变量声明在try里面，finally找不到。
//        }finally {
//            preparedStatement.close();
//            connection.close();
//            System.out.println(connection);
//        }


    //演示数据库查询
    void test5_1() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from customers");
        //PreparedStatement.executeQuery()执行查询语句，返回ResultSet对象
        ResultSet resultSet = preparedStatement.executeQuery();
        //调用ResultSet.next()
        if (resultSet.next()) {
            //ResultSet.getXX(索引),获取数据,每一行为一个对象，每一列为一个数据.
            //ResultSet.getXX("别名");获取数据的重载方法
            //getBlob()获得的是Blob对象，可以调用该对象的getBinaryStream()方法获得一个输入流;
            customer customer = new customer(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getDate(4), resultSet.getObject(5));
            System.out.println(customer);
        }
    }

    //演示数据库通用查询
    void test5_2() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from customers where id =100");
        //PreparedStatement.executeQuery()执行查询语句，返回ResultSet对象
        ResultSet resultSet = preparedStatement.executeQuery();
        //ResultSe.getMetaData()获取元数据对象,该对象保存了列的名字,总列数等信息
        ResultSetMetaData metaData = resultSet.getMetaData();
        //调用ResultSet.next()查看有无结果
        if (resultSet.next()) {
            //造Customer对象，该对象代表一行信息
            customer customer = new customer();
            //通过MetaData对象获取每列的名字，使用反射找到customer中与该列同名的属性，并将该列的值赋给该属性
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                //通过MetaData对象获取每列的名字
                //若列名和属性名不一直，可以在查询时起别名来进行匹配
                //获得别名的方法为MetaDate.getColumnLabel(int 从左往右第几列),若无别名，则返回列名
                String columnName = metaData.getColumnName(i + 1);
                //使用反射找到customer对象中与该列同名的属性
                Field declaredField = customer.getClass().getDeclaredField(columnName);
                //declaredField.setAccessible(true);
                //并将该列的值赋给该属性
                declaredField.set(customer, resultSet.getObject(i + 1));
            }
            System.out.println(customer);
        }
    }

    //数据库批量操作
    //似乎有毛病，URL后面那个就算是false也没影响...
    void test5_3() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String e = "jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true";
        String e1 = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
        Connection connection = DriverManager.getConnection(e, "root", "123456");
        //Connection.setAutoCommit(false);关闭自动提交
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `aa` value (?);");
        //获取statement方式，batch比prepareStatement方式快一些，prepareStatement的预编译空间有限,可能会出现上限的情况
        //获取statement,Statement statement = connection.createStatement();
        long l1 = System.currentTimeMillis();
        long l = Instant.now().toEpochMilli();
        for (int i = 1; i <= 20000; i++) {
            //preparedStatement.addBatch("INSERT INTO `aa` value ()");
            //开启了rewriteBatchedStatements=true后，statement执行的语句不能加`;`
            //statement.addBatch("INSERT INTO `aa` value ()");
            preparedStatement.setObject(1, i);
            //1."攒"sql
            preparedStatement.addBatch();
            if (i % 500 == 0) {
                //statement.executeBatch();
                //statement.clearBatch();
                //2.执行batch
                preparedStatement.executeBatch();
                //3.清空batch
                preparedStatement.clearBatch();
            }
        }
        //提交数据
        connection.commit();
        System.out.println(System.currentTimeMillis() - l + " " + (System.currentTimeMillis() - l1));
        preparedStatement.execute("TRUNCATE TABLE `aa`");
    }

    //Druid数据库连接池
    Connection test6() throws Exception {
        Properties pos = new Properties();
        //Properties中写配置，具体查看文档.
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        pos.load(systemResourceAsStream);
        //1.造池,使用DruidDataSourceFactory.createDataSource(Properties 数据库连接池配置)获取数据库连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pos);
        //2.造连接,使用DataSource.getConnection()从数据库连接池中获取连接
        Connection connection = dataSource.getConnection();
        return connection;
    }

    //dbUtils工具类使用
    void test7() throws Exception {
        Properties pos = new Properties();
        //Properties中写配置，具体查看文档.
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        pos.load(systemResourceAsStream);

        //第一步，造对象
        //new QueryRunner()造对象，执行语句加要加Connection参数
        //new QueryRunner(DataSource 连接池)造对象,执行语句不需要加Connection参数
        QueryRunner queryRunner = new QueryRunner(DruidDataSourceFactory.createDataSource(pos));

        //第二步，增删改
        //QueryRunner.update(String sql)执行增删改，返回成功个数
        queryRunner.update(test6(), "insert into aa value ()");
        queryRunner.update("truncate table ?","aa");

        //如何查询
        //QueryRunner.query(String 语句,ResultSetHandler 结果集,Object 占位符)
        //ResultSetHandler重写handle方法,该方法的返回值为query()方法的返回值.
        ResultSetHandler<customer> resultSetHandler = new ResultSetHandler() {
            @Override
            //该方法中处理ResultSet.
            public customer handle(ResultSet resultSet) throws SQLException {
                return null;
            }
        };
        queryRunner.query("SELECT id,NAME,email,birth FROM customers WHERE id = 1;", resultSetHandler);

        //如何批量
        //QueryRunner.batch(String 语句, Object[][] 数组的长度为执行的次数，其次为占位符的值),该方法返回执行成功个数.
        queryRunner.batch("insert into aa value ()", new Object[5][]);


    }



    //JDBC大致步骤


    //第一步,造驱动,可省略new Drive()
        //Drive类在加载时自动执行DriverManager.registerDriver(Driver driver)


    //第二步,造连接
        //造连接方式一 Driver.connect(String URL,Properties 账号密码)
        //造连接方式二 DriverManager.getConnection(String url, String 账号, String 密码)
            //Drive类在加载时自动执行DriverManager.registerDriver(Driver driver)
        //造连接方式三 DruidDataSource d = DruidDataSourceFactory.createDataSource(Properties 配置文件)
            //造连接池后,调用d.getConnection()获取连接;  配置文件可对数据库连接池进行配置,其中要必须写URL,账号密码等.


    //第三步,操作数据库的方式一
        //获取PrepareStatement对象,预编译语句对象
            //通过Connection.PreparedStatement(String sql语句)的方式获得PrepareStatement对象
            //占位符<--SQL语句中的?-->可以通过PrepareStatement.Set要填充的类型(int 占位符的索引,要填充的类型 值)的方式填充
        //PrepareStatement对象的增删改操作
            //调用PrepareStatement.execute()可以执行增删改SQL语句
            //重载方法PrepareStatement.execute(String SQL语句)可以执行参数中的SQL语句
        //PrepareStatement对象的查询操作
            //PrepareStatement.executeQuery()可以执行查询语句，并返回结果集ResultSet
            //ResultSet.next()查询结果是否存在,否则下述getMetaData,getXX等方法无法使用.
            //ResultSet.get列的类型(int 列索引)可以获得表中每列的数据,重载方法->get列的类型(String 列名)获得表中每列数据
            //ResultSet.getMetaData()获得ResultSetMetaDate,该元数据对象包含了结果集中每列的名字,别名,以及列的总个数等信息
        //PrepareStatement对象的批量执行操作
            //PrepareStatement.addBatch(),PrepareStatement.executeBatch(),PrepareStatement.clearBatch()
            //批量执行注意事项,URL中要开启rewriteBatchedStatements=true,事物最好关闭自动提交


    //第三步,操作数据库的方式二,dbUtils工具类的使用
        //获取QueryRunner对象
            //QueryRunner q = new QueryRunner() 或者 把<数据库连接池>作为参数传入
            //若为把<数据库连接池>作为参数传入,则下列方法需要额外传入连接参数.
        //QueryRunner的增删改操作
            //QueryRunner.update(String Sql,Object... 占位符填充参数)
            //返回影响个数
        //QueryRunner的查询操作
            //QueryRunner.query(String Sql,ResultSetHandler 处理查询结果的对象,Object... 占位符)
            //ResultSetHandler对象重写handle方法,该方法的返回值为query()方法的返回值.
            //该方法中处理查询后的ResultSet.
            //public 处理结果集后要返回的类型 handle(ResultSet resultSet) throws SQLException {}
        //QueryRunner的批量操作
            //QueryRunner.batch(String Sql,Object[要批量的次数][要填充的占位符])
            //该方法返回操作个数


    //DAO（Data Access Object 数据库访问对象）
        //第一步
            //BaseDAO,其中封装基本增删改查功能。
        //第二步
            //针对表写一个xxDAO规范接口
        //第三步
            //xxDAOImpl实现接口,继承BaseDAO,并把想要实现的功能写在该类中
        //第四步
            //每次调用xxDAOImpl的方法操作数据库时,要把连接作为参数传入
            //考虑上事物的并发问题,每次执行方法时首先把事物自动提交关闭,执行成功后再开启
            //设置隔离级别,只对当前会话有效Connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

    //连接需要关闭
    //事物不提交，连接结束时会回滚.

    //ORM的编程思想,意思就是表的每行数据代表一个对象↓
    class customer {
        int id;
        String name;
        String email;
        Date birth;
        Object photo;

        public customer(int id, String name, String email, Date birth, Object photo) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.birth = birth;
            this.photo = photo;
        }

        public customer() {
        }


        @Override
        public String toString() {
            return "customer{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", birth=" + birth +
                    ", photo=" + photo +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

}
