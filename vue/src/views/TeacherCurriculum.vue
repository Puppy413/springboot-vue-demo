<template>
  <div style="padding: 10px">
    <!--    搜索区域-->
    <div style="margin: 10px">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 10px" @click="load">查询</el-button>
    </div>

    <!--    表格-->
    <el-table :data="tableData" border style="width: 100%" :row-class-name="tableRowClassName">
      <el-table-column prop="id" label="课程ID" width="180" sortable/>
      <el-table-column prop="curname" label="课程名称" width="180"/>
      <el-table-column prop="curmajor" label="课程所属专业" width="180"/>
      <el-table-column prop="curyear" label="课程所属年份" width="180"/>
      <el-table-column prop="curtime" label="课时" width="180"/>
      <el-table-column prop="curteacher" label="任课教师" width="180"/>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" type="success" plain @click="showGrades(scope.row.gradeList)">查看课程成绩</el-button>
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

<!--      <el-dialog-->
<!--          v-model="ErrorVis"-->
<!--          title="Tips"-->
<!--          width="30%"-->
<!--      >-->
<!--        <span>您无法查看该课程名单</span>-->
<!--        <template #footer>-->
<!--      <span class="dialog-footer">-->
<!--        <el-button @click="ErrorVis = false">取消</el-button>-->
<!--        <el-button type="primary" @click="ErrorVis = false">-->
<!--          确定-->
<!--        </el-button>-->
<!--      </span>-->
<!--        </template>-->
<!--      </el-dialog>-->
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {

  name: 'TeacherCurriculum',
  components: {},
  data() {
    return {
      user:{},
      form: {},
      dialogVisible: false,
      dialogVisible1: false,
      gradesVis: false,
      ErrorVis: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
      gradeList:[],
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)
    this.load()
  },

  methods: {
    tableRowClassName({ row, rowIndex }){
      if (row.curteacher !== this.user.name) {
        return 'hidden_box'  //可以动态添加class样式
      }
      return ''
    },
    showGrades(grades){
      this.gradeList = grades
      // this.form = JSON.parse(JSON.stringify(row))
      // if(this.user.name!==this.form.curteacher){
      //   this.ErrorVis = true
      //   console.log(this.form.curteacher)
      // }else{
      //   this.gradesVis = true
      // }
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
        this.tableData = res.data.records
        this.total = res.data.total
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