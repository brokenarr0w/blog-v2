<script setup>
import {onMounted, ref} from "vue";
import {Plus} from "@element-plus/icons-vue";
import {deleteTag, getTagCount, getTagListByPageNum, saveTag} from "../../../api/backend/tag.js";
import {handleResponse} from "../../../api/backend/alert.js";
import colorData from "../../../common/tagColor.js";
import {ElMessageBox} from "element-plus";

let dialogVisible = ref(false)
let formData = ref({
  id: '',
  name: '',
  color: '',
})
let count = ref()
let tagList = ref([])
onMounted(async () => {
  const res =await getTagListByPageNum(1,10);
  tagList.value = res.data.records
  const countRes = await getTagCount()
  count.value = countRes.data
})
const handleEdit = (data) => {
  formData.value = data
  dialogVisible.value=true
}
const handleUpload =async () => {
  const res = await saveTag(formData.value)
  handleResponse(res)
  setTimeout(() => window.location.reload(),1500)
}
const handlePagination =async (num) => {
  const res = await getTagListByPageNum(num,10)
  tagList.value = res.data.records
}
const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除这个标签吗','Warning',{
    confirmButtonText: '忍心的确认',
    cancelButtonText: '取消',
  }).then(async () => {
    const res = await deleteTag(id)
    handleResponse(res)
    setTimeout(() => window.location.reload(),1500)
  })

}
</script>

<template>
  <div>
    <div style="margin: 20px 0;">
      <el-button type="primary" @click="dialogVisible=true"><el-icon><Plus /></el-icon>添加标签</el-button>
    </div>
    <div class="table">
      <el-table style="width: 95%" :data="tagList">
        <el-table-column align="center" label="序号" type="index" :width="100"></el-table-column>
        <el-table-column align="center" label="名称" prop="name" :width="480"></el-table-column>
        <el-table-column align="center" label="颜色" prop="color" :width="100">
          <template #default="scope">
            <div :style="{height: '25px',width: '200px',backgroundColor: scope.row.color}"></div>
          </template>
        </el-table-column>
        <el-table-column align="center">
          <template #header>
            操作
          </template>
          <template #default="scope">
            <el-button size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
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
    <el-form :model="formData">
      <el-form-item label="标签名称">
        <el-input v-model="formData.name"></el-input>
      </el-form-item>
      <el-form-item label="标签颜色">
        <el-select v-model="formData.color" style="width: 100%" @change="console.log(formData)">
          <el-option v-for="item in colorData" :key="item.id" :label="item.name" :value="item.color">
              <span style="float: left;width: 100px">{{item.name}}</span>
              <div :style="{height:'100%',width: '100px',backgroundColor: item.color,float:'left'}"></div>
             <el-text style="float: right" type="info">{{item.enName}}</el-text>
          </el-option>
        </el-select>
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