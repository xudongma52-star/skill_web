package com.max.myskills.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
    @Entity
    @Table(name = "knowledge_article")
    public class KnowledgeArticle {
        /**
         * 主键ID
         * @Id:声明主键字段
         * @GenerateValue:指定主键的生成策略
         * -GenerationType.IDENTITY告诉数据库这是一个自增主键
         */
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        /**
         * 技能名称
         *
         * @Column：用于更详细的定义字段属性
         * name是数据库的字段名
         * nullabel=false 对应数据库的非空约束
         */
        @Column(name = "skill_name",nullable = false)
        private String skillName;

        /**
         * 技能内容
         * name = "content"可以省略
         * columnDefinittion = "TEXT"明确指定字段在数据库对应TEXT类型，用于储存长文本
         */
        @Column(name = "content",columnDefinition = "TEXT" ,nullable = false)
        private String content;

        /**
         * name是category，可以省略
         * nullable=false是非空
         */
        @Column(name = "category",nullable = false )
        private String category;

        /**
         *创建时间
         * @CreationTimestamp每当实体被保存，自动将时间戳赋值给该字段
         */
        @CreationTimestamp
        @Column(name = "create_time")
        private LocalDateTime createTime;

        /**
         * 修改时间
         * @UpdateTimestamp每当实体被保存或者修改，自动将时间戳赋值给该字段
         */
        @UpdateTimestamp
        @Column(name = "update_time")
        private LocalDateTime updateTime;
    }


