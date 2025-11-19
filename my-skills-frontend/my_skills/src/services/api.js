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

//添加请求拦截器
apiClient.interceptors.request.use(config => {
    //每次发送前都去localStorage里面看看这家伙有没有token
    const token = localStorage.getItem("user_token");
    if (token) {
        //如果有，就把它加加到请求的Authorization头里面
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});



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

    /**
     * API 5:删除文章
     * @param id
     * @returns {Promise<axios.AxiosResponse<any>>}
     */
    deleteArticle(id){
        return apiClient.delete(`/articles/${id}`);
    },

    /**
     * API 6:用户注册
     * @param id
     * @param userName
     * @param phone
     * @param password
     * @param age
     * @param Avatar
     * @param region
     * @returns {Promise<axios.AxiosResponse<any>>}
     */
    getUserRegister(userData){
        return apiClient.post(`/user/register`, userData);
    },

    /**
     * API7: 用户登录
     * @param username
     * @param password
     * @returns {Promise<axios.AxiosResponse<any>>}
     */
    getUserLogin(credentials){
        return apiClient.post(`/user/login`, credentials);
    }

};


//记得npm install pinia安装配置Pinia（全局状态管路员） 安装完去mian.js注册