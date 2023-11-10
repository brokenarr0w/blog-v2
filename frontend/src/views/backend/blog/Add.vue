<script setup>
import {onMounted, ref} from "vue";
import {MdEditor} from "md-editor-v3";
import {useRoute} from "vue-router";
import {compress} from 'image-conversion';
import 'md-editor-v3/lib/style.css';
import {getBlogById, saveBlog} from "../../../api/backend/blog.js";
import {getTagList} from "../../../api/backend/tag.js";
import {getCategoryList} from "../../../api/backend/category.js";
import {handleResponse} from "../../../api/backend/alert.js";
import baseURL from "../../../config/config.js";
import {Plus} from "@element-plus/icons-vue";
import {upload} from "../../../api/backend/file.js";
import request from "../../../request/Index.js";

let image = ref()
const route = useRoute()
let data = ref({})

let tagList = ref([])
let categoryList = ref([])
let tagData = ref([])
let categoryData = ref()
let tagResult = []
onMounted(async() => {
  const tag = await getTagList()
  tagList.value = tag.data
  const category = await getCategoryList()
  categoryList.value = category.data
  if(route.params.id){
    const blog = await getBlogById(route.params.id)
    data.value = blog.data
    categoryData.value=blog.data.categoryId
    tagData.value = blog.data.tagList
  }
})
const uploadBlog =async ()=> {
    tagResult = []
  data.value.tagList = tagData.value
  data.value.categoryId = categoryData
  console.log(tagResult);
  const res = await saveBlog(data.value)
  await handleResponse(res)
}
const calTime = () => {
  data.value.time = Math.round(data.value.words) / 200
}
const handleUpload =async  (file) => {
  console.log(file)
  const res =await compress(file.raw,0.6)
  const temp = new File([res],file.name,{
    type: res.type
  })
  console.log(temp)
  let data1 = new FormData()
  data1.append("file",temp)
  console.log(data1.get("file"));
  const img = await upload(data1)
  image.value = img.data
  data.value.image = image.value
}
const handleRemove = () => {
  image.value = null
  return true
}
const onUpload =async (files,callback) => {
  const res = await Promise.all(
      files.map((file) => {
        return new Promise((rev, rej) => {
          const form = new FormData();
          form.append('file', file);

          request
              .post('upload', form, {
                headers: {
                  'Content-Type': 'multipart/form-data'
                }
              })
              .then((res) =>{
                rev(res)})
              .catch((error) => rej(error));
        });
      })
  );

  callback(res.map((item) => baseURL+ item.data));

}
</script>

<template>
  <div>
<el-row :gutter="16">
  <el-col :span="12">
    文章标题
    <el-input v-model="data.title" style="margin-top: 20px;" size="large" placeholder="请输入标题"></el-input>
  </el-col>
  <el-col :span="12">
    文章首图
    <el-upload class="upload-demo" :limit="1" :auto-upload="false" :on-change="handleUpload" :action="baseURL+'upload'" :before-remove="handleRemove">
      <template #tip>
        <div style="font-size: 12px;color: #919191;">
          单次限制上传一张图片
        </div>
      </template>

     <el-image v-if="image" :src="baseURL+image"></el-image>
      <template v-if="!image" #trigger>
        <el-button><el-icon><Plus/></el-icon></el-button>
      </template>
    </el-upload>
  </el-col>
  <el-col :span="24" style="margin-top: 20px;">
    文章描述
    <MdEditor v-model="data.description" />
  </el-col>
  <el-col :span="24" style="margin-top: 20px;">
    文章正文
    <MdEditor v-model="data.content" @onUploadImg="onUpload" />
  </el-col>
  <el-col :span="12" style="margin-top: 20px;">
    <div>文章分类</div>
    <el-select v-model="categoryData">
      <el-option v-for="item in categoryList" :label="item.categoryName" :value="item.id">

      </el-option>
    </el-select>
  </el-col>
  <el-col :span="12" style="margin-top: 20px;">
  <div>文章标签</div>
  <el-select  v-model="tagData" multiple value-key="id" clearable>
    <el-option v-for="item in tagList" :label="item.name" :value="item"></el-option>
  </el-select>
</el-col>
  <el-col :span="8" style="margin-top: 20px;">
    字数
    <el-input v-model="data.words" size="large" style="margin-top: 20px;" @blur="calTime"  placeholder="请输入文章字数（会自动计算阅读时长）"></el-input>
  </el-col>
  <br/>

  <el-col :span="8" style="margin-top: 20px;">
    阅读时长
    <el-input v-model="data.time" size="large" style="margin-top: 20px;" placeholder="请输入阅读时长（可选）默认math.round(字数/200)"></el-input>
  </el-col>
  <el-col :span="8" style="margin-top: 20px;">
    浏览次数
    <el-input v-model="data.views" style="margin-top: 20px;" size="large"></el-input>
  </el-col>
  <el-col :span="8" style="margin-top: 20px;">

    <el-checkbox v-model="data.isTop" style="margin-top: 40px;">是否置顶</el-checkbox>
  </el-col>
</el-row>
  </div>
  <div style="display: flex;justify-content: flex-end;margin-top: 20px;">
    <el-button @click="uploadBlog" type="primary">
      √ 保存
    </el-button>
  </div>
</template>

<style scoped>

</style>