<script setup>
import {onMounted, ref} from "vue";
import {Plus} from "@element-plus/icons-vue";
import {
  deleteCategory,
  getCategoryCount,
  getCategoryListByPageNum,
  uploadCategory
} from "../../../api/backend/category.js";
import {ElMessageBox} from "element-plus";
import {handleResponse} from "../../../api/backend/alert.js";

let dialogVisible = ref(false)
let data = ref([])
let category = ref({})
let count = ref()
onMounted(async () => {
  const res = await getCategoryListByPageNum(1,10)
  data.value = res.data.records
  const countRes = await getCategoryCount()
  count.value = countRes.data
})
const handleEdit =(item) => {
  category.value = item
  dialogVisible.value = true
}
const handleUpload =async () => {
  const res = await uploadCategory(category.value)
  handleResponse(res)
  setTimeout(() => window.location.reload(),1500)
}
const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除这个分类吗(会删除这个分类下的所有文章)','Warning',{
    confirmButtonText: '忍心的确认',
    cancelButtonText: '取消',
  }).then(async () => {
    const res = await deleteCategory(id)
    handleResponse(res)
    setTimeout(() => window.location.reload(),1500)
  })

}
const handlePagination =async (num) => {
  const res = await getCategoryListByPageNum(num,10)
  data.value = res.data.records
}
</script>

<template>
  <div >
    <div style="margin: 20px 0;"><el-button type="primary" @click="dialogVisible=true;category = {}"><el-icon><Plus /></el-icon>添加分类</el-button></div>

    <div class="table">
      <el-table style="width: 95%" :data="data">
        <el-table-column align="center" type="index" label="序号" :width="100"></el-table-column>
        <el-table-column align="center" prop="categoryName" label="名称" :width="480"></el-table-column>
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
  <el-dialog
      v-model="dialogVisible"
      title="添加分类"
  >
    <el-form>
      <el-form-item label="分类名称">
        <el-input v-model="category.categoryName"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleUpload">
          确定
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>