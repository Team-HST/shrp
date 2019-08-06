<template>
  <v-content>
    <v-app-bar
      app
      color="indigo"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>SHRP smart hils report program</v-toolbar-title>
    </v-app-bar>
    <div id="core-view">
      <v-fade-transition mode="out-in">
        <router-view />
      </v-fade-transition>
    </div>
    <!-- <core-footer v-if="$route.name !== 'Maps'" /> -->
  </v-content>
</template>

<script>
</script>

<style>
#core-view {
  padding-bottom: 80px;
}
</style>
