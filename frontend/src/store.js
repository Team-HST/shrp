import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: { //vuex 데이터 저장소
        layout: {
            drawer: true
        }
    },
    mutations: { // vuex 저장소 데이터 변경
        changeDrawer: (state) => {
            state.layout.drawer = !state.layout.drawer
          }
    },
    getters: { // vuex 저장소 데이터 조회
        getLayoutDrawer: (state) => {
            return state.layout.drawer;
        }
    }
})