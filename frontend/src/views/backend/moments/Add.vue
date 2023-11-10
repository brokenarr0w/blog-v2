<script setup>

import {MdEditor} from "md-editor-v3";
import 'md-editor-v3/lib/style.css';
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getMomentById, saveMoment} from "../../../api/backend/moment.js";
import {handleResponse} from "../../../api/backend/alert.js";

let route = useRoute();
const router = useRouter()
let formData = ref({
  id: '',
  content: '',
  createTime: '',
  likes: ''
})
onMounted(async () => {
  if(route.params.id){
    const res = await getMomentById(route.params.id)
    formData.value = res.data
  }
})
const handleSave =async () => {
  const res = await saveMoment(formData.value)
  handleResponse(res)
  setTimeout(() => router.push('/backend/moment'),1500)
}
</script>

<template>
<div>
  <el-text tag="b">动态内容</el-text>
  <md-editor v-model="formData.content" style="height: 300px;margin-top: 20px;" ></md-editor>
  <el-text tag="b" style="margin-top: 20px;">点赞数</el-text>
  <br/>
  <el-input v-model="formData.likes" style="width: 300px;margin-top: 20px;" size="large" ></el-input>
  <br/>
  <el-text tag="b" style="margin-top: 20px;">创建时间</el-text>
  <br/>
  <el-date-picker v-model="formData.createTime" style="margin-top: 20px;"></el-date-picker>
  <br/>
  <el-button type="primary" size="large" style="margin-top: 20px;" @click="handleSave">保存</el-button>
</div>
</template>

<style scoped>

</style>