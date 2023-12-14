<script setup>
import {MdEditor} from "md-editor-v3";
import {onMounted, ref, watch} from "vue";
import 'md-editor-v3/lib/style.css';
import CommentList from "./CommentList.vue";
import {
  getAboutCommentsByPageNum,
  getBlogCommentsByPageNum, submitComment,
} from "../../../api/backend/comment.js";
import {Toolbars} from "../../../config/MarkdownCommentToolbars.js";
import {handleResponse} from "../../../api/backend/alert.js";
import browser from 'browser-tool'
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";
let comments = ref([])
let form = ref({
  nickname: '',
  text: '',
  email:'',
  appName: '',
  platform: '',
})
let props = defineProps(["type","blogId"]);
let isClick = false
let router =  useRouter()
let count = ref()
const ruleFormRef = ref()
let currentPage = ref()
let rules = {
  nickname: [
    {required: true,message: '必须留个名字哦', trigger: 'blur' },
    {min: 3,max:10,message: "名字不宜过长哦",trigger: 'blur'}
  ],
  text: [
    {required: true,message: '必须留下点什么'}
  ]
}
watch(currentPage,async (newValue) =>{
  getCommentList(newValue,5)
})
const uploadComment =async (item) => {
  if (!item) return
  await item.validate(async (valid, fields) => {
    if (!valid) {
      ElMessage({
        message: "你还没填写好内容呢",
        type: "error"
      })

    } else {
      if (!isClick) {
        isClick = true
        setTimeout(() => {
          isClick = false
          router.go(0);
        }, 3000)
        let ua = browser()
        form.value.appName = ua.browser
        form.value.platform = ua.system + ' ' + ua.systemVersion

        let res = await submitComment(form.value.text, form.value.nickname, form.value.email, props.type, form.value.appName, form.value.platform, props.blogId);
        await handleResponse(res)

      } else {
        ElMessage({
          message: "不可短时间内多次发表评论哦",
          type: 'error'
        })
      }
    }
  })
}
const getCommentList =async (page,pageSize) => {
  if (props.type === 1) {
    let data = await getBlogCommentsByPageNum(page, pageSize, props.blogId);
    comments.value = data.data["records"]

  } else {
    let data = await getAboutCommentsByPageNum(page, pageSize);
    console.log(data.data)
    count.value = data.data.total
    comments.value = data.data.records
  }
}
onMounted(async () => {
  await getCommentList(1, 5)

})
</script>

<template>
<div class="comment">
  <el-form ref="ruleFormRef" :model="form" :rules="rules">

  <el-text size="large">发表评论</el-text>
      <el-form-item prop="text">
  <md-editor :toolbars="Toolbars" style="height: 400px" v-model="form.text" placeholder="评论千万条，开车第一条" :preview="false"></md-editor>
    </el-form-item>

    <div class="put">
      <el-form-item prop="nickname">
  <el-input class="item" v-model="form.nickname" placeholder="昵称(必填)"/>
      </el-form-item>
      <el-form-item>
  <el-input class="item" v-model="form.email" placeholder="邮箱"></el-input>
      </el-form-item>
  </div>
  <div class="submit">
    <el-form-item>
    <el-button  @click="uploadComment(ruleFormRef)">提交</el-button>
    </el-form-item>
  </div>
  </el-form>
  <div class="list">
    <el-text >评论 | 共{{ count }}条评论</el-text>
    <el-divider style="margin: 10px"/>
    <CommentList v-if="comments" :comment="comments"/>
    <el-pagination layout="prev, pager, next" :page-size=5 :total="count" v-model:current-page="currentPage">翻页</el-pagination>
  </div>
</div>
</template>

<style scoped>
.put{
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}
.put .item{
  margin-right: 10px;
}
.submit{
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
.list{
  margin-top: 15px;
}
</style>