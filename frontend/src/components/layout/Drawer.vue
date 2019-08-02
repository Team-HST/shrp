<template>
  <v-navigation-drawer
    fixed
    :clipped="$vuetify.breakpoint.mdAndUp"
    app
    v-model="drawer"
  >
    <v-list>
      <v-list-item-group color="primary">
        <v-list-item
          v-for="(link, i) in links"
          :to="link.to"
          :key="i"
        >
          <v-list-item-icon>
            <v-icon v-text="link.icon"></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title v-text="link.text"></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  export default {
    data () {
      return {
        drawer: true,
        links: [
          {
            to: '/analysis',
            icon: 'mdi-view-dashboard',
            text: 'Analysis'
          },
          {
            to: '/history',
            icon: 'mdi-account',
            text: 'history'
          }
        ]
      }
    },
    created: function() {
        this.$eventBus.$on('setDrawer', (drawer) => {
          this.drawer = drawer;
          console.log(this.drawer);
        });
    }
  }
</script>
