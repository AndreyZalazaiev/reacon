const eventBus = new Vue();

var messageApi = Vue.resource('/messages/all{/idConversation}');
var conversationApi = Vue.resource('/conversations/all{/idUser}');
var usersApi = Vue.resource('/conversations/users{/idConversation}')

var token = $("meta[name='_csrf']").attr("content");
Vue.http.headers.common['X-CSRF-TOKEN'] = token;




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



Vue.component('send-message',{
    template:'<div><input type="text" size="40"></div>'
});//todo tomorrow

Vue.component('messages-list', {
    props: ['messages','currentUserId','users'],
    template: '<div v-if="messages"><div v-for ="msg in messages"> <div class="outgoing_msg">\n' +
                                     '<div v-if="msg.idUser==currentUserId">' +
                                     '<div class="output_msg">{{getUserData(msg.idUser).fullName}}' +
                                     '<img class="user_avatar" :src="getUserData(msg.idUser).userpic" alt="failed to load"/></div>'+
         '                            <div  class="sent_msg">\n' +
        '                            <p>{{msg.text}}</p>\n' +
        '                            <span class="time_date">{{msg.sentDate}}</span> </div></div>\n' +
                                            '<div v-else>' +
                                            '<div class="input_msg"><img class="user_avatar" :src="getUserData(msg.idUser).userpic" alt="failed to load"/>' +
                                            '{{getUserData(msg.idUser).fullName}}' +
                                            '</div>'+
        '                                   <div class="received_msg">\n' +
        '                                   <div class="received_withd_msg">\n' +
        '                                   <p>{{msg.text}}</p>\n' +
        '                                   <span class="time_date">{{msg.sentDate}}</span></div>\n' +
                                            '</div>' +
        '                        </div></div>' +
        '                    </div>' +
        '</div></div>',
    methods: {
        getUserData(id) {
          return this.users.find(usr=>usr.idUser==id);
        }
    },

});
var messagesApp = new Vue({
    el: '#load_messages',
    template: '<messages-list :messages="messages"' +
                ':currentUserId="currentUserId"' +
        ':users="users"/>',
    data: {
        messages: [],
        users:[],
        currentUserId:idUser,
        idConversation: null
    },
    methods: {
        getData(idConversation) {
            this.messages=[];
            messageApi.get({idConversation: this.idConversation}).then(result =>
                result.json().then(data =>this.messages=data)
            )
            this.users.length=0;
            usersApi.get({idConversation: this.idConversation}).then(result =>
                result.json().then(data =>this.users=data)
            )
        }
    },
    mounted() {
        eventBus.$on('loadMessages', (idConversation) => {
            this.idConversation = idConversation;
            this.getData(idConversation);
        })

    },
   /* created: function () {
        if(this.idConversation)
        this.getData(this.idConversation)
    },*/

});
