<script setup>
import {onMounted, ref} from "vue";
import {getBlogByCategoryId, getBlogCountByCategory} from "../../../api/backend/blog.js";
import {useRoute} from "vue-router";
import {getCategoryById} from "../../../api/backend/category.js";
import BlogList from "../../frontend/blog/BlogList.vue";

let categoryName = ref('')
let blogList = ref([])
let count = ref()
const route = useRoute()
const getBlogList =async (page) => {
  const res =await getBlogByCategoryId(route.params.id,page,10)
  blogList.value = res.data.records
  const countRes = await getBlogCountByCategory(route.params.id)
  count.value = countRes.data

}
onMounted(async () => {
  await getBlogList(1)
  const nameRes = await getCategoryById(route.params.id)
  categoryName.value = nameRes.data
})
</script>

<template>
  <div >
    <div style="text-align: center;margin-bottom: 10px;">
      <h2>分类 {{ categoryName.categoryName }} 下的文章</h2>
    </div>
    <BlogList :getBlogList="getBlogList" :blogList="blogList" :count="count"></BlogList>
  </div>
</template>

<style scoped>

</style>