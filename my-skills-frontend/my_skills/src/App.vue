<template>
  <div class="app-container">
    <!-- 全局背景纹理 -->
    <div class="global-background"></div>

    <!-- 花瓣动画全局应用 -->
    <PetalAnimation />

    <header>
      <div class="wrapper">
        <nav>
          <!-- 网站名称 -->
          <div class="site-name">朝花夕拾</div>

          <!-- 导航链接 -->
          <div class="nav-links">
            <RouterLink to="/">首页</RouterLink>

            <template v-if="authStore.isAuthenticated">
              <RouterLink to="/article">文章列表</RouterLink>
              <button @click="logout" class="logout-btn">退出</button>
            </template>

            <template v-else>
              <RouterLink to="/login">登录</RouterLink>
              <RouterLink to="/register">注册</RouterLink>
            </template>
          </div>
        </nav>
      </div>
    </header>

    <main>
      <RouterView />
    </main>
  </div>
</template>

<script setup lang="ts">
import { RouterLink, RouterView, useRouter } from 'vue-router';
import { useAuthStore } from './stores/user';
import PetalAnimation from './components/PetalAnimation.vue';

const router = useRouter();
const authStore = useAuthStore();

function logout() {
  authStore.clearToken();
  alert('您已退出登录');
  router.push('/login');
}
</script>

<style scoped>
/* 全局容器与背景 - 统一旧书页风格 */
.app-container {
  min-height: 100vh;
  position: relative;
  font-family: "Noto Serif SC", serif; /* 衬线字体增强文学感 */
  overflow-x: hidden;
}

.global-background {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: #f8f4e9; /* 旧书页底色 */
  background-image:
      url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='100' height='100' viewBox='0 0 100 100'%3E%3Cg fill-rule='evenodd'%3E%3Cg fill='%238b7d6b' fill-opacity='0.02'%3E%3Cpath opacity='.5' d='M96 95h4v1h-4v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4h-9v4h-1v-4H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15v-9H0v-1h15V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h9V0h1v15h4v1h-4v9h4v1h-4v9h4v1h-4v9h4v1h-4v9h4v1h-4v9h4v1h-4v9h4v1h-4v9zm-1 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-9-10h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm9-10v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-9-10h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm9-10v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-9-10h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm9-10v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-10 0v-9h-9v9h9zm-9-10h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9zm10 0h9v-9h-9v9z'/%3E%3Cpath d='M6 5V0H5v5H0v1h5v94h1V6h94V5H6z'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E"),
      linear-gradient(rgba(248, 240, 227, 0.6) 1px, transparent 1px);
  background-size: 100px 100px, 100% 24px;
  z-index: -1;
}

/* 头部导航 */
header {
  line-height: 1.6;
  border-bottom: 1px solid #e6dfc9; /* 暖色调边框 */
  padding: 1rem 2rem;
  background-color: rgba(255, 255, 255, 0.85);
  backdrop-filter: blur(8px);
  position: relative;
  z-index: 10;
  box-shadow: 0 2px 4px rgba(0,0,0,0.02);
}

.wrapper {
  max-width: 1200px;
  margin: 0 auto;
}

nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* 网站名称样式 */
.site-name {
  font-size: 1.8rem;
  font-weight: 700;
  color: #6d6353; /* 深棕色调 */
  letter-spacing: 2px;
  position: relative;
  padding-left: 1.2rem;
}

.site-name::before {
  content: "";
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  width: 4px;
  height: 60%;
  background-color: #8b7d6b;
}

/* 导航链接 */
.nav-links {
  display: flex;
  gap: 0.5rem;
  align-items: center;
}

nav a {
  padding: 0.5rem 1rem;
  text-decoration: none;
  color: #6d6353;
  border-radius: 2px;
  transition: all 0.3s ease;
  position: relative;
}

nav a::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  width: 0;
  height: 1px;
  background-color: #8b7d6b;
  transition: width 0.3s ease;
}

nav a:hover::after,
nav a.router-link-exact-active::after {
  width: 100%;
}

nav a:hover,
nav a.router-link-exact-active {
  color: #8b7d6b;
  background-color: rgba(240, 234, 215, 0.5);
}

/* 退出按钮 */
.logout-btn {
  padding: 0.5rem 1rem;
  background-color: transparent;
  color: #6d6353;
  border: 1px solid #e6dfc9;
  border-radius: 2px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.logout-btn:hover {
  background-color: #f0ead7;
  border-color: #d9d1b8;
}

/* 主内容区 */
main {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
  position: relative;
  z-index: 10;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .site-name {
    font-size: 1.5rem;
  }

  nav {
    flex-direction: column;
    align-items: flex-start;
    gap: 1rem;
  }

  .nav-links {
    width: 100%;
    justify-content: space-between;
  }
}
</style>