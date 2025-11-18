<script setup>
import {ref} from "vue";
import{useRouter} from "vue-router";
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
    alert("登陆成功");
    router.push("/");
  }catch(err){
    error.value=err.response?.data?.message||'登录失败请检查用户名或者密码';
  }
}
</script>


<template>
  <div>
    <h2>登录</h2>
    <form @submit.prevent="handleLogin">
      <input type="text" v-model="username" placeholder="用户名" required>
      <input type="text" v-model="password" placeholder="密码" required>
      <button type="submit">登录</button>
      <div v-if="error">{{error}}</div>
    </form>
    <p>没有帐户?<router-link to="/register">去注册</router-link></p>
  </div>
</template>

