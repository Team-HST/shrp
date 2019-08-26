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
        <tr v-for="(value, index) in getDiagramData.values" :key="index">
          <td v-for="(item, index) in value" :key="index">{{ item }}</td>
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
    }
  }
 </script>