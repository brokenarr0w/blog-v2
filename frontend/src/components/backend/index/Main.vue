<script setup>

import {Clock, Document, Pointer, View} from "@element-plus/icons-vue";
import {onMounted, ref} from "vue";
import {getView} from "../../../api/backend/view.js";
import dayjs from "dayjs";
import relativeTime from "dayjs/plugin/relativeTime";
import zh from "dayjs/locale/zh-cn";

dayjs.extend(relativeTime)
let s = dayjs('2023-10-31').fromNow(true);
dayjs.locale('zh')
let data = ref()
onMounted(async () => {
 const res = await getView()
  data.value=res.data
})
</script>

<template>
  <div v-if="data" class="card">
      <el-card shadow="hover" class="stats">
        <el-icon><View /></el-icon>
        <span style="padding-left: 50px;">访问人数</span>
        <div style="float: right;padding-right: 50px;">{{data.views}}人</div>
      </el-card>
      <el-card shadow="hover" class="stats">
        <el-icon><Document /></el-icon>
        <span style="padding-left: 50px;">博客总数</span>
        <div style="float: right;padding-right: 50px;">{{data.blogCount}}个</div>
      </el-card>
      <el-card shadow="hover" class="stats">
        <el-icon><Pointer /></el-icon>
        <span style="padding-left: 50px;">点赞总数</span>
        <div style="float: right;padding-right: 50px;">{{data.likeCount}}个</div>
      </el-card>
      <el-card shadow="hover" class="stats">
        <el-icon><Clock /></el-icon>
        <span style="padding-left: 50px;">创立至今</span>
        <div style="float: right;padding-right: 50px;">{{ s }}</div>
      </el-card>
  </div>
  <div class="welcome">
     <h1>欢迎来到Thunder博客后台管理系统：D   </h1>

  </div>
</template>

<style scoped>
.card{
  display: flex;
  justify-content: space-around;
}
.stats{
  width: 220px;
  margin-top: 10px;
}
.welcome{
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 50%;
}
</style>