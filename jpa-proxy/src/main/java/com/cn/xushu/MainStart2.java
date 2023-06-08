package com.cn.xushu;

import com.cn.config.SpringDataJPAConfig;
import com.cn.pojo.Customer;
import com.cn.repositories.CustomerRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class MainStart2 {

    public static void main(String[] args) throws ClassNotFoundException {
        // spring上下文   spring容器   --->  ioc加载过程：创建所有的bean  包括Repository的Bean
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringDataJPAConfig.class);


        CustomerRepository repository = ioc.getBean(CustomerRepository.class);

        System.out.println(repository.getClass());   //jdk动态
        Optional<Customer> byId = repository.findById(20L);

        System.out.println(byId.get());


    }
}
