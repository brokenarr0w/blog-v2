import request from "../../request/Index.js";

export function getCard(){
    return request.get('/setting/card')
}
export function getSetting(){
    return request.get('/setting')
}
export function saveSetting(card,setting){
    return request.post('/setting',{
        card,setting
    })
}