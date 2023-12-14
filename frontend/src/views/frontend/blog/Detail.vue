<script setup>
import {onMounted, ref} from "vue";
import {Collection, Timer, View, Watch} from "@element-plus/icons-vue";
import 'md-editor-v3/lib/preview.css';
import {useRoute, useRouter} from "vue-router";
import {getBlogById, getNextById, getPrevById} from "../../../api/backend/blog.js";
import {MdPreview} from "md-editor-v3";
import Comment from "../../../components/frontend/comment/Comment.vue";
const router = useRouter()
let id = ref('preview-only')
const route = useRoute()
let blog = ref({})
let prev = ref()
let next = ref()
onMounted(async () => {
  const res = await getBlogById(route.params.id)
  blog.value=res.data
  const prevRes = await getPrevById(route.params.id)
  const nextRes = await getNextById(route.params.id)
  prev.value = prevRes.data
  next.value = nextRes.data
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
        <el-tag style="margin: 10px 5px;padding: 10px" v-for="item in blog.tagList" :color="item.color">{{item.name}}</el-tag>
      </div>
      <div v-if="prev || next" class="otherBlog">
        <router-link  :to="prev == null ? '': prev.id"><span>{{ prev == null ? '这是第一篇文章' : '上一篇:'+prev.title }}</span></router-link>
        <router-link :to="next == null ? '' : next.id"><span>{{ next == null ? '这已经是最后一篇文章了':'下一篇:'+next.title }}</span></router-link>
      </div>
      <div class="comment">
        <Comment v-if="blog.id" :type="1" :blogId="blog.id" ></Comment>
      </div>

    </el-card>

  </div>
</template>

<style scoped>
.otherBlog{
  display: flex;
  justify-content: space-around;
  margin: 10px 0;
}
.otherBlog a{
  text-decoration: none;
}
.otherBlog span{
  color: #9f9f9f;
}
</style>