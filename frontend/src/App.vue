<template>
  <v-app id="app">
    <v-layout wrap justify-space-around>
      <router-link to="/">anlysis</router-link> |
      <router-link to="/history">history</router-link> |
      <router-link to="/chart">Simulation Chart</router-link>
    </v-layout>
    <router-view></router-view>
  </v-app>
</template>

<style>
    #app {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
    }
</style>