import Vue from 'vue';
/*引入资源请求插件*/
import VueResource from 'vue-resource'
/*使用VueResource插件*/
Vue.use(VueResource);
Vue.http.options.emulateJSON = true;
export default({
});
