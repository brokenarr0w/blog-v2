<script setup>

import {useRoute} from "vue-router";
import useClientStore from "../../../store/client.js";

const route = useRoute()
const props = defineProps(['count','getBlogList'])
const handleCurrentChange = (newPage) => {
  scrollToTop()
  props.getBlogList(newPage)

}
const scrollToTop = () => {
  if(route.name === 'home'){
    window.scrollTo({
      behavior: 'smooth',
      top: useClientStore().clientHeight
    })
  }else{
    window.scrollTo({
      behavior: 'smooth',
      top: 0
    })
  }
}
</script>

<template>

  <div>

    <el-row style=" justify-content: center; margin: 20px 0;">
      <el-pagination v-if="count" :total="count"
                      background
                      :page-size="10"
                      @current-change="handleCurrentChange"
                      layout="prev, pager, next">

      </el-pagination>
    </el-row>
  </div>
</template>

<style scoped>

</style>