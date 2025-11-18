import { createRouter,createWebHashHistory } from "vue-router"; 
import ArticleList from '../views/ArticleList.vue';
import ArticleDetail from '../views/ArticleDetail.vue';
import ArticleForm from '../views/ArticleForm.vue';
import UserRegister from "../views/UserRegister.vue";
import UserLogin from "../views/UserLogin.vue";
import {useAuthStore} from "@/stores/user.js"//导入authStore
const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes:[
        {
            //路由1: 首页文章显示列表
            //URL:http://localhost:5173/
            path:'/',
            name:'ArticleList',
            component:ArticleList
        },
        {
            //路由2：创建新文章页面
            //URL：http://localhost:5173/article/new
            path:"/article/new",
            name:'ArticleCreate',
            component:ArticleForm,
        },
        {
            //路由3：查看单篇文章详细的页面
            //URL：id是动态的/article/1或者/article2
            path:'/article/:id',
            name:'ArticleDetail',
            component:ArticleDetail
        },
        {
            //路由4：编辑文章的页面
            //URL：http://localhost:5173/article/edit/我选择的id
            path:'/article/edit/:id',
            name:'ArticleEdit',
            component:ArticleForm

        },
        {
            //路由5:注册界面
            //URL:http://local:5173/register
            path:'/register',
            name:'UserRegister',
            component:UserRegister
        },
        {
            //路由6：登录页面
            path:'/login',
            name:'UserLogin',
            component:UserLogin
        }
    ]
});
router.beforeEach((to, from, next) => {
    const authStore = useAuthStore();
    const requiresAuth = ['ArticleCreate', 'ArticleEdit','ArticleList']; // 定义需要登录才能访问的路由名称

    // 如果目标路由需要认证，但用户未登录
    if (requiresAuth.includes(to.name) && !authStore.isAuthenticated) {
        // 重定向到登录页
        next({ name: 'UserLogin' });
    } else {
        // 否则，正常放行
        next();
    }
});

export default router;