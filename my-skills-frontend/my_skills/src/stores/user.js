// src/stores/auth.js
import { defineStore } from 'pinia';
import { ref, computed } from 'vue';

export const useAuthStore = defineStore('auth', () => {
    // 从 localStorage 初始化 token
    const token = ref(localStorage.getItem('user_token') || null);

    // 定义一个计算属性来判断用户是否登录
    const isAuthenticated = computed(() => !!token.value);

    // action: 设置 token (登录时调用)
    function setToken(newToken) {
        token.value = newToken;
        localStorage.setItem('user_token', newToken);
    }

    // action: 清除 token (登出时调用)
    function clearToken() {
        token.value = null;
        localStorage.removeItem('user_token');
    }

    return { token, isAuthenticated, setToken, clearToken };
});