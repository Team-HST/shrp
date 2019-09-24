<template>
  <v-container
		fill-height
    fluid
    grid-list-xl
	>
    <v-layout wrap>
      <v-flex md12 lg8>
        <material-card
          color="#FFAF20"
          title="모의실험 목록"
          text="분석할 파일을 선택하세요.(비교시 중복선택)."
        >
          <v-data-table
            v-model="simulation.selected"
            :headers="simulation.headers"
            :items="simulation.list"
            :single-select="false"
						:items-per-page="10"
            item-key="simulationNumber"
						class="elevation-1"
						loading="true"
						loading-text="데이터 로딩중..."
						no-data-text="데이터가 존재하지 않습니다."
            show-select
            :footer-props="{
              disableItemsPerPage: true,
              itemsPerPageText: '',
            }"
          >
            <template v-slot:item.simulationNumber="{ item }">
              {{ simulation.list.length - simulation.list.map((x) => {
                  return x.simulationNumber; 
                 }).indexOf(item.simulationNumber) 
              }}
            </template>
          </v-data-table>
        </material-card>
      </v-flex>
      <v-flex md12 lg4>
				<material-card
          color="#11455C"
          title="분석 지표 설정"
          text="분석 지표를 선택하세요."
        >
					<v-select
            v-model="ixType.selected"
						:items="ixType.list"
						item-text="subName"
						item-value="subCode"
						label="분석 지표"
					>
					</v-select>
				</material-card>
        <div class="text-center">
          <v-btn
            class="font-weight-light white--text"
            @click="searchSimulationStats"
            color="#11455C"
          >
            모의실험 분석 실행
          </v-btn>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapMutations, mapActions } from 'vuex'
import routerLinks from '@/router/paths'

export default {
  data() {
    return {
      ixType: {
       selected: [],
       list: []
      },
      networkType: {
        selected: [],
        list: []
      },
      ampmType: {
        selected: [],
        list: []
      },
      simulation: {
        selected: [],
        headers: [
          {
            sortable: false,
            text: '번호',
            value: 'simulationNumber',
            align: 'center',
            width: '10%'
          },
          {
            sortable: false,
            text: '파일 명',
            value: 'fileName',
            align: 'center',
            width: '90%'
          }
        ],
        list: []
      },
      service: {} // 서비스 메소드 정의
    };
  },
  created() {
    // API 요청 서비스
    this.service = {
      // 지표 선택 종류 조회
      searchIxTypeList: () => {
        this.$http.get('/api/codes/100')
        .then(response => {
          this.ixType.list = response.data.body.commonCodes;
          this.ixType.selected = response.data.body.commonCodes[0].subCode;
        })
        .catch(e => {
          console.error('error : ', e);
        });
      },
      // 지표 시뮬레이션 조회
      searchSimulationList: () => {
        this.$http.get('/api/simulation/histories')
        .then(response => {
          this.simulation.list = response.data.body.simulationHistories;
        })
        .catch(e => {
          console.error('error : ', e);
        });
      },
      // 시뮬레이션 분석
      searchSimulationAnalysis: (requestURL) => {
        // 이동 페이지 라우터 정보설정
        this.changeLayoutLink(
          routerLinks.filter(link => link.name === 'SimulationAnalysis')[0]
        );

        // 시뮬레이션 데이터 저장
        this.searchSimulationAnalysis(requestURL + '?crossRoadNumber=all' + '&userNm=ddd')
        .then(() => {
          // 시뮬레이션 분석 페이지 이동
          this.$router.push({name: 'SimulationAnalysis'});
        });
      }
    };
  },
  mounted() {
    // 초기 데이터 설정
    this.initalize();
  },
  methods: {
    // mutations 설정
    ...mapMutations(['changeLayoutLink', 'setSimulationNumbers']),
    ...mapActions(['searchSimulationAnalysis']),
    // 화면 초기 설정
    initalize() {
      // 지표 종류 조회
      this.service.searchIxTypeList();

      // 최근 시뮬레이션 목록 조회
      this.service.searchSimulationList();
    },
    // 분석 실행 이벤트
    searchSimulationStats() {
      let selectSimulLangth = this.simulation.selected.length;
      // 시뮬레이션 분석 API URL
      let chartAnalysisAPI = '';

      if (selectSimulLangth === 0) {
        alert('분석을 진행 할 시뮬레이션을 선택하여주세요.');
        return;
      } else {
        /** 
         *  기준 - 시뮬 단일 선택, 비교 선택
         *  교차로 - 분석차트 화면에서 전체에서 단일 교차로선택
         **/
        if (selectSimulLangth > 3) {
          alert('시뮬레이션은 3개까지 선택이 가능합니다.');
          return;
        }

        // 조회 시뮬레이션 조건 생성
        let simulationNumbers = this.simulation.selected.map(simulation => simulation.simulationNumber).join('_');

        this.setSimulationNumbers(simulationNumbers);

        chartAnalysisAPI = `/api/analysis/${simulationNumbers}/${this.ixType.selected}`;
        this.service.searchSimulationAnalysis(chartAnalysisAPI);
      }
    }
  }
};
</script>