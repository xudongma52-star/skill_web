<!--src/views/ArticleDetail.vue-->
<template>
    <div class="article-details">
        <div v-if="loading">正在加载</div>
        <div v-if="error" class="error">{{ error }}</div>
        <div v-if="article">
            <h1>{{ article.skillName }}</h1>
            <p><strong>分类：</strong>{{article.category}}</p>
            <p><strong>创建时间</strong>{{new Date(article.createTime).toLocaleString()}}</p>
            <p><strong>更新时间</strong>{{new Date(article.updateTime).toLocaleString()}}</p>
            <hr>
            <div class='content' v-html="article.content"></div>
            <router-link to="/">返回列表</router-link>
        </div>
    </div>
</template>

<script setup >
import{ref ,onMounted} from 'vue';
import api from '@/services/api';
import { useRoute } from 'vue-router';

const article = ref(null);
const loading = ref(true);
const error = ref(null);
const route = useRoute();
const articleId = route.params.id;

onMounted(async()=>{
    try{
        const response = await api.getArticleById(articleId);
        article.value = response.data;
    }catch(err){
        console.error('获取详情页失败：',err);
        error.value = '无法加载详情页。';
    }finally{
        loading.value = false;
    }
});
</script>

<style scoped>
.article-details{max-width: 800px; margin: 0 auto; padding: 20px;}
.content{margin-top:20px ; white-space: pre-wrap;}
</style>