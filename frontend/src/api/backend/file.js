import request from "../../request/Index.js";

export function upload(file){
    return request.post('upload',file)
}