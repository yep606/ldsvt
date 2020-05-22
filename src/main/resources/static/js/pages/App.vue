<template>
    <v-app>
        <v-app-bar app>
            <v-toolbar-title>Chatty</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex'
    import { addHandler } from "../util/ws";

    export default {
        computed: mapState(['profile']),
        methods: mapMutations(['addMessageMutation', 'updateMessageMutation', 'removeMessageMutation']),
        created() {
            addHandler(data => {
                if (data.objectType === "MESSAGE") {
                    switch (data.eventType) {
                        case "CREATE":
                            this.addMessageMutation(data.body)
                            break;
                        case "UPDATE":
                            this.updateMessageMutation(data.body)
                            break;
                        case "REMOVE":
                            this.removeMessageMutation(data.body)
                            break;
                        default:
                            console.error(`Unknown event type ${data.eventType}`)
                    }
                } else {
                    console.error(`Unknown event type ${data.objectType}`)
                }
            })
        }
    }
</script>

<style>
</style>