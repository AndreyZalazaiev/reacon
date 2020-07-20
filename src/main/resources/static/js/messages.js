
var messageApi = Vue.resource('/messages/all{/idConversation}');
var conversationApi = Vue.resource('/conversations/all{/idUser}');
var userApi=Vue.resource('user{/idUser}');

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
Vue.http.headers.common['X-CSRF-TOKEN'] = token;


const eventBus = new Vue();


Vue.component('groups-list', {
    props: ['groups', 'id'],
    template: '<div> <div v-for ="group in groups">' +
        '<div class="single_chat" @click="loadMsg(group.idConversation)">\n' +
        '    <div class="chat_img"> ' +
        '<img :src="group.conversationImage" alt="failed to load"/>' +
        ' </div>\n' +
        '    <div class="chat_ib">\n' +
        '        <h5>{{group.conversationName}} <span class="chat_date">{{group.lastMessageDate}}</span></h5>\n' +
        '        <p></p>\n' +
        '    </div></div>  </div></div>'
    ,
    methods: {
        loadMsg(idConversation) {
            eventBus.$emit('loadMessages', idConversation)
        }
    }
});

var groupsApp = new Vue({
    el: '#load_groups',
    template: '<groups-list :groups="groups"' +
        ':id="id"/>',
    data: {
        groups: [],
        id: idUser,
        name: name,
        pictrue: picture
    },
    created: function () {
        conversationApi.get({idUser: this.id}).then(result =>
            result.json().then(data =>
                data.forEach(group => this.groups.push(group))
            )
        )
    },
});



Vue.component('messages-list', {
    props: ['messages','id'],
    template: '<div v-if="messages"><div v-for ="msg in messages"> <div class="outgoing_msg">\n' +
        '                        <div v-if="msg.idUser=id" class="sent_msg">\n' +
        '                            <p>{{msg.text}}</p>\n' +
        '                            <span class="time_date">{{msg.sentDate}}</span> </div>\n' +
        '                        <div v-else class="received_msg">\n' +
        '                            <div class="received_withd_msg">\n' +
        '                                <p>{{msg.text}}</p>\n' +
        '                                <span class="time_date">{{msg.sentDate}}</span></div>\n' +
        '                        </div>' +
        '                    </div>' +

        '</div></div>',
    methods: {
      loadUserData(idUser) {
            var user;
            userApi.get({idUser:idUser }).then(result =>
                result.json().then(data => user=data));
            if (user)
            {
                return user;
            }
            else {
                return null;
            }
        }
    },

});
var messagesApp = new Vue({
    el: '#load_messages',
    template: '<messages-list :messages="messages"' +
                ':id="id"/>',
    data: {
        messages: [],
        id:idUser,
        idConversation: null
    },
    methods: {
        getData(idConversation) {
            this.messages.length=0;
            messageApi.get({idConversation: this.idConversation}).then(result =>
                result.json().then(data =>
                    data.forEach(msg => this.messages.push(msg))
                )
            )
        }
    },
    mounted() {
        eventBus.$on('loadMessages', (idConversation) => {
            this.idConversation = idConversation;
            this.getData(idConversation);

        })
    },
    created: function () {
        if(this.idConversation)
        this.getData(this.idConversation)
    },

});
