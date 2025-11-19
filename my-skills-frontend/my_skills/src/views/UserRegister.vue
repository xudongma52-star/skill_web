<script setup>
import { useRouter } from "vue-router";
import api   from "@/services/api.js";
import {useAuthStore}  from "@/stores/user.js";
import {ref} from "vue";

const username = ref("")
const password = ref("")
const avatar = ref("")
const phone = ref("")
const region = ref("")
const age = ref("")

const error = ref(null);
const isLoading = ref(false);

const router = useRouter();
const authStore = useAuthStore();

async function handleRegister(){
  // console.log("register")
  error.value=null;
  isLoading.value=true;//进入加载状态
  try{
    console.log("register")
    console.log("can");
    const response = await api.getUserRegister({
      username:username.value,
      password:password.value,
      avatar:avatar.value,
      phone:phone.value,
      region:region.value,
      age:age.value,

    });
    console.log("can01");

     router.push("/login");
    alert("注册成功");
  }catch (err){
    error.value=err.response?.data?.message||'注册失败';
  }finally {
    isLoading.value=false;
  }
}
</script>

<template>
  <div>
    <h2>注册</h2>
    <form @submit.prevent="handleRegister">
      <div class="form-row">
        <label for="username">用户名</label>
        <input type="text" v-model="username" placeholder="用户名" required>
        <br>
        <label for="password">密码</label>
        <input type="text" v-model="password" placeholder="密码" required>
        <br>
        <label for="phone">手机号</label>
        <input type="text" v-model="phone" placeholder="手机号" required>
        <br>
        <label for="avatar">头像</label>
        <input type="text" v-model="avatar" placeholder="头像" required>
        <br>
        <label for="region">地区</label>
        <input type="text" v-model="region" placeholder="地区" required>
        <br>
        <br>
        <label for="age">年龄</label>
        <input type="number" v-model.number="age" placeholder="年龄" required>
        <br>
        <button type="submit">注册</button>
        <div v-if="error">{{error}}</div>


      </div>

    </form>
  </div>
</template>

<style scoped>
/* 添加一些简单的样式让页面更好看 */
.register-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}
.form-group {
  margin-bottom: 15px;
}
label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}
input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}
button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
.error-message {
  color: red;
  margin-top: 10px;
}
.switch-link {
  margin-top: 20px;
  text-align: center;
}
</style>