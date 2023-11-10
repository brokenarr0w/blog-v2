<script setup>
import BlogItem from "../../../components/frontend/blog/BlogItem.vue";
import Pagination from "../../../components/frontend/blog/Pagination.vue";

import {defineProps, onMounted} from "vue";
import SearchBlog from "../../../components/frontend/common/SearchBlog.vue";

let prop = defineProps(['blogList','getBlogList','count'])
onMounted(() => {
  prop.getBlogList(1)
})


</script>

<template>
  <div>
  <SearchBlog :getBlogList="getBlogList"/>
  <BlogItem :blogList="blogList"/>
  <Pagination :getBlogList="getBlogList" :count="count"/>
  </div>
</template>

<style scoped>

</style>