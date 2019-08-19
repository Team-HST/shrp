/**
 * router Path 설정
 */
export default [
    {
      path: '/analysis',
      name: 'Analysis',
      icon: 'mdi-view-dashboard',
      view: 'AnalysisView',
      subName: 'Simulation analysis screen',
      props: true,
      display: true
    },
    {
      path: '/history',
      name: 'History',
      icon: 'mdi-account',
      view: 'HistoryView',
      subName: 'Simulation analysis record inquiry screen',
      props: true,
      display: true
    },
    {
      path: '/simulationAnalysis',
      name: 'SimulationAnalysis',
      icon: 'mdi-account',
      view: 'SimulationAnalysisView',
      subName: 'Simulation analysis record inquiry screen',
      props: true,
      display: false
    }
  ]
  