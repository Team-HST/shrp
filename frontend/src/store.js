import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import lodash from 'lodash'

Vue.use(Vuex)

export default new Vuex.Store({
    state: { //vuex 데이터 저장소
      layout: {
        link: {}
      },
      analysis: {
        apiURL: '', // 분석 요청 API URL
        data: [], // 분석 요청 API RESPONSE DATA
        diagramData: { // 도표 DATA
          labels: [],
          values: []
        }
      }
    },
    getters: { // vuex 저장소 데이터 조회
      getLayoutLink: (state) => {
        return state.layout.link;
      },
      getAnalysisData: (state) => {
        return state.analysis.data;
      },
      getAnalysisApiURL: (state) => {
        return state.analysis.apiURL
      },
      getDiagramData: (state) => {
        return state.analysis.diagramData;
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
      },
      setDiagramData: (state, data) => {
        state.analysis.diagramData.values = [];

        data[0].labels = data[0].labels.map(label => {
          return label.replace('교차로', '');
        });
        data[0].labels.unshift("시뮬레이션 명 ＼ 교치로");
        data[0].values.unshift(data[0].fileName);

        state.analysis.diagramData.labels = data[0].labels;
        state.analysis.diagramData.values.push(data[0].values);

        if (data.length > 1) {
          data[1].values.unshift(data[1].fileName);
          state.analysis.diagramData.values.push(data[1].values);
        } 
      }
    },
    actions: { // vuex 저장소 비동기 데이터 변경
    // 시뮬레이션 분석 데이터 조회
    searchSimulationAnalysis: (context, apiURL) => {
      return axios.get(apiURL)
      .then(response => {
        if (response.data.body.dataset == null) {
          // analysis data 변경
          context.commit('setAnalysisData', new Array(response.data.body));
          context.commit('setDiagramData', new Array(lodash.cloneDeep(response.data.body)));
        } else {
          context.commit('setAnalysisData', response.data.body.dataset);
          context.commit('setDiagramData', lodash.cloneDeep(response.data.body.dataset));
        }
      })
      .catch(e => {e
        // eslint-disable-next-line no-console
        console.error("error : ", e);
      });
    }
   }
})