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
              <a>보기</a>
            </template>
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
              width: '30%'
            },
            {
              sortable: true,
              text: "분석대상",
              value: "indicator",
              align: 'center',
              width: '20%'
            },
            {
              sortable: true,
              text: "교차로",
              value: "analysisTarget",
              align: 'center',
              width: '10%'
            },
            {
              sortable: true,
              text: "날짜",
              value: "analysisDate",
              align: 'center',
              width: '20%'
            },
            {
              sortable: false,
              text: "차트",
              value: "displayChart",
              align: 'center',
              width: '10%'
            }
          ]
        },
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
    }
  }
</script>