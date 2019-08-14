<template>
	<v-container
		fill-height
		fluid
		grid-list-xl
	>
		<v-layout wrap>
			<v-flex md12 lg12>
				<chart-bar></chart-bar>
			</v-flex>
		</v-layout>
	</v-container>
</template>

<script>
import { Bar } from 'vue-chartjs'
import { mapMutations, mapActions } from 'vuex'

export default {
	name: 'VueChartJS',
	data() {
		return {}
	},
	mounted() {
		let apiURL = this.$route.params.apiURL;

		// 시뮬레이션 분석 URL이 존재하지 않을 경우
		if (apiURL === undefined || apiURL === null) {
			this.$router.push({name: "Analysis"})
			return
		}

		this.setAnalysisApiUrl(apiURL);
		this.searchSimulationAnalysis();
	},
	methods: {
		...mapMutations(['setAnalysisApiUrl']),
		...mapActions(['searchSimulationAnalysis'])
	}
}
</script>