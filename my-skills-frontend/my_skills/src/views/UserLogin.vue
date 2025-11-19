<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import api from "@/services/api";
import { useAuthStore } from "@/stores/user.js";

const username = ref("");
const password = ref("");
const error = ref(null);

const router = useRouter();
const authStore = useAuthStore();

async function handleLogin() {
  error.value = null;
  try {
    const response = await api.getUserLogin({
      username: username.value,
      password: password.value
    });
    // 登录成功，调用store的action来保存token
    authStore.setToken(response.data.token);
    router.push('/');
    alert("登陆成功");
  } catch (err) {
    error.value = err.response?.data?.message || '登录失败，请检查用户名或密码';
  }
}
</script>

<template>
  <div class="login-page">
    <!-- 花瓣装饰元素 -->
    <div class="petal-decoration"></div>

    <div class="login-container">
      <!-- 网站名称 -->
      <div class="site-title">朝花夕拾</div>

      <h2>登录</h2>
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <input type="text" v-model="username" placeholder="用户名" required>
        </div>
        <div class="form-group">
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
/* 整体页面布局：花瓣主题背景 */
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #fcfaf7;
  background-image:
      radial-gradient(rgba(255, 222, 222, 0.15) 1px, transparent 1px),
      radial-gradient(rgba(255, 240, 200, 0.1) 1px, transparent 1px);
  background-size: 40px 40px;
  background-position: 0 0, 20px 20px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  margin: 0;
  padding: 0 1rem;
  position: relative;
  overflow: hidden;
}

/* 花瓣装饰动画 */
.petal-decoration {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  pointer-events: none;
  z-index: 0;
}

.petal-decoration::before,
.petal-decoration::after,
.petal-decoration span::before,
.petal-decoration span::after {
  content: '';
  position: absolute;
  border-radius: 100% 0;
  background-color: rgba(255, 221, 221, 0.3);
  animation: fall linear infinite;
}

/* 花瓣元素定义 */
.petal-decoration::before {
  width: 15px;
  height: 15px;
  top: -20px;
  left: 10%;
  animation-duration: 25s;
}

.petal-decoration::after {
  width: 20px;
  height: 20px;
  top: -20px;
  left: 70%;
  background-color: rgba(255, 240, 200, 0.25);
  animation-duration: 30s;
  animation-delay: 5s;
}

.petal-decoration span::before {
  width: 12px;
  height: 12px;
  top: -20px;
  left: 40%;
  background-color: rgba(221, 240, 255, 0.2);
  animation-duration: 22s;
  animation-delay: 8s;
}

.petal-decoration span::after {
  width: 18px;
  height: 18px;
  top: -20px;
  left: 85%;
  background-color: rgba(230, 221, 255, 0.2);
  animation-duration: 28s;
  animation-delay: 12s;
}

/* 花瓣下落动画 */
@keyframes fall {
  0% {
    transform: translateY(-10px) rotate(0deg);
    opacity: 0.4;
  }
  50% {
    opacity: 0.2;
  }
  100% {
    transform: translateY(100vh) rotate(360deg);
    opacity: 0;
  }
}

/* 登录表单容器 */
.login-container {
  width: 100%;
  max-width: 400px;
  padding: 2.5rem;
  background: rgba(255, 255, 255, 0.95);
  border-radius: 12px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.05);
  text-align: center;
  transition: all 0.3s ease;
  position: relative;
  z-index: 10;
  backdrop-filter: blur(5px);
  border: 1px solid rgba(255, 255, 255, 0.8);
}

.login-container:hover {
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.08);
  transform: translateY(-3px);
}

/* 网站名称 */
.site-title {
  font-size: 1.8rem;
  font-weight: 700;
  color: #8d7b6c;
  margin-bottom: 0.5rem;
  letter-spacing: 2px;
}

/* 标题样式 */
h2 {
  margin-bottom: 1.8rem;
  font-size: 1.6rem;
  font-weight: 600;
  color: #5a5246;
  position: relative;
  display: inline-block;
}

h2::after {
  content: "";
  position: absolute;
  bottom: -6px;
  left: 10%;
  width: 80%;
  height: 2px;
  background: linear-gradient(90deg, transparent, #d4b898, transparent);
  border-radius: 3px;
}

/* 表单组样式 */
.form-group {
  margin-bottom: 1.8rem;
  position: relative;
}

/* 输入框样式 */
input {
  width: 100%;
  padding: 0.85rem 1.2rem;
  font-size: 1rem;
  border: 1px solid #f0e8e0;
  border-radius: 8px;
  box-sizing: border-box;
  background-color: #fcfaf7;
  transition: all 0.3s ease;
  color: #5a5246;
}

input::placeholder {
  color: #c8b8a8;
  transition: color 0.3s ease;
}

input:focus {
  outline: none;
  border-color: #d4b898;
  background-color: #fff;
  box-shadow: 0 0 0 3px rgba(212, 184, 152, 0.1);
}

input:focus::placeholder {
  color: #d9c9b8;
}

/* 登录按钮样式 */
button {
  width: 100%;
  padding: 0.85rem;
  font-size: 1.1rem;
  font-weight: 600;
  color: #fff;
  background: linear-gradient(90deg, #b98b69, #a67c52);
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  overflow: hidden;
  z-index: 1;
}

button:hover {
  background: linear-gradient(90deg, #a67c52, #916c45);
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(185, 139, 105, 0.2);
}

/* 按钮点击波纹效果 */
button::after {
  content: "";
  position: absolute;
  top: 50%;
  left: 50%;
  width: 0;
  height: 0;
  background-color: rgba(255, 255, 255, 0.3);
  border-radius: 50%;
  transform: translate(-50%, -50%);
  transition: width 0.5s, height 0.5s;
  z-index: -1;
}

button:active::after {
  width: 200px;
  height: 200px;
}

/* 错误信息样式 */
.error-message {
  color: #b94a48;
  background-color: #fae3e3;
  border: 1px solid #f5d6d6;
  border-radius: 6px;
  padding: 0.75rem;
  margin-top: 1.5rem;
  font-size: 0.9rem;
  animation: fadeIn 0.3s ease forwards;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 注册链接样式 */
.register-link {
  margin-top: 1.8rem;
  font-size: 0.95rem;
  color: #8d7b6c;
}

.register-link a {
  color: #a67c52;
  text-decoration: none;
  font-weight: 600;
  position: relative;
  padding-bottom: 2px;
  transition: color 0.3s ease;
}

.register-link a:hover {
  color: #916c45;
}

.register-link a::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  width: 0;
  height: 2px;
  background-color: #a67c52;
  transition: width 0.3s ease;
}

.register-link a:hover::after {
  width: 100%;
}

/* 响应式适配 */
@media (max-width: 480px) {
  .login-container {
    padding: 2rem 1.5rem;
  }

  .site-title {
    font-size: 1.5rem;
  }

  h2 {
    font-size: 1.4rem;
  }
}
</style>