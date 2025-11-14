package com.max.myskills.repository;

import com.max.myskills.entity.KnowledgeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 知识文章的仓库接口
 * JpaRepository 提供了大量的内置方法用于 CRUD 操作。
 * 我们只需要继承它，就可以免费获得这些功能。
 */
@Repository
public interface KnowledgeArticleRepository extends JpaRepository<KnowledgeArticle, Long> {
    // 目前这里是空的，但已经功能非常强大了！
    // Spring Data JPA 会在运行时自动为我们生成这个接口的实现。
}