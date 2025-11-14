<!--src/views/ArticleForm.vue-->
<template>
    <div class="article-form">
        <h1>{{ isEditing ? '编辑文章':'创建文章' }}</h1>
        <form @submit.prevent="handleSubmit">
                    <div class="form-group">
            <label for="skillName">技能名称</label>
            <input type="text" id = "skillName" v-model="form.skillName" required></input>
        </div>
        <div class ="form-group">
            <label for="category">分类</label>
            <input type="text" id="category" v-model="form.category" required></input>
        </div>
        <div class="form-group">
            <label for="content">内容</label>
            <textarea id="content" v-model="form.content" required></textarea>
        </div>
        <div class="form-group">
            <button type="submit">{{ isEditing ? '更新文章':'创建文章' }}</button>
        </div>
        </form>  <!--submit.prevent="handleSumbit"作用是，在提交完之后用，.prevent阻止页面重新加载，以免造成不好的体验之后执行handleSumbit-->


        
    </div>
</template>

<script setup >
import{ref ,onMounted, computed} from 'vue'; //computed计算属性
import api from '@/services/api';
import { useRoute,useRouter } from "vue-router";

const route = useRoute();//Vue Router提供的钩子函数，获取当前激活的路由信息对象，这个对象是只读的
const router = useRouter();//获取路由实例，可以决定跳转到哪里
const articleId = route.params.id;//包含了路由动态段的参数，动态参数是id所以它获得的是id
const isEditing = computed(()=> !!articleId);//！！可以将所有的值强制转换成对应的布尔值第一个！取布尔值并取反，第二个再转回来 根据id的有无来决定创建还是编辑

const form = ref({
    skillName:'',
    content:'',
    category:''
});

onMounted(async()=>{
    if(isEditing.value){
        try{
            const response = await api.getArticleById(articleId);
            form.value = response.data;
        }catch(error){
            alert("无法加载文章");
            router.push("/");
        }
    }
});

async function handleSubmit() {
    try{
        if(isEditing.value){
            await api.updateArticle(articleId,form.value);
            alert("文章更新成功");
        }else{
            await api.createArticle(form.value);
                alert("文章创建成功");
            
         
        }
           router.push("/");
    }catch(error){
        alert("操作失败");
        console.error("提交失败：", error); 
    }
}
</script>

<style scoped>
.article-form { max-width: 600px; margin: 0 auto; padding: 20px; }
.form-group { margin-bottom: 15px; }
.form-group label { display: block; margin-bottom: 5px; }
.form-group input, .form-group textarea { width: 100%; padding: 8px; box-sizing: border-box; }
.form-actions button { margin-right: 10px; }
</style>


