<template>
  <v-navigation-drawer
    fixed
    :clipped="$vuetify.breakpoint.mdAndUp"
    app
    :value="getLayoutDrawer"
  >
    <v-list>
      <v-list-item-group color="primary">
        <template
          v-for="(link, i) in links"
        >
          <v-list-item
            v-if="link.display"
            :to="link.path"
            :key="i"
            @click="changeLayoutLink(link)"
          >
            <v-list-item-icon>
              <v-icon v-text="link.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="link.name"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </template>
      </v-list-item-group>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  import { mapGetters, mapMutations } from 'vuex'
  import routerLinks from '@/router/paths'

  export default {
    data () {
      return {
        links: routerLinks
      }
    },
    computed: {
      ...mapGetters(['getLayoutDrawer'])
    },
    methods: {
      ...mapMutations(['changeLayoutLink'])
    }
  }
</script>
