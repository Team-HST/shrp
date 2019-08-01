import 'babel-polyfill'
import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import vuetify from '@/plugins/vuetify'
import router from '@/router'
import 'vuetify/dist/vuetify.min.css'

// Components
import './components'

Vue.config.productionTip = false
Vue.prototype.$http = axios

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')