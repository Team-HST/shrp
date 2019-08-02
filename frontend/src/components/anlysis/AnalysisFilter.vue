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
                item-text="title"
                item-value="code"
                label="Filled style"
                @change="changeIxType"
                ></v-select>
            </v-flex>
            <v-flex xs6>
                <v-select
                :items="ampmTypeList"
                item-text="title"
                item-value="code"
                label="Filled style"
                @change="changeAmpmType"
                ></v-select>
            </v-flex>
            <!-- <select id="ix_select" v-on:change="changeIxType">
                <option value="" selected="selected">Please select one</option>
                <option v-for="ixType in ixTypeList" v-bind:key="ixType.code" v-bind:value="ixType.code">
                    {{ ixType.title }}
                </option>
            </select>
            <select id="ampm_select" v-on:change="changeAmpmType">
                <option value="" selected="selected">Please select one</option>
                <option v-for="ampmType in ampmTypeList" v-bind:key="ampmType.code" v-bind:value="ampmType.code">
                    {{ ampmType.title }}
                </option>
            </select> -->
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        data () {
            return {
                ixTypeList: [
                    {code: '101', title: 'VEHS'},
                    {code: '102', title: 'SPEEDAVGHARM'},
                    {code: '103', title: 'STOPDELAY'},
                    {code: '104', title: 'VEHDELAY'},
                    {code: '105', title: 'VEHS'},
                    {code: '106', title: 'TRAVTM'}
                ],
                ampmTypeList: [
                    {code: '201', title: 'AM'},
                    {code: '202', title: 'PM'},
                    {code: '203', title: 'NONE'}
                ],
                ixList: [
                    {no: '1', fileNm: 'AM_2018_01_03'},
                    {no: '2', fileNm: 'PM_2018_01_03'},
                    {no: '3', fileNm: 'NONE_2018_01_03'},
                    {no: '4', fileNm: 'AM_2018_01_03'},
                    {no: '5', fileNm: 'NONE_2018_01_03'}
                ]
            }
        },
        methods: {
            ixClickEvent: function(e) {
                console.dir('선택 최근 시뮬레이션 NO : ' + e.currentTarget.getAttribute('no'));
            },
            changeIxType: function(value) {
                console.dir('선택 IX TYPE : ' + value);
            },
            changeAmpmType: function(value) {
                console.dir('선택 AMPM TYPE : ' + value);
            }
        }
    }
</script>