window.onload = function() {
    console.log("Load");
    let url = 'api/employees';
    fetch(url)
        .then(function(response) {
            console.log(response);
            return response.json();
        })
        .then(function(jsonData) {
            console.log(jsonData);
            fillList(jsonData);
        });
};

function fillList(employees) {
    console.log("Fill", employees);
    var list = document.getElementById("employees-ul");

    content = "";
    for (employee of employees) {
        content += "<li>" + employee.name + "</li>";
    }
    list.innerHTML = content;
}