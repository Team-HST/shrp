<script>
  import { mapGetters } from 'vuex'

  export default { 
    name: 'resultTable',
    template: '<material-card color="orange" title="Data Table" text="dd"><v-data-table v-model="dataTable":headers="dataTable.headers" :items-per-page="10" show-select></v-data-table></material-card>',	
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