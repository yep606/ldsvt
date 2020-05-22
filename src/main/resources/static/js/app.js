import Vue from 'vue'
import vuetify from "./plugins/vuetify";
import '@babel/polyfill'
import '../api/resource'
import router from "../router/router";
import App from 'pages/App.vue'
import store from "../store/store";
import {connect} from "./util/ws";
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile)
    connect();

new Vue({
    el: '#app',
    vuetify,
    store,
    router,
    render: a => a(App)
});





