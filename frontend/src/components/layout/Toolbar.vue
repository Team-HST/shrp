<template>
  <v-app-bar
    app
    color="indigo"
    :clipped-left="$vuetify.breakpoint.mdAndUp"
    dark  
  >
    <v-app-bar-nav-icon @click="changeDrawer"></v-app-bar-nav-icon>
    <div class="v-toolbar-title">
      <v-toolbar-title>SHRP smart hils report program</v-toolbar-title>
    </div>
  </v-app-bar>
</template>

<script>
import { mapMutations } from 'vuex'

  export default {
    data () {
      return {
      }
    },
    methods: {
      ...mapMutations(['changeDrawer'])
    }
  }
</script>