<template>
  <el-menu
      style="width: 200px;min-height: calc(100vh - 50px)"
      active-text-color="#ffd04b"
      router
      background-color="#545c64"
      class="el-menu-vertical-demo"
      :default-active="path"
      text-color="#fff"
  >
    <el-sub-menu index="1">
      <template #title>
        <el-icon><UserFilled /></el-icon>

        <span>人员管理</span>
      </template>
      <el-menu-item index="teacher" >教师管理</el-menu-item>
      <el-menu-item index="student" >学生管理</el-menu-item>
    </el-sub-menu>

    <el-menu-item index="classes">
      <el-icon><HomeFilled /></el-icon>
      <span>班级管理</span>
    </el-menu-item>

    <el-menu-item index="curriculum">
      <el-icon><Setting /></el-icon>
      <span>课程管理</span>
    </el-menu-item>

    <el-menu-item index="grade">
      <el-icon><Finished /></el-icon>
      <span>成绩管理</span>
    </el-menu-item>
  </el-menu>
</template>

<script>

import {Location,Setting,Menu,Finished,UserFilled,HomeFilled} from "@element-plus/icons";

export default {

  name: "Aside",
  data(){
    return{
      path:this.$route.path
    }
  },
  components: {
    Location,
    Setting,
    Menu,
    Finished,
    UserFilled,
    HomeFilled,
  },
  }
</script>

<style scoped>

</style>