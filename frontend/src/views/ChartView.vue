<template>
  <section class="container">
    <h1>SIMULATION CHART</h1>
      <div class="column">
        <h3>Bar Chart</h3>
        <Bar-chart></Bar-chart>
        <!--Bar Chart example-->
      </div>
  </section>
</template>

<script>
  import BarChart from '@/components/chart/BarChart'

  export default {
    name: 'ChartView',
    components: {
      BarChart
    }
  }
</script>