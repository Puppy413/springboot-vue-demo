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
      <el-table-column prop="id" label="课程ID" width="180" sortable/>
      <el-table-column prop="curname" label="课程名称" width="180"/>
      <el-table-column prop="curmajor" label="课程所属专业" width="180"/>
      <el-table-column prop="curyear" label="课程所属年份" width="180"/>
      <el-table-column prop="curtime" label="课时" width="180"/>
      <el-table-column prop="curteacher" label="任课教师" width="180"/>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" type="success" plain @click="showGrades(scope.row.gradeList)">查看课程成绩</el-button>
          <el-button size="small" type="primary" plain @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
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

      <el-dialog title="班级成员" v-model="gradesVis" width="30%">
        <el-table :data="gradeList" stripe border>
          <el-table-column prop="id" label="学生ID"></el-table-column>
          <el-table-column prop="gsname" label="学生姓名"></el-table-column>
          <el-table-column prop="gtname" label="任课教师"></el-table-column>
          <el-table-column prop="gcurname" label="课程名称"></el-table-column>
          <el-table-column prop="gmajor" label="课程所属专业"></el-table-column>
          <el-table-column prop="score" label="得分"></el-table-column>
          <el-table-column prop="gp" label="绩点"></el-table-column>
        </el-table>

      </el-dialog>

      <el-dialog v-model="dialogVisible" title="新添表单" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="课程名称">
            <el-input v-model="form.curname" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="课程所属专业">
            <el-select v-model="form.curmajor" placeholder="选择专业">
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
          <el-form-item label="课程所属年份">
            <el-input v-model="form.curyear" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="课时">
            <el-input v-model="form.curtime" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="任课教师">
            <el-input v-model="form.curteacher" style="width: 80%"/>
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
          <el-form-item label="课程名称">
            <el-input v-model="form.curname" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="课程所属专业">
            <el-select v-model="form.curmajor" placeholder="选择专业">
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
          <el-form-item label="课程所属年份">
            <el-input v-model="form.curyear" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="课时">
            <el-input v-model="form.curtime" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="任课教师">
            <el-input v-model="form.curteacher" style="width: 80%"/>
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
  name: 'Curriculum',
  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      dialogVisible1: false,
      gradesVis: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      gradeList:[],
    }
  },
  created() {
    this.load()
  },
  methods: {
    showGrades(grades){
      this.gradeList = grades
      this.gradesVis = true
    },

    load() {
      request.get("/curriculum", {
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
      request.put("/curriculum", this.form).then(res => {
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
      request.post("/curriculum", this.form).then(res => {
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
      request.delete("/curriculum/"+ id).then(res => {
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
