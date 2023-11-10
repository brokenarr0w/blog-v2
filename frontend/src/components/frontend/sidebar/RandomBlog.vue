<script setup>
import {onMounted, ref} from "vue";
import {getBlogByIsTop} from "../../../api/backend/blog.js";
import dayjs from 'dayjs'
import baseURL from "../../../config/config.js";

let blogList = ref()
onMounted(async() => {
const res =  await getBlogByIsTop()
blogList.value =res.data
})
</script>

<template>
<div>
  <el-card>
    <template #header>
      <el-text type="info">这里有一些置顶的博客</el-text>

    </template>
    <div style="height: 100%;position: relative">

    <div class="item" v-for="item in blogList">
      <router-link :to="'/frontend/blog/'+item.id">
      <el-image v-if="item.image" class="image" :src="baseURL+item.image"></el-image>
      <span class="time" type="info">{{dayjs(item.createTime).format('YYYY-MM-DD')}}</span>
      <div class="words" type="info">{{item.title}}</div>
      </router-link>
    </div>
  </div>
  </el-card>
</div>
</template>

<style scoped>
.item{
  position: relative;
}
.image{
  opacity: 1;
  width: 100%;
  height : 75px
}
.time{
  position: absolute;
  left: 0;
  bottom: 25px;
  color: #efefef;
  font-size: 15px;
}
.words{
  position: absolute;
  left: 0;
  bottom: 5px;
  color: #efefef;
  font-size: 15px;
}
</style>