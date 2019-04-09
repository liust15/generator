import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import resource from './resource'
import '../plugins/element.js'
import '../plugins/highlight'

Vue.prototype.$axios = axios;
//axios.defaults.baseURL = '/api'
import qs from 'qs';
axios.defaults.headers['Content-Type'] = 'application/json; charset=UTF-8';
axios.interceptors.request.use((request) => {
    if (request.data && request.headers['Content-Type'] === 'application/x-www-form-urlencoded') {
        request.data = qs.stringify(request.data);
    }
    return request;
});
const apiClient = axios.create()
apiClient.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json; charset=UTF-8';
    config.headers['OPTIONS']='Access-Control-Allow-Origin';
    return config
}, function (error) {
    // Do something with request error
    return Promise.reject(error)
})

Vue.config.productionTip = false
new Vue({
    router,
    store,
    resource,
    render: h => h(App),
}).$mount('#app')
