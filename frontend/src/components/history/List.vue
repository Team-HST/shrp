<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
    ref="historyList"
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
						:loading="history.loading"
						loading-text="데이터 로딩중..."
            no-data-text="조회된 데이터가 없습니다"
            :footer-props="{
              disableItemsPerPage: true,
              showFirstLastPage: true,
              itemsPerPageText: '',
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
            <template v-slot:item.analysisFileName="{ item }">
              <template v-if="item.analysisData.dataset.length > 1">
                {{ item.analysisFileName }} 외 {{ item.analysisData.dataset.length-1 }}
              </template>
               <template v-else>
                 {{ item.analysisFileName }}
              </template>

            </template>
            <template v-slot:item.displayChart="{ item }">
              <a @click="showHistoryChart(item.analysisData.dataset)">보기</a>
            </template>
            <template v-slot:item.displayDiagram="{ item }">
              <a @click="showHistoryDiagram(item.analysisData.dataset)">보기</a>
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
    </v-layout>
    <v-dialog
      v-model="chartDialog"
      max-width="1000"
    >
      <div ref="chart">
        <material-card
          color="#11455C"
          title="모의실헝 분석결과 그래프"
          text="그래프에 마우스 올릴 시 값 확인이 가능합니다."
          imageDown="chart"
          :clickPrint="print"
        >
          <v-card>
            <v-card-text>
              <chart-bar :chart-data="getChartData"></chart-bar>
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
      </div>
    </v-dialog>

    <v-dialog
      v-model="diagramDialog"
      width="unset"
    >
      <div ref="diagram">
        <material-card
          color="#11455C"
          title="모의실험 분석결과 도표"
          text="모의실험 별 분석결과 도표입니다."
          imageDown="diagram"
          :clickPrint="print"
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
      </div>
    </v-dialog>
  </v-container>
</template>

<script>
  import { mapGetters ,mapMutations } from 'vuex'
  import html2canvas from 'html2canvas'
  
  export default {
    data() {
      return {
        history: {
          loading: true,
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
        chartDialog: false, // 차트 모달 표출 여부
        diagramDialog: false, // 이력 모달 표출 여부
        service: {} // 서비스 메소드 정의
      }
    },
    computed: {
      ...mapGetters(['getAnalysisData', 'getChartData'])
    },
    created() {
      this.service = {
        searchHistoryList: () => {
          this.$http.get('/api/analysis/histories?page=1&size=1000')
          .then(response => {
            this.history.list = response.data.body.simulationAnalysisHistories;
            this.history.loading = false;
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
      ...mapMutations(['setAnalysisData', 'setChartData' ,'setDiagramData']),

      // 시뮬레이션 차트 결과 표출
      showHistoryChart: function(data) {
        data = _.cloneDeep(data);

        // 분석 데이터 설정
        this.setAnalysisData(data);
        // 차트 데이터 설정
        this.setChartData(data);

        // 차트 모달 표출
        this.chartDialog = true;
      },
      showHistoryDiagram: function(data) {
        data = _.cloneDeep(data);

        // 분석 데이터 설정
        this.setAnalysisData(data);
        // 도표 데이터 설정
        this.setDiagramData(data);

        // 도표 모달 표출
        this.diagramDialog = true;
      },
			// 시뮬레이션 결과 이미지 저장
			async print(printEl) {
				const el = this.$refs[printEl];
				const options = {
					type: 'dataURL'
				}
				// VueHtml2Canvas를 이용한 base64생성
        let output = await this.$html2canvas(el, options);
                
				let byteString = atob(output.split(',')[1]);
				let mimeString = output.split(',')[0].split(':')[1].split(';')[0]

				// Bolb 타입 데이터를 만들이기위한 데이터생성
				let ab = new ArrayBuffer(byteString.length);
				let ia = new Uint8Array(ab);
				for (let i = 0; i < byteString.length; i++) {
					ia[i] = byteString.charCodeAt(i);
				}

        // 파일저장이름
        let filename = `${this.getAnalysisData[0].fileName.replace(/:/g, '')} 외 ${this.getAnalysisData.length-1}_(${this.getAnalysisData[0].indicatorName}_`+
                       `${this.getAnalysisData[0].crossRoadNumber}).png`;
        // Blob 생성
				let bolb = new Blob([ab], { type: 'image/png' });
                
                // ie
				if (window.navigator && window.navigator.msSaveOrOpenBlob) {
					window.navigator.msSaveOrOpenBlob(bolb, filename);
				} else { // chrome
					let link = document.createElement('a');
					link.href = window.URL.createObjectURL(bolb);
					link.download = filename;
					link.click();
				}
			}
    }
  }
</script>

<style>
  .v-dialog {
    box-shadow: none;
  }

  .v-data-footer__select {
    visibility: hidden;
  }
</style>