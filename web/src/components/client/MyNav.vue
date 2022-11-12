<template>
  <div>
    <el-menu :router="true" :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="/index/all">所有商品</el-menu-item>
        <el-menu-item index="/index/active" >活动商品</el-menu-item>
        <el-menu-item index="/index/good">推荐商品</el-menu-item>
        <el-menu-item index="/myself">我的</el-menu-item>
        <el-menu-item index="6">
          <el-input v-model="input" placeholder="请输入..."></el-input>
          <el-button round @click="searchGoods">搜索</el-button>
        </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
        input:''
      };
    },
    methods: {
      searchGoods(){
        console.log(this.input);
        this.getGoodsByGoodsName(this.input)
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
        if (key === '/index/all') {
          console.log('all');
          this.getGoodsByAll()
        }else if (key === '/index/active'){
          console.log('active');
          this.getGoodsByActive()
        }else if (key === '/index/good'){
          console.log('good');
          this.getGoodsByGood()
        }
      },
      ...mapActions(['getGoodsByAll','getGoodsByActive','getGoodsByGood','getGoodsByGoodsName'])
    }
}
</script>

<style>

</style>