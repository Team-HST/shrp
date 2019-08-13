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
      props: true
    },
    {
      path: '/history',
      name: 'History',
      icon: 'mdi-account',
      view: 'HistoryView',
      subName: 'Simulation analysis record inquiry screen',
      props: true
    },
    {
      path: '/simulationStat',
      name: 'SimulationStat',
      icon: 'mdi-account',
      view: 'SimulationStatView',
      subName: 'Simulation analysis record inquiry screen',
      props: true
    }
  ]
  