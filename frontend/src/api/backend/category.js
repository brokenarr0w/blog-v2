import request from "../../request/Index.js"

export function getCategoryList(){
    return request.get('category/list')
}
export function getCategoryListByPageNum(page,pageNum){
    return request.get('category/list/page',{
        params:{
            page,
            pageNum
        }
    })
}
export function uploadCategory(data){
    return request.post('category',{
        data
    })
}
export function getCategoryCount(){
    return request.get('category/count')
}
export function deleteCategory(id){
    return request.delete('category',{
        params:{
            id
        }
    })
}
export function getCategoryById(id){
    return request.get('category',{
        params:{
            id
        }
    })
}