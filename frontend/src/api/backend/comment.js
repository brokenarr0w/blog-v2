import request from "../../request/Index.js"

export function getAboutCommentsByPageNum(page,pageSize){
    return request.get("comment/page",{
        params:{page,pageSize,"type":2}
    })
}
export function getBlogCommentsByPageNum(page,pageSize,blogId){
    return request.get("comment/page",{
        params:{page,pageSize,"type":1,blogId}
    })
}
export function submitComment(content,name,email,commentType,appName,platform,blogId=null){
    return request.post("comment",{
         content,name,email,commentType,appName,platform,blogId
    })
}