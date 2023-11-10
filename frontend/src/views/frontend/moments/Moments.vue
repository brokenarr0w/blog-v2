<script setup>
import {onMounted, ref} from "vue";
import {getMomentCount, getMomentListByPageNum, momentLike} from "../../../api/backend/moment.js";
import {GoodTwo, ThumbsUp} from "@icon-park/vue-next";
import {ElNotification} from "element-plus";

let momentList = ref([])
let count = ref(0)
const getMomentList =async (page) => {
 const res = await getMomentListByPageNum(page,10,'')
  const countRes = await getMomentCount()
 momentList.value = res.data.records
  count.value = countRes.data
}
onMounted(async () => {
  await getMomentList(1)
  if(!localStorage.getItem("like_data")){
    localStorage.setItem("like_data", JSON.stringify({}))

  }
  like_data.value = JSON.parse(localStorage.getItem("like_data"))
})
let like_data = ref({})
const handleLike =async (id) => {
  if(!like_data.value[id]){
    console.log("还没有点过赞")
    const res = await momentLike(id)
    if(res.code === 200){
      ElNotification({
        title: '提示',
        message: "点赞成功",
        type: 'success'
      })
      like_data.value[id] = true
      localStorage.setItem("like_data",JSON.stringify(like_data.value))
    }
  }else{
      ElNotification({
        title: '提示',
        message: "不能重复点赞哦",
      })
  }
}
const handleChange =async (page) => {
const res = await getMomentList(page)
 momentList.value = res.data.records
}
</script>

<template>
  <div>
    <el-card>
      <template #header>
        <div style="text-align: center">
        <h1>我的动态</h1>
        </div>
      </template>
  <el-timeline>
    <el-timeline-item v-for="item in momentList" center :timestamp="item.createTime" placement="top">
    <el-card>
      <template #header>
        {{item.content}}
      </template>
      <div style="cursor: pointer" @click="handleLike(item.id)">
      <thumbs-up v-if="!like_data[item.id]" theme="outline" size="24" fill="#333" />
        <good-two v-else theme="filled" size="24" fill="#dd233c"/>
      {{item.likes}}

      </div>
    </el-card>
    </el-timeline-item>
  </el-timeline>
    </el-card>
    <div class="pagination">
      <el-pagination layout="prev, pager, next" :total="count" @current-change="handleChange">

      </el-pagination>
    </div>
  </div>
</template>

<style scoped>
.pagination{
  text-align: center;
  display: flex;
  justify-content: center;
  margin: 20px 0;
}
</style>