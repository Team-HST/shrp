<template>
  <v-app-bar
    app
    color="indigo"
    :clipped-left="$vuetify.breakpoint.mdAndUp"
    dark  
  >
    <v-app-bar-nav-icon @click="setDrawer"></v-app-bar-nav-icon>
    <v-toolbar-title>SHRP smart hils report program</v-toolbar-title>
  </v-app-bar>
</template>

<script>
  export default {
    data () {
      return {
        darwer: null
      }
    },
    methods: {
      setDrawer: function() {
        this.darwer = !this.darwer
        this.$eventBus.$emit('setDrawer', this.darwer);
      }
    }
  }
</script>