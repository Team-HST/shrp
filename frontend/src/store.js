import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
    state: { //vuex 데이터 저장소
      layout: {
        drawer: true,
        link: {
          path: '/analysis',
          name: 'Analysis',
          icon: 'mdi-filter',
          view: 'AnalysisView',
          subName: 'Simulation analysis screen'
        }
      },
      analysis: {
        apiURL: '', // 분석 요청 API URL
        data: { // 분석 요청 API RESPONSE DATA
          labels: [],
          values: []
        } 
      }
    },
    getters: { // vuex 저장소 데이터 조회
      getLayoutDrawer: (state) => {
        return state.layout.drawer;
      },
      getLayoutLink: (state) => {
        return state.layout.link;
      },
      getAnalysisData: (state) => {
        return state.analysis.data;
      },
      getAnalysisApiURL: (state) => {
        return state.analysis.apiURL
      }
    },
    mutations: { // vuex 저장소 데이터 변경
      setDrawer: (state, drawer) => {
        state.layout.drawer = drawer
      },
      changeDrawer: (state) => {
        state.layout.drawer = !state.layout.drawer;
      },
      setAnalysisApiUrl: (state, apiURL) => {
        state.analysis.apiURL = apiURL;
      },
      changeLayoutLink: (state, link) => {
        state.layout.link = link;
      },
      setAnalysisData: (state, data) => {
        state.analysis.data = data;
      }
    },
    actions: { // vuex 저장소 비동기 데이터 변경
    // 시뮬레이션 분석 데이터 조회
    searchSimulationAnalysis: (context, apiURL) => {
      return axios.get(apiURL)
      .then(response => {
        // analysis data 변경
        context.commit('setAnalysisData', response.data.body);
      })
      .catch(e => {
        console.error("error : ", e);
      })
    }
  }
})