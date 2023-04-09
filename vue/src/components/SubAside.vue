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
    <el-menu-item v-if="user.role===1" index="teachercurriculum">
      <el-icon><MessageBox /></el-icon>
      <span>所授课程</span>
    </el-menu-item>

    <el-menu-item v-if="user.role===1" index="teachergrade">
      <el-icon><Share /></el-icon>
      <span>所授课程成绩</span>
    </el-menu-item>

    <el-menu-item v-if="user.role===2" index="studentgrade">
      <el-icon><Star /></el-icon>
      <span>个人成绩</span>
    </el-menu-item>

    <el-menu-item v-if="user.role===2" index="studentcurriculum">
      <el-icon><Menu /></el-icon>
      <span>个人课程</span>
    </el-menu-item>

    <el-menu-item v-if="user.role===2" index="studentclasses">
      <el-icon><Position /></el-icon>
      <span>个人班级</span>
    </el-menu-item>


  </el-menu>
</template>

<script>

import {Location,Setting,Menu,Finished,Check,Position,Star,MessageBox,Share} from "@element-plus/icons";

export default {

  name: "SubAside",
  data() {
    return {
      path: this.$route.path,
      user: {},
    }
  },
  components: {
    Location,
    Setting,
    Menu,
    Finished,
    Check,
    Position,
    Star,
    MessageBox,
    Share,
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
  }
}

</script>

<style scoped>

</style>