<template>
    <v-app id="anlysisFilter">
        <img alt="Vue logo" src="../assets/logo.png">
        <HelloWorld msg="분석 조건을 선택하세요." />
        <MainSelectBox />
    </v-app>
</template>

<script>
    import HelloWorld from '../components/anlysis/HelloWorld.vue'
    import MainSelectBox from '../components/anlysis/AnalysisFilter'

    export default {
        el: '#anlysisFilter',
        components: {
            HelloWorld,
            MainSelectBox
        }
    }
</script>