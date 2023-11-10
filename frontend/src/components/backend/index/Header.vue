<script setup>
import {useRouter} from "vue-router";

const title = useRouter().currentRoute.value.meta.title
const router = useRouter()
const onBack = () => {
  router.back()
}
</script>

<template>
<div style="padding-top: 20px; padding-left: 5px; display: flex">
  <el-page-header @back="onBack">
    <template #content>
      <el-text>{{title}}</el-text>
    </template>
  </el-page-header>
</div>
</template>

<style scoped>

</style>