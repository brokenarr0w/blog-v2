<script setup>
import Nav from "../../components/frontend/index/Nav.vue";
import Image from "../../components/frontend/index/Image.vue";
import PersonalCard from "../../components/frontend/sidebar/PersonalCard.vue";
import RandomBlog from "../../components/frontend/sidebar/RandomBlog.vue";
import TagCloud from "../../components/frontend/sidebar/TagCloud.vue";
import useClientStore from "../../store/client.js";
import Footer from "../../components/frontend/index/Footer.vue";
import {useRoute, useRouter} from "vue-router";
import {onMounted} from "vue";
import Toc from "../../components/frontend/blog/Toc.vue";
import {getUserIP} from "../../api/backend/user.js";

const router = useRouter()
const id = 'preview-only'
const route = useRoute()
const clientStore = useClientStore()
onMounted(async () => {
  if (!sessionStorage.getItem("ip")){
    const res = await getUserIP()
    console.log(res.data.ip)
    sessionStorage.setItem("ip",res.data.ip)
  }
})
</script>

<template>
  <div class="container" :style="{height: clientStore.getHeight}">
  <div class="header-container">
    <Nav/>
    <Image v-if="clientStore.clientHeight > 500 && clientStore.clientWidth > 500 && router.currentRoute.value.name === 'home' "/>
  </div>
  <div class="main">
    <el-row :gutter="16">
      <el-col :span="4">
        <PersonalCard v-if="clientStore.clientWidth > 500"/>
      </el-col>
      <el-col :span="16" :xs="24">
        <router-view :key="route.fullPath"/>
      </el-col>
      <el-col :span="4">
        <RandomBlog v-if="clientStore.clientWidth > 500"/>
        <TagCloud v-if="clientStore.clientWidth > 500"/>
          <Toc v-if="router.currentRoute.value.name==='detail'"/>

      </el-col>
    </el-row>
  </div>
    <Footer/>
    <el-backtop :right="100" :bottom="100" />
  </div>
</template>

<style scoped>
.main{
  padding-top: 100px;
}
.container{
  display: grid;
  grid-template-rows: auto 1fr auto;
}
.header-container{
  height: 100%;
}
</style>