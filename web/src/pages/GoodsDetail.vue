<template>
<div class="detail_bar">
    <div>
        <MyNav></MyNav>
    </div>
    <div class="detail_container">
    <!-- 图片 -->
    <div class="detail_left">
        <el-carousel :interval="5000" arrow="always">
            <el-carousel-item v-for="item in goodsImgs" :key="item.imgId">
                <img :src="item.goodsImg">
            </el-carousel-item>
        </el-carousel>
    </div>
    <!-- 介绍 -->
    <div class="detail_right">
        <h1>
            {{goods.goodsName}}
        </h1>

        <p class="detail_intro">
            {{goods.goodsIntro}}
        </p>

        <p class="detail_price">
            <span>价格: {{goods.goodsPrice}}</span>
        </p>

        <div class="detail_opration">
            <el-input v-model="address" placeholder="请输入送货地址"></el-input>
            <el-input v-model="telephone" placeholder="请输入联系方式"></el-input>
            <el-button type="danger" @click="addCar(goods.goodsId)">加入购物车</el-button>
        </div>
    </div>

    <!-- 评论 -->

  </div>
</div>
  
</template>

<script>
import MyNav from '../components/client/MyNav.vue'
export default {
    data(){
        return {
            goods:{},
            goodsImgs:[],
            address:'',
            telephone:''
        }
    },
    methods:{
        addCar(goodsId){
            console.log(goodsId);
            let formData = new FormData();
            formData.append('goodsId',goodsId);
            formData.append('goodsName',this.goods.goodsName);
            formData.append('goodsIntro',this.goods.goodsIntro);
            formData.append('goodsPrice',this.goods.goodsPrice);
            formData.append('address',this.address)
            formData.append('telephone',this.telephone)
            this.$axios.post('/car/addCar',formData).then(data=>{
                let result = data.data.data
                if (result) {
                    alert('添加购物车成功')
                }else{
                    alert('添加购物车失败')

                }
            })
        }
    },
    components:{
        MyNav
    },
    created(){
        let that = this
        let route = this.$route
        let goodsId = route.params.goodsId
        console.log(goodsId);
        
        this.$axios.get('/goods/getGoodsById/'+goodsId).then(data=>{
            let result = data.data.data
            console.log(result);
            that.goods = result

            let formData = new FormData()
            formData.append('goodsId',result.goodsId)
            formData.append('goodsName',result.goodsName)
            formData.append('goodsPrice',result.goodsPrice)
            this.$axios.post('/userHistory/addUserHistory',formData)
        })
        this.$axios.get('/goods/getGoodsImgByGoodsId/'+goodsId).then(data=>{
            let result = data.data.data
            console.log(result);
            that.goodsImgs = result
        })

        
    }
}
</script>

<style scoped>
.detail_bar{
    width: 100%;
    height: 1000px;
}
.detail_container{
    width: 50%;
    display:flex;
    margin:10px auto;
}
.detail_left{
    flex:1;
}
.detail_right{
    flex:2;
    margin-left: 10px;
}
.detail_intro{
    background-color:grey;
    opacity: 0.75;
    margin:30px 0;
    font-size: 24px;
}
.detail_price span{
    font-size: 24px;
    color: red;
}
.detail_opration{
    margin-top: 10px;
    width: 100%;
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