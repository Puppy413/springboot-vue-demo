import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layout/Layout";
import SubLayout from "@/layout/SubLayout";
const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/teacher",
    children:[
      {
        path: 'teacher',
        name: 'Teacher',
        component:  ()=> import("@/views/Teacher")
      },
      {
        path: 'student',
        name: 'Student',
        component:  ()=> import("@/views/Student")
      },
      {
        path: 'classes',
        name: 'Classes',
        component:  ()=> import("@/views/Classes")
      },
      {
        path: 'curriculum',
        name: 'Curriculum',
        component:  ()=> import("@/views/Curriculum")
      },
      {
        path: 'grade',
        name: 'Grade',
        component:  ()=> import("@/views/Grade")
      },
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component:  ()=> import("@/views/Login")
  },

  {
    path: '/sub',
    name: 'SubLayout',
    component: SubLayout,
    children: [
      {
        path: 'teachercurriculum',
        name: 'TeacherCurriculum',
        component:  ()=> import("@/views/TeacherCurriculum")
      },
      {
        path: 'teachergrade',
        name: 'TeacherGrade',
        component:  ()=> import("@/views/TeacherGrade")
      },
      {
        path: 'studentcurriculum',
        name: 'StudentCurriculum',
        component:  ()=> import("@/views/StudentCurriculum")
      },
      {
        path: 'studentclasses',
        name: 'StudentClasses',
        component:  ()=> import("@/views/StudentClasses")
      },
      {
        path: 'studentgrade',
        name: 'StudentGrade',
        component:  ()=> import("@/views/StudentGrade")
      },
    ]
  },


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
