<script setup>
import {onMounted, ref} from "vue";
import {Collection, Timer, View, Watch} from "@element-plus/icons-vue";
import 'md-editor-v3/lib/preview.css';
import {useRoute, useRouter} from "vue-router";
import {getBlogById} from "../../../api/backend/blog.js";
import {MdPreview} from "md-editor-v3";

const router = useRouter()
let id = ref('preview-only')
const route = useRoute()
let blog = ref({})
onMounted(async () => {
  const res = await getBlogById(route.params.id)

  blog.value=res.data
  console.log(res.data)
})
</script>

<template>

  <div>
    <el-card shadow="always" :body-style="{ padding: '20px' }">
      <template #header>
        <div style="text-align: center;">
          <h1>{{blog.title}}</h1>
        </div>
        <div style="text-align: center;">
          <el-space>
            <el-icon><Timer /></el-icon><el-text size="small">{{ blog.createTime }}</el-text>
            <el-icon><View /></el-icon><el-text size="small">{{ blog.views }}</el-text>
            <el-icon><Collection /></el-icon><el-text size="small">字数 = {{ blog.words }}字</el-text>
            <el-icon><Watch /></el-icon><el-text size="small">阅读时长 = {{blog.time}} 分钟</el-text>
          </el-space>
        </div>
      </template>
      <div>
        <span>{{blog.description}}</span>
        <md-preview :model-value="blog.content" :editor-id="id"></md-preview>
      </div>
      <div class="tag">
        <el-divider></el-divider>
        <el-tag v-for="item in blog.tagList">{{item.tagName}}</el-tag>
      </div>

    </el-card>

  </div>
</template>

<style scoped>

</style>