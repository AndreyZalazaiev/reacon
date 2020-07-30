export const eventBus = new Vue();

var messageApi = Vue.resource('/messages/all{/idConversation}');
var conversationApi = Vue.resource('/conversations/all{/idUser}');
var conversationDelete=Vue.resource('/participants/delete{/idConversation}')
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
        '        <h5>{{group.conversationName}} <span class="chat_date">{{group.lastMessageDate}} <button @click="deleteGroup(group.idConversation)">Leave</button></span></h5>\n' +
        '        <p></p>\n' +
        '    </div></div>  </div></div>'
    ,
    methods: {
        loadMsg(idConversation) {
            eventBus.$emit('loadMessages', idConversation)
        },
        deleteGroup(idConversation){
            conversationDelete.get({idConversation:idConversation})
                .then(eventBus.$emit("deletedGroup",idConversation));
        }
    },

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
    methods:{
      loadGroups()  {
          conversationApi.get({idUser: this.id}).then(result =>
              result.json().then(data =>
                  data.forEach(group => this.groups.push(group))
              )
          )
      }
    },
    created: function () {
        this.loadGroups();
    },
    mounted() {
        eventBus.$on('deletedGroup', (idConversation) => {
            for (var i=0;i<this.groups.length;i++)
            {
                if(this.groups[i].idConversation==idConversation)
                {
                    this.groups.splice(i,1);
                    console.log(this.groups);
                    break;
                }
            }
        })
        eventBus.$on('joinedGroup',(group)=>{
            this.groups.push(group);
        })
    }

});

Vue.component('post-message',{
    props:['idConversation','currentUserId','messages'],
   data: function(){
       return{
           text:''
       }
   },
    template:'<div class="input-group mb-3  write_message" >\n' +
        '  <input type="text" v-model="text" class="form-control" placeholder="Write Hello!" aria-label="Write Hello!" aria-describedby="basic-addon2">\n' +
        '  <div class="input-group-append">\n' +
        '    <button class="btn btn-outline-secondary" type="button" @click="save">Send</button>\n' +
        '  </div>\n' +
        '</div>',
    methods:{
       save: function () {
            //Had to use Axious due to bugs in Vue resourses
            var postUrl="/messages/all?idConversation="+this.idConversation+"&text="+this.text;
            this.$http.get(postUrl)
                .then(res=>res.json())
                .then(data=>this.messages.push(data));
            this.text='';
       }
    }
});

Vue.component('messages-list', {
    props: ['messages','currentUserId','users','idConversation'],
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
        '                        </div></div> ' +
        '                    </div><post-message :idConversation="idConversation" :messages="messages"></post-message>' +
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
        ':users="users"' +
        ':idConversation="idConversation"/>',
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
});
