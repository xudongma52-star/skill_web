import { createRouter,createWebHashHistory } from "vue-router"; 
import ArticleList from '../views/ArticleList.vue';
import ArticleDetail from '../views/ArticleDetail.vue';
import ArticleForm from '../views/ArticleForm.vue';
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

        }
    ]
});
export default router;