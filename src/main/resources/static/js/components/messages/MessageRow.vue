<template>
    <v-card class="my-2">
        <v-card-text>
            <i>({{ message.id }})</i>
            {{ message.text }}
        </v-card-text>
        <v-card-actions>
            <v-btn value="Edit" @click="edit" small rounded>Edit</v-btn>
            <v-btn icon @click="del" small rounded>
                <v-icon>mdi-delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
    import {mapActions} from 'vuex'
    export default {
        props: ['message','editMessage'],
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message);
            },
            del() {
                this.removeMessageAction(this.message);
            }
        }
    }
</script>

<style>
    /*deleteMessage(message) {*/
    /*messagesApi.remove(message.id).then(result => {*/
    /*if (result.ok) {*/
    /*// this.messages.splice(this.messages.indexOf(message), 1);*/
    /*}*/
    /*})*/
    /*}*/
</style>