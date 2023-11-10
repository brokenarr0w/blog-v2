<script setup>
import {Document, Folder, House, Iphone, SwitchFilled} from "@element-plus/icons-vue";
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getCategoryList} from "../../../api/backend/category.js";

const router = useRouter()

let color = ref('transparent')
let categoryList = ref([])
onMounted(async () => {
 const res = await getCategoryList()
  categoryList.value = res.data
})
</script>

<template>
<div class="navbar">
  <el-menu mode="horizontal" router  default-active="/frontend" :background-color="color">
    <div style="display: flex;align-content: center;">
      <el-text class="title" size="large">Thunder</el-text>
    </div>
    <el-space>

    <el-menu-item index="/">
      <el-icon><House /></el-icon>首页
    </el-menu-item>
    <el-sub-menu index="/">
      <template #title><el-icon><Folder /></el-icon>分类</template>
     <el-menu-item v-for="item in categoryList" :index="'/frontend/category/'+item.id">
        {{item.categoryName}}
     </el-menu-item>
    </el-sub-menu>
    <el-menu-item index="/frontend/archive">
      <el-icon><Document /></el-icon>
      归档
    </el-menu-item>
    <el-menu-item index="/frontend/moments">
      <el-icon><Iphone /></el-icon>
      动态
    </el-menu-item>
    <el-menu-item index="/frontend/about">
      <el-icon><SwitchFilled /></el-icon>
      关于我
    </el-menu-item>
    </el-space>

  </el-menu>
</div>
</template>

<style scoped>
.title{
  margin: 0 50px;
}
.navbar{
  width: 100%;
  position: fixed;
  z-index: 1000;
}

</style>