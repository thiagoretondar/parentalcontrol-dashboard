<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8"/>
    <link rel="icon" type="image/png" href="assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Light Bootstrap Dashboard by Creative Tim</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>


    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="assets/css/light-bootstrap-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet"/>


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet"/>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>

    <script type="text/javascript">
        function getDate(daysBefore) {
            var todayTimeStamp = +new Date; // Unix timestamp in milliseconds
            var oneDayTimeStamp = 1000 * 60 * 60 * 24 * daysBefore; // Milliseconds in a day
            var diff = todayTimeStamp - oneDayTimeStamp;
            var newDate = new Date(diff);

            return newDate.getFullYear() + '-' + (newDate.getMonth() + 1) + '-' + newDate.getDate();
        }
    </script>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        function mostUsed(apps, id) {
            // Load the Visualization API and the corechart package.
            google.charts.load('current', {'packages':['corechart']});

            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);

            // Callback that creates and populates a data table,
            // instantiates the pie chart, passes in the data and
            // draws it.
            function drawChart() {

                // Create the data table.
                var data = new google.visualization.DataTable();
                data.addColumn('string', 'Aplicativo');
                data.addColumn('number', 'Quantitade');

                for (i in apps) {
                    var name = apps[i].appname;
                    var quantity = apps[i].quantity;
                    if (quantity > 0) {
                        data.addRow([name, quantity]);
                    }
                }

                // Set chart options
                var options = {'legend': "Aplicativos",tooltip: {isHtml: true}, 'width':400, pieHole: 0.4,  pieSliceText: "none"};

                // Instantiate and draw our chart, passing in some options.
                var chart = new google.visualization.PieChart(document.getElementById(id));

                function selectHandler() {
                    alert("Testing");
                }

                google.visualization.events.addListener(chart, 'select', selectHandler);

                chart.draw(data, options);
            }
        }

        function areaChartMostUsed() {
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {

                var settings = {
                    "async": true,
                    "crossDomain": true,
                    "url": "/user/9/usage/today",
                    "method": "GET",
                    "headers": {
                        "content-type": "application/json"
                    }
                }

                $.ajax(settings).done(function (apps) {
                    var usages = [];
                    var usageNames = ['Hour'];
                    var hours = [3, 6, 9, 12, 15, 18, 21, 24];

                    // create names of app
                    for (i in apps) {
                        usageNames.push(apps[i].appname);
                    }

                    // add to the usage array
                    usages.push(usageNames);

                    var pos = 0;
                    for (hour in hours) {
                        var newHourUsage = [];
                        newHourUsage.push(hours[hour]);

                        for (i in apps) {
                            newHourUsage.push(apps[i].hours[pos]);
                        }
                        pos++;

                        usages.push(newHourUsage);
                    }

                    var data = google.visualization.arrayToDataTable(usages );

                    var options = {
                        legend: {position: 'top', maxLines: 3},
                        hAxis: {title: 'Horas',  titleTextStyle: {color: '#333'}},
                        vAxis: {minValue: 0, maxValue: 60, title: 'Quantidade',  titleTextStyle: {color: '#333'}  }
                    };

                    var chart = new google.visualization.AreaChart(document.getElementById('areaChartMostUsed'));
                    chart.draw(data, options);

                });
            }
        }
    </script>
</head>
<body>

<div class="wrapper">
    <div class="sidebar" data-color="blue" data-image="assets/img/sidebar-4.jpg">

        <!--

            Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
            Tip 2: you can also add an image using data-image tag

        -->Ï

        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="/" class="simple-text">
                    Monitoramento Parental
                </a>
            </div>

            <ul class="nav">
                <li class="active">
                    <a href="dashboard.ftl">
                        <i class="pe-7s-graph"></i>
                        <p class="text-center">Dashboard</p>
                    </a>
                </li>
            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Dashboard</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                Dropdown
                                <b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#">
                                Log out
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Localizações</h4>
                                <p class="sub-title"><b>Última localização:</b> Rua Tijuco Preto, 1342. <b>Data:</b> 26/11/2016 9:50</p>
                            </div>
                            <div id="locations" style="height: 555px; width: 100%">

                            </div>

                            <!-- Maps API Javascript -->
                            <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAsWP3cgcsHlGmmFd_yEKq1Z_emXcWasxI&sensor=false"></script>

                            <!-- Caixa de informação -->
                            <script src="assets/js/infobox.js"></script>

                            <!-- Agrupamento dos marcadores -->
                            <script src="assets/js/markerclusterer.js"></script>

                            <!-- Arquivo de inicialização do mapa -->
                            <script src="assets/js/map.js"></script>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Aplicativos mais usados</h4>
                                <p class="category">Ontem</p>
                            </div>
                            <div class="content" id="most_used_yesterday">
                                <script type="text/javascript">

                                    var settings = {
                                        "async": true,
                                        "crossDomain": true,
                                        "url": "/user/9/timestart/"+getDate(1)+"T00:00:00/timeend/"+getDate(1)+"T23:59:00",
                                        "method": "GET",
                                        "headers": {
                                            "content-type": "application/json"
                                        }
                                    }

                                    $.ajax(settings).done(function (response) {
                                        mostUsed(response, 'most_used_yesterday');
                                    });
                                </script>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Aplicativos mais usados</h4>
                                <p class="category">Na última semana</p>
                            </div>
                            <div class="content" id="most_used_one_week">
                                <script type="text/javascript">
                                    var settings = {
                                        "async": true,
                                        "crossDomain": true,
                                        "url": "/user/9/timestart/"+getDate(7)+"T00:00:00/timeend/"+getDate(0)+"T23:59:00",
                                        "method": "GET",
                                        "headers": {
                                            "content-type": "application/json"
                                        }
                                    }

                                    $.ajax(settings).done(function (response) {
                                        mostUsed(response, 'most_used_one_week');
                                    });
                                </script>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-12">
                        <div class="card ">
                            <div class="header">
                                <h4 class="title">Quantidade de execuções de cada aplicativo hoje</h4>
                            </div>
                            <div class="content" id="areaChartMostUsed">
                                <script type="text/javascript">
                                    areaChartMostUsed();
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="#">
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Company
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Portfolio
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Blog
                            </a>
                        </li>
                    </ul>
                </nav>
                <p class="copyright pull-right">
                    &copy; 2016 <a href="http://www.creative-tim.com">Creative Tim</a>, made with love for a better web
                </p>
            </div>
        </footer>

    </div>
</div>


</body>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script src="assets/js/bootstrap-checkbox-radio-switch.js"></script>

<!--  Charts Plugin -->
<script src="assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="assets/js/bootstrap-notify.js"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="assets/js/light-bootstrap-dashboard.js"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="assets/js/demo.js"></script>

</html>