<script>
  import { Bar, mixins } from 'vue-chartjs'
  const { reactiveProp } = mixins

  export default {
    extends: Bar,
    mixins: [reactiveProp],
    props: ['chartdata', 'options'],
    data() {
      return {
      }
    },
    mounted() {
      // 차트 렌더링
      this.renderChart(this.chartData, this.options)
    },
    watch: {
     options () {
        this.renderChart(this.chartData, this.options);
     }
    }
  }
</script>
 
