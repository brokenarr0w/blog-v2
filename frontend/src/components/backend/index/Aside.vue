<script setup>

</script>

<template>
  <div class="menu">
    <div style="text-align: center"><h2 style="color:#66ccff; padding-top: 20px;">Thunder</h2></div>
  <el-menu default-active="/backend" class="el-menu-vertical-demo" router background-color="#efefef">
      <el-menu-item index="/backend">
        首页
      </el-menu-item>
    <el-menu-item index="/backend/blog/write">
      写博客
    </el-menu-item>
    <el-menu-item index="/backend/moment/write">
      写动态
    </el-menu-item>
      <el-menu-item index="/backend/blog">
        博客管理
      </el-menu-item>
      <el-menu-item index="/backend/category">
        分类管理
      </el-menu-item>
    <el-menu-item index="/backend/tag">
      标签管理
    </el-menu-item>
    <el-menu-item index="/backend/moment">
    动态管理
    </el-menu-item>
    <el-menu-item index="/backend/comment">
      评论管理
    </el-menu-item>
    <el-menu-item index="/backend/setting">
      网站设置
    </el-menu-item>
    <el-menu-item index="/backend/about">
      关于我页面
    </el-menu-item>
  </el-menu>
  </div>
</template>

<style scoped>
.menu{
  min-height: 100%;
}
</style>