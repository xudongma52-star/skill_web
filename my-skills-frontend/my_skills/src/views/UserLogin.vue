<script setup>
import {ref} from "vue";
import {useRouter} from "vue-router";
import api from "@/services/api";
import {useAuthStore} from "@/stores/user.js";

const username = ref("");
const password = ref("");
const error = ref(null);

const router = useRouter();
const authStore = useAuthStore();

async function handleLogin() {
  error.value=null;
  try{
    const response=await api.getUserLogin({
      username:username.value,
      password:password.value
    });
    //登录成功，调用store的action来保存token
    authStore.setToken(response.data.token);
    router.push('/');
    alert("登陆成功");
  }catch(err){
    error.value=err.response?.data?.message||'登录失败，请检查用户名或密码';
  }
}
</script>

<template>
  <div class="login-page">
    <div class="login-container">
      <h2>登录</h2>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <input type="text" v-model="username" placeholder="用户名" required>
        </div>
        <div class="form-group">
          <!-- 最佳实践：密码输入框使用 type="password" -->
          <input type="password" v-model="password" placeholder="密码" required>
        </div>
        <button type="submit">登录</button>
        <div v-if="error" class="error-message">{{ error }}</div>
      </form>
      <p class="register-link">
        没有帐户? <router-link to="/register">去注册</router-link>
      </p>
    </div>
  </div>
</template>

<style scoped>
/* 整体页面布局 */
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f0f2f5; /* 柔和的背景色 */
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
}

/* 登录表单容器 */
.login-container {
  width: 100%;
  max-width: 400px;
  padding: 2.5rem;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
}

/* 标题 */
h2 {
  margin-bottom: 1.5rem;
  font-size: 2rem;
  color: #333;
  font-weight: 600;
}

/* 表单组，用于控制间距 */
.form-group {
  margin-bottom: 1.5rem;
}

/* 输入框样式 */
input {
  width: 100%;
  padding: 0.75rem 1rem;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box; /* 保证 padding 不会撑大宽度 */
  transition: border-color 0.3s, box-shadow 0.3s;
}

/* 输入框获取焦点时的样式 */
input:focus {
  outline: none;
  border-color: #007bff;
  box-shadow: 0 0 0 3px rgba(0, 123, 255, 0.25);
}

/* 登录按钮 */
button {
  width: 100%;
  padding: 0.75rem;
  font-size: 1.1rem;
  font-weight: bold;
  color: #fff;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

/* 按钮悬停效果 */
button:hover {
  background-color: #0056b3;
}

/* 错误信息 */
.error-message {
  color: #dc3545; /* 醒目的红色 */
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  border-radius: 4px;
  padding: 0.75rem;
  margin-top: 1.5rem;
  font-size: 0.9rem;
}

/* 注册链接 */
.register-link {
  margin-top: 1.5rem;
  font-size: 0.9rem;
  color: #6c757d;
}

.register-link a {
  color: #007bff;
  text-decoration: none;
  font-weight: 600;
}

.register-link a:hover {
  text-decoration: underline;
}
</style>