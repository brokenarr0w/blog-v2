<script setup>
import {onMounted, ref} from "vue";
import {getTagList} from "../../../api/backend/tag.js";
import {useRouter} from "vue-router";

const router = useRouter()
let tagList = ref([])
onMounted(async () => {
const res = await getTagList()
tagList.value = res.data
})
const goTag = (id) => {
  router.push(`/frontend/tag/${id}`)
}
</script>

<template>
<div style="margin-top: 15px;">
  <el-card>
    <template #header>
      <el-text type="info">标签云</el-text>
    </template>
    <el-row>
      <el-tag @click="goTag(item.id)" v-for="item in tagList"  :color="item.color" round style="cursor: pointer;" class="tag">
        <el-text size="small" style="color: #fff">{{item.name}}</el-text>
      </el-tag>
    </el-row>
  </el-card>
</div>
</template>

<style scoped>
.tag:hover{
  animation: pulse;
  animation-duration: 2s;
}
</style>