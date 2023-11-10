import request from "../../request/Index.js"

export function getTagList() {
    return request.get('tag/list')
}
export function getTagListByPageNum(page,pageNum){
    return request.get('tag/page',{
        params:{
            page,pageNum
        }
    })
}
export function saveTag(data){
    return request.post('tag',{
        data
    })
}
export function getTagCount(){
    return request.get('tag/count')
}
export function deleteTag(id){
    return request.delete('tag',{
        params:{
            id
        }
    })
}
export function getTagById(id){
    return request.get('tag',{
        params:{
            id
        }
    })
}