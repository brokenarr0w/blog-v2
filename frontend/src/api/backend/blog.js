import request from "../../request/Index.js"

export function getBlogById(id){
    return request.get('blog',{
        params:{
            id: id
        }
    })
}
export function getBlogListByPageNum(page, pageNum, name){
    return request.get('blog/list',{
        params:{
            page:page,
            pageNum: pageNum,
            name: name
        }
    })
}
export function saveBlog(blog){
    return request.post('blog',{
        data:blog
    })
}
export function deleteBlog(blog){
    return request.delete('blog',{
        params:{
            blog
        }
    })
}
export function getBlogByCategoryId(id,page,pageNum){
    return request.get('blog/category',{
        params:{
            id,page,pageNum
        }
    })
}
export function getBlogCount(){
    return request.get('/blog/count')
}
export function getBlogCountByCategory(id){
    return request.get('/blog/category/count',{
        params:{
            id
        }
    })
}
export function getBlogByTagId(id,page,pageNum){
    return request.get('blog/tag',{
        params:{
            id,page,pageNum
        }
    })
}
export function getBlogCountByTag(id){
    return request.get('blog/tag/count',{
        params:{
            id
        }
    })
}
export function getBlogList(){
    return request.get('blog/all')
}
export function getBlogByIsTop(){
    return request.get('blog/top')
}
export function getPrevById(id){
    return request.get('blog/prev',{
        params:{id}
    })
}
export function getNextById(id){
    return request.get('blog/next',{
        params:{id}
    })
}
export function getBlogTitleList(){
    return request.get('blog/title')
}