import {ElMessage} from "element-plus";

export function handleResponse(res){
    if(res.code === 200){
        ElMessage({
            message: res.msg,
            type: 'success'
        })
    }else{
        ElMessage({
            message: res.msg,
            type: 'error'
        })
    }
}