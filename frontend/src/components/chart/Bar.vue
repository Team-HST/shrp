<script>
  import { Bar, mixins } from 'vue-chartjs'
  const { reactiveProp } = mixins

  export default {
    extends: Bar,
    props : ["text"],
    mixins: [reactiveProp],
    data() {
      return {
        options: {
          title: {
            display: true,
            text: ''
          },
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
          tooltips: {
            mode: 'index',
            callbacks: {
                label: function(tooltipItem, data) {
                    var label = ': ';
                    label += Math.round(tooltipItem.yLabel * 100) / 100;
                    return label;
                }
            }
          },
          responsive: true,
          maintainAspectRatio: false
        }
      }
    },
    mounted() {
      // 차트 타이틀
      this.options.title.text = this.text;
      // 차트 렌더링
      this.renderChart(this.chartData, this.options)
    }
  }
</script>
 
