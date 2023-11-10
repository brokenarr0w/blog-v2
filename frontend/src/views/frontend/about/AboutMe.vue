<script setup>
import {onMounted, ref} from "vue";
import {getAbout} from "../../../api/backend/about.js";
import {MdPreview} from "md-editor-v3";
import 'md-editor-v3/lib/preview.css';

let data = ref({})
onMounted(async () => {
  const res = await getAbout()
  data.value = res.data
})

</script>

<template>
  <div style="margin-bottom: 10px;">
    <el-card>
      <template #header>
        <div style="text-align: center">
        <h1>{{data.title}}</h1>
          <div style="margin-top: 15px;">
          <meting-js v-if="data.musicId"
              server="netease"
              type="playlist"
              :id="String(data.musicId)"
              list-folded="true">
          </meting-js>
          </div>
        </div>
      </template>
      <div>
      <MdPreview v-if="data.content" v-model="data.content"></MdPreview>
      </div>
    </el-card>
  </div>
</template>

<style scoped>

</style>