var messageApi = Vue.resource('/messages/all/1')
var conversationApi = Vue.resource('/conversations/all/101276394831723524679')

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
Vue.http.headers.common['X-CSRF-TOKEN'] =token;
/*console.log(Vue.http.headers.common['X-CSRF-TOKEN']);

const headers = new Headers({
    'Content-Type': header,
    'X-CSRF-TOKEN': token
});
var a = fetch('http://localhost:8080/conversations/all/101276394831723524679',headers)
    .then((response=>console.log(response.text().then(data=>console.log(data)))));
Vue.component('group-row', {
    template: ''
});*/
Vue.component('groups-list', {
    props: ['groups'],
    template: '<div><div v-for ="group in groups">' +
        '<div class="single_chat">\n' +
        '    <div class="chat_img"> ' +
        '<img :src="group.conversationImage" alt="failed to load"/>' +
        ' </div>\n' +
        '    <div class="chat_ib">\n' +
        '        <h5>{{group.conversationName}} <span class="chat_date">{{group.lastMessageDate}}</span></h5>\n' +
        '        <p></p>\n' +
        '    </div></div>  </div></div>',
});

var groupsApp = new Vue({
    el: '#load_groups',
    template: '<groups-list :groups="groups"/>',
    data: {
        groups : [],
       // profile: userData
    },
    created: function () {
        conversationApi.get().then(result =>
            result.json().then(data =>
                data.forEach(group => this.groups.push(group))
            )
        )
    },
});



Vue.component('messages-list', {
    props: ['messages'],
    template: '<div><div v-for ="msg in messages"> <div class="outgoing_msg">\n' +
        '                        <div class="sent_msg">\n' +
        '                            <p>{{msg.text}}</p>\n' +
        '                            <span class="time_date">{{msg.sentDate}}</span> </div>\n' +
        '                    </div>'+
        '</div></div>'
});
var messagesApp= new Vue({
    el: '#load_messages',
    template: '<messages-list :messages="messages"/>',
    data: {
        messages: []
    },
    created: function () {
        messageApi.
        get().then(result =>
            result.json().then(data =>
                data.forEach(msg => this.messages.push(msg))
            )
        )
    },
});
