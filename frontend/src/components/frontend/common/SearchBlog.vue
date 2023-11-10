<script setup>
import {defineProps, ref} from "vue";

let props = defineProps(['getBlogList'])
let text = ref()
</script>

<template>
  <div class="search">
    <el-input v-model="text">
    </el-input>
    <el-button @click="getBlogList(1,text)" style="margin-left: 20px;" type="primary">搜索</el-button>
  </div>
</template>

<style scoped>
.search{
  width: 35%;
  display: flex;
  margin-left: 40vw;
  margin-bottom: 10px;
}
</style>