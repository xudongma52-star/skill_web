package com.max.myskills.service;

import com.max.myskills.entity.KnowledgeArticle;
import com.max.myskills.repository.KnowledgeArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeArticleService {
    @Autowired
    private KnowledgeArticleRepository knowledgeArticleRepository;

    /**
     * 功能一：获取所有知识文章
     * @return
     */
    public List<KnowledgeArticle> findAll(){
        return knowledgeArticleRepository.findAll();
    }

    /**
     * 功能二：根据id查询文章
     * @param id 文章id
     * @return
     */
    public KnowledgeArticle findById(Long id){
        Optional<KnowledgeArticle> optionalArticle = knowledgeArticleRepository.findById(id);
        if(optionalArticle.isPresent()){
            return optionalArticle.get();
        }else{
            throw new EntityNotFoundException("Knowledge article with id " + id + " not found");
        }
    }

    /**
     * 功能三：保存文章
     * @param article 要保存的文章对象
     * @return
     */
    public KnowledgeArticle createArticle(KnowledgeArticle article){
        // 调用 repository 的 save 方法，将数据持久化到数据库
        return knowledgeArticleRepository.save(article);
    }

    /**
     * 功能四：更新一篇文章
     * @param id 更新文章的id
     * @param articleDetails 包含新数据的文章对象
     * @return
     */
    public KnowledgeArticle updateArticle(Long id,KnowledgeArticle articleDetails){
        Optional<KnowledgeArticle> optionalArticle = knowledgeArticleRepository.findById(id);
        //如果对象存在
        if(optionalArticle.isPresent()){
            //获取原始文章对象
            KnowledgeArticle existsArticle = optionalArticle.get();
            existsArticle.setSkillName(articleDetails.getSkillName());
            existsArticle.setContent(articleDetails.getContent());
            existsArticle.setCategory(articleDetails.getCategory());

            return knowledgeArticleRepository.save(existsArticle);
        }else {
            return null;
        }
    }

    public void deleteById(Long id){
        knowledgeArticleRepository.deleteById(id);
    }

}
