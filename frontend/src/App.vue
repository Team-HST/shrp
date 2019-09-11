<template>
  <v-app>
    <layout-drawer />
    <!-- <layout-toolbar /> -->
    <layout-views />
    <!-- <layout-footer /> -->
  </v-app>
</template>
<script>
export default {
  data() {
    return {};
  }
};
</script>

<style lang="scss">
@import "@/styles/index.scss";

/* Remove in 1.2 */
.v-datatable thead th.column.sortable i {
  vertical-align: unset;
}

.v-application {
  font-family: "KoPub Dotum" !important;
}
</style>
