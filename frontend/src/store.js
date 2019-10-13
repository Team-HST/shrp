import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import _ from 'lodash';
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
    plugins: [
      createPersistedState
    ],
    state: { //vuex 데이터 저장소
      layout: {
        link: {}
      },
      analysis: {
        simulationNumbers: '', // 분석 요청 API URL
        data: [], // 분석 요청 API RESPONSE DATA,
        chartData: {}, // 차트 DATA
        diagramData: {} // 도표 DATA
      },
      userName: ''
    },
    getters: { // vuex 저장소 데이터 조회
      getLayoutLink: (state) => {
        return state.layout.link;
      },
      getAnalysisData: (state) => {
        return state.analysis.data;
      },
      getSimulationNumbers: (state) => {
        return state.analysis.simulationNumbers;
      },
      getChartData: (state) => {
        return state.analysis.chartData;
      },
      getDiagramData: (state) => {
        return state.analysis.diagramData;
      },
      getUserName: (state) => {
        return state.userName;
      }
    },
    mutations: { // vuex 저장소 데이터 변경
      setDrawer: (state, drawer) => {
        state.layout.drawer = drawer
      },
      changeDrawer: (state) => {
        state.layout.drawer = !state.layout.drawer;
      },
      setSimulationNumbers: (state, simulationNumbers) => {
        state.analysis.simulationNumbers = simulationNumbers;
      },
      changeLayoutLink: (state, link) => {
        state.layout.link = link;
      },
      setAnalysisData: (state, data) => {
        state.analysis.data = data;
      },
      setChartData: (state, data) => {
        let colorArr = ['#FFAF20' , '#11455C' , '#D3482A'];

        // 차트 데이터 생성
        let chartDataset = data.map((data, index) => (
          {
            label: data.fileName,
            backgroundColor: colorArr[index],
            pointBackgroundColor: 'white',
            borderWidth: 1,
            pointBorderColor: colorArr[index],
            data: data.values
          }
        ));
        
        //  차트 데이터 적용
				let chartData = {
					labels: data[0].labels,
          datasets: chartDataset,
        }
        
        state.analysis.chartData = chartData;
      },
      setDiagramData: (state, data) => {
        data[0].labels = data[0].labels.map(label => {
          return label.replace('교차로', '');
        });
        data[0].labels.unshift("시뮬레이션 명 ＼ 교치로");

        let diagramData = {};
        diagramData.labels = data[0].labels;
        diagramData.values = data.map((data) => {
          data.values.unshift(data.fileName);
          return data.values;
        });

        state.analysis.diagramData = diagramData;
      },
      setUserName: (state, userName) => {
        state.userName = userName;
      }
    },
    actions: { // vuex 저장소 비동기 데이터 변경
    // 시뮬레이션 분석 데이터 조회
    searchSimulationAnalysis: (context, apiURL) => {
      return axios.get(apiURL)
      .then(response => {
          context.commit('setAnalysisData', response.data.body.dataset);
          context.commit('setDiagramData', _.cloneDeep(response.data.body.dataset));
      })
      .catch(e => {
        // eslint-disable-next-line no-console
        console.error("error : ", e);
      });
    }
   }
})