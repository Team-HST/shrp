<template>
    <v-container>
        <v-layout wrap align-center>
            <v-flex xs12>
                <v-subheader>시뮬 조건</v-subheader>
            </v-flex>
            <v-flex xs12>
                <v-simple-table>
                    <thead>
                        <tr>
                            <th class="text-center">번호</th>
                            <th class="text-center">파일명</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="ix in ixList" v-bind:key="ix.no" v-bind:no="ix.no" @click="ixClickEvent" style="cursor: pointer;">
                            <td>{{ ix.no }}</td>
                            <td>{{ ix.fileNm }}</td>
                        </tr>
                    </tbody>
                </v-simple-table>
            </v-flex>
            <v-flex xs6>
                <v-select
                :items="ixTypeList"
                item-text="subNm"
                item-value="subCd"
                label="Filled style"
                @change="changeIxType"
                ></v-select>
            </v-flex>
            <v-flex xs6>
                <v-select
                :items="ampmTypeList"
                item-text="subNm"
                item-value="subCd"
                label="Filled style"
                @change="changeAmpmType"
                ></v-select>
            </v-flex>
            <v-btn @click="searchSimulationStats">Simulation Stats</v-btn>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        data () {
            return {
                ixTypeList: [],
                ampmTypeList: [],
                ixList: [
                    {no: '1', fileNm: 'AM_2018_01_03'},
                    {no: '2', fileNm: 'PM_2018_01_03'},
                    {no: '3', fileNm: 'NONE_2018_01_03'},
                    {no: '4', fileNm: 'AM_2018_01_03'},
                    {no: '5', fileNm: 'NONE_2018_01_03'}
                ]
            }
        },
        created() {
            // 지표 종류 조회
            this.$http.get('/api/analysis/simulationType')
            .then(response => {
                this.ixTypeList = response.data.body;
            })
            .catch(e => {
                console.error('error : ', e)
            })

            // Ampm 시간대 종류 조회
            this.$http.get('/api/analysis/ampmType')
            .then(response => {
                this.ampmTypeList = response.data.body
            })
            .catch(e => {
                console.error('error : ', e)
            })
        },
        methods: {
            ixClickEvent: (e) => {
                console.dir('선택 최근 시뮬레이션 NO : ' + e.currentTarget.getAttribute('no'));
            },
            changeIxType: (value) => {
                console.dir('선택 IX TYPE : ' + value);
            },
            changeAmpmType: (value) => {
                console.dir('선택 AMPM TYPE : ' + value);
            },
            searchSimulationStats: () => {
                alert('시뮬레이션 조회 API');
            }
        }
    }
</script>