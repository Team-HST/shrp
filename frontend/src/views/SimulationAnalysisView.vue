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
					<chart-bar :chart-data="barchartData"></chart-bar>
				</material-card>
			</v-flex>
			<v-flex md12 lg2>
				<material-card
					color="#11455C"
					title="교차로 선택"
					text="교차로 별 세부 결과입니다."
				>
					<v-select
						v-model="crossNumType.selected"
						@change="changeBarChartData($event)"
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
	import { mapGetters, mapActions } from 'vuex'

	export default {
		name: 'VueChartJS',
		data() {
			return {
				barchartData: {}, // Bar 차트 데이터
				crossNumType: { // 교차로 선택 데이터
					selected: {}, // 박스 선택 데이터
					list: [] // 박스 데이터 목록
				}
			}
		},
		computed: {
			...mapGetters(['getAnalysisData', 'getAnalysisApiURL'])
		},
		created() {
			// 시뮬레이션 분석 URL이 존재하지 않을 경우
			if (this.getAnalysisApiURL === undefined || this.getAnalysisApiURL === null || this.getAnalysisApiURL === "") {
				this.$router.push({name: 'Analysis'})
				return
			}

			// 차트 데이터 변경 요청
			this.setBarchartData(this.getAnalysisData);

			// 교차로 셀렉트 박스 설정
			this.crossNumType.list = this.getAnalysisData[0].labels.map((item, index) => {
				return {text: item, value: index+1}
			});

			// 교차로 셀렉트박스 all 추가
			this.crossNumType.list.unshift({text: '전체', value: 'all'});

			// 교차로 셀렉트박스 전체 지정
			this.crossNumType.selected = this.crossNumType.list[0];
		},
		methods: {
			...mapActions(['searchSimulationAnalysis']),
			
			// 차트 데이터 동적 변경
			setBarchartData: function(data) {
        // 차트 데이터 생성
        let chartDataset = chartDataset = [
					{
						label: data[0].fileName,
						backgroundColor: '#FFAF20',
						pointBackgroundColor: 'white',
						borderWidth: 1,
						pointBorderColor: '#FFAF20',
						data: data[0].values
					}
				];

				// 비교 분석 데이터 추가
        if (data.length > 1) {
          chartDataset.push({
              label: data[1].fileName,
              backgroundColor: '#11455C',
              pointBackgroundColor: 'white',
              borderWidth: 1,
              pointBorderColor: '#11455C',
              data: data[1].values
         	});
        }

        //  차트 데이터 적용
        this.barchartData = {
          labels: data[0].labels,
          datasets: chartDataset
        }
        
			},
			// 시뮬레이션 교차로 변경 이벤트
			changeBarChartData: function(value) {
				// 시뮬레이션 분석 api 조회
				this.searchSimulationAnalysis(`${this.getAnalysisApiURL}?crossRoadNumber=${value}`)
				.then(() => {
					this.setBarchartData(this.getAnalysisData);
				});
			}
		}
	}
</script>