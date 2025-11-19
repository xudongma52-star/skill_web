<!--src/App.vue-->
<template>
    <header>
        <div class="wrapper">
            <nav>
                <!--这个链接总是指向首页-->

              <div v-if="authStore.isAuthenticated"></div>
              <div v-else>
                <!-- 如果未登录，显示登录/注册链接 -->
                <RouterLink to="/login"></RouterLink>
                <RouterLink to="/register"></RouterLink>
              </div>
            </nav>
        </div>
    </header>
    <main>
        <!--路由的匹配组件在这里-->
        <RouterView/>
    </main>
</template>

<script setup lang="ts">
import { RouterLink, RouterView,useRouter } from 'vue-router';
import { useAuthStore } from './stores/user';

const router = useRouter();
const authStore = useAuthStore();

function logout(){
  authStore.clearToken();
  alert('您已退出登录');
  router.push('/login');
}


</script>

<style scoped>
header {
  line-height: 1.5;
  border-bottom: 1px solid #ddd;
  padding: 1rem 2rem;
}
nav a {
  padding: 0 1rem;
  text-decoration: none;
  color: #333;
}
nav a.router-link-exact-active {
  color: #42b983;
}
main {
  padding: 2rem;
}
nav { display: flex; justify-content: space-between; align-items: center; }
</style>