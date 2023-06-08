package com.cn;

import com.cn.config.SpringDataJPAConfig;
import com.cn.pojo.Customer;
import com.cn.pojo.Role;
import com.cn.repositories.CustomerRepository;
import com.cn.repositories.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@ContextConfiguration(classes = SpringDataJPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ManyToManyTest {
    @Autowired
    CustomerRepository repository;
    @Autowired
    RoleRepository roleRepository;

    // 保存
    /*
    1.如果保存的关联数据  希望使用已有的 ，就需要从数据库中查出来（持久状态）。否则 提示 游离状态不能持久化
    2.如果一个业务方法有多个持久化操作， 记得加上@Transactional ，否则不能共用一个session
    3. 在单元测试中用到了@Transactional , 如果有增删改的操作一定要加@Commit
    4. 单元测试会认为你的事务方法@Transactional，  只是测试而已， 它不会为你提交事务，  需要单独加上 @Commit
     */
    @Test
    @Transactional
    @Commit
    public void testC() {

        List<Role> roles=new ArrayList<>();
        roles.add(roleRepository.findById(3L).get());
        roles.add(roleRepository.findById(4L).get());

        Customer customer = new Customer();
        customer.setCustName("诸葛");
        customer.setRoles(roles);

        repository.save(customer);
    }

    @Test
    public void testI() {

        List<Role> roles=new ArrayList<>();
        roles.add(new Role("超级管理员"));
        roles.add(new Role("商品管理员"));

        Customer customer = new Customer();
        customer.setCustName("诸葛");
        customer.setRoles(roles);

        repository.save(customer);
    }


    @Test
    @Transactional(readOnly = true)
    public void testR() {


        System.out.println(repository.findById(14L));

        //repository.save(customer);
    }


    /*
    * 注意加上
    * @Transactional
        @Commit
      多对多其实不适合删除， 因为经常出现数据出现可能除了和当前这端关联还会关联另一端，此时删除就会： ConstraintViolationException。
      * 要删除， 要保证没有额外其他另一端数据关联
    * */
    @Test
    @Transactional
    @Commit
    public void testD() {

        Optional<Customer> customer = repository.findById(1L);

        repository.delete(customer.get());
    }
}
