import {defineStore} from "pinia";

const useClientStore = defineStore('client',{
    state: () => ({
        clientWidth: 0,
        clientHeight: 0,
    }),
    actions:{
        setWidth(value){
            this.clientWidth = value
        },
        setHeight(value){
            this.clientHeight=value
        },
    },
    getters: {
        getWidth(){
            return this.clientWidth+ 'px'
        },
        getHeight(){
            return this.clientHeight+ 'px'
        }
    }
})
export default useClientStore