function drawLineChart() {
    var ctx = $("#lineChart").get(0).getContext("2d");

    var labels = ["January","February","March","April","May","June","July"];
    var chartArray = new Array();

    <c:forEach var="note" items="${notes}">
    chartArray.push(${note.amount})
    </c:forEach>

    var lineChartData = {
        labels: labels,
        datasets: [
            {
                fillColor : "rgba(151,187,205,0.5)",
                strokeColor : "rgba(151,187,205,1)",
                pointColor : "rgba(151,187,205,1)",
                pointStrokeColor : "#fff",
                data: chartArray

            }
        ]
    };

    new Chart(ctx).Line(lineChartData);
}
