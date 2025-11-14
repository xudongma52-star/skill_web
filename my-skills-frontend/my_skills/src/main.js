import { createApp } from 'vue'
import App from './App.vue'
// 1. 导入你即将创建的路由文件
import router from './router' 

const app = createApp(App)

// 2. 告诉 Vue 实例使用路由
app.use(router) 

app.mount('#app')