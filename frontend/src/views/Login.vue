<script setup>
import {onMounted, ref} from "vue";
import {login} from "../api/backend/user.js";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

let user = ref()
let password = ref()
const router = useRouter()
onMounted(() => {
  if(localStorage.getItem('sa-token')){
    ElMessage({
      message: '您已经登录了，即将跳转至后台管理页面',
      type: 'info'
    })
    setInterval(() => {
      router.push('/backend')
    },1500)
  }
})
const handleLogin =async () => {
 const res =  await login(user.value,password.value)
 if(res.code === 200){
   ElMessage({
     message:res.msg,
     type: 'success'
   })
   localStorage.setItem('sa-token',res.data);
  await router.push('/backend')
 }else{
   ElMessage({
     message:res.msg,
     type: 'error'
   })
 }
}
</script>

<template>
  <div class="container">
  <div class="login">
    <div class="box">
      <p class="table">Login</p>
      <br>
      <input v-model="user" type="text" placeholder="账号">
      <input v-model="password" type="password" placeholder="密码">
      <br>
      <el-button @click="handleLogin" class="go">GO</el-button>
    </div>
  </div>
  </div>
</template>

<style scoped>
*{
  padding: 0;
  margin: 0
}
.container{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: url('../assets/background.jpg');
  background-size: cover;
}
.login{
  width: 550px;
  height: 400px;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(
      to right bottom,
      rgba(255,255,255,.7),
      rgba(255,255,255,.5),
      rgba(255,255,255,.4)
  );
  /* 使背景模糊化 */
  backdrop-filter: blur(10px);
  box-shadow: 0 0 20px #a29bfe;
  border-radius: 15px;
}

.table{
  font: 900 40px '';
  text-align: center;
  letter-spacing: 5px;
  color: #3d3d3d;
}

.box{
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.box input{
  width: 400px;
  height: 100%;
  margin-bottom: 20px;
  outline: none;
  border: 0;
  padding: 10px;
  background-color: transparent;
  border-bottom: 3px solid rgb(150, 150, 240);
  font: 900 16px '';
}

.go{
  text-align: center;
  display: block;
  height: 48px;
  padding: 12px;
  font: 900 20px '';
  border-radius: 10px;
  margin-top: 20px;
  color: #fff;
  letter-spacing: 3px;
  background-image: linear-gradient(to left,
  #fd79a8,#a29bf6);
}
a{
  text-decoration: none;
}
</style>