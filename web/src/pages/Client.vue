<template>
  <div>
    <el-container>
        <el-header height="300px">
            <el-carousel indicator-position="outside">
                <el-carousel-item v-for="(item,index) in imgPath" :key="index">
                <img :src="item"/>
                </el-carousel-item>
            </el-carousel>
        </el-header>
        <el-main>
            <!-- 导航栏 -->
            <div class="nav_menu">
                <MyNav></MyNav>
            </div>
            <!-- 展示 -->
            <div class="main">
                <div class="container">
                    <ul class="goods_list">
                        <li class="goods_item" @click="goDetail(item.goodsId)" v-for="item in goodsList" :key="item.goodsId">
                            <GoodsItem
                            :goodsName="item.goodsName"
                            :goodsPrice="item.goodsPrice"
                            :goodsIntro="item.goodsIntro"
                            :goodsImg="item.goodsImg"
                            ></GoodsItem>
                        </li>
                    </ul>
                </div>
            </div>
        </el-main>
    </el-container>
  </div>
</template>

<script>
import MyNav from '../components/client/MyNav.vue'
import GoodsItem from '../components/client/GoodsItem.vue'

import {mapGetters} from 'vuex'
export default {
    data(){
        return{
            path:'',
            imgPath:[require('../assets/images/1.jpg'),require('../assets/images/2.jpg')]
        }
    },
    methods:{
        goDetail(goodsId) {
            console.log(goodsId);
            this.$router.push('/detail/'+goodsId);
        }
    },
    components:{
        MyNav,
        GoodsItem
    },
    computed:{
        ...mapGetters(['goodsList'])
    }
}
</script>

<style scoped>
.myself_bar{
    width: 50%;
    height: 500px;
    background-color: red;
}
ul li{
    cursor: pointer;
}
.nav_menu{
    width: 100%;
    height: 80px;
    /* position:fixed; */
    margin-bottom: 4px;
    z-index: 999;
    background-color: #fff;
}
.goods_list{
    display: flex;
    flex-wrap: wrap;
}
.goods_item{
    width: 300px;
    height:350px;
}
.main {
    width: 100%;
    height: 700px;
    position: relative;
    /* margin-top: 100px; */
}
.container {
    width: 70%;
    height: 100%;
    margin: 0 auto;
    background-color: #fff;
}
.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
background-color: #d3dce6;
}
</style>