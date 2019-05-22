var button = document.getElementById("setStatus");
button.onclick = setStatus;

var xHRObject = new XMLHttpRequest();
var statusObject = new XMLHttpRequest();
var possiblefriendObject = new XMLHttpRequest();
var addFriendObject = new XMLHttpRequest();
var showFriendsObject = new XMLHttpRequest();

var pfrienduser;
var pfriendfname;
var pfriendlname;
var pfriendstatus;


var chaturl = "Controller?action=ChatHandler"


function setStatus() {
    var status = document.getElementById("statusInput").value;
    if(status == "custom") {
        status = document.getElementById("statusCustomInput").value;
    }
    var information = "status=" + encodeURIComponent(status);

    statusObject.open("POST", "Controller?action=SetStatus", true);

    statusObject.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    statusObject.onreadystatechange = getData;
    statusObject.send(information);
}

function getAllFriends() {
    showFriendsObject.open("GET", "Controller?action=AllFriends", true);
    showFriendsObject.onreadystatechange = receiveFriends;
    showFriendsObject.send(null);
}

var friendsAlreadyIncluded = []
function receiveFriends() {
    if (showFriendsObject.status == 200) {
        if (showFriendsObject.readyState == 4) {
            var serverResponse = JSON.parse(showFriendsObject.responseText);
            var friendlisttbody = document.getElementById("friendslist");

            for (var i = 0; i < serverResponse.length; i++) {
                if (!friendsAlreadyIncluded.includes(serverResponse[i].userId)) {

                    var userid = serverResponse[i].userId;
                    friendsAlreadyIncluded.push(serverResponse[i].userId)

                    var frienddiv = document.createElement('div');
                    frienddiv.classList.add("clickable_row");
                    frienddiv.classList.add(makeSelector(serverResponse[i].userId));

                    var name = document.createElement('p');
                    var status = document.createElement('p');
                    status.classList.add("status")

                    name.appendChild(document.createTextNode(serverResponse[i].firstName +" " + serverResponse[i].lastName))
                    status.appendChild(document.createTextNode(serverResponse[i].status))

                    frienddiv.appendChild(name);
                    frienddiv.appendChild(status);
                    friendlisttbody.appendChild(frienddiv);


                    //deel van opdracht 3
                    var chatdiv = $("<div></div>").addClass("chatbox")
                    var title = $("<h2></h2>").text(serverResponse[i].userId)
                    var chatmessages = $("<div></div>").addClass("chatmessages")
                    var messagelabel = $("<label></label>").text("Message:").attr("for",  "message")
                    var textarea = $("<textarea></textarea>").attr("name","message").attr("id","message")
                    var sendbutton = $("<button></button>").text("send")
                    var cancelbutton = $("<button></button>").text("cancel")

                    $(chatdiv).append(title, chatmessages, messagelabel, textarea, sendbutton, cancelbutton)
                    $(frienddiv).append(chatdiv)

                    var id = serverResponse[i].userId
                    $(sendbutton).click(function () {
                        var that = this;

                        $.post(chaturl,
                            {message: $(this).parent().find("textarea").val(),
                            target: $(this).parent().find("h2").text() }, //serverResponse[i].userId
                            function(data) {
                                var obj = JSON.parse(data)
                                if (obj.message != "") {
                                    var messagecontainer = $("<p></p>").text(obj.message) //data.message)
                                    messagecontainer.addClass("ownmessage")
                                    messagecontainer.addClass("chatmessage")

                                    $(that).siblings('.chatmessages').append(messagecontainer)
                                    $(that).parent().find("textarea").val("")
                                }
                            })
                    })

                    $(frienddiv).click(function(){ //show chatbox
                        $(this).find('.chatbox').show()
                    });

                    cancelbutton.click(function(event) { //hide chatbox
                        event.stopPropagation();
                        $(this).parent().hide();
                    })
                }
            }
        }
    }
}


function showNewChatMessage(message, ownmessage, messagediv) {
    var messagecontainer = $("<p></p>").text(message)
    ownmessage? messagecontainer.addClass("ownmessage") : messagecontainer.addClass("friendmessage")

    $(messagediv).append(messagecontainer);
}



function getPossibleFriend() {
    possiblefriendObject.open("GET", "Controller?action=PossibleFriend", true);
    possiblefriendObject.onreadystatechange = getPossibleFriendData;
    possiblefriendObject.send(null);
}

function addFriend() {
    addFriendObject.open("POST", "Controller?action=AddFriend", true);
    addFriendObject.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    var info = "pfriend=" + pfrienduser;

    var pfrienddiv = document.getElementById("possiblefriend")

    while(pfrienddiv.hasChildNodes()) {
        pfrienddiv.removeChild(pfrienddiv.lastChild);
    }

    addFriendObject.onreadystatechange = findNewFriend; //show new friend
    addFriendObject.send(info);

}

