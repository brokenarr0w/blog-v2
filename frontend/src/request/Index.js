import axios from "axios";
import baseURL from "../config/config.js";
import NProgress from 'nprogress'
import router from "../router/index.js";

const request = axios.create({
    baseURL: baseURL,
    timeout: 30000,
})
request.interceptors.response.use(
    response => {

        let res = response.data
        if(res.code !== 200 && res.msg!=="密码错误" && res.msg !== "用户不存在"){
            return router.replace({path:'/error',query:{code:res.code,msg:res.msg}})
        }else{
            NProgress.done()
            return res
        }
    }
)
request.interceptors.request.use(request => {
    NProgress.start()
    if(localStorage.getItem("sa-token")){
        request.headers.set("satoken",localStorage.getItem("sa-token"))
    }
    return request
})
export default request