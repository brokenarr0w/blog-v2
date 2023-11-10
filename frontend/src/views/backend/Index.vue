<script setup>
import Aside from "../../components/backend/index/Aside.vue";
import Header from "../../components/backend/index/Header.vue";
import useClientStore from "../../store/client.js";
import {useRoute} from "vue-router";

let route = useRoute()
const clientHeight = useClientStore().getHeight
</script>

<template>
  <div class="container" :style="{height: clientHeight}">
    <div class="header">
    <Header/>
  </div>
    <div class="aside">
    <Aside/>
    </div>
  <div class="main">
    <router-view :key="route.fullPath"/>
  </div>
  </div>
  <el-backtop :right="100" :bottom="100" />
</template>

<style scoped>
.container{
  display:grid;
  grid-template-areas:
  "aside header"
  "aside main";
  grid-template-columns: 1fr 5fr;
  grid-template-rows: 1fr 10fr;
  column-gap: 20px;
}
.header{
  grid-area: header;
}
.aside{
  grid-area: aside;
}
.main{
  grid-area: main;

}
</style>