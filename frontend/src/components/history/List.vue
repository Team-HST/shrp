<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-layout wrap>
      <v-flex md12 lg12>
        <material-card
          color="#FFAF20"
          title="History List"
          text="Simulation inquiry history list"
        >
          <v-data-table
            v-model="history.selected"
            :headers="history.headers"
            :items="history.list"
						:items-per-page="10"
            item-key="simulationNumber"
						class="elevation-1"
						loading="true"
						loading-text="Data Loading..."
          >
          </v-data-table>
        </material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  export default {
    data() {
      return {
        history: {
          selected: {},
          list: [],
          headers: [
            {
              sortable: true,
              text: "번호",
              value: "simulationNumber",
              align: 'left',
              width: '10%'
            },
            {
              sortable: true,
              text: "교차로 명",
              value: "fileName",
              align: 'left',
              width: '50%'
            },
            {
              sortable: true,
              text: "요일",
              value: "fileName",
              align: 'left',
              width: '10%'
            },
            {
              sortable: true,
              text: "시간대",
              value: "fileName",
              align: 'left',
              width: '10%'
            },
            {
              sortable: false,
              text: "표",
              value: "fileName",
              align: 'left',
              width: '10%'
            },
            {
              sortable: false,
              text: "차트",
              value: "fileName",
              align: 'left',
              width: '10%'
            }
          ]
        },
        service: {} // 서비스 메소드 정의
      }
    },
    created() {

    },
    methods: {
      
    }
  }
</script>