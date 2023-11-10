import request from "../../request/Index.js";

export function getAbout(){
    return request.get('/about')
}
export function uploadAbout(about){
    return request.post('/about',{
        about
    })
}