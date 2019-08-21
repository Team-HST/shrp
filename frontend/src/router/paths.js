/**
 * router Path 설정
 */
export default [
    {
      path: '/analysis',
      name: 'Analysis',
      icon: 'mdi-filter',
      view: 'AnalysisView',
      subName: 'Simulation analysis screen',
      props: true,
      display: true
    },
    {
      path: '/history',
      name: 'History',
      icon: 'mdi-history',
      view: 'HistoryView',
      subName: 'Simulation analysis record inquiry screen',
      props: true,
      display: true
    },
    {
      path: '/simulationAnalysis',
      name: 'SimulationAnalysis',
      icon: 'mdi-chart-bar',
      view: 'SimulationAnalysisView',
      subName: 'Simulation analysis record inquiry screen',
      props: true,
      display: false
    }
  ]
  