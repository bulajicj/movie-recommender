import Vue from 'vue'
import './plugins/bootstrap-vue'
import App from './App.vue'
import axios from 'axios'

import globalVars from './globalVars'

Vue.prototype.$apiUrl = globalVars.apiUrl
Vue.config.productionTip = false


axios.defaults.withCredentials = true
axios.defaults.baseURL = globalVars.apiUrl

Vue.config.productionTip = false

new Vue({
  render: function (h) { return h(App) },
}).$mount('#app')
