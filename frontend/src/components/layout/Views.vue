<template>
  <v-content>
    <v-flex class="pt-0 pb-0" md12 lg12>
      <div class="layout align-center layout px-4 pl-10 app--page-header row">
        <div class="page-header-left">
          <h3 class="pr-3">{{ getLayoutLink.mainName }}</h3>
        </div>
        <v-breadcrumbs class="pb-0" style="justify-content: flex-start;">
          <v-breadcrumbs-item>
            <v-icon color="blue">{{ getLayoutLink.icon }}</v-icon>
          </v-breadcrumbs-item>
          <v-breadcrumbs-divider>
            -
          </v-breadcrumbs-divider>
          <v-breadcrumbs-item>
            <div class="blue--text">{{ getLayoutLink.subName }}</div>
          </v-breadcrumbs-item>
        </v-breadcrumbs>
        <div class="spacer"></div>
      </div>
    </v-flex>
    <div id="core-view">
      <v-fade-transition mode="out-in">
        <router-view />
      </v-fade-transition>
    </div>
    <!-- <core-footer v-if="$route.name !== 'Maps'" /> -->
  </v-content>
</template>

<script>
  import { mapGetters } from 'vuex'

  export default {
    data () {
      return {
      }
    },
    computed: {
      ...mapGetters(['getLayoutLink'])
    },
    created() {
      
    }
  }
</script>

<style>
#core-view {
  padding-bottom: 80px;
}
</style>
