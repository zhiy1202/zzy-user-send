<template>
  <div class="car_container">
    <ul>
        <li v-for="item in userCar" :key="item.goodsId">
            <div class="car_bar">
                <!-- 图片展示 -->
                <div class="car_img">
                    <img :src="item.goodsImg" alt="">
                </div>
                <!-- 介绍 -->
                <div class="car_info">
                    <p>{{item.goodsName}}</p>
                </div>

                <div class="car_info">
                    <p>{{item.address}}</p>
                </div>

                <div class="car_info">
                    <p>{{item.telephone}}</p>
                </div>

                <div class="car_price">
                    <p>￥{{item.goodsPrice}}</p>
                </div>
                <div class="car_opration">
                    <el-button type="danger" @click="addBill(item)">购买</el-button>
                    <el-button type="danger" @click="delCar(item)">删除</el-button>
                </div>
            </div>
        </li>
    </ul>
  </div>
</template>

<script>
export default {
    data() {
        return {
          userCar:[]
        }
    },
    methods: {
        delCar(item){
            console.log(item);
            let that =this
            this.$axios.delete(`/car/delCarById/${item.carId}`).then(data => {
                if (data.data.data) {
                    this.$axios.get('/car/getAllCarByUserId').then(data=>{
                        console.log(data);
                        that.userCar = data.data.data
                    })
                }
            })
        },
        addBill(item){
            let that = this
            this.$axios.delete(`/car/statement/${item.carId}`).then(data => {
                let result = data.data.data
                if (result) {
                    alert("结账成功")
                    this.$axios.get('/car/getAllCarByUserId').then(data=>{
                        that.userCar = data.data.data
                    })
                }else {
                    alert("结账失败")
                }
            })
        }
    },
    created(){
        let that = this
        this.$axios.get('/car/getAllCarByUserId').then(data=>{
            console.log(data);
            that.userCar = data.data.data
        })
    }
}
</script>

<style scoped>
.car_container{
    width: 60%;

}
ul{
    width: 100%;
}
ul li{
    width: 100%;
    height: 150px;
    border-bottom: 1px solid black;
}
.car_bar{
    width: 100%;
    height:100%;
    display:flex;
    justify-content: space-around;
}
.car_img{
    width: 200px;
    height:100%;
    display: inline-block;
}
img{
    width: 100%;
    height: 100%;
}
.car_info{
    /* flex:2; */
    font-size: 24px;
}
.car_price{
    /* flex:1; */
    font-size:30px;
    color: red;
}
.car_opration{
    /* flex:1; */
}
</style>