import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

import Welcome from '../pages/Welcome'
import Client from '../pages/Client'
import GoodsDetail from '../pages/GoodsDetail'
import MySelf from '../pages/MySelf'
import AdminWelcome from '../pages/AdminWelcome'
import AdminOpration from '../pages/AdminOpration'

import Login from '../components/Login'
import Register from '../components/Register'
import MyCar from '../components/self/MyCar'
import AllBill from '../components/self/AllBill'
import MyGoods from '../components/self/MyGoods'
import MyHistory from '../components/self/MyHistory'
import MyInfo from '../components/self/MyInfo'
import AddGoods from '../components/self/AddGoods'

import UserManage from '../components/admin/UserManage'
import GoodsManage from '../components/admin/GoodsManage'
import BillManage from '../components/admin/BillManage'
const router = new Router({
    routes: [
        {
            path:"/",
            component: Welcome,
            redirect:'/login',
            children:[
                {
                    path: "/login",
                    component: Login
                },
                {
                    path: "/register",
                    component: Register
                }
            ]
        },
        {
            path:"/index/:type",
            component: Client,
        },
        {
            path:"/detail/:goodsId",
            component: GoodsDetail
        },
        {
            path:"/myself",
            component: MySelf,
            redirect:'/myself/myinfo',
            children:[
                {
                    path: "mycar",
                    component: MyCar
                },
                {
                    path: "mybill",
                    component: AllBill
                },
                {
                    path: "mygoods",
                    component: MyGoods
                },
                {
                    path: "myhistory",
                    component: MyHistory
                },
                {
                    path: "myinfo",
                    component: MyInfo
                },
                {
                    path: "addGoods",
                    component: AddGoods
                },
            ]
        },
        {
            path:"/admin",
            component: AdminWelcome
        },
        {
            path:"/admin/opration",
            component: AdminOpration,
            redirect:'/admin/adminUser',
            children:[
                {
                    path: "/admin/adminUser",
                    component: UserManage
                },
                {
                    path: "/admin/adminGoods",
                    component: GoodsManage
                },
                {
                    path: "/admin/adminBill",
                    component: BillManage
                }
            ]
        },
    ]
})
export default router