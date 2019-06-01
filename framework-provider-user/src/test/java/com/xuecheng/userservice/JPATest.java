package com.xuecheng.userservice;

import com.xuecheng.entities.*;
import org.hibernate.jpa.QueryHints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPATest {



    @Autowired
    private EntityManager entityManager;


    /*
    find
    返回指定的 OID 对应的实体类对象，如果这个实体存在于当前的持久化环境，则返回一个被缓存的对象；否则会创建一个新的 Entity, 并加载数据库中相关信息；若 OID 不存在于数据库中，则返回一个 null。第一个参数为被查询的实体类类型，第二个参数为待查找实体的主键值。
     */
    @Test
    @Transactional
    public void test01(){
        Customer customer = entityManager.find(Customer.class,1);
        System.out.println(customer.getInfo());
    }
    /*
与find()方法类似，不同的是：如果缓存中不存在指定的 Entity, EntityManager 会创建一个 Entity 类的代理，但是不会立即加载数据库中的信息，只有第一次真正使用此 Entity 的属性才加载，所以如果此 OID 在数据库不存在，getReference() 不会返回 null 值, 而是抛出EntityNotFoundException
     */
    @Test
    @Transactional
    public void test02(){
        Customer customer = entityManager.find(Customer.class,1);
        System.out.println(customer.getInfo());
    }

    /*
    删除实例。如果实例是被管理的，即与数据库实体记录关联，则同时会删除关联的数据库记录。
     */
    @Test
    @Transactional
    @Rollback(false)//防止事务自动回滚
    public void test03(){
        Customer customer = entityManager.find(Customer.class,4);
        entityManager.remove(customer);
    }

    /*
    merge() 用于处理 Entity 的同步。即数据库的插入和更新操作
     */
    @Test
    @Transactional()
    @Rollback(false)//防止事务自动回滚
    public void test04(){
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("cc@163.com");
        customer.setLastName("CC");
        entityManager.persist(customer);
        System.out.println("customer#id:" + customer.getId());
//        System.out.println("customer2#id:" + customer2.getId());
    }


    /*
同步持久上下文环境，即将持久上下文环境的所有未保存实体的状态信息保存到数据库中。

     */
    @Test
    @Transactional
    public void test05(){
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println(customer);

        customer.setLastName("AA");
        entityManager.flush();
    }


    @Rollback(false)//防止事务自动回滚
    @Transactional
    @Test
    public void testManyToOnePersist(){
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("gg@163.com");
        customer.setLastName("11");

        Order order1 = new Order();
        order1.setOrderName("G-GG-1");

        Order order2 = new Order();
        order2.setOrderName("G-GG-2");
        //设置关联关系
        order1.setCustomer(customer);
        order2.setCustomer(customer);
        entityManager.persist(customer);
        //执行保存操作
        entityManager.persist(order1);
        entityManager.persist(order2);


    }



    //默认情况下, 使用左外连接的方式来获取 n 的一端的对象和其关联的 1 的一端的对象.
    //可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
    @Rollback(false)//防止事务自动回滚
    @Transactional
    @Test
    public void testManyToOneFind(){
        Order order = entityManager.find(Order.class, 9);
        System.out.println(order.getOrderName());
//        System.out.println(order.getCustomer().getLastName());
    }


    //不能直接删除 1 的一端, 因为有外键约束.
    @Test
    @Rollback(false)//防止事务自动回滚
    @Transactional
    public void testManyToOneRemove(){
//		Order order = entityManager.find(Order.class, 1);
//		entityManager.remove(order);

        Customer customer = entityManager.find(Customer.class, 21);
        entityManager.remove(customer);
    }


	@Transactional
    @Test
    @Rollback(false)//防止事务自动回滚
	public void testManyToOneUpdate(){
		Order order = entityManager.find(Order.class, 11);
		order.getCustomer().setLastName("FFF");
	}






    //默认对关联的多的一方使用懒加载的加载策略.
    //可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
    @Transactional
    @Test
    @Rollback(false)//防止事务自动回滚
    public void testOneToManyFind(){
        Customer customer = entityManager.find(Customer.class, 9);
        System.out.println(customer.getLastName());

        System.out.println(customer.getOrders().size());
    }


    //默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行删除.
    //可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
    @Transactional
    @Test
    @Rollback(false)//防止事务自动回滚
    public void testOneToManyRemove(){
        Customer customer = entityManager.find(Customer.class, 3);
        entityManager.remove(customer);
    }

    @Transactional
    @Test
    @Rollback(false)//防止事务自动回滚s
    public void testUpdate(){
        Customer customer = entityManager.find(Customer.class, 21);

        customer.getOrders().iterator().next().setOrderName("123123123");
    }



    //若是双向 1-n 的关联关系, 执行保存时
    //若先保存 n 的一端, 再保存 1 的一端, 默认情况下, 会多出 n 条 UPDATE 语句.
    //若先保存 1 的一端, 则会多出 n 条 UPDATE 语句
    //在进行双向 1-n 关联关系时, 建议使用 n 的一方来维护关联关系, 而 1 的一方不维护关联系, 这样会有效的减少 SQL 语句.
    //注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了.

    //单向 1-n 关联关系执行保存时, 一定会多出 UPDATE 语句.
    //因为 n 的一端在插入时不会同时插入外键列.
    @Transactional
    @Test
    @Rollback(false)//防止事务自动回滚s
    public void testOneToManyPersist(){
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("mm@163.com");
        customer.setLastName("MM");

        Order order1 = new Order();
        order1.setOrderName("O-MM-1");

        Order order2 = new Order();
        order2.setOrderName("O-MM-2");

        //建立关联关系
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        order1.setCustomer(customer);
        order2.setCustomer(customer);

        //执行保存操作
        entityManager.persist(customer);

        entityManager.persist(order1);
        entityManager.persist(order2);
    }



    //双向 1-1 的关联关系, 建议先保存不维护关联关系的一方, 即没有外键的一方, 这样不会多出 UPDATE 语句.
    @Transactional
    @Test
    @Rollback(false)//防止事务自动回滚s
    public void testOneToOnePersistence(){
        Manager mgr = new Manager();
        mgr.setMgrName("M-BB");

        Department dept = new Department();
        dept.setDeptName("D-BB");

        //设置关联关系
        mgr.setDept(dept);
        dept.setMgr(mgr);

        //执行保存操作
        entityManager.persist(mgr);
        entityManager.persist(dept);
    }


    //1. 默认情况下, 若获取不维护关联关系的一方, 则也会通过左外连接获取其关联的对象.
    //可以通过 @OneToOne 的 fetch 属性来修改加载策略. 但依然会再发送 SQL 语句来初始化其关联的对象
    //这说明在不维护关联关系的一方, 不建议修改 fetch 属性.
    @Test
    public void testOneToOneFind2(){
        Manager mgr = entityManager.find(Manager.class, 1);
        System.out.println(mgr.getMgrName());

        System.out.println(mgr.getDept().getMgr().getMgrName());
    }

    //1.默认情况下, 若获取维护关联关系的一方, 则会通过左外连接获取其关联的对象.
    //但可以通过 @OntToOne 的 fetch 属性来修改加载策略.
    @Test
    public void testOneToOneFind(){
        Department dept = entityManager.find(Department.class, 1);
        System.out.println(dept.getDeptName());
        System.out.println(dept.getMgr().getClass().getName());
    }

    //对于关联的集合对象, 默认使用懒加载的策略.
    //使用维护关联关系的一方获取, 还是使用不维护关联关系的一方获取, SQL 语句相同.
    @Transactional
    @Test
    @Rollback(false)//防止事务自动回滚s
    public void testManyToManyFind(){
//		Item item = entityManager.find(Item.class, 5);
//		System.out.println(item.getItemName());
//
//		System.out.println(item.getCategories().size());

        Category category = entityManager.find(Category.class, 3);
        System.out.println(category.getCategoryName());
        System.out.println(category.getItems().size());
    }

    //多对所的保存
    @Transactional
    @Test
    @Rollback(false)//防止事务自动回滚s
    public void testManyToManyPersist(){
        Item i1 = new Item();
        i1.setItemName("i-1");

        Item i2 = new Item();
        i2.setItemName("i-2");

        Category c1 = new Category();
        c1.setCategoryName("C-1");

        Category c2 = new Category();
        c2.setCategoryName("C-2");

        //设置关联关系
        i1.getCategories().add(c1);
        i1.getCategories().add(c2);

        i2.getCategories().add(c1);
        i2.getCategories().add(c2);

        c1.getItems().add(i1);
        c1.getItems().add(i2);

        c2.getItems().add(i1);
        c2.getItems().add(i2);
        entityManager.persist(c1);
        entityManager.persist(c2);
        //执行保存
        entityManager.persist(i1);
        entityManager.persist(i2);
    }


    @Transactional
    @Test
    @Rollback(false)//防止事务自动回滚s
    public void test(){
        entityManager.find(Customer.class,1);
        entityManager.find(Customer.class,1);
        entityManager.find(Customer.class,1);
    }


    @Transactional
    @Rollback(false)//防止事务自动回滚s
    @Test
    public void testHelloJPQL(){
        String jpql = "FROM Customer c WHERE c.age > ?";
        Query query = entityManager.createQuery(jpql);
        //占位符的索引是从 1 开始
        query.setParameter(1, 1);
        List<Customer> customers = query.getResultList();
        System.out.println(customers.size());
    }

    //默认情况下, 若只查询部分属性, 则将返回 Object[] 类型的结果. 或者 Object[] 类型的 List.
    //也可以在实体类中创建对应的构造器, 然后再 JPQL 语句中利用对应的构造器返回实体类的对象.
    @Test
    public void testPartlyProperties(){
        String jpql = "SELECT new Customer(c.lastName, c.age) FROM Customer c WHERE c.age > ?";
        List result = entityManager.createQuery(jpql).setParameter(1, 1).getResultList();

        System.out.println(result);

    }


    //createNamedQuery 适用于在实体类前使用 @NamedQuery 标记的查询语句
    @Test
    public void testNamedQuery(){
        Query query = entityManager.createNamedQuery("testNamedQuery").setParameter(    1,1);
        Customer customer = (Customer) query.getSingleResult();
        System.out.println(customer);
    }

    //createNativeQuery 适用于本地 SQL
    @Test
    public void testNativeQuery(){
        String sql = "SELECT age FROM jpa_cutomers WHERE id = ?";
        Query query = entityManager.createNativeQuery(sql).setParameter(1, 1);
        Object result = query.getSingleResult();
        System.out.println(result);
    }


    //使用 hibernate 的查询缓存.
    @Test
    public void testQueryCache(){
        String jpql = "FROM Customer c WHERE c.age > ?";
        Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);

        //占位符的索引是从 1 开始
        query.setParameter(1, 1);
        List<Customer> customers = query.getResultList();
        System.out.println(customers.size());

        query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);

        //占位符的索引是从 1 开始
        query.setParameter(1, 1);
        customers = query.getResultList();
        System.out.println(customers.size());
    }


    @Test
    public void testOrderBy(){
        String jpql = "FROM Customer c WHERE c.age > ? ORDER BY c.age DESC";
        Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);

        //占位符的索引是从 1 开始
        query.setParameter(1, 1);
        List<Customer> customers = query.getResultList();
        System.out.println(customers.size());
    }


    //查询 order 数量大于 2 的那些 Customer
    @Test
    public void testGroupBy(){
        String jpql = "SELECT o.customer FROM Order o "
                + "GROUP BY o.customer "
                + "HAVING count(o.id) >= 2";
        List<Customer> customers = entityManager.createQuery(jpql).getResultList();

        System.out.println(customers);
    }

    /**
     * JPQL 的关联查询同 HQL 的关联查询.
     */
    @Test
    public void testLeftOuterJoinFetch(){
        String jpql = "FROM Customer c LEFT OUTER JOIN FETCH c.orders WHERE c.id = ?";

        Customer customer =
                (Customer) entityManager.createQuery(jpql).setParameter(1, 12).getSingleResult();
        System.out.println(customer.getLastName());
        System.out.println(customer.getOrders().size());

//		List<Object[]> result = entityManager.createQuery(jpql).setParameter(1, 12).getResultList();
//		System.out.println(result);
    }



    @Test
    public void testSubQuery(){
        //查询所有 Customer 的 lastName 为 YY 的 Order
        String jpql = "SELECT o FROM Order o "
                + "WHERE o.customer = (SELECT c FROM Customer c WHERE c.lastName = ?)";

        Query query = entityManager.createQuery(jpql).setParameter(1, "YY");
        List<Order> orders = query.getResultList();
        System.out.println(orders.size());
    }


    //使用 jpql 内建的函数
    @Test
    public void testJpqlFunction(){
        String jpql = "SELECT lower(c.email) FROM Customer c";

        List<String> emails = entityManager.createQuery(jpql).getResultList();
        System.out.println(emails);
    }


    //可以使用 JPQL 完成 UPDATE 和 DELETE 操作.
    @Transactional
    @Test
    @Rollback(false)//防止事务自动回滚s
    public void testExecuteUpdate(){
        String jpql = "UPDATE Customer c SET c.lastName = ? WHERE c.id = ?";
        Query query = entityManager.createQuery(jpql).setParameter(1, "YYY").setParameter(2, 12);
        query.executeUpdate();
    }

}
