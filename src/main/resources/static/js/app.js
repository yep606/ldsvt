Vue.component('messages-list', {

    props: ['messages'],
    template: '<div> <div v-for="message in messages">{{message.text}}</div> </div>'
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages"/>' ,
    data: {
        messages: [
            {id: "123", text: "First"},
            {id: "1234", text: "Second"},
            {id: "12345", text: "Third"},
        ]
    }
})