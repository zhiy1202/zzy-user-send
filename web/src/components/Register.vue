<template>
  <div>
    <div class="title">
        <span>Register</span>
    </div>
    <div class="sumbit">
        <!-- <form> -->
            <table>
                <tr>
                    <td>
                        <input type="text" @input="usernameCheck" v-model="user.username" placeholder="请输入你的用户名...">
                    </td>
                    <td>
                        <span>{{usernameIsOk}}</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="password" name="password" id="" v-model="user.password" placeholder="请输入你的密码...">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" name="password" id="" v-model="user.phoneNumber" placeholder="请输入你的手机号...">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="date" v-model="user.birthday">
                    </td>
                </tr>
                <tr>
                    <td>
                        <button @click="register">注册</button>
                    </td>
                </tr>
            </table>
        <!-- </form> -->
    </div>
  </div>
</template>

<script>
export default {
    name: 'Register',
    data(){
        return{
            usernameIsOk:'',
            user:{
                username: '',
                password:'',
                phoneNumber:'',
                birthday:''
            }
            
        }
    },
    methods: {
        register(){
            if (!this.usernameIsOk) {
                alert('用户名已存在')
                return
            }
            let formData  = new FormData();
            formData.append('username',this.user.username);
            formData.append('password',this.user.password)
            formData.append('phoneNumber',this.user.phoneNumber);
            formData.append('birthday',this.user.birthday)
            this.$axios.post('/user/register',formData).then(res =>{
                let result = res.data.data
                console.log(res);
                if (result) {
                    alert('注册成功 请登录')
                }
            })
        },
        usernameCheck(){
            let that = this
            this.$axios.get("/user/isExits?username="+that.user.username).then(res =>{
                let result = res.data
                if (result) {
                    that.usernameIsOk = true
                }else{
                    that.usernameIsOk = false
                }
            })
        }
    }
}
</script>

<style scoped>
h2{
    color:white;
    cursor: pointer;
}
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
span{
    color:white;
}
</style>