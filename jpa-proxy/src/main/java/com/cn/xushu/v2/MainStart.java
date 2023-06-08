package com.cn.xushu.v2;


import com.cn.config.SpringDataJPAConfig;
import com.cn.pojo.CustomerTwo;
import com.cn.repositories.CustomerTwoRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class MainStart {

    public static void main(String[] args) throws ClassNotFoundException {
        // spring上下文   spring容器   --->  ioc加载过程：创建所有的bean  包括Repository的Bean
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringDataJPAConfig.class);

        CustomerTwoRepository repository = ioc.getBean(CustomerTwoRepository.class);

        System.out.println(repository.getClass());   //jdk动态
        Optional<CustomerTwo> byId = repository.findById(1L);

        System.out.println(byId.get());


    }
}
