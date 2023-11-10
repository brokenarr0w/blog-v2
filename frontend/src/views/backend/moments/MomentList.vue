<script setup>
import {Search} from "@element-plus/icons-vue";
import {onMounted, ref} from "vue";
import {deleteMoment, getMomentCount, getMomentListByPageNum} from "../../../api/backend/moment.js";
import {useRouter} from "vue-router";
import {ElMessageBox} from "element-plus";
import {handleResponse} from "../../../api/backend/alert.js";

const router = useRouter()
let momentList = ref([])
let count = ref()
let text = ref('')
onMounted(async () => {
  const res = await getMomentListByPageNum(1, 10, text.value)
  momentList.value = res.data.records
  const countRes = await getMomentCount()
  count.value = countRes.data
})

const handleEdit = (data) => {
  router.push(`/backend/moment/write/${data.id}`)
}
const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除这个碎碎念吗','Warning',{
    confirmButtonText: '忍心的确认',
    cancelButtonText: '取消',
  }).then(async () => {
    const res = await deleteMoment(id)
    handleResponse(res)
    setTimeout(() => window.location.reload(),1500)
  })

}
const handlePagination =async (num) => {
  const res = await getMomentListByPageNum(num,10,text.value)
  momentList.value = res.data.records
}
const handleSearch =async () => {
  const res = await getMomentListByPageNum(1, 10, text.value)
  momentList.value = res.data.records
  handleResponse(res)
}
</script>

<template>
  <div>
    <div class="operation">
      <el-input v-model="text" style="width: 250px" placeholder="请输入标题" :prefix-icon="Search"></el-input>
      <el-button @click="handleSearch"><el-icon><Search/></el-icon></el-button>
    </div>
    <div class="table">
      <el-table style="width: 95%" :data="momentList">
        <el-table-column align="center" label="序号" type="index" :width="100"></el-table-column>
        <el-table-column align="center" label="内容" prop="content" :width="480"></el-table-column>
        <el-table-column align="center" label="点赞数" prop="likes" :width="100"></el-table-column>
        <el-table-column align="center" label="创建时间" prop="createTime" :width="200"></el-table-column>
        <el-table-column align="center" label="操作">
          <template #default="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.row)"
            >编辑</el-button
            >
            <el-button
                size="small"
                type="danger"
                @click="handleDelete(scope.row.id)"
            >删除</el-button
            ></template>
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