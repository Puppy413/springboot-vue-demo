<template>
  <div style="padding: 10px">
    <!--    功能区域-->
    <div style="margin: 10px">
      <el-button type="primary" @click="add">新增</el-button>

    </div>
    <!--    搜索区域-->
    <div style="margin: 10px">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 10px" @click="load">查询</el-button>
    </div>

    <!--    表格-->
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="教师ID" width="180" sortable/>
      <el-table-column prop="name" label="教师姓名" width="180"/>
      <el-table-column prop="num" label="教师账号" width="180"/>
      <el-table-column prop="tsex" label="教师性别" width="180"/>
      <el-table-column prop="tage" label="教师年龄" width="180"/>
      <el-table-column prop="taddress" label="教师地址" width="180"/>
      <el-table-column prop="ttel" label="教师电话" width="180"/>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" type="primary" @click="handleEdit(scope.row)"
          >编辑
          </el-button
          >
          <el-button
              size="small"
              type="danger"
              @click="handleDelete(scope.row.id)"
          >删除
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size=pageSize
          :page-sizes="[5,10,20]"
          layout="total, sizes, prev, pager, next, jumper"
          :total=total>

      </el-pagination>

      <el-dialog v-model="dialogVisible" title="新添表单" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="教师姓名">
            <el-input v-model="form.name" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="教师账户">
            <el-input v-model="form.num" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="教师性别">
            <el-radio v-model="form.tsex" label="男">男</el-radio>
            <el-radio v-model="form.tsex" label="女">女</el-radio>
            <el-radio v-model="form.tsex" label="X">X</el-radio>
          </el-form-item>
          <el-form-item label="教师年龄">
            <el-input v-model="form.tage" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="教师地址">
            <el-input v-model="form.taddress" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="教师电话">
            <el-input v-model="form.ttel" style="width: 80%"/>
          </el-form-item>
        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save">
              确定
            </el-button>
          </span>
        </template>
      </el-dialog>

      <el-dialog v-model="dialogVisible1" title="更新表单" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="教师姓名">
            <el-input v-model="form.name" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="教师账户">
            <el-input v-model="form.num" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="教师密码">
            <el-input v-model="form.password" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="教师性别">
            <el-radio v-model="form.tsex" label="男">男</el-radio>
            <el-radio v-model="form.tsex" label="女">女</el-radio>
            <el-radio v-model="form.tsex" label="X">X</el-radio>
          </el-form-item>
          <el-form-item label="教师年龄">
            <el-input v-model="form.tage" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="教师地址">
            <el-input v-model="form.taddress" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="教师电话">
            <el-input v-model="form.ttel" style="width: 80%"/>
          </el-form-item>
        </el-form>

        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible1 = false">取消</el-button>
            <el-button type="primary" @click="update">
              确定
            </el-button>
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: 'Teacher',
  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      dialogVisible1: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/teacher", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },

    login(){
      request.post("/teacher/login",this.form).then(res =>{
        if(res.code==='0'){
          this.$message({
            type:"success",
            message:"登录成功"
          })
          this.$router.push("/teacher")
        }else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
      })
    },

    add() {
      this.dialogVisible = true
      this.form = {}
    },

    update() {
      request.put("/teacher", this.form).then(res => {
        console.log(res)
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "更新成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load() // 刷新表格的数据
        this.dialogVisible1 = false  // 关闭弹窗
      })
    },

    save() {
      request.post("/teacher", this.form).then(res => {
        console.log(res)
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "新增成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }

        this.load() // 刷新表格的数据
        this.dialogVisible = false  // 关闭弹窗
      })
    },

    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible1 = true
    },

    handleDelete(id) {
      console.log(id)
      request.delete("/teacher/"+ id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()  // 删除之后重新加载表格的数据
      })
    },
    handleSizeChange(pageSize) {   // 改变当前每页的个数触发
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {  // 改变当前页码触发
      this.currentPage = pageNum
      this.load()
    }

  }


}
</script>
