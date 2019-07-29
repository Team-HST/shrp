<template>
    <div>
        <div id="ix_recent_list" style="margin-left: 850px;">
            <table>
                <colgroup>
                    <col style="50%;" />
                    <col style="50%;" />
                </colgroup>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>파일명</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="ix in ixList" v-bind:key="ix.no" v-bind:no="ix.no" v-on:click="ixClickEvent" style="cursor: pointer;">
                        <td>{{ ix.no }}</td>
                        <td>{{ ix.fileNm }}</td>
                    </tr>
                </tbody>
            </table>    
        </div>
        <select id="ix_select" v-on:change="changeIxType">
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
        </select>
    </div>
</template>

<script>
    export default {
        name: 'MainSelectBox',
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
            changeIxType: function(e) {
                console.dir('선택 IX TYPE : ' + e.target.value);
            },
            changeAmpmType: function(e) {
                console.dir('선택 AMPM TYPE : ' + e.target.value);
            }
        }
    }
</script>