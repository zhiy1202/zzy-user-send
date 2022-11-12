import Vue from 'vue'
import VueX from 'vuex'

import axios from '../http/http'
Vue.use(VueX)

const store = new VueX.Store({
    state:{
        goodsList:[]
    },
    getters:{
        goodsList(state){
            return state.goodsList
        }
    },
    mutations:{
        updateGoodsList(state,value){
            state.goodsList = value
        }
    },
    actions:{
        getGoodsByAll(context){
            axios.get('/goods/getAllGoods').then(data=>{
                let result = data.data.data
                console.log(result);
                context.commit('updateGoodsList',result)
            })
        },
        getGoodsByActive(context){
            axios.get('/goods/getALlGoodsByActive').then(data=>{
                let result = data.data.data
                console.log(result);
                context.commit('updateGoodsList',result)
            })
        },
        getGoodsByGood(context){
            axios.get('/goods/getALlGoodsByGood').then(data=>{
                let result = data.data.data
                console.log(result);
                context.commit('updateGoodsList',result)
            })
        },
        getGoodsByGoodsName(context,goodsName){
            axios.get('/goods/getGoodsImgByGoodsName/'+goodsName).then(data=>{
                let result = data.data.data
                console.log(result);
                context.commit('updateGoodsList',result)
            })
        }
    }
})
export default store
