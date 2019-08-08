import 'babel-polyfill'
import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import vuetify from '@/plugins/vuetify'
import router from '@/router'
import store from './store'

// Components
import './components'

Vue.config.productionTip = false
Vue.prototype.$http = axios

new Vue({
  store,
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')