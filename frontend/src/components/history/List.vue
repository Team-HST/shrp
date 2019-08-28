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
          title="분석이력 목록"
          text="분석이력 목록입니다.(차트 또는 도표 보기 클릭 시 결과 확인이 가능합니다.)"
        > 
          <v-row>
            <div class="flex-grow-1"></div>
            <div class="flex-grow-1"></div>
            <v-text-field
              class="pr-2"
              v-model="history.searchText"
              append-icon="mdi-magnify"
              label="검색어를 입력하세요."
              single-line
              hide-details
            ></v-text-field>
          </v-row>
          <v-data-table
            :headers="history.headers"
            :items="history.list"
						:items-per-page="10"
            :search="history.searchText"
            item-key="analysisNumber"
						class="elevation-1"
						loading="true"
						loading-text="데이터 로딩중..."
            :footer-props="{
              itemsPerPageText: '목록 갯수',
              showFirstLastPage: true,
              firstIcon: 'mdi-chevron-double-left',
              lastIcon: 'mdi-chevron-double-right',
              prevIcon: 'mdi-chevron-left',
              nextIcon: 'mdi-chevron-right'
            }"
          > 
            <template v-slot:item.analysisNumber="{ item }">
              {{ history.list.length - history.list.map((x) => {
                return x.analysisNumber; 
                }).indexOf(item.analysisNumber) 
              }}
            </template>
            <template v-slot:item.displayChart="{ item }">
              <a @click="showHistoryChart(item.analysisData)">보기</a>
            </template>
            <template v-slot:item.displayDiagram="{ item }">
              <a @click="showHistoryDiagram(item.analysisData)">보기</a>
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
    </v-layout>
    <v-dialog
      v-model="chartDialog"
      max-width="1000"
    >
      <material-card
        color="#11455C"
        title="모의실헝 분석결과 그래프"
        text="그래프에 마우스 올릴 시 값 확인이 가능합니다."
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
              @click="chartDialog = false"
            > 
              닫기
            </v-btn>
          </v-card-actions>
        </v-card>
      </material-card>
    </v-dialog>

    <v-dialog
      v-model="diagramDialog"
      max-width="1000"
    >
      <material-card
        color="#11455C"
        title="모의실험 분석결과 도표"
        text="모의실험 별 분석결과 도표입니다."
      >
        <v-card>
          <v-card-text>
            <simulationAnalysis-table></simulationAnalysis-table>
          </v-card-text>
          <v-card-actions>
            <div class="flex-grow-1"></div>
            <v-btn
              class="font-weight-bold"
              color="blue darken-1"
              text
              @click="diagramDialog = false"
            > 
              닫기
            </v-btn>
          </v-card-actions>
        </v-card>
      </material-card>
    </v-dialog>
  </v-container>
</template>

<script>
  import { mapMutations } from 'vuex'

  export default {
    data() {
      return {
        history: {
          list: [],
          headers: [
            {
              sortable: false,
              text: '번호',
              value: 'analysisNumber',
              align: 'center',
              width: '10%'
            },
            {
              sortable: false,
              text: '시물레이션',
              value: 'analysisFileName',
              align: 'center',
              width: '26%'
            },
            {
              sortable: true,
              text: '분석지표',
              value: 'indicator',
              align: 'center',
              width: '20%'
            },
            {
              sortable: true,
              text: '분석대상',
              value: 'analysisTarget',
              align: 'center',
              width: '10%'
            },
            {
              sortable: true,
              text: '분석날짜',
              value: 'analysisDate',
              align: 'center',
              width: '16%'
            },
            {
              sortable: false,
              text: '차트',
              value: 'displayChart',
              align: 'center',
              width: '8%'
            },
            {
              sortable: false,
              text: '도표',
              value: 'displayDiagram',
              align: 'center',
              width: '10%'
            }
          ],
          searchText: ''
        },
        chartData: {}, // 이력 차트 데이터
        chartDialog: false, // 차트 모달 표출 여부
        diagramData: {}, // 이력 도표 데이터
        diagramDialog: false, // 이력 모달 표출 여부
        service: {} // 서비스 메소드 정의
      }
    },
    created() {
      this.service = {
        searchHistoryList: () => {
          this.$http.get('/api/analysis/histories?page=1&size=1000')
          .then(response => {
            this.history.list = response.data.body.simulationAnalysisHistories;
          })
          .catch(e => {
            console.error('error : ', e);
          });
        }
      }

      // 시뮬레이셔 이력 조회
      this.service.searchHistoryList();
    },
    methods: {
      ...mapMutations(['setDiagramData']),

      // 시뮬레이션 차트 결과 표출
      showHistoryChart: function(chartData) {
        let chartDataArr = new Array();

        // 단일, 비교 분석 체크
        if (chartData.dataset == null) { // 단일 분석 배열 생성
          chartDataArr.push(chartData);
        } else { // 비교 분석
          chartDataArr = chartData.dataset;
        }

        // 차트 데이터셋 생성
        let chartDataset = [
          {
            label: chartDataArr[0].fileName,
            backgroundColor: '#FFAF20',
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: '#FFAF20',
            data: chartDataArr[0].values
          }
        ];
        
        // 비교 분석 데이터 추가
        if (chartDataArr.length > 1) {
           chartDataset.push({
              label: chartDataArr[1].fileName,
              backgroundColor: '#11455C',
              pointBackgroundColor: 'white',
              borderWidth: 1,
              pointBorderColor: '#11455C',
              data: chartDataArr[1].values
          });
        } 
        
        //  차트 데이터 적용
        this.chartData = {
          labels: chartDataArr[0].labels,
          datasets: chartDataset
        }
        
        // 차트 모달 표출
        this.chartDialog = true;
      },
      showHistoryDiagram: function(diagramData) {
        let diagramDataset = [];

        // 단일 / 비교 시뮬레이션 체크
        if (diagramData.dataset == null) {
          diagramDataset = _.cloneDeep(new Array(diagramData));
        } else {
          diagramDataset = _.cloneDeep(diagramData.dataset);
        }

        // 도표 데이터 설정
        this.setDiagramData(diagramDataset);

        // 도표 모달 표출
        this.diagramDialog = true;
      }
    }
  }
</script>

<style>
  .v-dialog {
    box-shadow: none;
  }
</style>