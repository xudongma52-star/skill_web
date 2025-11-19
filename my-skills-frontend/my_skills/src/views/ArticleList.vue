<template>
  <div class="article-list-page">

    <div class="background-texture"></div>


    <div class="literary-decoration">
      <div class="ink-splash"></div>
      <div class="paper-fold"></div>
    </div>


    <div class="falling-words">
      <div class="word" v-for="(word, i) in ['朝', '花', '夕', '拾', '回', '忆', '故', '事']" :key="i">{{ word }}</div>
    </div>

    <div class="article-list">
      <div class="header-actions">
        <h1>书卷</h1>
        <router-link to="/article/new" class="add-button">创建灵感</router-link>
      </div>

      <div v-if="loading" class="loading">正在加载</div>
      <div v-if="error" class="error">{{ error }}</div>

      <ul v-if="articles.length > 0" class="articles-container">
        <li v-for="article in articles" :key="article.id" class="article-item">
          <div class="article-info">
            <h2 class="article-title">{{ article.skillName }}</h2>
            <p class="article-category">分类: {{ article.category }}</p>
          </div>
          <div class="article-actions">
            <router-link :to="`/article/${article.id}`" class="action-btn view-btn">查看详情</router-link>
            <router-link :to="`/article/edit/${article.id}`" class="action-btn edit-btn">编辑</router-link>
            <button @click="confirmDelete(article.id)" class="action-btn delete-btn">删除</button>
          </div>
        </li>
      </ul>
      <div v-else-if="!loading" class="no-articles">暂时没有文章</div>

      <button @click="LogOut" class="logout-btn">退出登录</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '@/services/api';
import { useRouter } from 'vue-router';
import { useAuthStore } from "@/stores/user.js";
const authStore = useAuthStore();

const articles = ref([]);
const loading = ref(true);
const error = ref(null);
const router = useRouter();

async function fetchArticles() {
  loading.value = true;
  error.value = null;
  try {
    const response = await api.getAllArticles();
    articles.value = response.data;
  } catch (err) {
    console.error('获取文章失败：', err);
    error.value = '无法加载文章列表，请稍后再试。';
  } finally {
    loading.value = false;
  }
}

async function deleteArticle(id) {
  try {
    await api.deleteArticle(id);
    fetchArticles();
  } catch (err) {
    console.error(`删除文章${id}失败：`, err);
    alert('删除失败');
  }
}

function LogOut() {
  authStore.clearToken();
  router.push('/login');
  alert("退出成功");
}

function confirmDelete(id) {
  if (window.confirm('你确定删除这篇文章吗？')) {
    deleteArticle(id);
  }
}

onMounted(() => {
  fetchArticles();
});
</script>

<style scoped>
/* 页面背景：模仿旧书页质感，呼应《朝花夕拾》文学主题 */
.article-list-page {
  min-height: 100vh;
  background-color: #f8f4e9; /* 旧书页泛黄色调 */
  background-image:
      url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='100' height='100' viewBox='0 0 100 100'%3E%3Cg fill-rule='evenodd'%3E%3Cg fill='%238b7d6b' fill-opacity='0.03'%3E%3Cpath opacity='.5' d='M96 95h4v1h-4v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h4v1h-4v9h4v1h-4v9h4v1h-4v9h4v1h-4v9h4v1h-4v9h4v1h-4v9h4v1h-4v9zm-1 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-9-10h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm9-10v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-9-10h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm9-10v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-9-10h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm9-10v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-9-10h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9z'/%3E%3Cpath d='M6 5V0H5v5H0v1h5v94h1V6h94V5H6z'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E"),
      linear-gradient(rgba(248, 240, 227, 0.8) 1px, transparent 1px);
  background-size: 100px 100px, 100% 24px; /* 纸张纹理+行距线 */
  padding: 20px 0;
  position: relative;
  overflow: hidden;
  font-family: "Noto Serif SC", serif; /* 衬线字体增强书卷气 */
}

/* 文学装饰元素 */
.literary-decoration {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 1;
}

/* 墨迹效果 */
.ink-splash {
  position: absolute;
  top: 20%;
  right: 10%;
  width: 200px;
  height: 200px;
  background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100'%3E%3Cpath d='M50,10 Q70,20 80,40 Q90,60 70,80 Q50,90 30,80 Q10,60 20,40 Q30,20 50,10 Z' fill='%233a3226' opacity='0.03'/%3E%3C/svg%3E");
  background-repeat: no-repeat;
}

/* 纸张折角 */
.paper-fold {
  position: absolute;
  bottom: 0;
  right: 0;
  width: 120px;
  height: 120px;
  background-image: linear-gradient(135deg, transparent 50%, rgba(255,255,255,0.3) 50%);
  pointer-events: none;
}

/* 文字飘落效果（选取"朝花夕拾"相关文字） */
.falling-words {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 2;
}

.word {
  position: absolute;
  color: rgba(58, 50, 38, 0.08);
  font-size: 1.5rem;
  font-weight: bold;
  opacity: 0;
  animation: float linear infinite;
}

