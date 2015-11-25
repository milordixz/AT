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
      <!--index.innerHTML = ind;
      <!--tr.appendChild(index);
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

  <!--console.log(context);
  }