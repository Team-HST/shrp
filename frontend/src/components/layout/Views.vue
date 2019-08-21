<template>
  <v-content>
    <v-flex class="pt-0 pb-0" md12 lg12>
      <div class="layout align-center layout px-4 pl-10 app--page-header row">
        <div class="page-header-left">
          <h3 class="pr-3">{{ getLayoutLink.name }}</h3>
        </div>
        <ul class="v-breadcrumbs pb-0" style="justify-content: flex-start;">
          <li>
            <a class="v-breadcrumbs__item">
              <v-icon color="blue">{{ getLayoutLink.icon }}</v-icon>
            </a>
          </li>
          <li class="v-breadcrumbs__divider">-</li>
          <li>
            <a class="v-breadcrumbs__item">
              {{ getLayoutLink.subName }}
            </a>
          </li>
        </ul>
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
    }
  }
</script>

<style>
#core-view {
  padding-bottom: 80px;
}
</style>
