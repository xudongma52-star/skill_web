<!--src/App.vue-->
<template>
    <header>
        <div class="wrapper">
            <nav>
                <!--这个链接总是指向首页-->
                <RouterLink to="/">首页</RouterLink>
            </nav>
        </div>
    </header>
    <mian>
        <!--路由的匹配组件在这里-->
        <RouterView/>
    </mian>
</template>

<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router';


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
</style>