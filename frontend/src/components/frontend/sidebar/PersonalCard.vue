<script setup>
import {Github, MovieBoard, Music, TencentQq} from "@icon-park/vue-next";
import baseURL from "../../../config/config.js";
import useCardStore from "../../../store/card.js";
import {onMounted, ref} from "vue";

const cardStore = useCardStore();
let text = ref('')
text.value = cardStore.rollText
const rollText = () => {
  var binft = function (r) {
    function t() {
      return b[Math.floor(Math.random() * b.length)]
    }
    function e() {
      return String.fromCharCode(94 * Math.random() + 33)
    }
    function n(r) {
      for (var n = document.createDocumentFragment(), i = 0; r > i; i++) {
        var l = document.createElement("span");
        l.textContent = e(), l.style.color = t(), n.appendChild(l)
      }
      return n
    }
    function i() {
      var t = o[c.skillI];
      c.step ? c.step-- : (c.step = g, c.prefixP < l.length ? (c.prefixP >= 0 && (c.text += l[c.prefixP]), c.prefixP++) : "forward" === c.direction ? c.skillP < t.length ? (c.text += t[c.skillP], c.skillP++) : c.delay ? c.delay-- : (c.direction = "backward", c.delay = a) : c.skillP > 0 ? (c.text = c.text.slice(0, -1), c.skillP--) : (c.skillI = (c.skillI + 1) % o.length, c.direction = "forward")), r.textContent = c.text, r.appendChild(n(c.prefixP < l.length ? Math.min(s, s + c.prefixP) : Math.min(s, t.length - c.skillP))), setTimeout(i, d)
    }
    var l = "",
        o = text.value.split(',').map(function (r) {
          return r + ""
        }),
        a = 2,
        g = 1,
        s = 5,
        d = 75,
        b = ["rgb(110,64,170)", "rgb(150,61,179)", "rgb(191,60,175)", "rgb(228,65,157)", "rgb(254,75,131)", "rgb(255,94,99)", "rgb(255,120,71)", "rgb(251,150,51)", "rgb(226,183,47)", "rgb(198,214,60)", "rgb(175,240,91)", "rgb(127,246,88)", "rgb(82,246,103)", "rgb(48,239,130)", "rgb(29,223,163)", "rgb(26,199,194)", "rgb(35,171,216)", "rgb(54,140,225)", "rgb(76,110,219)", "rgb(96,84,200)"],
        c = {
          text: "",
          prefixP: -s,
          skillI: 0,
          skillP: 0,
          direction: "forward",
          delay: a,
          step: g
        };
    i()
  };
  binft(document.getElementById('rollText'));
}
onMounted(()=> {
  rollText()
})
</script>

<template>
<div>
  <el-card shadow="hover">
    <el-image  :src="baseURL+ 'avatar.jpg'"/>
    <div id="rollText"></div>
    <div class="content">
      <el-link :href="cardStore.qq">
    <div class="content-icon">
      <el-tooltip content="qq">
      <tencent-qq theme="multi-color" size="31" :fill="['#333' ,'#2F88FF' ,'#FFF' ,'#43CCF8']"/>
      </el-tooltip>
    </div>
      </el-link>
      <el-link :href="cardStore.github">
    <div class="content-icon">
      <el-tooltip content="github">
      <github theme="two-tone" size="31" :fill="['#333' ,'#2F88FF']"/>
      </el-tooltip>
    </div>
      </el-link>
      <el-link :href="cardStore.music">
      <div class="content-icon">
        <el-tooltip content="网易云音乐">
        <music theme="filled" size="31" fill="#333"/>
        </el-tooltip>
    </div>
      </el-link>
      <el-link :href="cardStore.bilibili">
      <div class="content-icon">
        <el-tooltip content="哔哩哔哩">
        <movie-board theme="filled" size="31" fill="#333"/>
        </el-tooltip>
    </div>
      </el-link>
    </div>
    <el-collapse >
      <el-collapse-item title="最喜欢玩的游戏">
        {{cardStore.diy1}}
      </el-collapse-item>
      <el-collapse-item title="待定">
        {{ cardStore.diy2 }}
      </el-collapse-item>
    </el-collapse>
  </el-card>
</div>
</template>

<style scoped>
.content{
  margin: 10px 0;
  display: flex;
  justify-content: space-evenly;
  text-align: center;
}
.content-icon{
  border-radius: 50px;
  width: 40px;
  height: 40px;
  display: block;
}
#rollText{
  font-size: 14px;
  margin: 10px 0;
  text-align: center;
}
</style>