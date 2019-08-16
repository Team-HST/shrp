<template>
	<v-container
		fill-height
		fluid
		grid-list-xl
	>
		<v-layout wrap>
			<v-flex md12 lg10>
				<chart-bar :chart-data="barchartData"></chart-bar>
			</v-flex>
			<v-flex md12 lg2>
				<material-card
          color="green"
          title="Select CrossNum"
          text="select an cross number."
        >
					<v-select
						v-model="crossNumType.selected"
						@change="changeBarChartData($event)"
						:items="crossNumType.list"
						item-text="text"
						item-value="value"
						label="Select Indicator"
					>
					</v-select>
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
				selected: {},
				list: []
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
		this.setBarchartData(this.getAnalysisData.labels, this.getAnalysisData.values);

		// 교차로 셀렉트 박스 설정
		this.crossNumType.list = this.getAnalysisData.labels.map((item, index) => {
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
		setBarchartData: function(labels, data) {
			this.barchartData = {
				labels: labels,
				datasets: [
					{
						label: '지표번호',
						backgroundColor: '#ff9800',
						pointBackgroundColor: 'white',
						borderWidth: 1,
						pointBorderColor: '#249EBF',
						data: data
					}
				]
			}
		},
		changeBarChartData: function(value) {
			// 시뮬레이션 분석 api 조회
			this.searchSimulationAnalysis(this.getAnalysisApiURL + "?crossRoadNumber=" + value)
			.then(() => {
				this.setBarchartData(this.getAnalysisData.labels, this.getAnalysisData.values);
			});
		}
	}
}
</script>