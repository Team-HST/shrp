<template>
   <material-card 
      color="orange" 
      title="Data Table" 
      text="dd"
   >
    <!-- simple-table로 고려 YP -->
    <v-data-table 
      v-model="dataTable"
      :headers="dataTable.headers" 
    >
    </v-data-table>
   </material-card>
</template>

<script>
  import { mapGetters } from 'vuex'

  export default { 
    data () {
        return {
          dataTable : {
            headers: []
          }
        }
    },
    mounted() {
      console.log('mount 실행');
    }
  }
 </script>