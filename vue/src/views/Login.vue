<template>
  <div style="width: 100%;height: 100vh;background-color: darkslateblue;overflow: hidden">
    <div style="width: 400px;margin: 100px auto">
      <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px 0">欢迎登录</div>
      <el-form ref="form" :model="form">
        <el-form-item>
          <el-radio v-model="form.role" label="0" size="large" style="color: #cccccc">管理员</el-radio>
          <el-radio v-model="form.role" label="1" size="large" style="color: #cccccc">教师</el-radio>
          <el-radio v-model="form.role" label="2" size="large" style="color: #cccccc">学生</el-radio>
        </el-form-item>
        <el-form-item>
          <el-input  v-model="form.num" size="normal">
            <template #prefix>
              <el-icon><User /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-input  v-model="form.password" size="normal" show-password>
            <template #prefix>
              <el-icon><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width:100%" type="primary" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>
</template>

<script>
import {Lock,User} from "@element-plus/icons";
import request from "@/utils/request";
export default {
  name: "Login",
  components: {
    Lock,
    User
  },
  data(){
    return{
      form:{},
    }
  },

  methods:{
    login(){
      if(this.form.role === '0'){
        request.post("/admin/login",this.form).then(res =>{
          if(res.code==='0'){
            this.$message({
              type:"success",
              message:"登录成功"
            })
            sessionStorage.setItem("user",JSON.stringify(res.data))
            this.$router.push("/teacher")
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
        })
      }

      if(this.form.role === '1'){
        request.post("/teacher/login",this.form).then(res =>{
          if(res.code==='0'){
            this.$message({
              type:"success",
              message:"登录成功"
            })
            sessionStorage.setItem("user",JSON.stringify(res.data))
            this.$router.push("/sub/teachercurriculum")
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
        })
      }

      if(this.form.role === '2'){
        request.post("/student/login",this.form).then(res =>{
          if(res.code==='0'){
            this.$message({
              type:"success",
              message:"登录成功"
            })
            sessionStorage.setItem("user",JSON.stringify(res.data))
            this.$router.push("/sub/studentcurriculum")
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
        })
      }
    }
  }
}
</script>

<style scoped>

</style>