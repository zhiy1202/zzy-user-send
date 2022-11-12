import Vue from 'vue'
import App from './App.vue'

import router from './router'
import store from './store'
import axios from './http/http'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  render: h => h(App),
  router,
  store,
  created(){
    router.beforeEach((to,from,next)=>{
      let path = to.path
      if (path === '/login' || path === '/register' || 
      path === '/admin' || path === '/admin/opration' || path ==='/admin/adminUser' ||
      path === '/admin/adminGoods' || path === '//admin/adminBill') {
        next()
      }else{
        let user = window.localStorage.getItem('user')
        console.log(user);
        if (user != null) {
          next()
        }else{
          alert('请登录')
          this.$router.push('/login')
        }
      }
    
    })
  }
}).$mount('#app')
