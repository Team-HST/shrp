<template>
  <v-container
		fill-height
    fluid
    grid-list-xl
	>
    <v-layout wrap>
      <v-flex md12 lg6>
        <material-card
          color="orange"
          title="Employee Stats"
          text="New employees on 15th September, 2016"
        >
          <v-data-table
            :headers="simulation.headers"
            :items="simulation.list"
						:items-per-page="5"
						class="elevation-1"
						loading="true"
						loading-text="Data Loading..."
          >

          </v-data-table>
        </material-card>
      </v-flex>
      <v-flex md12 lg6>
				<material-card
          color="green"
          title="Employee Stats"
          text="New employees on 15th September, 2016"
        >
					<v-select
						:items="ixTypeList"
						item-text="subName"
						item-value="subCode"
						label="Filled style"
						@change="changeIxType"
					>
					</v-select>
					<v-select
						:items="ampmTypeList"
						item-text="subName"
						item-value="subCode"
						label="Filled style"
						@change="changeAmpmType"
					>
					</v-select>
					<v-btn
						color="success"
						class="font-weight-light"
						@click="searchSimulationStats"
					>Simulation Stats
					</v-btn>
				</material-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      ixTypeList: [],
      ampmTypeList: [],
      simulation: {
        headers: [
          {
            sortable: false,
            text: "번호",
            value: "simulationNumber"
          },
          {
            sortable: false,
            text: "파일 명",
            value: "fileName"
          }
        ],
        list: []
      }
    };
  },
  created() {
    this.initalize();
  },
  methods: {
    // 화면 초기 설정
    initalize() {
      // 지표 종류 조회
      this.$http
        .get("/api/codes/100")
        .then(response => {
          this.ixTypeList = response.data.body.commonCodes;
        })
        .catch(e => {
          console.error("error : ", e);
        });

      // Ampm 시간대 종류 조회
      this.$http
        .get("/api/codes/200")
        .then(response => {
          this.ampmTypeList = response.data.body.commonCodes;
        })
        .catch(e => {
          console.error("error : ", e);
        });

      // 최근 시뮬레이션 목록 조회
      this.$http
        .get("/api/simulation/histories")
        .then(response => {
          this.simulation.list = response.data.body.simulationHistories;
        })
        .catch(e => {
          console.error("error : ", e);
        });
    },
    ixClickEvent(e) {
      console.dir(
        "선택 최근 시뮬레이션 NO : " + e.currentTarget.getAttribute("no")
      );
    },
    changeIxType(value) {
      console.dir("선택 IX TYPE : " + value);
    },
    changeAmpmType(value) {
      console.dir("선택 AMPM TYPE : " + value);
    },
    searchSimulationStats() {
      alert("시뮬레이션 조회 API");
    }
  }
};
</script>