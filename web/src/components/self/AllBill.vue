<template>
  <div>
    <el-table
      :data="allBill"
      style="width: 100%">
      <el-table-column
        prop="createTime"
        label="购买日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="goodsName"
        label="产品"
        width="180">
      </el-table-column>
      <el-table-column
        prop="goodsPrice"
        label="价格">
      </el-table-column>
      <el-table-column
        prop="telephone"
        label="联系方式">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" circle @click="delBill(scope.row)"></el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
   data() {
        return {
          allBill:[]
        }
    },
    methods: {
      delBill(row){
        console.log(row);
        let that = this;
        let billId = row.billId
        this.$axios.delete(`/bill/delBillById/${billId}`).then(data=>{
          if (data.data.data) {
            alert("删除账单成功")
            this.$axios.get("/bill/getAllBillByUserId").then(data=>{
              that.allBill = data.data.data
            })
          }
        })
      }
    },
    created(){
      let that = this;
      this.$axios.get("/bill/getAllBillByUserId").then(data=>{
        that.allBill = data.data.data
      })
    }
}
</script>

<style>

</style>