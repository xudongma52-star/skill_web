import axios from "axios";
//创建一个Axios实例，可以用它进行通用配置
const apiClient = axios.create(
    {
        baseURL:'http://localhost:8080/api',
        headers:{
            'Content-Type':'application/json'
        }
    }
);
export default{
    /**
     * API 1:获取所有文章
     * '/articles'这个一般是固定不变
     * @returns 
     */
    getAllArticles(){
        return apiClient.get('/articles');
    },

    /**
     * API 2：根据id获取文章
     * @param {*} id  `/articles/${id}`用于灵活改变
     * @returns 
     */
    getArticleById(id){
        return apiClient.get(`/articles/${id}`);
    },

    /**
     * API 3: 创建文章
     * @param {*} articleData 
     * @returns 
     */
    createArticle(articleData){
        return apiClient.post('/articles',articleData);
    },

    /**
     * API4 :更新文章
     * @param {*} id 
     * @param {*} articleData 
     * @returns 
     */
    updateArticle(id,articleData){
        return apiClient.put(`/articles/${id}`,articleData);
    },

    deleteArticle(id){
        return apiClient.delete(`/articles/${id}`);
    }
};