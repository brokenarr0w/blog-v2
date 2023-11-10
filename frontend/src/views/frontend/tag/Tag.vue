<script setup>

import BlogList from "../../frontend/blog/BlogList.vue";
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {getBlogByTagId, getBlogCountByTag} from "../../../api/backend/blog.js";
import {getTagById} from "../../../api/backend/tag.js";

let tagName = ref('')
let blogList = ref([])
let count = ref()
const route = useRoute()
const getBlogList =async (page) => {
  const res =await getBlogByTagId(route.params.id,page,10)
  console.log(res)
  blogList.value = res.data.records
  const countRes = await getBlogCountByTag(route.params.id)
  count.value = countRes.data

}
onMounted(async () => {
  await getBlogList(1)
  const nameRes = await getTagById(route.params.id)
  console.log(nameRes)
  tagName.value = nameRes.data
})
</script>

<template>
  <div >
    <div style="text-align: center;margin-bottom: 10px;">
      <h2>标签 {{ tagName.name }} 下的文章</h2>
    </div>
    <BlogList :getBlogList="getBlogList" :blogList="blogList" :count="count"></BlogList>
  </div>
</template>

<style scoped>

</style>