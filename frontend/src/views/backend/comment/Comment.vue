<script setup>
import {onMounted, ref} from "vue";
import {getBlogTitleList} from "../../../api/backend/blog.js";
import {getCommentList} from "../../../api/backend/comment.js";


const options = ref()
const selected = ref()
const commentList = ref()
const commentTotal = ref()
onMounted(async () => {
 const res = await getBlogTitleList()
 options.value = res.data
  await getCommentListByPageNum(1, 1)
})
const getCommentListByPageNum =async (page,pageNum) => {
 const res = await getCommentList(page,pageNum)
 commentList.value = res.data.records;
 commentTotal.value = res.data.size;
}
</script>
<template>
  <div class="comment">
    <div class="select">
      <el-select v-model="selected" placeholder="请选择页面">
        <el-option v-for="item in options"
        :key="item.id"
        :label="item.title"
        :value="item.id"></el-option>
      </el-select>
      <el-table :data="commentList">
        <el-table-column prop="avatar" label="头像"/>
        <el-table-column prop="name" label="昵称"/>
        <el-table-column prop="site" label="网站"/>
        <el-table-column prop="IP" label="IP"/>
        <el-table-column prop="content" label="评论内容"/>
        <el-table-column prop="QQ" label="QQ"/>
        <el-table-column prop="page" label="所在页面"/>
        <el-table-column prop="createTime" label="创建时间"/>
        <el-table-column label="操作"></el-table-column>
      </el-table>
      <el-pagination style="display: flex;justify-content: center;margin-top: 10px;" layout="prev,pager,next" :total="commentTotal"></el-pagination>
    </div>
  </div>
</template>


<style scoped>
.comment{
  width: 100%;
}
</style>