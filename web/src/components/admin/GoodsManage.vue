<template>
  <div>
    <el-table
    :data="goodsList"
    border
    max-height="1000px"
    style="width: 100%">
      <el-table-column
        prop="goodsName"
        label="产品名"
        min-width="25%">
      </el-table-column>
      <el-table-column
        prop="goodsPrice"
        label="产品价格"
        width="180"
        min-width="40%">
      </el-table-column>
      <el-table-column
        prop="goodsIntro"
        label="简介"
        width="180"
        min-width="40%">
      </el-table-column>
      <el-table-column
        prop="goodsType"
        label="产品类型"
        width="180"
        min-width="40%">
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
         min-width="25%">
      </el-table-column>
      <el-table-column
        label="操作"
         min-width="10%">
        <template slot-scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return{
      goodsList:[]
    }
  },
  methods: {
    handleClick(row){
        let that = this
        this.$axios.delete(`/goods/delGoodsById/${row.goodsId}`).then(res=>{
            if (res.data.data) {
                this.$axios.get(`/goods/getAllGoodsForAdmin`).then((res) => {
                let result = res.data.data
                that.goodsList = result
                })
            }
        })
    }
  },
  created() {
    let that = this
    this.$axios.get(`/goods/getAllGoodsForAdmin`).then((res) => {
      let result = res.data.data
      that.goodsList = result
    })
  }

}
</script>

<style>

</style>