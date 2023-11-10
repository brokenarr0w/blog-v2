<script setup>
import 'md-editor-v3/lib/style.css';
import {MdEditor} from "md-editor-v3";
import {onMounted, ref} from "vue";
import {getAbout, uploadAbout} from "../../../api/backend/about.js";
import {handleResponse} from "../../../api/backend/alert.js";

let formData = ref({
  title: '',
  musicId: '',
  content:'',
})
onMounted(async ()=> {
 const res = await getAbout()

  formData.value=res.data
})
const handleUpdate =async () => {
  const res = await uploadAbout(formData.value)
  handleResponse(res)
}
</script>

<template>
  <div>
    <el-text tag="b" style="margin-top: 20px;" >标题</el-text>
    <br/>
    <el-input style="width: 300px;margin-top: 20px;" size="large" v-model="formData.title"></el-input>
    <br/>
    <el-text tag="b" style="margin-top: 20px;">网易云id</el-text>
    <br/>
    <el-input style="width: 300px;margin-top: 20px;" size="large"  v-model="formData.musicId"></el-input>
    <br/>
    <el-text tag="b">正文</el-text>
    <md-editor style="margin-top: 20px;" v-model="formData.content"></md-editor>


    <br/>
    <el-button type="primary" size="large" style="margin-top: 20px;" @click="handleUpdate">√ 保存</el-button>
  </div>
</template>

<style scoped>

</style>