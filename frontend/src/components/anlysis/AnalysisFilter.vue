<template>
  <v-container
		fill-height
    fluid
    grid-list-xl
	>
    <v-layout wrap>
      <v-flex md12 lg8>
        <material-card
          color="orange"
          title="Simulation List"
          text="Please select an indicator to analyze."
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
						loading-text="Data Loading..."
            show-select
          >

          </v-data-table>
        </material-card>
      </v-flex>
      <v-flex md12 lg4>
				<material-card
          color="green"
          title="Search Conditions"
          text="Select analysis search condition."
        >
					<v-select
						:items="ixType.list"
						item-text="subName"
						item-value="subCode"
            v-model="ixType.selected"
						label="Select Indicator"
					>
					</v-select>
          <v-select
						:items="networkType.list"
						item-text="subName"
						item-value="subCode"
            v-model="networkType.selected"
						label="Select Network"
					>
					</v-select>
					<v-select
						:items="ampmType.list"
						item-text="subName"
						item-value="subCode"
            v-model="ampmType.selected"
						label="Select Ampm"
					>
					</v-select>
				</material-card>
        <div class="text-center">
          <v-btn
            color="success"
            class="font-weight-light"
            @click="searchSimulationStats"
          >Simulation Stats
          </v-btn>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
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
            text: "번호",
            value: "simulationNumber",
            align: 'left',
            width: '20%'
          },
          {
            sortable: false,
            text: "파일 명",
            value: "fileName",
            align: 'left',
            width: '80%'
          }
        ],
        list: []
      },
      service: {}
    };
  },
  created() {
    // API 요청 서비스
    this.service = {
        // 지표 선택 종류 조회
        searchIxTypeList: () => {
          this.$http.get("/api/codes/100")
          .then(response => {
            this.ixType.list = response.data.body.commonCodes;
            this.ixType.selected = response.data.body.commonCodes[0];
          })
          .catch(e => {
            console.error("error : ", e);
          });
        },      
        // 네트워크 선택 종류 조회
        searchNetworkTypeList: () => {
          this.$http.get("/api/codes/400")
          .then(response => {
            this.networkType.list = response.data.body.commonCodes;
            this.networkType.selected = response.data.body.commonCodes[0];
          })
          .catch(e => {
            console.error("error : ", e);
          });
        },
        // AMPM 선택 종류 조회
        searchAmpmTypeList: () => {
          this.$http.get("/api/codes/200")
          .then(response => {
            this.ampmType.list = response.data.body.commonCodes;
            this.ampmType.selected = response.data.body.commonCodes[0];
          })
          .catch(e => {
            console.error("error : ", e);
          });
        },
        // 지표 시뮬레이션 조회
        searchSimulationList: () => {
          this.$http.get("/api/simulation/histories")
          .then(response => {
            this.simulation.list = response.data.body.simulationHistories;
          })
          .catch(e => {
            console.error("error : ", e);
          });
        },
        // 시뮬레이션 분석
        searchSimulationAnalysis: () => {

        }
    };
  },
  mounted() {
    // 초기 데이터 설정
    this.initalize();
  },
  methods: {
    // 화면 초기 설정
    initalize() {
      // 지표 종류 조회
      this.service.searchIxTypeList();

      // 네트워크 종류 조회
      this.service.searchNetworkTypeList();

      // Ampm 시간대 종류 조회
      this.service.searchAmpmTypeList();

      // 최근 시뮬레이션 목록 조회
      this.service.searchSimulationList();
    },
    // 분석 실행 이벤트
    searchSimulationStats() {
      if (this.simulation.selected.length === 0) {
        alert("분석을 진행 할 시뮬레이션을 선택하여주세요.");
        return;
      } else {
        /** 
         *  단일, 전체 네트워크
         *  단일 - 1-19 중 교차로 선택 
         *  전체 - 모든교차로 평균 (비교 = 2개 시뮬레이션 평균 표출)
         * */
        if (this.networkType.selected.subCode === "401") {
          if (this.simulation.selected.length > 2) {
            alert("전체 네트워크는 시뮬레이션 2개까지 선택이 가능합니다.");
            return;
          }
        } else {
          if (this.simulation.selected.length > 1) {
            alert("단일 네트워크는 시뮬레이션 1개만 선택이 가능합니다.");
            return;
          }
        }

        let requestData = {};
      }
    }
  }
};
</script>