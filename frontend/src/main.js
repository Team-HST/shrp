import 'babel-polyfill'
import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import 'vuetify/dist/vuetify.min.css'
import vuetify from '@/plugins/vuetify'
import router from '@/router'

Vue.config.productionTip = false
Vue.prototype.$http = axios

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')