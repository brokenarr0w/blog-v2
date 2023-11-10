import request from '../../request/Index.js'

export function login(username,password){
    return request.post('/login',{
        data:{
            username,
            password,
        }
    })
}