<script>
  import { Bar } from 'vue-chartjs'

  export default {
    extends: Bar,
    data () {
      return {
        datacollection: {
          labels: [ 
          			'1교차로', '2교차로', 
          			'3교차로', '4교차로',
          			'5교차로', '6교차로',
          			'7교차로', '8교차로',
          			'9교차로', '10교차로',
          			'11교차로', '12교차로',
          			'13교차로', '14교차로',
          			'15교차로', '16교차로',
          			'17교차로', '18교차로','19교차로'
          		  ],
          datasets: [
            {
              label: 'QLEN/대기행렬길이',
              backgroundColor: '#f87979',
              pointBackgroundColor: 'white',
              borderWidth: 1,
              pointBorderColor: '#249EBF',
              //Data to be represented on y-axis
              data: [
              		 40, 20,
              		 30, 50,
              		 90, 10,
              		 20, 40,
              		 50, 70,
              		 90, 100,
              		 150.3, 175.3,
              		 17.1, 28.3,
              		 30, 40.2, 13
              		]
            }
          ]
        },
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
    mounted () {
      this.renderChart(this.datacollection, this.options)
    }
  }
</script>