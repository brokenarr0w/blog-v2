<script setup>
import {defineProps, onMounted, toRefs} from "vue";
let props = defineProps(['comment'])
onMounted(() => {
})
</script>

<template>
<div class="comment" v-for="item in comment">
<div class="data">
  <el-avatar class="avatar" size="default" src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"/>
  <div class="UA">
  <el-text class="name">{{item.name}}</el-text>
  <el-text class="time" size="small" type="info">{{item.createTime}}</el-text>
    <el-space style="margin-left: 5px;">
  <el-tag>{{ item.appName }}</el-tag>
  <el-tag>{{item.platform}}</el-tag>
    </el-space>
  </div>
  <el-text class="context">{{ item.content }}</el-text>
</div>
</div>
</template>

<style scoped>
.comment{
  margin: 10px 0;
}
.data{
  display: grid;
  grid-template-rows: 25px 30px;
  justify-items: flex-start;
  row-gap: 2px;
  grid-template-columns: 50px 370px;
  grid-template-areas:
  "avatar name"
  "avatar context";
}
.data .avatar{
  grid-area: avatar;
}
.data .UA{
  grid-area: name;
}
.data .time{
  margin-left: 5px;
}
.data .context{
  grid-area: context;
}
</style>