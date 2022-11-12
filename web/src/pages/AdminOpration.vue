<template>
  <div class="admin_container" :style="{width:adminWidth+'px',height:adminHeight+'px'}">
    <div class="admin_nav" :style="{height:adminHeight+'px'}" v-if="true">
        <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
        <el-radio-button :label="false">展开</el-radio-button>
        <el-radio-button :label="true">收起</el-radio-button>
    </el-radio-group>
    <el-menu 
    default-active="1-4-1" 
    class="el-menu-vertical-demo" 
    @open="handleOpen" 
    @close="handleClose" 
    :collapse="isCollapse"
    background-color="#000000"
    text-color="#ffffff"
    :router="true">
        <el-menu-item index="/admin/adminUser">
            <i class="el-icon-user-solid"></i>
            <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/adminGoods">
            <i class="el-icon-menu"></i>
            <span slot="title">产品管理</span>
        </el-menu-item>
        <el-menu-item index="/admin/adminBill">
            <i class="el-icon-document"></i>
            <span slot="title">订单查看</span>
        </el-menu-item>
        <el-menu-item index="/admin" @click="exit">
            <i class="el-icon-switch-button"></i>
            <span slot="title">退出</span>
        </el-menu-item>
    </el-menu>
    </div>
    <div class="admin_opration">
        <router-view></router-view>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'
export default {
    computed: {
        ...mapGetters(['navIsShow'])
    },
    data(){
        return{
            adminWidth: 0,
            adminHeight:0,
            isCollapse: true
        }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      exit(){
        this.$router.push('/admin')
      }
    },
    created(){
        this.$store.state.isAdmin = false
        localStorage.removeItem('admin')
        delete this.$axios.defaults.headers.common['admin']
        this.adminWidth = window.innerWidth;
        this.adminHeight = window.innerHeight;
    }
}
</script>

<style scoped>
.admin_container{
    display:flex;
    background-color: black;
    color:rgb(252, 252, 252);
}
.admin_nav{
    flex: 1;
    display:fixed;

}
.admin_opration{
    flex:3;
    display:fixed;
}
</style>