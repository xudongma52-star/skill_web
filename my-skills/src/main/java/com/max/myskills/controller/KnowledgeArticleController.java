package com.max.myskills.controller;

import com.max.myskills.security.JwtTokenProvider;
import com.max.myskills.service.KnowledgeArticleService;
import com.max.myskills.entity.KnowledgeArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//声明RESTFUL风格的控制器，所有方法默认JSON
@RequestMapping("/api/articles")//为这个控制器下的所有API设置一个公共的URL前缀
@CrossOrigin//允许不同源的跨域请求否则vue会无法访问
public class KnowledgeArticleController {
    @Autowired
    private KnowledgeArticleService knowledgeArticleService;
    private JwtTokenProvider jwtTokenProvider;
    /**
     * API 1:获取所有文章
     * HTTP 方法：GET
     * URL：/api/articles
     * @return
     */
    @GetMapping
    public List<KnowledgeArticle> getAllArticles(){
        return knowledgeArticleService.findAll();
    }

    /**
     * API 2:根据id获取文章
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<KnowledgeArticle> getArticleById(@PathVariable Long id){
        KnowledgeArticle article = knowledgeArticleService.findById(id);
        return ResponseEntity.ok().body(article);
    }

    /**
     * API3: 创建新文章
     * @param article
     * @return
     */
    @PostMapping
    public KnowledgeArticle createArticle(@RequestBody KnowledgeArticle article) {
        // @RequestBody 会自动将请求体中的 JSON 转换成 KnowledgeArticle 对象
        return knowledgeArticleService.createArticle(article);
    }

    /**
     * API 4:更新文章
     * @param
     * @return
     */
    @PutMapping("/{id}")//更新通常用PutMapping而不是PostMapping
    public ResponseEntity<KnowledgeArticle> updateArticle(@PathVariable Long id,@RequestBody KnowledgeArticle articleDetails) {
        KnowledgeArticle updateArticle = knowledgeArticleService.updateArticle(id,articleDetails);
        return ResponseEntity.ok().body(updateArticle);
    }

    @DeleteMapping("/{id}")//删除用DeleteMapping
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        knowledgeArticleService.deleteById(id);
        return ResponseEntity.ok().build();// 删除成功后，通常返回一个没有内容的 204 No Content 或 200 OK 响应
    }
}
