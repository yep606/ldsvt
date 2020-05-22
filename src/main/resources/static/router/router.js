import Vue from 'vue'
import VueRouter from 'vue-router'
import MessagesList from "../js/pages/MessagesList.vue"
import Auth from "../js/pages/Auth.vue"


Vue.use(VueRouter)

const routes = [

    {path: '/', component: MessagesList},
    {path: '/auth', component: Auth},
    {path: '*', component: MessagesList}

]

export default new VueRouter({
    mode: 'history',
    routes // short for `routes: routes`
})