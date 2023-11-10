<script setup>
import {defineProps, onMounted} from 'vue'
import {useRouter} from "vue-router";
import baseURL from "../../../config/config.js";
import {Collection, Timer, View, Watch} from "@element-plus/icons-vue";

const router = useRouter()
const props = defineProps(['blogList'])
onMounted(() => {
})
const toBlogList = (item) => {
  router.push(`/frontend/tag/${item.id}`)
}
const toBlogListByCategory = (item) => {
  router.push(`/frontend/category/${item.categoryId}`)
}
const toBlog = (item) => {
  router.push(`/frontend/blog/${item.id}`)
}
</script>

<template>
  <div class="content">
    <el-card v-for="item in blogList" style="margin-bottom: 10px;">
      <template #header>
        <div class="card-header">
          <div class="title">
            <a @click="toBlog(item)">
              <h2 class="words" style="cursor: pointer">{{ item.title }}</h2>
            </a>
          </div>
          <div class="message">
            <el-space>
            <el-icon><Timer /></el-icon><el-text size="small">{{ item.createTime }}</el-text>
            <el-icon><View /></el-icon><el-text size="small">{{ item.views }}</el-text>
            <el-icon><Collection /></el-icon><el-text size="small">字数 = {{ item.words }}字</el-text>
              <el-icon><Watch /></el-icon><el-text size="small">阅读时长 = {{item.time}} 分钟</el-text>
            </el-space>
          </div>
          <el-tag>
            <i class="iconfont icon-wenjuan"></i>
            <el-button @click="toBlogListByCategory(item)" type="primary" link>
              {{ item.categoryName }}
            </el-button>
          </el-tag>
          <div class="typo">
            <h2>{{ item.description }}</h2>
            {{item.firstPicture}}

            <el-image v-if="item.image" :src="baseURL+item.image" fit="scale-down" :lazy="true"
                      style="margin:0 auto;max-height: 50%;"></el-image>
          </div>
          <div class="btn">
            <a @click="toBlog(item)" class="button">阅读本文</a>
          </div>
        </div>
      </template>
      <el-tag v-for="tag in item.tagList" :color="tag.color">
        <el-button @click="toBlogList(tag)" type="primary" link>
          {{ tag.name }}

        </el-button>
      </el-tag>
    </el-card>
  </div>
</template>

<style scoped>
.card-header {
  position: relative;
}

.content {
  margin-left: 20px;
  padding-right: 20px;
}

.title {
  display: flex;
  width: 100%;
  justify-content: center;
}
.words:hover{
  animation: pulse;
  animation-duration: 2s;
}

.message {
  display: flex;
  justify-content: center;
}
.btn{
  display: flex;
  justify-content: center;
}
.button {
  display: inline-block;
  position: relative;
  z-index: 1;
  cursor: pointer;
  overflow: hidden;
  text-decoration: none;
  font-family: sans-serif;
  font-weight: 600;
  font-size: 1em;
  padding: 0.75em 1em;
  color: blue;
  border: 0.15em solid blue;
  border-radius: 2em;
  transition: 4s;
}

.button:before,
.button:after {
  content: "";
  position: absolute;
  top: -1.5em;
  z-index: -1;
  width: 200%;
  aspect-ratio: 1;
  border: none;
  border-radius: 40%;
  background-color: rgba(0, 0, 255, 0.25);
  transition: 4s;
}

.button:before {
  left: -80%;
  transform: translate3d(0, 5em, 0) rotate(-340deg);
}

.button:after {
  right: -80%;
  transform: translate3d(0, 5em, 0) rotate(390deg);
}

.button:hover,
.button:focus {
  color: white;
}

.button:hover:before,
.button:hover:after,
.button:focus:before,
.button:focus:after {
  transform: none;
  background-color: rgba(0, 0, 255, 0.75);
}

a {
  text-decoration-line: none;
}



</style>