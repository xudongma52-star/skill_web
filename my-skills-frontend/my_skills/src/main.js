import { createApp } from 'vue'
import {createPinia} from "pinia";//导入
import App from './App.vue'
// 1. 导入你即将创建的路由文件
import router from './router' 

const app = createApp(App)
const pinia = createPinia()//创建实例

// 2. 告诉 Vue 实例使用路由
app.use(pinia)//主策pinia
app.use(router)


app.mount('#app')