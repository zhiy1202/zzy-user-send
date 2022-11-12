<template>
  <div class="info_container">
    <div class="info_bar">
      用户名:<el-input v-model="userInfo.username" placeholder="请输入内容"></el-input>
      密码:<el-input placeholder="请输入密码" v-model="userInfo.password" show-password></el-input>
      联系电话:<el-input v-model="userInfo.phoneNumber" placeholder="请输入手机号"></el-input>
      生日:<el-date-picker
        v-model="userInfo.birthday"
        type="date"
        placeholder="选择日期">
      </el-date-picker>
      <el-button type="success" @click="updateUser">更新</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      userInfo:{}
    }
  },
  methods: {
    updateUser(){
      let formData = new FormData()
      formData.append('userId',this.userInfo.userId)
      formData.append('username',this.userInfo.username)
      formData.append('password',this.userInfo.password)
      formData.append('phoneNumber',this.userInfo.phoneNumber)
      formData.append('birthday',this.userInfo.birthday)
      this.$axios.put('/user/updateUser',formData).then(data=>{
        let result = data.data.data
        console.log(result);
        if (result) {
          alert("修改信息成功")
        }else {
          alert("修改信息失败")
          
        }
      })
    }
  },
  created(){
    let that = this
    this.$axios.get("/user/getUserInfo").then((data)=>{
      let result = data.data.data
      console.log(result);
      that.userInfo = result
    })
  }
}
</script>

<style scoped>
.info_container{
  width: 100%;
}
.info_bar{
  width: 80%;
  margin: 0 auto;
}

</style>