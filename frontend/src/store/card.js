import {defineStore} from "pinia";

const useCardStore = defineStore('cardStore', {
    state: () => ({
        avatar: '',
        name: '',
        rollText: '',
        github: '',
        music: '',
        qq: '',
        bilibili: '',
        diy1: '',
        diy2: '',
    }),
    actions: {
        setAvatar(avatar) {
            this.avatar = avatar;
        },
        setName(name) {
            this.name = name;
        },
        setRollText(rollText) {
            this.rollText = rollText;
        },
        setGithub(github) {
            this.github = github;
        },
        setMusic(music) {
            this.music = music;
        },
        setQQ(qq) {
            this.qq = qq;
        },
        setBilibili(bilibili) {
            this.bilibili = bilibili;
        },
        setDIY1(diy1) {
            this.diy1 = diy1;
        },
        setDIY2(diy2) {
            this.diy2 = diy2;
        }
    }
})
export default useCardStore;