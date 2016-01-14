function get(){

    var ajax = new XMLHttpRequest();
    ajax.open('GET', 'http://smart-route.ru:8100/adapter-web/rest/dictionary/c580d006-f86f-4bdd-84be-b51de6f626c6', false);
    ajax.send();
    var response = ajax.responseText;
    var context = JSON.parse(response);
    console.log(context);
    context.documents.sort();
    context.documents.reverse();
    context.documents.forEach(function(value) {

      var tr = document.createElement('tr');
      var fio = document.createElement('td');
      var gender = document.createElement('td');
      var birthDate = document.createElement('td');
      var phone = document.createElement('td');

      fio.innerHTML = value.fio;
      tr.appendChild(fio);
      gender.innerHTML = value.gender;
      tr.appendChild(gender);
      birthDate.innerHTML = value.birthDate;
      tr.appendChild(birthDate);
      phone.innerHTML = value.phone;
      tr.appendChild(phone);
      var tb = document.getElementById('tb');
      tb.appendChild(tr);

    });


  }


function send_sub(){
    var ajax = new XMLHttpRequest();
    var log = document.getElementById("login").value;
    var pas = document.getElementById("password").value;
    ajax.open('POST', '/login', false);

    var json = new Object();
    json.login = log;
    json.password = pas;
    ajax.send(JSON.stringify(json));
    var otv =  ajax.responseText;

    alert(otv);
}

function send_soap(){
    var ajax = new XMLHttpRequest();
    var sName = document.getElementById("sName").value;
    var sCode = document.getElementById("sCode").value;
    var sData = document.getElementById("sData").value;
    ajax.open('POST', '/soap', false);

    var json = new Object();
    json.sName = sName;
    json.sCode = sCode;
    json.sData = sData;
    ajax.send(JSON.stringify(json));
    var otv =  ajax.responseText;

    var div2 = document.getElementById('div2');
    div2.innerText = otv;


}