import request from '../../request/Index.js'

export function viewBlog(){
    return request.post('/view')
}
export function getView(){
    return request.get('/view')
}