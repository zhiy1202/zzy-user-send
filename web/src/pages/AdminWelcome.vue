<template>
  <div>
    <div class="title">
            <span>Welcome</span>
        </div>
        <div class="sumbit">
            <!-- <form> -->
                <table>
                    <tr>
                        <td>
                            <input type="text" name="username" v-model="user.username" placeholder="请输入管理员账号">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" name="password" v-model="user.password" placeholder="请输入密码">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button @click="login">登录</button>
                        </td>
                    </tr>
                </table>
            <!-- </form> -->
        </div>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
    name: "Login",
    data(){
        return {
            user:{
                username:'',
                password:''
            }
        }
    },
    methods: {
        login(){
            let that = this
            let param = new FormData();
            param.append('username',this.user.username)
            param.append('password',this.user.password)
            console.log(that.user.username,that.user.password);
            this.$axios.post('/user/admin/login', param).then((res)=>{
                let loginIsOk = res.data.data;
                if (loginIsOk) {
                    this.$router.push('/admin/opration')
                    alert(res.data.message)
                }else{
                  alert(res.data.message)
                }
            })
        },
        ...mapActions(['getGoodsByAll'])
    }
}
</script>

<style scoped>
.title{
    width: 100%;
    text-align: center;
    font-family: 'Courier New', Courier, monospace;
    font-size:34px;
    font-weight: bold;
    color: antiquewhite;
    position: relative;
    top: 30px;
}
.sumbit{
    margin: 0 auto;
    width: 80%;
    height: 400px;
    position: relative;
    top: 60px;
}
form{
    width: 100%;
}
table{
    width: 100%;
    text-align: center;
}
input{
    width: 300px;
    height: 50px;
    border-radius: 30px;
    padding-left: 25px;
    font-size: 20px;
    display: inline-block;
    margin-top: 30px;
}
button{
    width:150px;
    height: 50px;
    margin-top: 30px;
    border-radius: 20px;
    cursor: pointer;
    transition:all 0.5s;
}
button:hover{
    background-color:gray;
    width:160px;
    height: 55px;
    border-radius:30px;
}

</style>