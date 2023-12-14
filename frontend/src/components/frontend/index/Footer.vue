<script setup>
import useWebSettingStore from "../../../store/webSetting.js";
import data from '../../../common/footerData.js'
import {onMounted, ref} from "vue";
import {getHitokoto} from "../../../api/backend/outer.js";

const store = useWebSettingStore()
let hitokoto = ref('')
onMounted(async () => {
  const res = await getHitokoto()
  hitokoto.value = res.data
})
</script>

<template>
 <div style="width: 100%;background-color: #3e3b3b;height: 150px">
   <a href="https://hitokoto.cn/">
   <div v-if="hitokoto" style="text-align: center;margin: 10px 0">
   <el-text type="info">{{hitokoto.hitokoto}} -- {{hitokoto.from}}</el-text>
 </div>
   </a>
   <div class="copy">
      <div>
        <el-text size="large" type="info">Copyright &copy;{{ store.copyright }} <router-link style="color: rgb(255, 229, 0);text-decoration: none" to="/">
          {{ store.suffix }}
        </router-link>
        </el-text>
        <el-text size="large" style="margin: 0 15px">|</el-text>
        <a href="https://beian.miit.gov.cn/" style="color: rgb(255, 229, 0);">
          <el-text size="large">{{store.record}}</el-text>
        </a>
      </div>
   </div>
   <div class="badge">
        <div class="github-badge" v-for="item in data">
          <a :href="item.url">
          <div class="badge-subject" >
            <el-text size="small" style="color:#fff;"> {{item.title}} </el-text>
          </div>
          <div class="badge-value" :style="{backgroundColor:item.color}">
            <el-text size="small" style="color:#fff;"> {{item.value}} </el-text>
          </div>
          </a>
        </div>
   </div>
      </div>

</template>

<style scoped>
@import "../../../assets/css/badge.css";
.badge{
  text-align: center;
}
.copy{
  display: flex;
  justify-content: center;
  margin: 10px 0;
}
a{
  text-decoration: none;
}
</style>