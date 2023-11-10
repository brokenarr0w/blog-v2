import request from "../../request/Index.js";

export function getMomentListByPageNum(page,pageNum,name){
    return request.get('moment/page',{
        params:{
            page,pageNum,name
        }
    })
}
export function getMomentById(id){
    return request.get('moment',{
        params:{
            id
        }
    })
}
export function saveMoment(moment){
    return request.post('moment',{
        data:moment
    })
}
export function getMomentCount(){
    return request.get('moment/count')
}
export function deleteMoment(id){
    return request.delete('moment',{
        params:{
            id
        }
    })
}
export function momentLike(id){
    return request.post('moment/like',{
        id
    })
}