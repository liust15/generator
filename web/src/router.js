import Vue from 'vue'
import Router from 'vue-router'
import Index from './components/Index.vue'

Vue.use(Router);

const $root_router = new Router({
    routes: [{
            path: '/',
            name: 'index',
            component: Index,
            meta: {keepAlive: true},
            redirect: 'login'
        },]
});
export default $root_router


