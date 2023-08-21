import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueRouter from 'vue-router'
import router from './router'
Vue.use(axios);
Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(router)
new Vue({
  router, // 将router配置传给Vue实例
  render: h => h(App),
}).$mount('#app')
