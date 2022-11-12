<template>
  <div>
     <el-table
        :data="userHistory"
        style="width: 100%">
        <el-table-column
          prop="goodsName"
          label="产品"
          width="180">
        </el-table-column>
        <el-table-column
          prop="goodsPrice"
          label="价格"
          width="180">
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="日期">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作">
          <template slot-scope="scope">
            <el-button @click="goGoodsDetail(scope.row)" type="primary" icon="el-icon-link" circle></el-button>
            <el-button @click="delHistory(scope.row)" type="danger" icon="el-icon-delete" circle></el-button>
          </template>
        </el-table-column>
      </el-table>
  </div>
</template>

<script>
export default {
  data() {
        return {
          userHistory:[]
        }
  },
  methods: {
    goGoodsDetail(row) {
      console.log(row);
      this.$router.push('/detail/'+row.goodsId)
    },
    delHistory(row) {
      let that = this
      console.log(row);
      this.$axios.delete(`/userHistory/delUserHistory/${row.historyId}`).then(data=>{
        if (data.data.data) {
          this.$axios.get('/userHistory/getAllHistory').then(data=>{
          that.userHistory = data.data.data
        })
        }
      })
    }
  },
  created(){
    let that = this
    this.$axios.get('/userHistory/getAllHistory').then(data=>{
      that.userHistory = data.data.data
    })
  }
}
</script>

<style>

</style>