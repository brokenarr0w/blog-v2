<script setup>
import {MoreFilled} from '@element-plus/icons-vue'
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getBlogCount, getBlogList} from "../../../api/backend/blog.js";

const router = useRouter()
let count = ref()
let blogList = ref([])
onMounted(async () => {
  const countRes =  await getBlogCount()
  count.value = countRes.data
  const res = await getBlogList()
  blogList.value = res.data
})

const handleClick = (id) => {
  router.push(`/frontend/blog/${id}`)
}
</script>

<template>
  <div>
    <el-card>
      <template #header>
        <div style="text-align: center">
          <h1>文章归档</h1>
          <p>现在有{{count}}篇文章</p>
        </div>
      </template>
    <el-timeline>
    <el-timeline-item v-for="item in blogList" :timestamp="item.createTime" placement="top" :icon="MoreFilled">
    <div class="archive-item" style="cursor: pointer" @click="handleClick(item.id)">
      <span>{{item.title}}</span>
    </div>
    </el-timeline-item>
    </el-timeline>
    </el-card>
  </div>
</template>

<style scoped>
.archive-item{
  background-color: #ccc;
  padding: 10px;
  position: relative;
  display: inline-block;
}
.archive-item::after{
  width: 0;
  height: 0;
  content: "";
  border-top: 10px solid transparent;
  border-bottom: 10px solid transparent;
  border-right: 10px solid #ccc;
  position: absolute;
  top: 10px;
  left: -5px;
}
</style>