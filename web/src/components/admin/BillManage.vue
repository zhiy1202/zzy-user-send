<template>
  <div>
    <el-table
    :data="billList"
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
        prop="address"
        label="地址"
        width="180"
        min-width="40%">
      </el-table-column>
      <el-table-column
        prop="telephone"
        label="购买人联系方式"
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
      billList:[]
    }
  },
  methods: {
    handleClick(row){
        let that = this
        this.$axios.delete(`/goods/delGoodsById/${row.goodsId}`).then(res=>{
            if (res.data.data) {
                 this.$axios.get(`/bill/getAllBill`).then((res) => {
                    let result = res.data.data
                    that.billList = result
                })
            }
        })
    }
  },
  created() {
    let that = this
    this.$axios.get(`/bill/getAllBill`).then((res) => {
      let result = res.data.data
      that.billList = result
    })
  }

}
</script>

<style>

</style>