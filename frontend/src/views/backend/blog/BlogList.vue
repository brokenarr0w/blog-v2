<script setup>
import {Search} from "@element-plus/icons-vue";
import {onMounted, ref} from "vue";
import {deleteBlog, getBlogByCategoryId, getBlogCount, getBlogListByPageNum} from "../../../api/backend/blog.js";
import {useRouter} from "vue-router";
import {ElMessageBox} from "element-plus";
import {handleResponse} from "../../../api/backend/alert.js";
import {getCategoryList} from "../../../api/backend/category.js";

const router = useRouter()
let count = ref()
let blogList = ref([])
let categoryList = ref([])
let categoryData = ref()
let text = ref('')
onMounted(async () => {
  let res = await getBlogListByPageNum(1,10,'')
  let categoryRes = await getCategoryList()
  let countRes = await getBlogCount()
  categoryList.value = categoryRes.data
  if(res.code !== 200){
    handleResponse(res)
  }
  blogList.value = res.data["records"]
  count.value = countRes.data
})
const handleEdit = (data) => {
  router.push(`/backend/blog/write/${data.id.toString()}`)
  }

const handleDelete = (data) => {
  ElMessageBox.confirm('确定要删除这篇文章吗','Warning',{
    confirmButtonText: '忍心的确认',
    cancelButtonText: '取消',
  }).then(async () => {
    console.log(data)
  const res = await deleteBlog(data)
  handleResponse(res)
  setTimeout(() => window.location.reload(),1500)
  })
}
const handleSearch =async () => {
 const res = await getBlogListByPageNum(1,10,text.value)
 blogList.value = res.data.records
  handleResponse(res)
}
const handleChange = async () => {
  const res =await getBlogByCategoryId(categoryData.value.id,1,10)
  blogList.value = res.data.records
  handleResponse(res,res.msg)
}
const handlePagination =async (num) => {
  const res = await getBlogListByPageNum(num,10,text.value)
  blogList.value = res.data.records
}
</script>

<template>
  <div>
  <div class="operation">
    <el-select v-model="categoryData" placeholder="Select" value-key="id" @change="handleChange">
      <el-option v-for="item in categoryList" :label="item.categoryName" :key="item.id" :value="item"></el-option>
    </el-select>
    <el-input v-model="text" style="width: 250px" placeholder="请输入标题" :prefix-icon="Search"></el-input>
    <el-button @click="handleSearch"><el-icon><Search/></el-icon></el-button>
  </div>
    <div class="table">
      <el-table style="width: 95%" :data="blogList">
        <el-table-column align="center" label="序号" type="index" :width="100"></el-table-column>
        <el-table-column align="center" label="标题" prop="title" :width="280"></el-table-column>
        <el-table-column align="center" label="分类" prop="categoryName" :width="100"></el-table-column>
        <el-table-column align="center" label="置顶" :width="100">
          <template #default="scope">
            {{scope.row.isTop ? '是' : '否'}}
          </template>
        </el-table-column>
        <el-table-column align="center" label="创建时间" prop="createTime" :width="200"></el-table-column>
        <el-table-column align="center" label="最近更新" prop="updateTime" :width="200"></el-table-column>
        <el-table-column align="center" label="操作" :width="220">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="margin-top: 20px;">
      <el-pagination layout="prev, pager, next" :total="count" @current-change="handlePagination"></el-pagination>
    </div>
  </div>
</template>

<style scoped>
.table{
  width: 100%;
  margin-top: 50px;
  margin-left: 20px;
}
</style>