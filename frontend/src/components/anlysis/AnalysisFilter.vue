<template>
    <v-container>
        <v-layout wrap align-center>
            <v-flex
                md12
                lg12
            >
                <v-data-table
                    :headers="simulation.headers"
                    :items="simulation.list"
                    :items-per-page="5"
                    class="elevation-1"
                >
                    <template
                        slot="headerCell"
                        slot-scope="{ header }"
                    >
                    <span
                        class="font-weight-light text-warning text--darken-3"
                        v-text="header.text"
                    />
                    </template>
                    <template
                        slot="items"
                        slot-scope="{ index, item }"
                    >
                        <tr>
                            <td>{{ item.simulationNumber }}</td>
                            <td>{{ item.fileName }}</td>
                        </tr>
                    </template>
                </v-data-table>
            </v-flex>
            <!-- <v-flex xs12>
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
                        <tr v-for="ix in ixList" v-bind:key="ix.simulationNumber" v-bind:no="ix.simulationNumber" @click="ixClickEvent" style="cursor: pointer;">
                            <td>{{ ix.simulationNumber }}</td>
                            <td>{{ ix.fileName }}</td>
                        </tr>
                    </tbody>
                </v-simple-table>
            </v-flex> -->
            <v-flex xs6>
                <v-select
                    :items="ixTypeList"
                    item-text="subName"
                    item-value="subCode"
                    label="Filled style"
                    @change="changeIxType"
                ></v-select>
            </v-flex>
            <v-flex xs6>
                <v-select
                    :items="ampmTypeList"
                    item-text="subName"
                    item-value="subCode"
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
                simulation: {
                    headers: [
                        {
                            sortable: false,
                            text: '번호',
                            value: 'simulationNumber'
                        },
                        {
                            sortable: false,
                            text: '파일 명',
                            value: 'fileName'
                        }
                    ],
                    list: []
                }
            }
        },
        created() {
            this.initalize();
        },
        methods: {
            // 화면 초기 설정
            initalize() {
                // 지표 종류 조회
                this.$http.get('/api/codes/100')
                .then(response => {
                    this.ixTypeList = response.data.body.commonCodes;
                })
                .catch(e => {
                    console.error('error : ', e)
                })

                // Ampm 시간대 종류 조회
                this.$http.get('/api/codes/200')
                .then(response => {
                    this.ampmTypeList = response.data.body.commonCodes;
                })
                .catch(e => {
                    console.error('error : ', e)
                })

                // 최근 시뮬레이션 목록 조회
                this.$http.get('/api/simulation/histories')
                .then(response => {
                    this.simulation.list = response.data.body.simulationHistories;
                })
                .catch(e => {
                    console.error('error : ', e)
                })
            },
            ixClickEvent(e) {
                console.dir('선택 최근 시뮬레이션 NO : ' + e.currentTarget.getAttribute('no'));
            },
            changeIxType(value) {
                console.dir('선택 IX TYPE : ' + value);
            },
            changeAmpmType(value) {
                console.dir('선택 AMPM TYPE : ' + value);
            },
            searchSimulationStats() {
                alert('시뮬레이션 조회 API');
            }
        }
    }
</script>