<template>
   <material-card 
      color="orange" 
      title="Simulation Diagram" 
      text="Simulation Analysis Diagram Results"
   >
    <v-simple-table>
      <thead>
        <tr>
          <th class="text-left" v-for="item in getDiagramData.labels" :key="item">{{ item }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="value in getDiagramData.values" :key="value">
          <td v-for="item in value" :key="item">{{ item }}</td>
        </tr>
      </tbody>
    </v-simple-table>
  </material-card>
</template>

<script>
  import { mapGetters } from 'vuex'

  export default { 
    data () {
        return {
          diagramData : {
            headers: [],
            list: []
          }
        }
    },
    computed: {
      ...mapGetters(['getAnalysisData', 'getDiagramData'])
    },
    mounted() {
    }
  }
 </script>