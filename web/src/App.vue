<template>
  <div id="index">
    <Index  msg="Welcome to Your Vue.js App"></Index>
  </div>
</template>

<script>
import Index from './components/Index.vue'
export default {
  name: 'index',
  components: {
    Index
  }
}
</script>

<style>
#index {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
