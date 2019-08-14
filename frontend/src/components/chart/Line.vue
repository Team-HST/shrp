<script>
  import { Line } from 'vue-chartjs'
  import { mapGetters } from 'vuex'

  export default { 
    name: 'VueChartJS',
    extends: Line,
    data () {
      return {
        datacollection: {
          //Data to be represented on x-axis
          labels: [], 
          datasets: [
            {
              label: '지표번호',
              backgroundColor: '#4CAF50',
              pointBackgroundColor: 'white',
              borderWidth: 1,
              pointBorderColor: '#249EBF',
              //Data to be represented on y-axis
              data: []
            }
          ]
        },
        //Chart.js options that controls the appearance of the chart
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: true
              },
              gridLines: {
                display: true
              }
            }],
            xAxes: [ {
              gridLines: {
                display: false
              }
            }]
          },
          legend: {
            display: true
          },
          responsive: true,
          maintainAspectRatio: false
        }
      }
    },
    computed: {
        ...mapGetters(['getAnalysisData'])
    },
    mounted () {
      // API 라벨, 지표 값 데이터 적용
      this.datacollection.labels = this.getAnalysisData.labels
      this.datacollection.datasets[0].data = this.getAnalysisData.values
      
      // 차트 렌더링
      this.renderChart(this.datacollection, this.options)
    }
  }
</script>
