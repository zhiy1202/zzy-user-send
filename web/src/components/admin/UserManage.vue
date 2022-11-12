<template>
  <div>
    <el-table
    :data="userList"
    border
    max-height="1000px"
    style="width: 100%">
      <el-table-column
        prop="username"
        label="用户名"
        min-width="25%">
      </el-table-column>
      <el-table-column
        prop="password"
        label="密码"
        width="180"
        min-width="40%">
      </el-table-column>
      <el-table-column
        prop="birthday"
        label="生日"
        width="180"
        min-width="40%">
      </el-table-column>
      <el-table-column
        prop="phoneNumber"
        label="联系方式"
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
      userList:[]
    }
  },
  methods: {
    handleClick(row){
        let that = this
        this.$axios.delete(`/user/delUserById/${row.userId}`).then(res=>{
            if (res.data.data) {
                this.$axios.get(`/user/allUser`).then((res) => {
                let result = res.data.data
                that.userList = result
                })
            }
        })
    }
  },
  created() {
    let that = this
    this.$axios.get(`/user/allUser`).then((res) => {
      let result = res.data.data
      that.userList = result
    })
  }

}
</script>

<style>

</style>