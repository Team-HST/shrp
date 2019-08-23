<template>
  <v-navigation-drawer
    fixed
    app
    persistent
    mobile-break-point="991"
    width="260"
  >
    <v-layout
      tag="v-list"
      column
    >
      <v-list-item class="mb-3 pa-0">
        <v-list-item-avatar
          class="mr-3"
          color="white"
        >
          <v-img
            :src="logo"
            height="34"
            contain
          />
        </v-list-item-avatar>
        <v-list-item two-line class="ma-0 pa-0">
          <v-list-item-content>
            <v-list-item-title class="font-weight-bold">
              SHRP
            </v-list-item-title>
            <v-list-item-subtitle class="font-weight-medium">
              Smart Hills Report Program
            </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list-item>

      <v-divider class="mb-2" />
      <v-list>
        <v-list-item-group>
          <template
            v-for="(link, i) in links"
          >
            <v-list-item
              v-if="link.display"
              :to="link.path"
              :key="i"
              @click="changeLayoutLink(link)"
              active-class="blue--text"
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
    </v-layout>

    <template v-slot:append>
      <div class="pa-2 text-center">
        <p>@HST - 2019.07</p>
      </div>
    </template>
  </v-navigation-drawer>
</template>

<script>
  import { mapMutations } from 'vuex'
  import routerLinks from '@/router/paths'

  export default {
    data () {
      return {
        links: routerLinks,
        logo: require('@/assets/logo2.png')
      }
    },
    methods: {
      ...mapMutations(['changeLayoutLink'])
    }
  }
</script>
s