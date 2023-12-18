import request from '../../request/Index.js'
import axios from "axios";

export function login(username,password){
    return request.post('/login',{
        data:{
            username,
            password,
        }
    })
}
export function checkLogin(){
    return request.get("/login");
}
export function getUserIP(){
    return axios.get("https://api.ipify.org/?format=json")
}