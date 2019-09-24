/**
 * router Path 설정
 */
export default [
	{
	    path: '/login',
      name: 'Login',
      view: 'LoginView',
      mainName: '로그인',
      subName: '로그인 페이지',
      props: true,
      display: true
	},
    {
      path: '/analysis',
      name: 'Analysis',
      icon: 'mdi-filter',
      view: 'AnalysisView',
      mainName: '모의실험 분석',
      subName: '모의실험 분석 페이지',
      props: true,
      display: true
    },
    {
      path: '/history',
      name: 'History',
      icon: 'mdi-history',
      view: 'HistoryView',
      mainName: '분석이력 조회',
      subName: '분석이력 조회 페이지',
      props: true,
      display: true
    },
    {
      path: '/simulationAnalysis',
      name: 'SimulationAnalysis',
      icon: 'mdi-chart-bar',
      view: 'SimulationAnalysisView',
      mainName: '모의실험 분석 결과',
      subName: '모의실험 분석결과 페이지',
      props: true,
      display: false
    }
  ]
  