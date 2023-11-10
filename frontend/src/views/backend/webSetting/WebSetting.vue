<script setup>
import {onMounted, ref} from "vue";
import {getCard, getSetting, saveSetting} from "../../../api/backend/setting.js";
import {handleResponse} from "../../../api/backend/alert.js";

let cardData = ref({
  avatar: '',
  name: '',
  rollText: '',
  github: '',
  music: '',
  qq:'',
  bilibili:'',
  diy1:'',
  diy2:'',

})

let settingData = ref({
  title:'',
  suffix:'',
  copyright: '',
  record: '',
  img: '',
})
onMounted(async() => {
  const cardRes = await getCard()
  const settingRes = await getSetting()
  cardData.value = cardRes.data
  settingData.value = settingRes.data
})
const handleSave =async () => {
  const res = await saveSetting(cardData.value,settingData.value)
  handleResponse(res)
}
</script>

<template>
  <el-row :gutter="24" style="height: 100vh">
    <el-col :span="11">
      <el-card style="height: 100%;" shadow="hover">
      <template #header>
        <h3>基础设置</h3>
      </template>
    <el-form :model="settingData">
      <el-form-item label="网站名称">
        <el-input v-model="settingData.title"></el-input>
      </el-form-item>
      <el-form-item label="网站前缀">
        <el-input v-model="settingData.suffix"></el-input>
      </el-form-item>
      <el-form-item label="Copyright">
        <el-input v-model="settingData.copyright"></el-input>
      </el-form-item>
      <el-form-item label="备案号">
        <el-input v-model="settingData.record"></el-input>
      </el-form-item>
      <el-form-item label="页脚图片">
        <el-input v-model="settingData.img"></el-input>
      </el-form-item>
    </el-form>
    </el-card>
    </el-col>
    <el-col :span="11">
      <el-card style="height: 100%;" shadow="hover">
        <template #header>
          资料卡设置
        </template>
        <el-form :model="cardData">
          <el-form-item label="头像">
            <el-input v-model="cardData.avatar"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="cardData.name"></el-input>
          </el-form-item>
          <el-form-item label="滚动个签">
            <el-input v-model="cardData.rollText"></el-input>
          </el-form-item>
          <el-form-item label="Github">
            <el-input v-model="cardData.github"></el-input>
          </el-form-item>
          <el-form-item label="网易云">
            <el-input v-model="cardData.music"></el-input>
          </el-form-item>
          <el-form-item label="QQ">
            <el-input v-model="cardData.qq"></el-input>
          </el-form-item>
          <el-form-item label="Bilibili">
            <el-input v-model="cardData.bilibili"></el-input>
          </el-form-item>
          <el-form-item label="自定义1">
            <el-input v-model="cardData.diy1"></el-input>
          </el-form-item>
          <el-form-item label="自定义2">
            <el-input v-model="cardData.diy2"></el-input>
          </el-form-item>
        </el-form>
      </el-card>
    </el-col>
  </el-row>
  <div style="display:flex; margin:15px 0;width: 100%;    justify-content: flex-end;">
    <el-button type="primary" size="large" style="float: right" @click="handleSave">√ 保存 </el-button>
  </div>
</template>

<style scoped>

</style>