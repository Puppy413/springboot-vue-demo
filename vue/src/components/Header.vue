<template>
  <div style="height: 50px;line-height: 50px;border-bottom: 1px solid #ccc;display: flex">
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">教务管理系统</div>
    <div style="flex: 1"></div>
    <div style="width: 100px;padding-right: 30px;padding-top: 20px ">
      <el-dropdown>
        <span class="el-dropdown-link">
          {{user.name}}
        <el-icon><ArrowDown /></el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

  </div>
</template>

<script>
import {ArrowDown} from "@element-plus/icons";
export default {
  name: "Header",
  props: ['user'],
  components: {
    ArrowDown
  },
  data() {
    return {
      user:{}
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)

  }
}
</script>

<style scoped>

</style>