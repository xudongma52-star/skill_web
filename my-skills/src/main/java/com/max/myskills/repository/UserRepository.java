package com.max.myskills.repository;


import com.max.myskills.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // 推荐使用 Optional 来处理可能为空的结果
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     * 根据用户名查找用户
     * Spring Data JPA 会根据方法名自动生成查询
     * @param username 用户名
     * @return 可能存在也可能不存在的用户，所以用 Optional 包装
     */
    Optional<User> findByUserName(String username);

    /**
     * 检查用户名是否存在 (这也是一种很高效的方式)
     * @param userName 用户名
     * @return 如果存在返回 true，否则返回 false
     */


    boolean existsByUserName(String userName);
}
