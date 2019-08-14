import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: { //vuex 데이터 저장소
        layout: {
            drawer: true,
            link: {
                path: '/analysis',
                name: 'Analysis',
                icon: 'mdi-view-dashboard',
                view: 'AnalysisView',
                subName: 'Simulation analysis screen'
            }
        },
        simalationAnalysis: {
            apiURL: ''
        }
    },
    getters: { // vuex 저장소 데이터 조회
        getLayoutDrawer: (state) => {
            return state.layout.drawer;
        },
        getLayoutLink: (state) => {
            return state.layout.link;
        }
    },
    mutations: { // vuex 저장소 데이터 변경
        changeDrawer: (state) => {
            state.layout.drawer = !state.layout.drawer
        },
        changeLayoutLink: (state, link) => {
            state.layout.link = link
        }
    },
    actions: { // vuex 비동기 요청 데이터 변경

    }
})