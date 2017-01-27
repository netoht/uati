
function drawTopTwitterProfile() {
  var data = google.visualization.arrayToDataTable(DATA_CHARTS.profiles);

  var options = {
    chartArea: { width: '50%' },
    width: 600,
    height: 300,
    hAxis: {
      title: 'Quantidade de Seguidores',
      minValue: 0
    },
    vAxis: {
      title: ''
    }
  };

  var chart = new google.visualization.BarChart(document.getElementById('top_twitter_profile'));

  chart.draw(data, options);
}

function drawHashtagPt() {
  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Hashtag');
  data.addColumn('number', 'Total');
  data.addRows(DATA_CHARTS.hashtags);
  var table = new google.visualization.Table(document.getElementById('hashtag_pt'));

  table.draw(data, { showRowNumber: true, width: '50%', height: '30%' });
}

function drawTweetByHour() {
  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Hora do dia');
  data.addColumn('string', 'Data');
  data.addColumn('number', 'Total');
  data.addRows(DATA_CHARTS.tweetByHour);
  var table = new google.visualization.Table(document.getElementById('tweet_by_hour'));

  table.draw(data, { showRowNumber: true, width: '50%', height: '80%' });
}

google.charts.load('current', { packages: ['corechart', 'bar', 'table'] });
