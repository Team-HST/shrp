<template>
    <v-container grid-list-xl>
        <v-layout row>
            <v-flex xs12></v-flex>
            <v-flex xs12 sm6 offset-sm3>
                <v-card
                max-width="475"
                class="mx-auto"
                >
                    <material-card 
						color="orange" 
						title="사용자 목록" 
						text="사용자를 선택해주세요."
					>
					</material-card>
                    <v-list height="300" style="overflow-y: auto;">
                        <v-list-item-group
                        >
                            <v-list-item v-for="user in userList" :key="user.userName">
                            <template>
                                <v-list-item-avatar @click="fnGoMain(user.userName)">
                                    <v-avatar color="indigo">
                                        <v-icon dark>mdi-account-circle</v-icon>
                                    </v-avatar>
                                </v-list-item-avatar>
                                <v-list-item-content @click="fnGoMain(user.userName)">
                                    <v-list-item-title v-text="user.userName"></v-list-item-title>                       
                                </v-list-item-content>
                                <v-list-item-icon>
                                    <v-btn
                                        text icon color="red" @click.native="fnDeleteUser(user.userName)"
                                    >
                                        <v-icon>mdi-delete</v-icon>
                                    </v-btn>                              
                                </v-list-item-icon>
                            </template>
                            </v-list-item>
                        </v-list-item-group>
                    </v-list>
                </v-card>       
            </v-flex>
            <v-flex xs12 sm6 offset-sm3>
                <v-row>
                    <v-col cols="12">
                        <v-text-field
                            v-model="userName"
                            :rules="rules"
                            single-line
                            label="사용자명을 입력해주세요"
                            type="text"
                            counter
                            maxlength="10"
                        >
                            <template v-slot:append-outer>
                                <v-menu
                                    style="top: -12px"
                                    offset-y
                                >
                                <template v-slot:activator="{ on }">
                                <v-btn v-on="on"
                                    class="font-weight-light white--text"
                                    @click="fnCreateUser"
                                    color="warning" large
                                >
                                등록
                                </v-btn>
                                </template>
                                </v-menu>
                            </template>
                        </v-text-field>
                    </v-col>
                </v-row>
            </v-flex> 
        </v-layout>
    </v-container>
</template>

<script>

export default {
    name: 'login',
    data: () => ({
        userList: [],
        userName : '',
        rules: [
            value => value == '' || value.length >= 2 || '두 글자 이상 입력해주세요.',
            value => (value || '').length <= 10 || '10자 이내로 입력해주세요.'
        ]
    }),
    created() {
        // API 요청 서비스
        this.service = {
            // 사용자 목록 조회
            searchUserList: () => {
                this.$http.get('/api/user')
                .then(response => {
                    this.userList = response.data.body.users;
                })
                .catch(e => {
                    console.error('error : ', e);
                });
            },
            createUser: (name) => {
                this.$http.post('/api/user/'+name)
                .then(response => {
                    this.service.searchUserList();
                })
                .catch(e => {
                    alert('이미 등록된 사용자입니다.');
                })
            },
            deleteUser: (name) => {
                this.$http.delete('api/user/'+name)
                .then(response => {
                    this.service.searchUserList();
                })
                .catch(e => {
                    console.error('error: ', e);
                })
            }
        }
    },
    mounted() {
        // 초기 데이터 설정
        this.initalize();
    },
    methods: {
        // 초기 데이터 설정
        initalize() {
            this.service.searchUserList();
        },
        // 사용자 등록 이벤트
        fnCreateUser() {
            this.service.createUser(this.userName);
            // 등록 후 INPUTBOX 초기화
            this.userName = '';
        },
        // 사용자 삭제 이벤트
        fnDeleteUser(name) {
            this.service.deleteUser(name);
        },
        fnGoMain(name) {
            console.log('goMain', name);
            // 사용자명 쿠키 처리 store.js
            // 모의실험 분석 페이지 이동
            this.$router.push({name: 'Analysis'});
        }
    }
}
</script>