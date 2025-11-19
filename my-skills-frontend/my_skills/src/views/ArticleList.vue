<!--scr/views/ArticleList.vue-->
<template>
    <div class="article-list">
    <h1>知识文章列表</h1>
    <router-link to ="/article/new" class="add-button">创建文章</router-link>

    <div v-if="loading">正在加载</div>
    <div v-if="error" class="error">{{ error }}</div>
    
    <ul v-if="articles.length >0">
     <li v-for="article in articles" :key="article.id">
        <div class="article-info">
        <h2>{{ article.skillName }}</h2>
        <p>分类:{{ article.category }}</p>
        </div>
    <div class="articles-actions ">
        <router-link :to="`/article/${article.id}`">查看详情</router-link>
        <router-link :to="`/article/edit/${article.id}`">编辑</router-link>
        <button @click="confirmDelete(article.id)">删除</button>

    </div>
    </li>
    </ul>
    <div v-else-if="!loading">暂时没有文章</div>
    </div>

  <button @click="LogOut">退出登录</button>
</template>

<script setup>
import{ref, onMounted} from 'vue';
import api from '@/services/api';
import { useRoute,useRouter} from 'vue-router';
import {useAuthStore} from "@/stores/user.js";
const authStore = useAuthStore();

const articles = ref([]);
const loading = ref(true);
const error = ref(null);
const route = useRoute();
const router = useRouter();

//async函数内部使用await暂停关键字
async function fetchArticles() {
    loading.value = true;
    error.value = null;
    //先try如果出现异常先
    try{

        const response = await api.getAllArticles();
        articles.value = response.data;
        console.log(1);
    } catch(err){
        //console.error会显示红色
        console.error('获取文章失败：',err);
        //不会打断用户的操作，错误信息通常在附近
        error.value = '无法加载文章列表，请稍后再试。';
    } finally{
        loading.value = false
    }}

async function deleteArticle(id) {
    try{
        //删除文章
        await api.deleteArticle(id);
        //重新获取文章列表，刷新前端页面显示
        fetchArticles();
    }catch(err){
        console.error(`删除文章${id}失败：`,err);
        //冻结整个页面
        alert('删除失败')
    }
}

function LogOut(){
  authStore.clearToken();
  router.push('/login');
  alert("退出成功")
}


function confirmDelete(id){
    //window.confirm()是浏览器提供的js，会暂停所有的JavaScript执行，对话框有两个固定按钮OK和取消
    if(window.confirm('你确定删除这篇文章吗？')){
        deleteArticle(id);
    }
}

//onMounted调用fetchArticles,文章才能显示出来
onMounted(()=>{
    fetchArticles();
});



</script>
<style scoped>
.article-list { max-width: 800px; margin: 0 auto; padding: 20px; }
.add-button { display: inline-block; margin-bottom: 20px; padding: 10px 15px; background-color: #42b983; color: white; text-decoration: none; border-radius: 5px; }
ul { list-style: none; padding: 0; }
li { display: flex; justify-content: space-between; align-items: center; padding: 15px; border-bottom: 1px solid #eee; }
.article-actions a, .article-actions button { margin-left: 10px; }
.error { color: red; }
</style>