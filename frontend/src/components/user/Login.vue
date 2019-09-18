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


                    <v-list two-line flat>

                        <v-list-item-group
                            v-model="settings"
                        >
                            <v-list-item>
                            <template v-slot:default="{ active, toggle }">
                                <v-list-item-content>
                                <v-list-item-title>Notifications</v-list-item-title>                               
                                </v-list-item-content>
                                <v-list-item-content>
                                <v-icon>mdi-delete</v-icon>                               
                                </v-list-item-content>
                            </template>
                            </v-list-item>

                            <v-list-item>
                            <template v-slot:default="{ active, toggle }">
                                <v-list-item-action>
                                <v-checkbox
                                    v-model="active"
                                    color="primary"
                                    @click="toggle"
                                ></v-checkbox>
                                </v-list-item-action>

                                <v-list-item-content>
                                <v-list-item-title>Sound</v-list-item-title>
                                <v-list-item-subtitle>Hangouts message</v-list-item-subtitle>
                                </v-list-item-content>
                            </template>
                            </v-list-item>

                            <v-list-item>
                            <template v-slot:default="{ active, toggle }">
                                <v-list-item-action>
                                <v-checkbox
                                    v-model="active"
                                    color="primary"
                                    @click="toggle"
                                ></v-checkbox>
                                </v-list-item-action>

                                <v-list-item-content>
                                <v-list-item-title>Video sounds</v-list-item-title>
                                <v-list-item-subtitle>Hangouts video call</v-list-item-subtitle>
                                </v-list-item-content>
                            </template>
                            </v-list-item>

                            <v-list-item>
                            <template v-slot:default="{ active, toggle }">
                                <v-list-item-action>
                                <v-checkbox
                                    v-model="active"
                                    color="primary"
                                    @click="toggle"
                                ></v-checkbox>
                                </v-list-item-action>

                                <v-list-item-content>
                                <v-list-item-title>Invites</v-list-item-title>
                                <v-list-item-subtitle>Notify when receiving invites</v-list-item-subtitle>
                                </v-list-item-content>
                            </template>
                            </v-list-item>
                        </v-list-item-group>
                    </v-list>
                </v-card>       
            </v-flex>
            <v-flex xs12 sm6 offset-sm3>
                <v-text-field :rules="rules"></v-text-field>    
            </v-flex> 
        </v-layout>
    </v-container>
</template>

<script>

export default {
    name: 'login',
    data: () => ({
    rules: [
        value => (value || '').length >= 2 || '두 글자 이상 입력해주세요.',
        value => (value || '').length <= 10 || '10자 이내로 입력해주세요.'
      ]
  })
}
</script>