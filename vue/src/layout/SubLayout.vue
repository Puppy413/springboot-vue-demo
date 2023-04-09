<template>
  <div>
    <!--    头部-->
    <Header/>
    <!--    主体-->
    <div style="display: flex">
      <!--      侧边栏-->
      <SubAside/>
      <!--      内容区域-->
      <router-view style="flex:1"/>

    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";
import SubAside from "@/components/SubAside";
export default {
  name: "SubLayout",
  components:{
    Header,
    SubAside
  }
}
</script>

<style scoped>

</style>