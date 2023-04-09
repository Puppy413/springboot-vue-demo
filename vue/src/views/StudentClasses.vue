<template>
  <div style="padding: 10px">
    <!--    表格-->
    <el-table :data="tableData" border style="width: 100%" :row-class-name="tableRowClassName">
      <el-table-column prop="id" label="班级ID" width="180" sortable/>
      <el-table-column prop="cname" label="班级名称" width="180"/>
      <el-table-column prop="cdepartment" label="班级所属学院" width="180"/>
      <el-table-column prop="cmajor" label="班级所属专业" width="180"/>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" type="success" plain @click="showStudents(scope.row.studentList)">查看班级成员</el-button>
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

      <el-dialog title="班级成员" v-model="studentsVis" width="30%">
        <el-table :data="studentList" stripe border>
          <el-table-column prop="id" label="id"></el-table-column>
          <el-table-column prop="name" label="学生姓名"></el-table-column>
          <el-table-column prop="num" label="学号"></el-table-column>
        </el-table>

      </el-dialog>

      <el-dialog v-model="dialogVisible" title="新添表单" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="班级名称">
            <el-input v-model="form.cname" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="班级学院">
            <el-select v-model="form.cdepartment"  placeholder="选择学院">
              <el-option value="计算机学院" label="计算机学院">计算机学院</el-option>
              <el-option value="软件学院" label="软件学院">软件学院</el-option>
              <el-option value="经管学院" label="经管学院">经管学院</el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="班级专业">
            <el-select v-model="form.cmajor" placeholder="选择专业">
              <el-option-group label="计算机学院">
                <el-option value="计算机科学与技术" label="计算机科学与技术">计算机科学与技术</el-option>
                <el-option value="大数据技术" label="大数据技术">大数据技术</el-option>
                <el-option value="空间信息技术" label="空间信息技术">空间信息技术</el-option>
              </el-option-group>
              <el-option-group label="软件学院">
                <el-option value="软件工程" label="软件工程">软件工程</el-option>
                <el-option value="软件编译" label="软件编译">软件编译</el-option>
                <el-option value="软件安全" label="软件安全">软件安全</el-option>
              </el-option-group>
              <el-option-group label="经管学院">
                <el-option value="会计" label="会计">会计</el-option>
                <el-option value="审计" label="审计">审计</el-option>
                <el-option value="财务管理" label="财务管理">财务管理</el-option>
              </el-option-group>
            </el-select>
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
          <el-form-item label="班级名称">
            <el-input v-model="form.cname" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="班级学院">
            <el-select v-model="form.cdepartment"  placeholder="选择学院">
              <el-option value="计算机学院" label="计算机学院">计算机学院</el-option>
              <el-option value="软件学院" label="软件学院">软件学院</el-option>
              <el-option value="经管学院" label="经管学院">经管学院</el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="班级专业">
            <el-select v-model="form.cmajor" placeholder="选择专业">
              <el-option-group label="计算机学院">
                <el-option value="计算机科学与技术" label="计算机科学与技术">计算机科学与技术</el-option>
                <el-option value="大数据技术" label="大数据技术">大数据技术</el-option>
                <el-option value="空间信息技术" label="空间信息技术">空间信息技术</el-option>
              </el-option-group>
              <el-option-group label="软件学院">
                <el-option value="软件工程" label="软件工程">软件工程</el-option>
                <el-option value="软件编译" label="软件编译">软件编译</el-option>
                <el-option value="软件安全" label="软件安全">软件安全</el-option>
              </el-option-group>
              <el-option-group label="经管学院">
                <el-option value="会计" label="会计">会计</el-option>
                <el-option value="审计" label="审计">审计</el-option>
                <el-option value="财务管理" label="财务管理">财务管理</el-option>
              </el-option-group>
            </el-select>
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
  name: 'StudentClasses',
  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      dialogVisible1: false,
      studentsVis: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      studentList:[],
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
    this.load()
  },
  methods: {
    tableRowClassName({ row, rowIndex }){
      for(let item of row.studentList) {
        if (item.name !== this.user.name) {
          return 'hidden_box'  //可以动态添加class样式
        }

      }
      return ''
    },

    showStudents(students){
      this.studentList = students
      this.studentsVis = true
    },

    load() {
      request.get("/classes", {
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

    add() {
      this.dialogVisible = true
      this.form = {}
    },

    update() {
      request.put("/classes", this.form).then(res => {
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
      request.post("/classes", this.form).then(res => {
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
      request.delete("/classes/"+ id).then(res => {
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

<style>
.el-table .hidden_box {
  display: none;
}


</style>