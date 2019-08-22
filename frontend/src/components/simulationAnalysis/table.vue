<template>
   <material-card 
      color="orange" 
      title="Simulation Diagram" 
      text="Simulation Analysis Diagram Results"
   >
    <!-- simple-table로 고려 YP -->
    <v-data-table 
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