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
            :headers="history.headers"
            :items="history.list"
						:items-per-page="10"
            item-key="analysisNumber"
						class="elevation-1"
						loading="true"
						loading-text="Data Loading..."
          >
            <template v-slot:item.analysisNumber="{ item }">
              {{ history.list.length - history.list.map(function(x) {return x.analysisNumber; }).indexOf(item.analysisNumber) }}
            </template>
            <template v-slot:item.displayChart="{ item }">
              <a @click="showHistoryChart(item.analysisData)">보기</a>
            </template>
            <template v-slot:item.displayDiagram="{ item }">
              <a>보기</a>
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
    </v-layout>
    <v-dialog
      v-model="dialog"
      max-width="1000"
    >
      <material-card
        color="#11455C"
        title="History Chart Modal"
        text="Simulation inquiry history list"
      >
        <v-card>
          <v-card-text>
            <chart-bar :chart-data="chartData"></chart-bar>
          </v-card-text>
          <v-card-actions>
            <div class="flex-grow-1"></div>
            <v-btn
              class="font-weight-bold"
              color="blue darken-1"
              text
              @click="dialog = false"
            >
              Close
            </v-btn>
          </v-card-actions>
        </v-card>
      </material-card>
    </v-dialog>
  </v-container>
</template>

<script>
  export default {
    data() {
      return {
        history: {
          list: [],
          headers: [
            {
              sortable: false,
              text: "번호",
              value: "analysisNumber",
              align: 'center',
              width: '10%'
            },
            {
              sortable: false,
              text: "시물레이션",
              value: "analysisFileName",
              align: 'center',
              width: '26%'
            },
            {
              sortable: true,
              text: "분석지표",
              value: "indicator",
              align: 'center',
              width: '20%'
            },
            {
              sortable: true,
              text: "분석대상",
              value: "analysisTarget",
              align: 'center',
              width: '10%'
            },
            {
              sortable: true,
              text: "분석날짜",
              value: "analysisDate",
              align: 'center',
              width: '16%'
            },
            {
              sortable: false,
              text: "차트",
              value: "displayChart",
              align: 'center',
              width: '8%'
            },
            {
              sortable: false,
              text: "도표",
              value: "displayDiagram",
              align: 'center',
              width: '10%'
            }
          ]
        },
        chartData: {},
        dialog: false,
        service: {} // 서비스 메소드 정의
      }
    },
    created() {
      this.service = {
        searchHistoryList : () => {
          this.$http.get("/api/analysis/histories")
          .then(response => {
            this.history.list = response.data.body.simulationAnalysisHistories
          })
          .catch(e => {
            console.error("error : ", e);
          });
        }
      }

      this.service.searchHistoryList();
    },
    methods: {
      showHistoryChart: function(chartData) {
        this.chartData = {
					labels: chartData.labels,
					datasets: [
						{
							label: '지표번호',
							backgroundColor: '#FFAF20',
							pointBackgroundColor: 'white',
							borderWidth: 1,
							pointBorderColor: '#FFAF20',
							data: chartData.values
						}
					]
				}
        
        this.dialog = true;
      }
    }
  }
</script>

<style>
  .v-dialog {
    box-shadow: none;
    /* background-color: rgb(245, 246, 248) */
  }

</style>