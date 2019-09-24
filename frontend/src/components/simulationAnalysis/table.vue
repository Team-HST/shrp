<template>
   
    <v-simple-table>
      <thead>
        <tr>
          <th class="text-left font-weight-black" v-for="item in getDiagramData.labels" :key="item">{{ item }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(value, index) in getDiagramData.values" :key="index">
          <td v-for="(item, index) in value" :key="index">{{ item }}</td>
        </tr>
      </tbody>
    </v-simple-table>

</template>

<script>
  import { mapGetters } from 'vuex'

  export default { 
    data () {
        return {
          diagramData: {}
        }
    },
    computed: {
      ...mapGetters(['getAnalysisData', 'getDiagramData'])
    }
  }
 </script>