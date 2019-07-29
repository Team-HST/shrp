<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="분석 조건을 선택하세요."/>
    <MainSelectBox />
  </div>
</template>

<script>
import HelloWorld from './components/anlysis/HelloWorld.vue'
import MainSelectBox from './components/anlysis/AnalysisFilter'

export default {
  name: 'app',
  components: {
    HelloWorld,
    MainSelectBox
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
