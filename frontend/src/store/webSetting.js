import {defineStore} from "pinia";

const useWebSettingStore = defineStore("webSetting",{
    state: () => ({
        title : '',
        suffix: '',
        copyright: '',
        img: '',
        record:''
    }),
    actions:{
        setTitle(title) {
            this.Title = title;
        },
        setSuffix(suffix) {
            this.suffix = suffix;
        },
        setCopyright(copyright) {
            this.copyright = copyright;
        },
        setImg(img) {
            this.img = img;
        },
        setRecord(record) {
            this.record = record;
        }
    }
})
export default useWebSettingStore