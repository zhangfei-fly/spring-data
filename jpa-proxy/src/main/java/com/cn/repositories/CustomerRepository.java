package com.cn.repositories;

import com.cn.pojo.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {

}
