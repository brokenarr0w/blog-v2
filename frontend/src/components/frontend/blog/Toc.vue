<script setup>

import {MdCatalog} from "md-editor-v3";
import {defineProps, onMounted, ref} from 'vue'

const prop = defineProps(['id'])
const scrollElement = document.documentElement
let id = ref()
onMounted(() => {
  setTimeout(()=>{
    id.value= 'preview-only'
  },500)

})
</script>

<template>
<div style="position: sticky;top: 70px;">
  <el-card style="margin: 10px 0;">
  <MdCatalog v-if="id"  :editorId="id" :scrollElement="scrollElement" />
  </el-card>
</div>
</template>

<style scoped>

</style>