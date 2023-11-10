<script setup>
import {onMounted} from "vue";
import useClientStore from "./store/client.js";
import debouce from "./utils/debouce.js";
import {getCard, getSetting} from "./api/backend/setting.js";
import useWebSettingStore from "./store/webSetting.js";
import useCardStore from "./store/card.js";

const clientStore = useClientStore();
const settingStore = useWebSettingStore()
const cardStore = useCardStore()
onMounted(async () => {
  clientStore.setWidth(window.innerWidth)
  clientStore.setHeight(window.outerHeight)
  const setting = await getSetting()
  const card = await getCard()
  settingStore.setTitle(setting.data.title);
  settingStore.setImg(setting.data.img);
  settingStore.setRecord(setting.data.record);
  settingStore.setSuffix(setting.data.suffix);
  settingStore.setCopyright(setting.data.copyright);

  cardStore.setAvatar(card.data.avatar);
  cardStore.setName(card.data.name);
  cardStore.setRollText(card.data.rollText);
  cardStore.setGithub(card.data.github);
  cardStore.setMusic(card.data.music);
  cardStore.setQQ(card.data.qq);
  cardStore.setBilibili(card.data.bilibili);
  cardStore.setDIY1(card.data.diy1);
  cardStore.setDIY2(card.data.diy2);
})
window.addEventListener('resize', debouce(() => {
  clientStore.setHeight(window.innerHeight)
  clientStore.setWidth(window.innerWidth)
},500));
</script>

<template>
<router-view/>
</template>

<style scoped>
</style>
