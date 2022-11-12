<template>
  <div class="st-table">
    <h2>在出售</h2>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="date" label="产品" width="180">
        <template slot-scope="scope">
          <el-input type="text" v-model="scope.row.goodsName" v-show="isEdit" />
          <span v-show="!isEdit">{{scope.row.goodsName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="价格" width="180">
        <template slot-scope="scope">
          <el-input type="text" v-model="scope.row.goodsPrice" v-show="isEdit" />
          <span v-show="!isEdit">{{scope.row.goodsPrice}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="简介">
        <template slot-scope="scope">
          <el-input type="text" v-model="scope.row.goodsIntro" v-show="isEdit" />
          <span v-show="!isEdit">{{scope.row.goodsIntro}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="产品类型">
        <template slot-scope="scope">
          <el-input type="text" v-model="scope.row.goodsType" v-show="isEdit" />
          <span v-show="!isEdit">{{scope.row.goodsType}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="活动产品">
        <template slot-scope="scope">
          <el-select v-model="scope.row.goodsActice" placeholder="请选择">
            <el-option
              v-for="item in goodsActices"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="300">
        <template slot-scope="scope">
          <el-button type="warning" @click="edit(scope.row, scope)">编辑</el-button>
          <el-button type="danger" @click="save(scope.row)">保存</el-button>
          <el-button type="danger" @click="delGoods(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <h2>已卖出</h2>

    <el-table :data="goodsList" border style="width: 100%">
      <el-table-column prop="date" label="产品" width="180">
        <template slot-scope="scope">
          <el-input type="text" v-model="scope.row.goodsName" v-show="isEdit" />
          <span v-show="!isEdit">{{scope.row.goodsName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="价格" width="180">
        <template slot-scope="scope">
          <el-input type="text" v-model="scope.row.goodsPrice" v-show="isEdit" />
          <span v-show="!isEdit">{{scope.row.goodsPrice}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="简介">
        <template slot-scope="scope">
          <el-input type="text" v-model="scope.row.goodsIntro" v-show="isEdit" />
          <span v-show="!isEdit">{{scope.row.goodsIntro}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="产品类型">
        <template slot-scope="scope">
          <el-input type="text" v-model="scope.row.goodsType" v-show="isEdit" />
          <span v-show="!isEdit">{{scope.row.goodsType}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="活动产品">
        <template slot-scope="scope">
          <el-select v-model="scope.row.goodsActice" placeholder="请选择">
            <el-option
              v-for="item in goodsActices"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
export default {
  data() {
    return {
      isEdit:false,
      tableData: [],
      goodsList:[],
      goodsActices:[
        {
          value:1,
          label:'活动产品'
        },
        {
          value:0,
          label:'非活动产品'
        }
      ]
      
    };
  },
  methods: {
    delGoods(row) {
      let that = this
      this.$axios.delete(`/goods/delGoodsById/${row.goodsId}`).then(res=>{
        let result = res.data.data
        if (result) {
          alert('删除成功')
          this.$axios.get('/goods/getGoodsByUserId').then((response)=>{
            that.tableData = response.data.data
          })
        }
      })
    },
    edit() {
      this.isEdit = !this.isEdit;
    },
    save(row) {
      this.isEdit = false;
      console.log(row);
      let formData = new FormData()
      formData.append('goodsId',row.goodsId)
      formData.append('goodsName',row.goodsName)
      formData.append('goodsPrice',row.goodsPrice)
      formData.append('goodsIntro',row.goodsIntro)
      formData.append('goodsType',row.goodsType)
      formData.append('goodsActice',row.goodsActice)
      this.$axios.put('/goods/updateGoodsInfo',formData).then(res=>{
        let result = res.data.data
        if (result) {
          alert('修改成功')
        }else {
          alert('修改错误')
        }
      })

    }
  },
  created(){
    let that = this
    this.$axios.get('/goods/getGoodsByUserId').then((response)=>{
      that.tableData = response.data.data
    })
    this.$axios.get('/goods/sell').then(res=>{
      that.goodsList = res.data.data
    })
  }
};
</script>
<style scoped>
.st-table {
  padding: 10px;
}
</style>