<script setup>

import BlogList from "../../frontend/blog/BlogList.vue";
import {getBlogCount, getBlogListByPageNum} from "../../../api/backend/blog.js";
import {ref} from "vue";

let blogList = ref([])
let count = ref(0)
const getBlogList =async (page,name) => {
  const res = await getBlogListByPageNum(page,10,name)
  const countRes = await getBlogCount()
  blogList.value = res.data.records
  count.value = countRes.data
}
</script>

<template>
<div>
  <BlogList :blogList="blogList" :getBlogList="getBlogList" :count="count"/>
</div>
</template>

<style scoped>

</style>