<template>
	<v-container
		fill-height
		fluid
		grid-list-xl
	>
		<v-layout wrap>
			<v-flex md12 lg10>
        <material-card
          color="#FFAF20"
          title="모의실헝 분석결과 그래프"
          text="그래프에 마우스 올릴 시 값 확인이 가능합니다."
        >
          <chart-bar :chartData="getChartData"></chart-bar>
        </material-card>
			</v-flex>
      <v-flex md12 lg2>
				<material-card
					color="#11455C"
					title="지표 선택"
					text="지표 별 세부 결과입니다."
				>
					<v-select
            ref="ixSelect"
						v-model="ixType.selected"
						@change="changeBarChartData()"
						:items="ixType.list"
						item-text="subName"
						item-value="subCode"
						label="지표"
					>
					</v-select>
				</material-card>
				<material-card
					color="#11455C"
					title="교차로 선택"
					text="교차로 별 세부 결과입니다."
				>
					<v-select
						v-model="crossNumType.selected"
						@change="changeBarChartData()"
						:items="crossNumType.list"
						item-text="text"
						item-value="value"
						label="전체/교차로"
					>
					</v-select>
				</material-card>
			</v-flex>
			<v-flex md12 lg10>
        <material-card 
          color="orange" 
          title="모의실험 분석결과 도표" 
          text="모의실험 별 분석결과 도표입니다."
        >
          <simulationAnalysis-table></simulationAnalysis-table>
        </material-card>
			</v-flex>
		</v-layout>
	</v-container>
</template>

<script>
	import { mapGetters, mapMutations, mapActions } from 'vuex'

	export default {
		name: 'VueChartJS',
		data() {
			return {
        barchartData: {}, // Bar 차트 데이터
        ixType: { // 지표 데이터
          selected: [], // 지표 선택 데이터
          list: [] // 지표 목록 데이터
        },
				crossNumType: { // 교차로 데이터
					selected: {}, // 박스 선택 데이터
					list: [] // 교차로 모록 데이터
				},
				userNm : '' // 사용자명
			}
		},
		computed: {
			...mapGetters(['getAnalysisData', 'getSimulationNumbers', 'getChartData', 'getChartOptions'])
		},
		created() {
      // API 요청 서비스
      this.service = {
        // 지표 선택 종류 조회
        searchIxTypeList: () => {
          this.$http.get('/api/codes/100')
          .then(response => {
            this.ixType.list = response.data.body.commonCodes;

            this.ixType.selected = this.ixType.list.filter(ix => 
              ix.subCode === this.getAnalysisData[0].indicatorCode
            )[0].subCode;
          })
          .catch(e => {
            console.error('error : ', e);
          });
        }
      }
    },
    mounted() {
      // 초기 데이터 설정
      this.initalize();
    },
		methods: {
      ...mapMutations(['setChartData']),
      ...mapActions(['searchSimulationAnalysis']),
			getUserName: function() {
				return this.$store.getters.getUserName;
			},
      // 화면 초기 설정
      initalize: function() {
        // 시뮬레이션 분석대상이 존재하지 않을 경우
        if (this.getSimulationNumbers === undefined || this.getSimulationNumbers === null || this.getSimulationNumbers === "") {
          this.$router.push({name: 'Analysis'})
          return
        }
				// 사용자명
				this.userNm = this.getUserName();

        // 차트 데이터 변경 요청
        this.setBarchartData(this.getAnalysisData);

        // 지표 종류 조회
        this.service.searchIxTypeList();

        // 교차로 셀렉트 박스 설정
        this.crossNumType.list = this.getAnalysisData[0].labels.map((item, index) => {
          return {text: item, value: index+1}
        });

        // 교차로 셀렉트박스 all 추가
        this.crossNumType.list.unshift({text: '전체', value: 'all'});

        // 교차로 셀렉트박스 전체 지정
        this.crossNumType.selected = this.crossNumType.list[0].value;
      },
			// 차트 데이터 동적 변경
			setBarchartData: function(data) {
        this.setChartData(data);
			},
			// 시뮬레이션 교차로 변경
			changeBarChartData: function() {
				// 시뮬레이션 분석 api 조회
        this.searchSimulationAnalysis(`/api/analysis/${this.getSimulationNumbers}/${this.ixType.selected}`
         + `?crossRoadNumber=${this.crossNumType.selected}&userNm=`+this.userNm)
				.then(() => {
					this.setBarchartData(this.getAnalysisData);
				});
			}
		}
	}
</script>