function findNewFriend() {
    if (addFriendObject.status == 200) {
        if (addFriendObject.readyState == 4) {
            getPossibleFriend();
        }
    }
}


function getPossibleFriendData() {
    if (possiblefriendObject.status == 200) {
        if (possiblefriendObject.readyState == 4) {

            var serverResponse = JSON.parse(possiblefriendObject.responseText);

            var nobody = serverResponse.nobody
            var pfrienddiv = document.getElementById("possiblefriend")
            if (nobody == null) {
                var pfriendName = serverResponse.name;
                var pfriendid = serverResponse.userid;
                var pfrienddiv = document.getElementById("possiblefriend")

                var pfriendparagraph = document.createElement('p');
                var addFriendButton =  document.createElement("BUTTON")

                var friendtext = document.createTextNode( "name: " + pfriendName);
                pfriendparagraph.appendChild(friendtext)

                addFriendButton.innerHTML = "Add Friend";
                pfrienduser = pfriendid;
                addFriendButton.onclick  = addFriend;

                pfrienddiv.appendChild(pfriendparagraph)
                pfrienddiv.appendChild(addFriendButton);
            } else {
                var pfriendparagraph = document.createElement('p');

                var friendtext = document.createTextNode( "there is nobody left to be friends with");
                pfriendparagraph.appendChild(friendtext);
                pfrienddiv.appendChild(pfriendparagraph)
            }
        }
    }
}

function getData() {
    if (statusObject.status == 200) {
        if (statusObject.readyState == 4) {
            var serverResponse = JSON.parse(statusObject.responseText);

            var status = serverResponse.status;

            var statusdiv = document.getElementById("status")   //getData();
            var statusparagraph = statusdiv.childNodes[0];

            if (statusparagraph == null) {
                statusparagraph = document.createElement('p');
                statusparagraph.id = "statustext";
                var statustext = document.createTextNode(status);
                statusparagraph.appendChild(statustext);
                statusdiv.appendChild(statusparagraph);
            }else {
                var statustext = document.createTextNode(status);
                statusparagraph.removeChild(statusparagraph.childNodes[0])
                statusparagraph.appendChild(statustext);
            }
        }
    }
}

getPossibleFriend();
getAllFriends();
setInterval(getAllFriends, 5000)

/*
 opdracht 3
*/

var openchats = [];

function createChatscreen(userid) {
    var maindiv = document.createElement('div')
    var textdiv = document.createElement('div')
    var input = document.createElement('textfield')
    var button = document.createElement('button')

    maindiv.classList.add("tab-" + userid)

    maindiv.appendChild(textdiv)
    maindiv.appendChild(input)
    maindiv.appendChild(button)
    return maindiv
}

function makeSelector(str) {
    var toret = "";
    for (var i in str) {
        if (str.charAt(i) != '@') {
            toret = toret + str.charAt(i);
        }
    }
    return toret;
}

function friendClicked(userid) {
    console.log("userid clicked: " + userid);
    var tabsdiv = document.getElementById("tabs")
    var tabslist = document.getElementById("tabslist")

    if (!openchats.includes(userid)) { //chat isn't open
        openchats.push(userid);
        var window = createChatscreen(userid);
        tabsdiv.appendChild(window);

        var li = document.createElement('li')
        var tablink = document.createElement('a')
        var tabtext = document.createTextNode(userid);
        tablink.appendChild(tabtext)
        tablink.href="#tab-" + userid

        li.appendChild(tablink)
        tabslist.appendChild(li);

        $( "#tabs" ).tabs();
    }
}

function getChatMessages() {
    $.get(chaturl, function(data) {
        //console.log("data: ");
        //console.log(data);


        //{"messages": [{"name":"an@ucll.be","messages": ["a","z"]}]}
            var obj = JSON.parse(data);

            if (!jQuery.isEmptyObject(obj)) {
                console.log("messages: ")
                console.log(obj.messages);
                for (var json in obj.messages) {
                    console.log("json: ")
                    console.log(json);
                    var parsed = JSON.parse(json)
                    console.log("name: " + parsed.name + " messages: " + parsed.messages);
                    //console.log(parsed);
                }
            }

            //console.log(obj);
            /*var friends = obj.friends;
            var messages = obj.messages;

            for (var friend in friends) {

                $("h2:contains('"+ friend +"')").siblings(".chatmessages").append($("<p></p>").text(friend))
                console.log("h2 selector: ")
                console.log($("h2:contains('"+ friend +"')"))
                console.log(obj.friend)
            }*/
        })



    setTimeout(getChatMessages, 2000)
}

$(function() {
    getChatMessages();

    function testfunction() {
        alert("ding ding ding");
    }

});
