import Vue from 'vue'
import vuetify from "./plugins/vuetify";
import 'api/resource'
import App from 'pages/App.vue'
import {connect} from "./util/ws";
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile)
    connect();

Vue.use(VueResource);

new Vue({
    el: '#app',
    vuetify,
    render: a => a(App)
});





