<template>
  <div class="self_bar">
    <!-- left -->
    <div>
        <MyNav></MyNav>
    </div>
    <div class="self_container">
        <div class="self_left">
        <el-col :span="12">
            <el-menu
            :router="true"
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
            <el-submenu index="1">
                <template slot="title">
                <i class="el-icon-location"></i>
                <span>个人信息</span>
                </template>
                <el-menu-item-group>
                <template slot="title">分组一</template>
                <el-menu-item index="/myself/mycar">购物车</el-menu-item>
                <el-menu-item index="/myself/mybill">所有订单</el-menu-item>
                </el-menu-item-group>
                <el-menu-item-group title="分组2">
                <el-menu-item index="/myself/mygoods">我的产品</el-menu-item>
                </el-menu-item-group>
                <el-submenu index="1-4">
                <template slot="title">我的信息</template>
                <el-menu-item index="/myself/myhistory">浏览历史</el-menu-item>
                <el-menu-item index="/myself/myinfo">修改信息</el-menu-item>
                </el-submenu>
            </el-submenu>
            <el-menu-item index="/myself/addGoods">
                <i class="el-icon-menu"></i>
                <span slot="title">出售好物</span>
            </el-menu-item>
            <el-menu-item @click="exitUser">
                <i class="el-icon-setting"></i>
                <span slot="title">退出</span>
            </el-menu-item>
            </el-menu>
        </el-col>   
    </div>
    <!-- right -->
    <div class="self_right">
        <router-view></router-view>
    </div>
    </div>
  </div>
</template>

<script>
import MyNav from '../components/client/MyNav.vue'
export default {
    components:{
        MyNav
    },
    methods:{
        exitUser(){
            window.localStorage.removeItem('user')
            this.$router.push('/login')

        }
    }
}
</script>

<style scoped>
.self_bar{
    width: 100%;

}
.self_container{
    display: flex;
}
.self_left{
    flex:1;
}
.self_right{
    flex:3;
}
</style>