/* 文字动画参数 */
.word:nth-child(1) { left: 15%; animation-duration: 30s; animation-delay: 0s; }
.word:nth-child(2) { left: 30%; animation-duration: 25s; animation-delay: 5s; }
.word:nth-child(3) { left: 45%; animation-duration: 35s; animation-delay: 2s; }
.word:nth-child(4) { left: 60%; animation-duration: 28s; animation-delay: 8s; }
.word:nth-child(5) { left: 75%; animation-duration: 32s; animation-delay: 4s; }
.word:nth-child(6) { left: 20%; animation-duration: 26s; animation-delay: 10s; }
.word:nth-child(7) { left: 50%; animation-duration: 29s; animation-delay: 1s; }
.word:nth-child(8) { left: 80%; animation-duration: 33s; animation-delay: 6s; }

@keyframes float {
  0% {
    transform: translateY(-50px) rotate(0deg);
    opacity: 0;
  }
  10% {
    opacity: 0.1;
  }
  90% {
    opacity: 0.1;
  }
  100% {
    transform: translateY(100vh) rotate(30deg);
    opacity: 0;
  }
}

.article-list {
  max-width: 1000px;
  margin: 0 auto;
  padding: 30px 20px;
  position: relative;
  z-index: 10;
}

/* 头部标题与按钮区域 */
.header-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
  padding-bottom: 15px;
  border-bottom: 1px solid #e6dfc9;
}

h1 {
  margin: 0;
  color: #3a3226;
  font-size: 1.8rem;
  font-weight: 600;
  position: relative;
  padding-left: 1rem;
}

h1::before {
  content: "";
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 4px;
  height: 80%;
  background-color: #8b7d6b;
  border-radius: 2px;
}

.add-button {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  padding: 10px 20px;
  background-color: #8b7d6b;
  color: #f8f4e9;
  text-decoration: none;
  border-radius: 2px;
  font-weight: 500;
  transition: all 0.3s ease;
  box-shadow: 2px 2px 0 rgba(0,0,0,0.1);
}

.add-button:hover {
  background-color: #6d6353;
  transform: translateY(-2px);
  box-shadow: 3px 3px 0 rgba(0,0,0,0.15);
}

/* 加载与错误状态 */
.loading {
  text-align: center;
  padding: 40px;
  color: #6d6353;
  font-size: 1.1rem;
  background-color: rgba(255, 255, 255, 0.7);
  border: 1px solid #e6dfc9;
  border-radius: 2px;
  box-shadow: 0 1px 3px rgba(0,0,0,0.05);
}

.error {
  text-align: center;
  padding: 20px;
  color: #9e2a2b;
  background-color: rgba(248, 215, 218, 0.7);
  border: 1px solid #e6c8c9;
  border-radius: 2px;
  margin-bottom: 20px;
  box-shadow: 0 1px 3px rgba(0,0,0,0.05);
}

/* 文章列表容器 */
.articles-container {
  list-style: none;
  padding: 0;
  margin: 0;
}

.article-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  margin-bottom: 15px;
  background-color: rgba(255, 255, 255, 0.85);
  border: 1px solid #e6dfc9;
  box-shadow: 2px 2px 0 rgba(0,0,0,0.03);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.article-item:hover {
  transform: translateY(-2px);
  box-shadow: 3px 3px 0 rgba(0,0,0,0.08);
}

.article-info {
  flex: 1;
}

.article-title {
  margin: 0 0 8px 0;
  font-size: 1.3rem;
  color: #3a3226;
  transition: color 0.3s ease;
  position: relative;
  padding-left: 0.5rem;
}

.article-title::before {
  content: "•";
  position: absolute;
  left: 0;
  color: #8b7d6b;
}

.article-item:hover .article-title {
  color: #8b7d6b;
}

.article-category {
  margin: 0;
  color: #6d6353;
  font-size: 0.9rem;
  font-style: italic;
}

/* 操作按钮组 */
.article-actions {
  display: flex;
  gap: 10px;
}

.action-btn {
  padding: 6px 12px;
  border-radius: 2px;
  font-size: 0.9rem;
  font-weight: 500;
  text-decoration: none;
  cursor: pointer;
  transition: all 0.2s ease;
  border: 1px solid #e6dfc9;
  background-color: #f8f4e9;
  color: #6d6353;
}

.action-btn:hover {
  background-color: #e6dfc9;
  border-color: #d9d1b8;
}

/* 无文章状态 */
.no-articles {
  text-align: center;
  padding: 60px 20px;
  color: #6d6353;
  background-color: rgba(255, 255, 255, 0.7);
  border: 1px dashed #e6dfc9;
  border-radius: 2px;
  box-shadow: 0 1px 3px rgba(0,0,0,0.05);
}

/* 退出登录按钮 */
.logout-btn {
  margin-top: 30px;
  padding: 10px 20px;
  background-color: transparent;
  color: #6d6353;
  border: 1px solid #e6dfc9;
  border-radius: 2px;
  cursor: pointer;
  font-weight: 500;
  transition: all 0.3s ease;
}

.logout-btn:hover {
  background-color: #f0ead7;
  border-color: #d9d1b8;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .article-item {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }

  .article-actions {
    width: 100%;
    justify-content: flex-start;
  }

  .header-actions {
    flex-direction: column;
    align-items: flex-start;
    gap: 15px;
  }

  .ink-splash {
    width: 150px;
    height: 150px;
    right: 5%;
  }
}
</style>